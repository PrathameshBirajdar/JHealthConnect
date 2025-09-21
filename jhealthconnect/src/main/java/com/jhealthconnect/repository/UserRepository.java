package com.jhealthconnect.repository;

import com.jhealthconnect.entity.User;
import com.jhealthconnect.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndUserType(String email, UserType userType);

    List<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);

    List<User> findByUserType(UserType userType);

    @Query("SELECT u FROM User u ORDER BY u.createdAt DESC")
    List<User> findTopByOrderByCreatedAtDesc(@Param("limit") int limit);

    long countByIsActiveTrue();

    @Query("SELECT COUNT(u) FROM User u WHERE YEAR(u.createdAt) = YEAR(CURRENT_DATE) AND MONTH(u.createdAt) = MONTH(CURRENT_DATE)")
    long countNewUsersThisMonth();

    @Query("SELECT u.userType, COUNT(u) FROM User u GROUP BY u.userType")
    Map<String, Long> getUserCountByType();
}