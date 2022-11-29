package com.redbox.pkdm.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.redbox.pkdm.entities.AccountUser;
import com.redbox.pkdm.entities.Book;
import com.redbox.pkdm.entities.DeliveryInfo;
import com.redbox.pkdm.entities.DeliveryTownship;
import com.redbox.pkdm.entities.DiscountCoupon;
import com.redbox.pkdm.entities.PurchasedInvoice;
import com.redbox.pkdm.entities.PurchasedTransition;
import com.redbox.pkdm.entities.SecurityInfo;
import com.redbox.pkdm.entities.ShelfRelated;
import com.redbox.pkdm.entities.ShelfRelatedMapper;
import com.redbox.pkdm.entities.Wallet;
import com.redbox.pkdm.models.WalletModel;
import com.redbox.pkdm.services.AccountUserService;
import com.redbox.pkdm.services.BookService;
import com.redbox.pkdm.services.DeliveryInfoService;
import com.redbox.pkdm.services.DeliveryTownshipService;
import com.redbox.pkdm.services.DiscountCouponService;
import com.redbox.pkdm.services.PurchasedInvoiceService;
import com.redbox.pkdm.services.PurchasedTransitionService;
import com.redbox.pkdm.services.ShelfRelatedMapperService;
import com.redbox.pkdm.services.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
public class WalletAPI {
		
	@Autowired
	private WalletService walletTopUpService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AccountUserService accountUserService;
	
	@Autowired
	private PurchasedTransitionService purchasedTransitionService;
	
	@Autowired
	private ShelfRelatedMapperService shelfRelatedMapperService;
	
	@Autowired
	private DiscountCouponService discountCouponService;
	
	@Autowired
	private PurchasedInvoiceService purchasedInvoiceService;
	
	@Autowired
	private DeliveryTownshipService deliveryTownshipService;
	
	@Autowired
	private DeliveryInfoService deliveryInfoService;
	
	@GetMapping("/walletlist")
	public List<Wallet> getWalletList(@PathVariable String id){
		return walletTopUpService.getWalletList(id);
	}
	
