package com.algoExpert.demo.UserAccount.AccountInfo.Controller;

import com.algoExpert.demo.Entity.HttpResponse;
import com.algoExpert.demo.Records.ChangePasswordRequest;
import com.algoExpert.demo.UserAccount.AccountInfo.Service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/change")
public class ChangePasswordController {
    @Autowired
    private ChangePasswordService changePasswordService;

    @PostMapping("/password")
    private HttpResponse changePassword(@RequestBody ChangePasswordRequest passwordRequest){
       return changePasswordService.createNewPassword(passwordRequest);
    }
}
