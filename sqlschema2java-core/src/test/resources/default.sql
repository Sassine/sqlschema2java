CREATE TABLE Company (
    id INT NOT NULL AUTO_INCREMENT, 
    duns VARCHAR(9) UNIQUE,
    name VARCHAR(255),
    address VARCHAR(255), 
    zip_code VARCHAR(5) DEFAULT '', 
    company_creation Date DEFAULT CURRENT_TIMESTAMP, 
    website VARCHAR(255), 
    phone_number VARCHAR(255), 
    city VARCHAR(255), 
    PRIMARY KEY(id)
);