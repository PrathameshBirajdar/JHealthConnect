-- USERS
INSERT INTO users (name, password, role, enabled)
VALUES
('prathamesh', '{noop}admin123', 'ADMIN', true),
('john', '{noop}password', 'USER', true);

-- USER SETTINGS
INSERT INTO user_settings (darkmode, user_id) VALUES (false, 1);
INSERT INTO user_settings (darkmode, user_id) VALUES (true, 2);

-- DOCTORS
INSERT INTO doctors (name, specialization, hospital, rating)
VALUES 
('Dr. Smith', 'Cardiologist', 'City Hospital', 4.8),
('Dr. Jane', 'Dermatologist', 'Skin Care Clinic', 4.5);

-- INSURANCE COMPANIES
INSERT INTO insurance_companies (name, address, phone, policy_count)
VALUES 
('HealthFirst', '123 Main St', '1234567890', 120),
('MediCare Plus', '456 Oak St', '0987654321', 200);

-- APPOINTMENTS
INSERT INTO appointments (date, time, doctor_id, user_id, status)
VALUES 
('2025-09-25', '10:00', 1, 2, 'CONFIRMED'),
('2025-09-27', '15:00', 2, 2, 'PENDING');
