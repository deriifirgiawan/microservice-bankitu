package com.bankitu.user_service.utils;

import com.bankitu.user_service.dto.ResponseCreateUser;
import com.bankitu.user_service.dto.ResponseCreateUserDetail;
import com.bankitu.user_service.entity.user.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GenerateResponseCreateUser {
    public ResponseCreateUser responseCreateUser(User user) {
        ResponseCreateUser response = new ResponseCreateUser();
        ResponseCreateUserDetail responseCreateUserDetail = new ResponseCreateUserDetail();

        response.setId(user.getId());
        response.setCreated_at(String.valueOf(user.getCreated_at()));
        response.setUpdated_at(String.valueOf(user.getUpdated_at()));
        response.setPin(user.getPin());
        response.setFullname(user.getFullname());
        response.setEmail(user.getEmail());
        responseCreateUserDetail.setNik(user.getUserDetail().getNik());
        responseCreateUserDetail.setUser_id(user.getId());
        responseCreateUserDetail.setId(user.getUserDetail().getId());
        responseCreateUserDetail.setMother_name(user.getUserDetail().getMotherName());
        responseCreateUserDetail.setAddress(user.getUserDetail().getAddress());
        response.setUser_detail(responseCreateUserDetail);


        return response;
    }
}
