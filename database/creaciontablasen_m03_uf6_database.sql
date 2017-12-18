CREATE TABLE departamentos (
    dept_id INT NOT NULL AUTO_INCREMENT,
    dept_name VARCHAR(50) NOT NULL,
    PRIMARY KEY(dept_id)
);

CREATE TABLE empleados (
    empl_id INT AUTO_INCREMENT NOT NULL,
    dept_id INT NOT NULL,
    empl_first_name VARCHAR(50) NOT NULL,
    empl_second_name VARCHAR(50) NOT NULL,
    empl_last_name VARCHAR(50) NOT NULL,
    empl_address VARCHAR(50),
    empl_tel_number INT,
    empl_birthdate DATE,
    empl_married BOOLEAN,
    empl_salary INT,
    CHECK(empl_salary > 0),
    PRIMARY KEY(empl_id),
    FOREIGN KEY(dept_id) REFERENCES departamentos(dept_id)
);