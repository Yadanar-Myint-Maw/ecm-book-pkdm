package com.redbox.pkdm.controllers.admin;

import com.redbox.pkdm.entities.AccountAdmin;
import com.redbox.pkdm.models.BookReportModel;
import com.redbox.pkdm.models.UserReportModel;
import com.redbox.pkdm.services.AccountAdminService;
import com.redbox.pkdm.services.PurchasedTransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("admin/report/user")
public class AdminUserReportController {

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
            model.addAttribute("userReports", purchasedTransitionService.findCountByUser());
        }else{
            model.addAttribute("userReports", purchasedTransitionService.findCountByUserAndDate(LocalDate.parse(dateFrom), LocalDate.parse(dateTo)));
        }
        return "adminuserreport";
    }

}
