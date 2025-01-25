CREATE TABLE users (
    id UUID PRIMARY KEY,
    fullname VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    pin VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE user_detail (
    id BIGINT PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) UNIQUE NOT NULL,
    nik VARCHAR(255) UNIQUE NOT NULL,
    mother_name VARCHAR(255) NOT NULL,
    user_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE role (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    role_type VARCHAR(255) NOT NULL
);

CREATE VIEW data_users AS
SELECT
    u.id AS user_id,
    u.fullname,
    u.role_id,
    u.email,
    u.created_at,
    ud.address,
    ud.phone_number,
    ud.mother_name
FROM users u
LEFT JOIN user_detail ud ON u.id = ud.user_id where u.role_id = 3;

CREATE VIEW data_teller AS
SELECT
    u.id AS user_id,
    u.fullname,
    u.role_id,
    u.email,
    u.created_at
FROM users u
where u.role_id = 2;
