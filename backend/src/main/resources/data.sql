INSERT INTO doctor (name, specialization, location, photo_url) VALUES
('Dr. Ananya Rao','Dermatologist','Mumbai','/assets/doctor1.jpg'),
('Dr. Rajesh Mehta','Cardiologist','Pune','/assets/doctor2.jpg');

INSERT INTO patient (name, age, gender, contact) VALUES
('Amit Sharma', 28, 'Male', '9876543210'),
('Sara Khan', 22, 'Female', '9876543211');

INSERT INTO appointment (doctor_id, patient_name, age, gender, contact, date) VALUES
(1, 'Amit Sharma', 28, 'Male', '9876543210', '2025-09-20 10:00:00'),
(2, 'Sara Khan', 22, 'Female', '9876543211', '2025-09-20 11:30:00');

INSERT INTO insurance_claim (patient_name, policy_number, hospital_name, claim_status) VALUES
('Amit Sharma', 'POL123', 'City Hospital', 'PENDING');
