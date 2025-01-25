package com.bankitu.user_service.service;

import com.bankitu.user_service.dto.CreateTeller;
import com.bankitu.user_service.entity.role.Role;
import com.bankitu.user_service.entity.role.RoleType;
import com.bankitu.user_service.entity.user.DataUserView;
import com.bankitu.user_service.entity.user.User;
import com.bankitu.user_service.repository.DataUserViewRepository;
import com.bankitu.user_service.repository.RoleRepository;
import com.bankitu.user_service.repository.UserRepository;
import com.bankitu.user_service.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceAdmin {
    private final DataUserViewRepository dataUserViewRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ValidationUtil validationUtil;

    public List<DataUserView> getDataByDateRange(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();

        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        return this.dataUserViewRepository.findByCreatedAtBetween(startDateTime, endDateTime);
    }

    public List<DataUserView> getDataByToday() {
        LocalDate today = LocalDate.now();
        return getDataByDateRange(today, today.plusDays(1));
    }

    public List<DataUserView> getDataByLast7Days() {
        LocalDate today = LocalDate.now();
        LocalDate startOfPeriod = today.minusDays(7);
        return this.getDataByDateRange(startOfPeriod, today.minusDays(1));
    }

    public List<DataUserView> getDataByLastMonth() {
        LocalDate today = LocalDate.now();
        LocalDate startOfPeriod = today.minusDays(30);
        return getDataByDateRange(startOfPeriod, today.minusDays(1));
    }

    public User createTeller(CreateTeller payload) {
        validationUtil.validate(payload);
        Optional<User> existingUser = this.userRepository.findUserByEmail(payload.getEmail());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exist");
        }
        Role role = this.roleRepository.findUserByRoleType(RoleType.TELLER);
        User user = new User();
        user.setPin(payload.getPin());
        user.setFullname(payload.getFullname());
        user.setEmail(payload.getEmail());
        user.setRole(role);

        return this.userRepository.save(user);
    }
}