	@GetMapping("/purchasesingleelectronicbook/{userID}/{BookID}/{couponID}")
	public boolean purchaseSingleElectronicBook (@PathVariable String userID, @PathVariable String BookID, @PathVariable String couponID) {
		try {
			DiscountCoupon coupon = new DiscountCoupon();
			PurchasedTransition transition = new PurchasedTransition();
			Wallet wallet = new Wallet();
			PurchasedInvoice purchasedInvoice = new PurchasedInvoice();
			
			double price = 0;
			String description = "";
			Book book = bookService.findByID(BookID);
			if (couponID.equals("No")) {
				if (book.getE_discount() != 0) {
					price = book.getE_price() - (book.getE_price() * book.getE_discount()) / 100;
					transition.setDiscountPercent(book.getE_discount());
					description = "လူကြီးမင်း ဝယ်ယူသော "+ book.getName() +"  စာအုပ်၏စျေးနှုန်းမှာ " + 
							String.valueOf(book.getE_price()) + " MMK ဖြစ်ပါသည်။ လျော့စျေး " + String.valueOf(book.getE_discount()) +" % ရရှိသောကြောင့် စုစုပေါင်းကျသင့်ငွေမှာ " + String.valueOf(price) + " ဖြစ်ပါသည်။ ကျသင့်ငွေကို လူကြီးမင်းပိုက်ဆံအိပ်ထဲမှ နှတ်ယူလိုက်ပါသည်။";
				} else {
					price = book.getE_price();
					description = "လူကြီးမင်း ဝယ်ယူသော "+ book.getName() +"  စာအုပ်၏စျေးနှုန်းမှာ " + 
							String.valueOf(book.getE_price()) + " MMK ဖြစ်ပါသည်။ ကျသင့်ငွေကို လူကြီးမင်းပိုက်ဆံအိပ်ထဲမှ နှတ်ယူလိုက်ပါသည်။";
				}
			} else {
				coupon = discountCouponService.findByID(Long.parseLong(couponID));
				transition.setDiscountCoupon(coupon);
				transition.setDiscountPercent(coupon.getRate());
				price = book.getE_price() - (book.getE_price() * coupon.getRate()) / 100;
				description = "လူကြီးမင်း ဝယ်ယူသော "+ book.getName() +"  စာအုပ်၏စျေးနှုန်းမှာ " + 
						String.valueOf(book.getE_price()) + " MMK ဖြစ်ပါသည်။ လျော့စျေး " + String.valueOf(coupon.getRate()) +" % ရရှိသောကြောင့် စုစုပေါင်းကျသင့်ငွေမှာ " + String.valueOf(price) + " ဖြစ်ပါသည်။ ကျသင့်ငွေကို လူကြီးမင်းပိုက်ဆံအိပ်ထဲမှ နှတ်ယူလိုက်ပါသည်။";
			}
			
			String invoice_no = "INV-" + LocalDateTime.now() + new Random().nextInt(99);
			AccountUser accountUser = accountUserService.findByID(userID);
			
			wallet.setWalletType("Purchased");
			wallet.setDescription(description);
			wallet.setAmount(price);
			wallet.setStatus("Approve");
			wallet.setAccountUser(accountUser);
			wallet.setSecurityInfo(new SecurityInfo());
			wallet.getSecurityInfo().setCreateDate(LocalDate.now());
			wallet.getSecurityInfo().setCreateTime(LocalTime.now().toString());
			wallet.getSecurityInfo().setCreateUser(accountUser.getId());
			walletTopUpService.save(wallet);
			
			transition.setAccountUser(accountUser);
			transition.setBook(book);
			transition.setBookType("ELECTRONIC");
			transition.setPaymentType("Balance");
			transition.setInvoice_no(invoice_no);
			transition.setSecurityInfo(new SecurityInfo());
			purchasedTransitionService.save(transition);
			
			purchasedInvoice.setId(invoice_no);
			purchasedInvoice.setAccountUser(accountUser);
			purchasedInvoice.setErase(false);
			purchasedInvoice.setTotal(price);
			purchasedInvoice.setSecurityInfo(new SecurityInfo());
			purchasedInvoiceService.save(purchasedInvoice);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@GetMapping("/purchasemultipleelectronicbook/{userID}/{bookID}")
	public boolean purchaseMultipleElectronicBook (@PathVariable String userID, @PathVariable String bookID) {
		try {
			AccountUser accountUser = accountUserService.findByID(userID);
			ShelfRelated shelfRelated = new ShelfRelated();
			List<ShelfRelated> shelfRelateds = shelfRelatedMapperService.findByBook(bookID);
			if (shelfRelateds != null && shelfRelateds.size() > 0) {
				shelfRelated = shelfRelateds.get(0);
			}
			
			String invoice_no = "INV-" + LocalDateTime.now() + new Random().nextInt(99);
			double total = 0;
			List<PurchasedTransition> purchasedTransitions = purchasedTransitionService.findByUser(userID);
			List<ShelfRelatedMapper> shelfRelatedMappers = shelfRelatedMapperService.findByErase(false);
			outer : for (ShelfRelatedMapper shelfRelatedMapper : shelfRelatedMappers) {
				for (PurchasedTransition purchasedTransition : purchasedTransitions) {
					if (purchasedTransition.getBook().getId().equals(shelfRelatedMapper.getBook().getId()))
						continue outer;
				}
				if (shelfRelatedMapper.getShelfRelated().getId() == shelfRelated.getId() && shelfRelatedMapper.getBook().isElectronicBook() && !shelfRelatedMapper.getBook().isErase()) {
					Book book = shelfRelatedMapper.getBook();
					double discount = shelfRelated.getPrice();
					double price = (book.getE_price() * discount) / 100; 
					total += price;
					String description = "လူကြီးမင်း ဝယ်ယူသော "+ book.getName() +"  စာအုပ်၏စျေးနှုန်းမှာ " + 
							String.valueOf(book.getE_price()) + " MMK ဖြစ်ပါသည်။ လျော့စျေး ရရှိသောကြောင့် " + 
							String.valueOf(book.getE_discount()) + " စုစုပေါင်းကျသင့်ငွေမှာ " + String.valueOf(price) + " ဖြစ်ပါသည်။ ကျသင့်ငွေကို လူကြီးမင်းပိုက်ဆံအိပ်ထဲမှ နှတ်ယူလိုက်ပါသည်။";
					Wallet wallet = new Wallet();
					wallet.setWalletType("Purchased");
					wallet.setDescription(description);
					wallet.setAmount(total);
					wallet.setStatus("Approve");
					wallet.setAccountUser(accountUser);
					wallet.setSecurityInfo(new SecurityInfo());
					wallet.getSecurityInfo().setCreateDate(LocalDate.now());
					wallet.getSecurityInfo().setCreateTime(LocalTime.now().toString());
					wallet.getSecurityInfo().setCreateUser(accountUser.getId());
					walletTopUpService.save(wallet);
					PurchasedTransition transition = new PurchasedTransition();
					transition.setAccountUser(accountUser);
					transition.setBook(book);
					transition.setBookType("ELECTRONIC");
					transition.setPaymentType("Balance");
					transition.setInvoice_no(invoice_no);
					transition.setDiscountPercent(shelfRelated.getPrice());
					transition.setSecurityInfo(new SecurityInfo());
					purchasedTransitionService.save(transition);
				}
			}
			PurchasedInvoice purchasedInvoice = new PurchasedInvoice();
			purchasedInvoice.setId(invoice_no);
			purchasedInvoice.setAccountUser(accountUser);
			purchasedInvoice.setErase(false);
			purchasedInvoice.setTotal(total);
			purchasedInvoice.setSecurityInfo(new SecurityInfo());
			purchasedInvoiceService.save(purchasedInvoice);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@GetMapping("/purchasesinglephysicalbook/{userID}/{BookID}/{townshipID}/{address}/{phone}")
	public boolean purchaseSinglePhysicalBook (@PathVariable String userID, @PathVariable String BookID, @PathVariable String townshipID, @PathVariable String address, @PathVariable String phone) {
		try {
			AccountUser user = accountUserService.findByID(userID);
			Book book = bookService.findByID(BookID);
			DeliveryTownship township = deliveryTownshipService.findByID(Long.parseLong(townshipID));
			String invoice_no = "INV-" + LocalDateTime.now() + new Random().nextInt(99);
			double total = book.getP_price() + township.getFee();
			String description = "လူကြီးမင်း ဝယ်ယူသော "+ book.getName() +"  စာအုပ်၏စျေးနှုန်းမှာ " + 
					String.valueOf(book.getP_price()) + " MMK ဖြစ်ပါသည်။ " + township.getName() + "အတွက် ပိုဆောင်ခမှာ " + township.getFee() + " ဖြစ်ပါသည။ စုစုပေါင်း ကျသင့်ငွေ " + String.valueOf(total) + "MMK ကို လူကြီးမင်းပိုက်ဆံအိပ်ထဲမှ နှတ်ယူလိုက်ပါသည်။";
			
			Wallet wallet = new Wallet();
			wallet.setWalletType("Purchased");
			wallet.setDescription(description);
			wallet.setAmount(total);
			wallet.setStatus("Approve");
			wallet.setAccountUser(user);
			wallet.setSecurityInfo(new SecurityInfo());
			wallet.getSecurityInfo().setCreateDate(LocalDate.now());
			wallet.getSecurityInfo().setCreateTime(LocalTime.now().toString());
			wallet.getSecurityInfo().setCreateUser(user.getId());
			walletTopUpService.save(wallet);
			
			DeliveryInfo deliveryInfo = new DeliveryInfo();
			deliveryInfo.setPhone(phone);
			deliveryInfo.setDeliveryRegion(township.getDeliveryRegion());
			deliveryInfo.setDeliveryTownship(township);
			deliveryInfo.setAddressDetail(address);
			deliveryInfoService.save(deliveryInfo);
			
			PurchasedTransition transition = new PurchasedTransition();
			transition.setAccountUser(user);
			transition.setBook(book);
			transition.setBookType("Physical");
			transition.setPaymentType("Balance");
			transition.setInvoice_no(invoice_no);
			transition.setDeliveryInfo(deliveryInfo);
			transition.setTotal(total);
			transition.setSecurityInfo(new SecurityInfo());
			purchasedTransitionService.save(transition);
			
			PurchasedInvoice purchasedInvoice = new PurchasedInvoice();
			purchasedInvoice.setId(invoice_no);
			purchasedInvoice.setAccountUser(user);
			purchasedInvoice.setErase(false);
			purchasedInvoice.setTotal(total);
			purchasedInvoice.setSecurityInfo(new SecurityInfo());
			purchasedInvoiceService.save(purchasedInvoice);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@GetMapping("/topup/{userID}/{amount}/{taskIDNo}/{payment}")
	public boolean topup (@PathVariable String userID, @PathVariable String amount, @PathVariable String taskIDNo, @PathVariable String payment) {
		try {
			AccountUser accountUser = accountUserService.findByID(userID);
			Wallet wallet = new Wallet();
			wallet.setWalletType("Top Up");
			wallet.setDescription("လူကြီးမင်း ပိုက်ဆံအိတ် အတွင်းသို့ " + amount + "MMK ငွေ ဖြည့်သွင်းခြင်းကို လက်ခံရရှိပါသည်။ စစ်ဆေးမှုများပြုလုပ်နေပါသဖြင့် ခဏစောင့်ဆိုင်းပေးပါ။");
			wallet.setAmount(Double.parseDouble(amount));
			wallet.setAccountUser(accountUser);
			wallet.setApprove(false);
			wallet.setTaskIDNo(taskIDNo);
			wallet.setPayment(payment);
			wallet.setStatus("Request");
			wallet.setSecurityInfo(new SecurityInfo());
			wallet.getSecurityInfo().setCreateDate(LocalDate.now());
			wallet.getSecurityInfo().setCreateTime(LocalTime.now().toString());
			wallet.getSecurityInfo().setCreateUser(accountUser.getId());
			walletTopUpService.save(wallet);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@GetMapping("/findwalletamount/{id}")
	public double findWalletAmount (@PathVariable String id) {
		double wallet_amount = 0.0;
		List<Wallet> wallets = walletTopUpService.findByUser(id);
		for (Wallet wallet : wallets) {
			if (wallet.getStatus().equals("Approve")) {
				if (wallet.getWalletType().equals("Top Up")) {
					wallet_amount += wallet.getAmount();
				} else {
					wallet_amount -= wallet.getAmount();
				}
			}
		}
		return wallet_amount;
	}
	
	@GetMapping("/findwalletdata/{id}")
	public List<WalletModel> findWalletData (@PathVariable String id) {
		WalletModel model = new WalletModel();
		List<WalletModel> models = new ArrayList<>();
		List<Wallet> wallets = walletTopUpService.findByUser(id);
		for (Wallet w : wallets) {
			model.setId(w.getId());
			model.setDate(w.getSecurityInfo().getCreateDate().toString());
			model.setTime(w.getSecurityInfo().getCreateTime());
			model.setWalletType(w.getWalletType());
			model.setDescription(w.getDescription());
			model.setAmount(w.getAmount());
			models.add(model);
			model = new WalletModel();
		}
		return models;
	}

}
