package com.algoExpert.demo.UserAccount.AccountInfo.Controller;

import com.algoExpert.demo.AppUtils.ImageConvertor;
import com.algoExpert.demo.Records.PasswordRequest;
import com.algoExpert.demo.ExceptionHandler.InvalidArgument;
import com.algoExpert.demo.UserAccount.AccountInfo.Entity.PasswordReset;
import com.algoExpert.demo.UserAccount.AccountInfo.Service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/recover")
public class RecoverPasswordController {

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private ImageConvertor imageConvertor;

    @PostMapping("/password")
    private PasswordReset savePassword(@RequestBody PasswordRequest passwordRequest) throws InvalidArgument {
       return passwordResetService.createPasswordReset(passwordRequest);
    }

    @GetMapping("/confirm")
    private ModelAndView confirmPassword(@RequestParam("token") String token) throws InvalidArgument{

        ModelAndView resetPasswordView = new ModelAndView("reset-password");
        String message = passwordResetService.confirmPassword(token);
        String successMsg ="";
        String errorMsg ="";
        String logoPath = "logo.png";
        String logo = imageConvertor.imageToBase64(logoPath);
        if (message.contains("expired")){
            errorMsg = message;
        }else if (message.contains("Successful")){
            successMsg = message;
        }else if (message.contains("Applicable")){
            errorMsg = "No Longer Applicable. Please request a new one to continue";
        }



        resetPasswordView.addObject("errorMsg",errorMsg);
        resetPasswordView.addObject("successMsg",successMsg);
        resetPasswordView.addObject("log",logo);
        return resetPasswordView;
    }
}
