insert into WARD (ID, NAME, BUILDING) values
    (1, 'cardiology', 'B');


insert into PATIENT (ID, FIRST_NAME, LAST_NAME, PATIENT_NUM, PESEL) values
                                                    (1,'Barbara', 'Wiadro','1234567', '12345678901'),
                                                    (2,'Tomasz', 'Kozak', '0987654', '23456789012');

insert into STAFF_MEMBER (hours_monthly, wage, id, ward_id, first_name, last_name) values
    ( 160, 150, 2, 1, 'Bogumil', 'Braz'),
    (184, 300, 1, 1, 'Karolina', 'Leszko');

insert into NURSE (BONUS, ID) values ( 1000, 2);

insert into DOCTOR (ID, LICENSE_NUM) VALUES ( 1, '1234567');

insert into DOCTOR_SKILL (DOCTOR_ID, SKILLS) VALUES ( 1, 'cardiology' );
insert into DOCTOR_SKILL (DOCTOR_ID, SKILLS) VALUES ( 1, 'emergency' );

insert into VISIT (DOCTOR_ID, ID, PATIENT_ID, DIAGNOSIS, DESCRIPTION) VALUES
    ( 1, 1, 1, 'Acute viral upper respiratory infection', 'Patient presents with nasal congestion, sore throat, and cough for the past 3 days.' );

insert into IDENTIFICATION_DOCUMENT (EXPIRY_DATE, ID, OWNER_ID, SERIAL_NUM, TYPE) VALUES
    ('2026-12-15', 1, 1,'MDP123456', 'license');
