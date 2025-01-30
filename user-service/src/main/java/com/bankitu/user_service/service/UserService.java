package com.bankitu.user_service.service;

import com.bankitu.user_service.dto.CreateUser;
import com.bankitu.user_service.dto.ResponseCreateUser;
import com.bankitu.user_service.entity.role.Role;
import com.bankitu.user_service.entity.role.RoleType;
import com.bankitu.user_service.entity.user.User;
import com.bankitu.user_service.entity.user.UserDetail;
import com.bankitu.user_service.feign.AccountServiceClient;
import com.bankitu.user_service.repository.RoleRepository;
import com.bankitu.user_service.repository.UserDetailRepository;
import com.bankitu.user_service.repository.UserRepository;
import com.bankitu.user_service.utils.GenerateResponseCreateUser;
import com.bankitu.user_service.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final RoleRepository roleRepository;
    private final GenerateResponseCreateUser generateResponseCreateUser;
    private final ValidationUtil validationUtil;
    private final AccountServiceClient accountServiceClient;

    public ResponseCreateUser findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(generateResponseCreateUser::responseCreateUser)
                .orElse(new ResponseCreateUser());
    }

    public User createUser(CreateUser payload) {
        validationUtil.validate(payload);

        Role role = getRole();
        User user = createUserEntity(payload, role);
        User userCreated = userRepository.save(user);

        createUserDetail(payload, userCreated);

        createAccountInAccountService(payload);

        return userCreated;
    }

    public User updateUser(CreateUser payload, String id) {
        validationUtil.validate(payload);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));

        Role role = getRole();
        updateUserEntity(user, payload, role);

        User userUpdated = userRepository.save(user);

        updateUserDetail(payload, userUpdated);

        return userUpdated;
    }

    public User deleteUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));

        user.setDeleted_at(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(user);
    }

    private Role getRole() {
        return roleRepository.findUserByRoleType(RoleType.USER);
    }

    private User createUserEntity(CreateUser payload, Role role) {
        User user = new User();
        user.setPin(payload.getPin());
        user.setFullname(payload.getFullname());
        user.setEmail(payload.getEmail());
        user.setRole(role);
        return user;
    }

    private void createUserDetail(CreateUser payload, User user) {
        UserDetail userDetail = new UserDetail();
        userDetail.setUser(user);
        userDetail.setAddress(payload.getAddress());
        userDetail.setPhoneNumber(payload.getPhone_number());
        userDetail.setNik(payload.getNik());
        userDetail.setMotherName(payload.getMother_name());
        userDetailRepository.save(userDetail);
    }

    private void updateUserEntity(User user, CreateUser payload, Role role) {
        user.setPin(payload.getPin());
        user.setFullname(payload.getFullname());
        user.setEmail(payload.getEmail());
        user.setRole(role);
    }

    private void updateUserDetail(CreateUser payload, User user) {
        UserDetail userDetail = userDetailRepository.findByUser(user)
                .orElseGet(UserDetail::new);

        userDetail.setUser(user);
        userDetail.setAddress(payload.getAddress());
        userDetail.setPhoneNumber(payload.getPhone_number());
        userDetail.setNik(payload.getNik());
        userDetail.setMotherName(payload.getMother_name());
        userDetailRepository.save(userDetail);
    }

    private void createAccountInAccountService(CreateUser payload) {
        try {
            accountServiceClient.createAccount(payload.getCreate_account());
        } catch (Exception e) {
            log.error("Error creating account in user-service: ", e);
        }
    }
}