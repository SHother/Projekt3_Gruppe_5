INSERT INTO car (brand, model, status, price, fuel_type, mileage) VALUES
('Peugeot', '208', 'Udlejet', 3000, 'Benzin', 15000.5),
('DS Automobiles', 'DS 4', 'Ledig', 4500, 'Hybrid', 1200.0);

INSERT INTO customer (customer_name) VALUES
('Jens Hansen', 'Stolvej 46B', 'Vordingborg', '4760', 'jegelskerstole@yahoo.com','67 67 67 67');
('Mette Frederiksen', 'Skoegevej 15', 'København NV', '2400', 'jegelskermagt@gmail.dk', '66 66 66 66');

INSERT INTO lease (car_id, customer_id, pickup_date, turn_in_date, pickup_location, turn_in_location) VALUES
(1, 1, '2024-05-01', '2024-10-01', 'Bilabonnement HQ', 'FDM Sjælland');

INSERT INTO damage (lease_id, title, description, price, status) VALUES
(1, 'Ridset fæl', 'Højre forhjul har ramt en kantsten', 1500, 'Skadet');