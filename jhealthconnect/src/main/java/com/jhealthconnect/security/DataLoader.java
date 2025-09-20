package com.jhealthconnect.config;

import com.jhealthconnect.entity.*;
import com.jhealthconnect.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private Repository userRepository;
    
    @Autowired
    private Repository doctorRepository;
    
    @Autowired
    private Repository insuranceRepository;

    @Autowired
    private Repository appointmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        // Only load data if database is empty
        if (userRepository.count() == 0) {
            loadSampleData();
        }
    }

    private void loadSampleData() {
        
        // Create Admin User
        User admin = new User();
        admin.setName("System Admin");
        admin.setEmail("admin@jhealthconnect.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setUserType(UserType.ADMIN);
        admin.setPhoneNumber("+91-9999999999");
        admin.setIsActive(true);
        userRepository.save(admin);

        // Create Sample Users
        User user1 = new User();
        user1.setName("John Doe");
        user1.setEmail("john@example.com");
        user1.setPassword(passwordEncoder.encode("user123"));
        user1.setUserType(UserType.USER);
        user1.setPhoneNumber("+91-9876543210");
        user1.setIsActive(true);
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("Jane Smith");
        user2.setEmail("jane@example.com");
        user2.setPassword(passwordEncoder.encode("user123"));
        user2.setUserType(UserType.USER);
        user2.setPhoneNumber("+91-9876543211");
        user2.setIsActive(true);
        userRepository.save(user2);

        // Create Sample Doctors
        Doctor[] doctors = {
            createDoctor("Dr. Sarah Johnson", "Cardiologist", "sarah.johnson@hospital.com", 
                        "Experienced cardiologist with 15+ years in treating heart conditions.", 
                        "Mumbai", 15, 800.0, 4.8, 124, 5000, "doctor1.jpg"),
            
            createDoctor("Dr. Michael Brown", "Dermatologist", "michael.brown@hospital.com",
                        "Skin specialist with expertise in cosmetic and medical dermatology.",
                        "Mumbai", 12, 600.0, 4.6, 98, 3200, "doctor2.jpg"),
            
            createDoctor("Dr. Emily Davis", "Pediatrician", "emily.davis@hospital.com",
                        "Child healthcare specialist with gentle approach to patient care.",
                        "Mumbai", 10, 500.0, 4.9, 156, 4100, "doctor3.jpg"),
            
            createDoctor("Dr. Robert Wilson", "Orthopedic", "robert.wilson@hospital.com",
                        "Joint and bone specialist with expertise in sports medicine.",
                        "Mumbai", 18, 900.0, 4.7, 89, 2800, "doctor4.jpg"),
            
            createDoctor("Dr. Lisa Anderson", "Gynecologist", "lisa.anderson@hospital.com",
                        "Women's health specialist providing comprehensive care.",
                        "Pune", 14, 700.0, 4.5, 112, 3600, "doctor5.jpg"),
            
            createDoctor("Dr. David Miller", "Neurologist", "david.miller@hospital.com",
                        "Brain and nervous system specialist with advanced training.",
                        "Delhi", 16, 1000.0, 4.8, 76, 2100, "doctor6.jpg"),
            
            createDoctor("Dr. Jennifer Taylor", "Psychiatrist", "jennifer.taylor@hospital.com",
                        "Mental health specialist providing therapy and medication management.",
                        "Bangalore", 11, 750.0, 4.6, 145, 1900, "doctor7.jpg"),
            
            createDoctor("Dr. James Moore", "ENT Specialist", "james.moore@hospital.com",
                        "Ear, nose, and throat specialist with surgical expertise.",
                        "Chennai", 13, 650.0, 4.4, 87, 2500, "doctor8.jpg"),
            
            createDoctor("Dr. Amanda White", "Endocrinologist", "amanda.white@hospital.com",
                        "Hormone and diabetes specialist providing comprehensive care.",
                        "Hyderabad", 9, 550.0, 4.7, 92, 1800, "doctor9.jpg"),
            
            createDoctor("Dr. Christopher Lee", "Urologist", "christopher.lee@hospital.com",
                        "Urinary system specialist with minimally invasive techniques.",
                        "Mumbai", 17, 850.0, 4.5, 68, 1600, "doctor10.jpg")
        };

        for (Doctor doctor : doctors) {
            doctorRepository.save(doctor);
        }

        // Create Sample Insurance Companies
        InsuranceCompany[] insuranceCompanies = {
            createInsuranceCompany("Star Health Insurance", "Comprehensive health insurance with cashless treatment",
                                 "Health Insurance", 85, 2500.0, "star_health.jpg", 4.2,
                                 Arrays.asList("Cashless Treatment", "No Claim Bonus", "Maternity Coverage")),
            
            createInsuranceCompany("HDFC ERGO Health", "Trusted health insurance with wide network coverage",
                                 "Health & Life Insurance", 90, 3000.0, "hdfc_ergo.jpg", 4.4,
                                 Arrays.asList("Cashless Treatment", "Health Check-ups", "Ambulance Cover")),
            
            createInsuranceCompany("ICICI Lombard Health", "Complete family health insurance solutions",
                                 "Health Insurance", 88, 2800.0, "icici_lombard.jpg", 4.3,
                                 Arrays.asList("Family Coverage", "Pre-existing Diseases", "Wellness Program")),
            
            createInsuranceCompany("New India Assurance", "Government health insurance with affordable premiums",
                                 "Health Insurance", 80, 2200.0, "new_india.jpg", 4.0,
                                 Arrays.asList("Government Backed", "Low Premium", "Wide Network")),
            
            createInsuranceCompany("Max Bupa Health", "Premium health insurance with international coverage",
                                 "Health Insurance", 95, 4000.0, "max_bupa.jpg", 4.6,
                                 Arrays.asList("International Coverage", "No Co-pay", "Telemedicine")),
            
            createInsuranceCompany("Care Health Insurance", "Comprehensive health coverage for all ages",
                                 "Health Insurance", 87, 2600.0, "care_health.jpg", 4.1,
                                 Arrays.asList("Senior Citizen Plans", "Critical Illness", "Day Care")),
            
            createInsuranceCompany("Niva Bupa Health", "Digital-first health insurance solutions",
                                 "Health Insurance", 92, 3200.0, "niva_bupa.jpg", 4.5,
                                 Arrays.asList("Digital Claims", "Instant Approval", "24/7 Support")),
            
            createInsuranceCompany("Bajaj Allianz Health", "Flexible health insurance with add-on benefits",
                                 "Health Insurance", 89, 2900.0, "bajaj_allianz.jpg", 4.3,
                                 Arrays.asList("Flexible Plans", "Add-on Benefits", "Quick Settlement")),
            
            createInsuranceCompany("United India Insurance", "Reliable health coverage with nation-wide presence",
                                 "Health Insurance", 83, 2400.0, "united_india.jpg", 3.9,
                                 Arrays.asList("Nation-wide Network", "Affordable Premium", "Easy Claims")),
            
            createInsuranceCompany("Oriental Insurance", "Traditional health insurance with modern benefits",
                                 "Health Insurance", 86, 2700.0, "oriental.jpg", 4.2,
                                 Arrays.asList("Traditional Plans", "Modern Benefits", "Trusted Brand"))
        };

        for (InsuranceCompany insurance : insuranceCompanies) {
            insuranceRepository.save(insurance);
        }

        System.out.println("✅ Sample data loaded successfully!");
        System.out.println("🔑 Admin Login: admin@jhealthconnect.com / admin123");
        System.out.println("🔑 User Login: john@example.com / user123");
    }

    private Doctor createDoctor(String name, String specialization, String email, String about,
                               String location, int experience, double consultationFee, double rating,
                               int reviewCount, int patientsCount, String image) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setEmail(email);
        doctor.setAbout(about);
        doctor.setLocation(location);
        doctor.setExperience(experience);
        doctor.setConsultationFee(consultationFee);
        doctor.setRating(rating);
        doctor.setReviewCount(reviewCount);
        doctor.setPatientsCount(patientsCount);
        doctor.setImage(image);
        doctor.setAvailable(true);
        doctor.setVerified(true);
        doctor.setPhoneNumber("+91-" + (9000000000L + (long)(Math.random() * 999999999)));
        doctor.setSpecialties(Arrays.asList(specialization, "General Consultation"));
        return doctor;
    }

    private InsuranceCompany createInsuranceCompany(String name, String description, String type,
                                                   int coverage, double premium, String logo,
                                                   double rating, List<String> features) {
        InsuranceCompany insurance = new InsuranceCompany();
        insurance.setName(name);
        insurance.setDescription(description);
        insurance.setType(type);
        insurance.setCoverage(coverage);
        insurance.setPremium(premium);
        insurance.setLogo(logo);
        insurance.setRating(rating);
        insurance.setFeatures(features);
        insurance.setActive(true);
        return insurance;
    }
}