DROP TABLE IF EXISTS damage;
DROP TABLE IF EXISTS lease;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS car;

CREATE TABLE car (
    car_id INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    status VARCHAR(50) DEFAULT 'Ledig',
    price INT NOT NULL,
    fuel_type VARCHAR(50),
    mileage FLOAT
);

CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE lease (
    lease_id INT AUTO_INCREMENT PRIMARY KEY,
    car_id INT NOT NULL,
    customer_id INT NOT NULL,
    pickup_date DATE NOT NULL,
    turn_in_date DATE NOT NULL,
    pickup_location VARCHAR(100),
    turn_in_location VARCHAR(100),
    FOREIGN KEY (car_id) REFERENCES car(car_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE damage (
    damage_id INT AUTO_INCREMENT PRIMARY KEY,
    lease_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price INT NOT NULL,
    status VARCHAR(50) DEFAULT 'Skadet',
    FOREIGN KEY (lease_id) REFERENCES lease(lease_id)
);