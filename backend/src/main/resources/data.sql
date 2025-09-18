INSERT INTO doctor (name, specialization, location, photo_url)
VALUES 
('Dr. Ananya Rao','Dermatologist','Mumbai','/assets/doctor1.jpg'),
('Dr. Rajesh Mehta','Cardiologist','Pune','/assets/doctor2.jpg');



INSERT INTO patient (name, age, gender, contact) VALUES
 ('Amit Sharma', 28, 'Male', '9876543210'),
 ('Sara Khan', 22, 'Female', '9876543211');

INSERT INTO appointment (appointment_date, doctor_id, patient_id) VALUES
 ('2025-09-20 10:00:00', 1, 1),
 ('2025-09-20 11:30:00', 2, 2);

INSERT INTO insurance_claim (user_name, policy_number, amount, reason, status) VALUES
 ('Amit Sharma', 'POL123', 1500.0, 'OPD refund', 'PENDING');
