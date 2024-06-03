package com.algoExpert.demo.UserAccount.AccountInfo.Service;

import com.algoExpert.demo.Entity.HttpResponse;
import com.algoExpert.demo.Records.ChangePasswordRequest;
import org.springframework.stereotype.Service;

@Service
public interface ChangePasswordService {
    HttpResponse createNewPassword(ChangePasswordRequest passwordRequest);
}
