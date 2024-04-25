package com.algoExpert.demo.UserAccount.AccountInfo.Controller;

import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.UserAccount.AccountInfo.Entity.AccountConfirmation;
import com.algoExpert.demo.UserAccount.AccountInfo.Repository.AccountConfirmationRepository;
import com.algoExpert.demo.UserAccount.AccountInfo.Service.AccountConfirmationService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/confirm")
public class AccountConfirmationController {

    @Autowired
    private AccountConfirmationService confirmationService;
    @Autowired
    private AccountConfirmationRepository confirmationRepository;

    @GetMapping("/account")
    private String confirmAccount(@RequestParam("token") String token) throws InvalidArgument {
        return confirmationService.activateAccount(token);
    }
    @GetMapping("/get")
    public AccountConfirmation findAccountByUserId() {
        return confirmationService.findAccountByUserId(1);
    }

    @GetMapping("/delete")
    public void delete() {
        AccountConfirmation confirmation = confirmationService.findAccountByUserId(2);
       confirmationService.deleteToken(confirmation);
    }
}
