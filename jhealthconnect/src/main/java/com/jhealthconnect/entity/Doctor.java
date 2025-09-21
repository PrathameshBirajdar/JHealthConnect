package com.jhealthconnect.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;  // Add this
    private String specialization;
    private String location;
    private String about;  // Add this
    private String phoneNumber;  // Add this
    private String image;  // Add this
    private double rating;
    private int reviewCount;
    private int experience;  // Add this
    private int patientsCount;  // Add this
    private boolean available = true;
    private boolean verified = false;
    private double consultationFee;
    
    @ElementCollection
    private List<String> specialties;  // Add this
    
    // Constructors
    public Doctor() {}
    
    public Doctor(String name, String specialization, String location, double consultationFee) {
        this.name = name;
        this.specialization = specialization;
        this.location = location;
        this.consultationFee = consultationFee;
    }
    
    // All the existing getters/setters PLUS these new ones:
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
    
    public int getPatientsCount() { return patientsCount; }
    public void setPatientsCount(int patientsCount) { this.patientsCount = patientsCount; }
    
    public List<String> getSpecialties() { return specialties; }
    public void setSpecialties(List<String> specialties) { this.specialties = specialties; }
    
    // Keep all your existing getters/setters for other fields too
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    
    public int getReviewCount() { return reviewCount; }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }
    
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    
    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }
    
    public double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; }
}