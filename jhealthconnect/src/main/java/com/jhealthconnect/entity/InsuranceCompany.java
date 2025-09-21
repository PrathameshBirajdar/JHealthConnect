package com.jhealthconnect.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "insurance_companies")
public class InsuranceCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String type;
    private String description;  // Add this
    private String logo;  // Add this
    private double coverage;
    private double rating;
    private double premium;  // Add this
    private boolean active = true;
    
    @ElementCollection
    private List<String> features;  // Add this
    
    // Constructors
    public InsuranceCompany() {}
    
    public InsuranceCompany(String name, String type, double coverage) {
        this.name = name;
        this.type = type;
        this.coverage = coverage;
    }
    
    // All existing getters/setters PLUS these new ones:
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }
    
    public double getPremium() { return premium; }
    public void setPremium(double premium) { this.premium = premium; }
    
    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = features; }
    
    // Keep all existing getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public double getCoverage() { return coverage; }
    public void setCoverage(double coverage) { this.coverage = coverage; }
    
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}