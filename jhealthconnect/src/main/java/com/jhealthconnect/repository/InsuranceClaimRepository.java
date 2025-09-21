package com.jhealthconnect.repository;

import com.jhealthconnect.entity.InsuranceClaim;
import com.jhealthconnect.entity.ClaimStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceClaimRepository extends JpaRepository<InsuranceClaim, Long> {

    List<InsuranceClaim> findByStatus(ClaimStatus status);

    List<InsuranceClaim> findByUserId(Long userId);

    List<InsuranceClaim> findByInsuranceCompanyId(Long insuranceCompanyId);

    long countByStatusIn(List<ClaimStatus> statuses);

    List<InsuranceClaim> findByUserIdOrderBySubmittedDateDesc(Long userId);
}