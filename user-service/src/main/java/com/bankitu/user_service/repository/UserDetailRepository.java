package com.bankitu.user_service.repository;

import com.bankitu.user_service.entity.user.User;
import com.bankitu.user_service.entity.user.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    UserDetail findByUserId(String userId);
    Optional<UserDetail> findByUser(User user);
}
