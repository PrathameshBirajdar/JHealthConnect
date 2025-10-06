JHealthConnect - Healthcare Management System
A comprehensive Spring Boot web application for managing healthcare appointments, doctors, insurance, and patient recommendations.

Features
User Features
User registration and authentication
Browse available doctors
Book appointments with doctors
View and manage appointments
Browse insurance providers
View personalized health recommendations
Admin Features
Manage all appointments
Manage doctors
Add/Remove insurance providers
Create and manage recommendations
View all registered users
Full administrative dashboard
Technology Stack
Backend: Spring Boot 3.2.0
Security: Spring Security 6
Database: H2 (development) / MySQL (production)
ORM: Spring Data JPA with Hibernate
Template Engine: Thymeleaf
Build Tool: Maven
Java Version: 17
Project Structure
jhealthconnect/
├── src/
│   ├── main/
│   │   ├── java/com/jhealthconnect/
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── CustomLoginSuccessHandler.java
│   │   │   │   ├── CustomLoginFailureHandler.java
│   │   │   │   └── DataInitializer.java
│   │   │   ├── controller/
│   │   │   │   ├── LoginController.java
│   │   │   │   ├── UserController.java
│   │   │   │   ├── AdminController.java
│   │   │   │   └── CustomErrorController.java
│   │   │   ├── entity/
│   │   │   │   ├── User.java
│   │   │   │   ├── Doctor.java
│   │   │   │   ├── Insurance.java
│   │   │   │   ├── Appointment.java
│   │   │   │   └── Recommendation.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── DoctorRepository.java
│   │   │   │   ├── InsuranceRepository.java
│   │   │   │   ├── AppointmentRepository.java
│   │   │   │   └── RecommendationRepository.java
│   │   │   ├── service/
│   │   │   │   ├── UserService.java
│   │   │   │   ├── DoctorService.java
│   │   │   │   ├── InsuranceService.java
│   │   │   │   ├── AppointmentService.java
│   │   │   │   ├── RecommendationService.java
│   │   │   │   └── CustomUserDetailsService.java
│   │   │   └── JhealthconnectApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   ├── css/
│   │       │   ├── js/
│   │       │   └── images/
│   │       └── templates/
│   │           ├── auth.html
│   │           ├── base.html
│   │           ├── admin/
│   │           ├── user/
│   │           └── error/
└── pom.xml
Getting Started
Prerequisites
Java 17 or higher
Maven 3.6+
(Optional) MySQL 8.0+ for production
Installation
Clone the repository
bash
git clone https://github.com/yourusername/jhealthconnect.git
cd jhealthconnect
Build the project
bash
mvn clean install
Run the application
bash
mvn spring-boot:run
Access the application
Open browser and go to: http://localhost:8080
H2 Console (dev only): http://localhost:8080/h2-console
Default Credentials
Admin Account:

Email: admin@jhc.com
Password: admin123
User Account:

Email: user@jhc.com
Password: user123
Configuration
Database Configuration
For Development (H2 - Default): Already configured in application.properties

For Production (MySQL):

Create a MySQL database:
sql
CREATE DATABASE jhealthconnect;
Update application.properties:
properties
# Comment out H2 settings
# Uncomment MySQL settings:
spring.datasource.url=jdbc:mysql://localhost:3306/jhealthconnect
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
Add MySQL dependency in pom.xml (already included, just uncomment)
API Endpoints
Public Endpoints
GET / - Login/Register page
POST /register - User registration
POST /login - User login
User Endpoints (Requires USER role)
GET /user/layout - User dashboard
GET /user/doctors - View all doctors
GET /user/doctor/{id} - View doctor details
POST /user/doctors/{id}/book - Book appointment
GET /user/appointments - View user appointments
GET /user/appointment/cancel/{id} - Cancel appointment
GET /user/insurance - View insurance options
GET /user/recommendations - View recommendations
Admin Endpoints (Requires ADMIN role)
GET /admin/layout - Admin dashboard
GET /admin/doctors - Manage doctors
GET /admin/doctor/delete/{id} - Delete doctor
GET /admin/appointments - Manage all appointments
GET /admin/appointment/delete/{id} - Delete appointment
GET /admin/insurance - Manage insurance
POST /admin/insurance/save - Add insurance
GET /admin/insurance/delete/{id} - Delete insurance
GET /admin/recommendations - Manage recommendations
POST /admin/recommendations/save - Add recommendation
GET /admin/users - Manage users
Sample Data
The application comes with pre-loaded sample data:

4 Doctors (various specializations)
3 Insurance providers
2 Recommendations
1 Admin user
1 Regular user
Security Features
Password encryption using BCrypt
Role-based access control (USER, ADMIN)
CSRF protection
Session management
Custom login success/failure handlers
Secure error handling
Error Handling
Custom error pages for:

401 Unauthorized
404 Not Found
500 Internal Server Error
General errors
Contributing
Fork the repository
Create your feature branch (git checkout -b feature/AmazingFeature)
Commit your changes (git commit -m 'Add some AmazingFeature')
Push to the branch (git push origin feature/AmazingFeature)
Open a Pull Request
License
This project is licensed under the MIT License.

Contact
Project Link: https://github.com/yourusername/jhealthconnect

Acknowledgments
Spring Boot Documentation
Thymeleaf Documentation
Bootstrap for CSS framework ideas
