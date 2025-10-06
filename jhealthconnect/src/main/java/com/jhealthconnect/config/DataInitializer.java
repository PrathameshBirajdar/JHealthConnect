// ========================================
// 4. DataInitializer.java - WITH IMAGES
// ========================================
package com.jhealthconnect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jhealthconnect.entity.Doctor;
import com.jhealthconnect.entity.Recommendation;
import com.jhealthconnect.entity.User;
import com.jhealthconnect.repository.DoctorRepository;
import com.jhealthconnect.repository.RecommendationRepository;
import com.jhealthconnect.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@jhc.com").isEmpty()) {
            User admin = new User();
            admin.setUsername("Admin User");
            admin.setEmail("admin@jhc.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
        }

        if (userRepository.findByEmail("user@jhc.com").isEmpty()) {
            User user = new User();
            user.setUsername("John Doe");
            user.setEmail("user@jhc.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole(User.Role.USER);
            userRepository.save(user);
        }

        if (doctorRepository.count() == 0) {
            Doctor d1 = new Doctor();
            d1.setName("Dr. Sarah Johnson");
            d1.setSpecialization("Cardiologist");
            d1.setExperience(15);
            d1.setPhone("+1-555-0101");
            d1.setStatus("ACTIVE");
            d1.setImageUrl("https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=400&h=400&fit=crop");
            d1.setDescription("Expert in heart diseases with 15 years of experience. Specializes in preventive cardiology and heart failure management.");
            doctorRepository.save(d1);

            Doctor d2 = new Doctor();
            d2.setName("Dr. Michael Chen");
            d2.setSpecialization("Neurologist");
            d2.setExperience(12);
            d2.setPhone("+1-555-0102");
            d2.setStatus("ACTIVE");
            d2.setImageUrl("https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=400&h=400&fit=crop");
            d2.setDescription("Specialist in brain and nervous system disorders. Expert in epilepsy, stroke, and neurodegenerative diseases.");
            doctorRepository.save(d2);

            Doctor d3 = new Doctor();
            d3.setName("Dr. Emily Williams");
            d3.setSpecialization("Pediatrician");
            d3.setExperience(10);
            d3.setPhone("+1-555-0103");
            d3.setStatus("ACTIVE");
            d3.setImageUrl("https://images.unsplash.com/photo-1594824476967-48c8b964273f?w=400&h=400&fit=crop");
            d3.setDescription("Dedicated pediatrician focusing on child health and development. Specializes in preventive care and vaccination.");
            doctorRepository.save(d3);

            Doctor d4 = new Doctor();
            d4.setName("Dr. Robert Martinez");
            d4.setSpecialization("Orthopedic Surgeon");
            d4.setExperience(18);
            d4.setPhone("+1-555-0104");
            d4.setStatus("ACTIVE");
            d4.setImageUrl("https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=400&h=400&fit=crop");
            d4.setDescription("Expert orthopedic surgeon specializing in joint replacement and sports injuries.");
            doctorRepository.save(d4);

            Recommendation r1 = new Recommendation();
            r1.setTitle("Top Cardiologist for Heart Health");
            r1.setDescription("Dr. Sarah Johnson is highly recommended for preventive cardiology.");
            r1.setMessage("Expert cardiologist with 15 years of experience in heart health");
            r1.setImageUrl("https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=400&h=300&fit=crop");
            r1.setDoctor(d1);
            recommendationRepository.save(r1);

            Recommendation r2 = new Recommendation();
            r2.setTitle("Best Neurologist in Town");
            r2.setDescription("Dr. Michael Chen specializes in treating complex neurological disorders.");
            r2.setMessage("Leading neurologist for brain and nervous system care");
            r2.setImageUrl("https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=400&h=300&fit=crop");
            r2.setDoctor(d2);
            recommendationRepository.save(r2);

            Recommendation r3 = new Recommendation();
            r3.setTitle("Trusted Pediatrician for Your Children");
            r3.setDescription("Dr. Emily Williams provides comprehensive pediatric care.");
            r3.setMessage("Caring pediatrician focused on child wellness and development");
            r3.setImageUrl("https://images.unsplash.com/photo-1594824476967-48c8b964273f?w=400&h=300&fit=crop");
            r3.setDoctor(d3);
            recommendationRepository.save(r3);
        }

        System.out.println("===========================================");
        System.out.println("Sample Data Initialized Successfully!");
        System.out.println("Admin: admin@jhc.com / admin123");
        System.out.println("User: user@jhc.com / user123");
        System.out.println("===========================================");
    }
}
