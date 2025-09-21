package com.jhealthconnect.repository;

import com.jhealthconnect.entity.Appointment;
import com.jhealthconnect.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByUserIdAndStatus(Long userId, AppointmentStatus status);

    List<Appointment> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<Appointment> findByStatus(AppointmentStatus status);

    List<Appointment> findByDate(String date);

    List<Appointment> findByStatusAndDate(AppointmentStatus status, String date);

    @Query("SELECT a FROM Appointment a ORDER BY a.createdAt DESC")
    List<Appointment> findTopByOrderByCreatedAtDesc(@Param("limit") int limit);

    long countByStatus(AppointmentStatus status);

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.date = CURRENT_DATE")
    long countTodayAppointments();

    @Query("SELECT SUM(d.consultationFee) FROM Appointment a JOIN a.doctor d WHERE YEAR(a.createdAt) = YEAR(CURRENT_DATE) AND MONTH(a.createdAt) = MONTH(CURRENT_DATE) AND a.status = 'COMPLETED'")
    Double getMonthlyRevenue();

    @Query("SELECT new map(MONTH(a.createdAt) as month, COUNT(a) as count) FROM Appointment a WHERE YEAR(a.createdAt) = YEAR(CURRENT_DATE) GROUP BY MONTH(a.createdAt)")
    Map<String, Object> getMonthlyStats();

    @Query("SELECT new map(MONTH(a.createdAt) as month, SUM(d.consultationFee) as revenue) FROM Appointment a JOIN a.doctor d WHERE YEAR(a.createdAt) = YEAR(CURRENT_DATE) AND a.status = 'COMPLETED' GROUP BY MONTH(a.createdAt)")
    Map<String, Object> getRevenueAnalytics();

    @Query("SELECT a.status, COUNT(a) FROM Appointment a GROUP BY a.status")
    Map<String, Long> getStatusDistribution();
}