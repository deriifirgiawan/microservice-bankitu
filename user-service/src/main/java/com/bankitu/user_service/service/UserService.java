package com.bankitu.user_service.service;

import com.bankitu.user_service.dto.CreateUser;
import com.bankitu.user_service.dto.ResponseCreateUser;
import com.bankitu.user_service.entity.role.Role;
import com.bankitu.user_service.entity.role.RoleType;
import com.bankitu.user_service.entity.user.User;
import com.bankitu.user_service.entity.user.UserDetail;
import com.bankitu.user_service.repository.RoleRepository;
import com.bankitu.user_service.repository.UserDetailRepository;
import com.bankitu.user_service.repository.UserRepository;
import com.bankitu.user_service.utils.GenerateResponseCreateUser;
import com.bankitu.user_service.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final RoleRepository roleRepository;
    private final GenerateResponseCreateUser generateResponseCreateUser;
    private final ValidationUtil validationUtil;

    public ResponseCreateUser findUserByEmail(String email) {
        Optional<User> user = this.userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            return generateResponseCreateUser.responseCreateUser(user.get());
        }

        return new ResponseCreateUser();
    }

    public User createUser(CreateUser payload) {
        validationUtil.validate(payload);
        Role role = this.roleRepository.findUserByRoleType(RoleType.USER);
        User user = new User();
        user.setPin(payload.getPin());
        user.setFullname(payload.getFullname());
        user.setEmail(payload.getEmail());
        user.setRole(role);

        User userCreated = this.userRepository.save(user);

        UserDetail userDetail = new UserDetail();
        userDetail.setUser(userCreated);
        userDetail.setAddress(payload.getAddress());
        userDetail.setPhoneNumber(payload.getPhone_number());
        userDetail.setNik(payload.getNik());
        userDetail.setMotherName(payload.getMother_name());

        this.userDetailRepository.save(userDetail);

        // Communicate with Account-Service
        // Code in Here

        return user;
    }

    public User updateUser(CreateUser payload, String id) {
        validationUtil.validate(payload);
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        Role role = this.roleRepository.findUserByRoleType(RoleType.USER);
        user.setPin(payload.getPin());
        user.setFullname(payload.getFullname());
        user.setEmail(payload.getEmail());
        user.setRole(role);

        User userCreated = this.userRepository.save(user);

        UserDetail userDetail = new UserDetail();
        userDetail.setUser(userCreated);
        userDetail.setAddress(payload.getAddress());
        userDetail.setPhoneNumber(payload.getPhone_number());
        userDetail.setNik(payload.getNik());
        userDetail.setMotherName(payload.getMother_name());

        this.userDetailRepository.save(userDetail);

        // Communicate with Account-Service
        // Code in Here

        return user;
    }

    public User deleteUser(String id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        user.setDeleted_at(new Timestamp(System.currentTimeMillis()));
        return this.userRepository.save(user);
    }
}
