package com.jhealthconnect.repository;

import com.jhealthconnect.entity.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceCompanyRepository extends JpaRepository<InsuranceCompany, Long> {

    @Query("SELECT i FROM InsuranceCompany i WHERE i.active = true ORDER BY i.rating DESC, i.coverage DESC")
    List<InsuranceCompany> findTopByOrderByRatingDescCoverageDesc(@Param("limit") int limit);

    List<InsuranceCompany> findByActiveTrue();

    List<InsuranceCompany> findByNameContainingIgnoreCase(String name);

    List<InsuranceCompany> findByType(String type);
}