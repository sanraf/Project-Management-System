package com.algoExpert.demo.UserAccount.AccountInfo.Controller;

import com.algoExpert.demo.Records.PasswordRequest;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.UserAccount.AccountInfo.Entity.PasswordReset;
import com.algoExpert.demo.UserAccount.AccountInfo.Service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recover")
public class RecoverPasswordController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/password")
    private PasswordReset savePassword(@RequestBody PasswordRequest passwordRequest) throws InvalidArgument {
       return passwordResetService.createPasswordReset(passwordRequest);
    }

    @GetMapping("/password")
    private String confirmPassword(@RequestParam("token") String token) throws InvalidArgument{
        return passwordResetService.confirmPassword(token);
    }
}
