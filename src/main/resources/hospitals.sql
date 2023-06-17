BEGIN TRANSACTION;

INSERT INTO location (id, municipality_id, district_id, street_address)
VALUES
    (1, 330, 28, 'Kanti Path'),
    (2, 330, 28, 'Kanti Path'),
    (3, 343, 30, 'Lagankhel'),
    (4, 330, 28, 'Teku'),
    (5, 330, 28, 'Minbhawan'),
    (6, 330, 28, 'Chhauni'),
    (7, 335, 28, 'Tokha'),
    (8, 330, 28, 'Thapathali'),
    (9, 330, 28, 'Maharajgunj');

INSERT INTO hospital (id, hospital_name, location_id)
VALUES
    (1, 'Bir Hospital', 1),
    (2, 'Kanti Children Hospital', 2),
    (3, 'Patan Mental Hospital', 3),
    (4, 'Sukraraj Tropical and Infectious Diseases Hospital', 4),
    (5, 'Civil Hospital', 5),
    (6, 'Shree Birendra Hospital', 6),
    (7, 'Grande International Hospital', 7),
    (8, 'Norvic Hospital', 8),
    (9, 'T.U Teaching Hospital', 9);

COMMIT;
