CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(255),
    role ENUM("ADMIN","CUSTOMER")
);

CREATE TABLE accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(30) UNIQUE,
    account_type ENUM("CREDIT","CURRENT","SAVINGS"),
    balance DOUBLE,
    user_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type ENUM("DEPOSIT","TRANSFER","WITHDRAWN"),
    amount DECIMAL(38,2),
    timestamp DATETIME,
    description VARCHAR(255),
    account_id BIGINT,
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE beneficiaries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    beneficiary_name VARCHAR(255),
    account_number VARCHAR(30),
    bank_name VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE customer_profiles (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    date_of_birth DATE,
    email VARCHAR(255),
    full_name VARCHAR(255),
    kyc_status ENUM('APPROVED', 'PENDING', 'REJECTED') NOT NULL,
    phone VARCHAR(255),
    pincode VARCHAR(255),
    state VARCHAR(255),
    user_id BIGINT
    FOREIGN KEY(user_id) REFERENCES users(id)
);




