package com.p.jhealthconnect.jhealthconnect.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "insurance_policies")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required")
    @Column(nullable = false)
    private String fullName;

    @NotBlank(message = "Policy number is required")
    @Column(nullable = false, unique = true)
    private String policyNumber;

    @NotBlank(message = "Insurance provider is required")
    @Column(nullable = false)
    private String insuranceProvider;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits")
    @Column(nullable = false)
    private String contactNumber;

    @NotBlank(message = "Insurance type is required")
    @Column(nullable = false)
    private String insuranceType;

    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate expiryDate;

    @Column(columnDefinition = "TEXT")
    private String additionalDetails;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    // Enum for verification status
    public enum VerificationStatus {
        PENDING, VERIFIED, REJECTED
    }

    // Constructors
    public Insurance() {
    }

    public Insurance(String fullName, String policyNumber, String insuranceProvider,
            String contactNumber, String insuranceType, LocalDate expiryDate) {
        this.fullName = fullName;
        this.policyNumber = policyNumber;
        this.insuranceProvider = insuranceProvider;
        this.contactNumber = contactNumber;
        this.insuranceType = insuranceType;
        this.expiryDate = expiryDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", insuranceProvider='" + insuranceProvider + '\'' +
                ", verificationStatus=" + verificationStatus +
                '}';
    }
}