package com.redbox.pkdm.controllers.admin;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.PurchasedTransitionService;

@Controller
@RequestMapping("admin/report/book")
public class AdminBookReportController {

    @Autowired
    private PurchasedTransitionService purchasedTransitionService;

    @Autowired
    private AccountAdminService accountAdminService;

    @GetMapping("/initialize/{id}")
    public String initialize(@PathVariable String id, @CookieValue("login_user_id") String cookieId, Model model, String dateFrom, String dateTo) throws Exception {

        AccountAdmin loginaccount = accountAdminService.findByID(cookieId);
            if (loginaccount == null) {
                throw new Exception();
            }
        model.addAttribute("loginaccount", loginaccount);
        if(id.equals("0")) {
            model.addAttribute("bookReports", purchasedTransitionService.findCountByBook());
        }else{
            model.addAttribute("bookReports", purchasedTransitionService.findCountByBookAndDate(LocalDate.parse(dateFrom), LocalDate.parse(dateTo)));
        }
        return "adminbookreport";
    }
    
}
