package com.jhealthconnect.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "insurance_claims")
public class InsuranceClaim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "insurance_company_id")
    private InsuranceCompany insuranceCompany;
    
    private double amount;
    
    @Enumerated(EnumType.STRING)
    private ClaimStatus status = ClaimStatus.PENDING;
    
    private LocalDateTime submittedDate;
    
    // Constructors
    public InsuranceClaim() {
        this.submittedDate = LocalDateTime.now();
    }
    
    public InsuranceClaim(User user, InsuranceCompany insuranceCompany, double amount) {
        this.user = user;
        this.insuranceCompany = insuranceCompany;
        this.amount = amount;
        this.submittedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public InsuranceCompany getInsuranceCompany() { return insuranceCompany; }
    public void setInsuranceCompany(InsuranceCompany insuranceCompany) { this.insuranceCompany = insuranceCompany; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public ClaimStatus getStatus() { return status; }
    public void setStatus(ClaimStatus status) { this.status = status; }
    
    public LocalDateTime getSubmittedDate() { return submittedDate; }
    public void setSubmittedDate(LocalDateTime submittedDate) { this.submittedDate = submittedDate; }
}