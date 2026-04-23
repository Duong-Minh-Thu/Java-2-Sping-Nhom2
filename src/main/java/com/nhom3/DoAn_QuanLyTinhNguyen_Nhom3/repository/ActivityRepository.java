package com.nhom3.DoAn_QuanLyTinhNguyen_Nhom3.repository;

import com.nhom3.DoAn_QuanLyTinhNguyen_Nhom3.entity.Activity;
import com.nhom3.DoAn_QuanLyTinhNguyen_Nhom3.enums.ActivityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    // Dùng @EntityGraph để tránh N+1 khi load organization
    @EntityGraph(attributePaths = {"organization"})
    Page<Activity> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"organization"})
    Page<Activity> findByStatus(ActivityStatus status, Pageable pageable);

    @EntityGraph(attributePaths = {"organization"})
    @Query("SELECT a FROM Activity a WHERE a.status = :status AND a.startDate >= :from AND a.endDate <= :to")
    Page<Activity> findByStatusAndDateRange(@Param("status") ActivityStatus status,
                                            @Param("from") LocalDateTime from,
                                            @Param("to") LocalDateTime to,
                                            Pageable pageable);

    long count();
}
