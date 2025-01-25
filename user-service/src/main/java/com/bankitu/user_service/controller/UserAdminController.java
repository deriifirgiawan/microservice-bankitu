package com.bankitu.user_service.controller;

import com.bankitu.user_service.dto.CreateTeller;
import com.bankitu.user_service.entity.user.DataUserView;
import com.bankitu.user_service.entity.user.User;
import com.bankitu.user_service.enums.FilterType;
import com.bankitu.user_service.response.BaseResponse;
import com.bankitu.user_service.service.UserServiceAdmin;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class UserAdminController {
    private final UserServiceAdmin userServiceAdmin;

    public UserAdminController(UserServiceAdmin userServiceAdmin) {
        this.userServiceAdmin = userServiceAdmin;
    }

    @GetMapping("/users")
    public BaseResponse<List<DataUserView>> getAllUsers(
            @RequestParam(value = "filterType")FilterType filterType
    ) {
        BaseResponse<List<DataUserView>> baseResponse = new BaseResponse<>();
        baseResponse.setStatus(HttpStatus.OK);
        baseResponse.setMessage("Success Get All Users");

        switch (filterType) {
            case TODAY -> baseResponse.setData(this.userServiceAdmin.getDataByToday());
            case LAST_WEEK -> baseResponse.setData(this.userServiceAdmin.getDataByLast7Days());
            case LAST_MONTH -> baseResponse.setData(this.userServiceAdmin.getDataByLastMonth());
        }

        return baseResponse;
    }

    @PostMapping("/create-teller")
    public BaseResponse<User> createTeller(@RequestBody() CreateTeller payload) {
        var user = this.userServiceAdmin.createTeller(payload);
        BaseResponse<User> response = new BaseResponse<>();
        response.setStatus(HttpStatus.CREATED);
        response.setMessage("Success Create Teller");
        response.setData(user);

        return response;
    }
}
