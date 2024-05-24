package com.algoExpert.demo.Records;


public record ChangePasswordRequest(String currentPassword,String newPassword, String confirmNewPassword) {

}
