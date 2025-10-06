# 🏥 JHealthConnect

JHealthConnect is a **Healthcare Management System** built with **Spring Boot 3, Spring Security, JPA, Thymeleaf, and MySQL**.  
It provides user-friendly dashboards for patients and admins to manage appointments, doctors, insurance, and personal settings.

---

## 🚀 Features

### 👤 User
- Register & login securely
- Book appointments with doctors
- View appointment history
- Manage personal settings (dark mode, notifications, reminders)
- View & search doctors
- Explore insurance companies

### 👨‍💼 Admin
- Manage users
- Manage doctors & insurance companies
- Approve/reject appointments
- Access admin dashboard with system overview

---

## 🛠️ Tech Stack
- **Backend:** Spring Boot 3, Spring Security, Spring Data JPA
- **Frontend:** Thymeleaf, CSS, JS
- **Database:** MySQL
- **Build Tool:** Maven
- **Java:** 17

---

## 📂 Project Structure

src/main/java/com/jhealthconnect
│── controller # Web controllers (Auth, Appointment, Doctor, Insurance, Admin, Profile, Settings)
│── service # Business logic layer
│── repository # Spring Data JPA repositories
│── entity # JPA entities (User, Doctor, Appointment, InsuranceCompany, UserSettings)
│── security # Security config & login handlers
│── JhealthconnectApplication.java # Main Spring Boot app


---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/yourusername/jhealthconnect.git
cd jhealthconnect

2️⃣ Configure Database

Create the database in MySQL:

CREATE DATABASE jhealthconnect;


Update src/main/resources/application.properties if needed:

spring.datasource.url=jdbc:mysql://localhost:3306/jhealthconnect?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=yourpassword

3️⃣ Build & Run
mvn clean install
mvn spring-boot:run

4️⃣ Access Application

Open http://localhost:8080

🔑 Default Users
Role	Username	Password
Admin	admin	admin123
User	user1	password📜 Database Schema

users → system users

user_settings → user preferences (dark mode, notifications, reminders)

doctors → doctor details

insurance_companies → insurance company info

appointments → user-doctor appointments

Schema and sample data are auto-loaded from:

src/main/resources/schema.sql

src/main/resources/data.sql

---
🖼️ Screenshots

Login page

User dashboard

Admin dashboard

Appointment booking

Doctor list

(Screenshots go here if available)

---
🤝 Contributing

Fork this repository

Create a feature branch (feature/new-thing)

Commit changes

Push & create a Pull Request

---
📄 License

This project is licensed under the MIT License.


---

✅ Next Steps for You:
1. Create a file `README.md` in your project root.  
2. Copy-paste the above content.  
3. Replace `yourusername` in the GitHub clone URL with your actual repo name if you push it online.  
4. Add screenshots in `/docs/images/` and link them under the **Screenshots** section.  

---


