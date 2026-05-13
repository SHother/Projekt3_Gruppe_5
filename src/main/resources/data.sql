INSERT INTO car (brand, model, status, price, fuel_type, mileage) VALUES
('Peugeot', '208', 'Udlejet', 3000, 'Benzin', 15000.5),
('DS Automobiles', 'DS 4', 'Ledig', 4500, 'Hybrid', 1200.0),
('Toyota', 'Yaris', 'Ledig', 2800, 'Hybrid', 22000.0),
('Volkswagen', 'Golf', 'Skadet', 3500, 'Diesel', 45000.3),
('Tesla', 'Model 3', 'Ledig', 6500, 'El', 8000.0),
('BMW', '320i', 'Udlejet', 5200, 'Benzin', 30000.7),
('Audi', 'A4', 'Ledig', 5400, 'Diesel', 27500.0),
('Hyundai', 'i20', 'Ledig', 2600, 'Benzin', 18000.2),
('Renault', 'Clio', 'Udlejet', 2400, 'Benzin', 35000.0),
('Mercedes', 'C200', 'Skadet', 6000, 'Hybrid', 16000.8);

INSERT INTO customer (customer_name, address, city, zip_code, email, phone) VALUES
('Jens Hansen', 'Stolvej 46B', 'Vordingborg', '4760', 'Jens@yahoo.com', '+4512345678'),
('Mette Frederiksen', 'Skoegevej 15', 'København NV', '2400', 'Mette@gmail.dk', '66666666');

INSERT INTO lease (car_id, customer_id, pickup_date, turn_in_date, pickup_location, turn_in_location) VALUES
(1, 1, '2024-05-01', '2024-10-01', 'Bilabonnement HQ', 'FDM Sjælland');

INSERT INTO damage (lease_id, title, description, price, status) VALUES
(1, 'Ridset fæl', 'Højre forhjul har ramt en kantsten', 1500, 'Skadet');