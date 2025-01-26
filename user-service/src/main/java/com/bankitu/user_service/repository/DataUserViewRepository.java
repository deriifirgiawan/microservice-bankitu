package com.bankitu.user_service.repository;

import com.bankitu.user_service.entity.user.DataUserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DataUserViewRepository extends JpaRepository<DataUserView, String> {
    @Query("SELECT d FROM DataUserView d WHERE d.createdAt >= :startDate AND d.createdAt < :endDate")
    List<DataUserView> findByCreatedAtBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
