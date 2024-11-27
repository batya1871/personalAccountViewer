package ru.batievsky.personalAccountViewer.controllers;

//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.batievsky.personalAccountViewer.dao.AccountDao;
import ru.batievsky.personalAccountViewer.models.Account;

import javax.validation.Valid;


@Controller
@RequestMapping(value ="/personal-accounts", produces = "text/plain;charset=UTF-8")
public class AccountsController {

    private AccountDao accountDao;
    @Autowired
    public AccountsController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @GetMapping(produces = "text/plain;charset=UTF-8")
    public String searchAccount(@ModelAttribute("searched_account") Account searched_account){
        return "search_account";
    }
    @PostMapping()
    public String PostAccount(@ModelAttribute("searched_account") Account searched_account){
        return "search_account";
    }
    @PostMapping(value = "/personal-account-data", produces = "text/plain;charset=UTF-8")
    public String data(@ModelAttribute("searched_account") @Valid Account searched_account, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            System.out.println("error!");
            System.out.println(bindingResult);
            return  "search_account";
        }
        searched_account = accountDao.getAccount(searched_account);
        if (searched_account == null) model.addAttribute("notFound", true);
        else model.addAttribute("notFound", false);
        model.addAttribute("account", searched_account);
        return "personal_account_data";
    }

}
