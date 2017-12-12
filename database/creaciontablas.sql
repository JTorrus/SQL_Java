CREATE TABLE departamentos (
    id INT NOT NULL AUTO_INCREMENT,
    deptname VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE empleados (
    id INT AUTO_INCREMENT NOT NULL,
    deptid INT,
    firstname VARCHAR(50) NOT NULL,
    secondname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    address VARCHAR(50),
    telnumber INT,
    birthdate DATE,
    married BOOLEAN,
    salary INT,
    CHECK(salary > 0),
    PRIMARY KEY(id),
    FOREIGN KEY(deptid) REFERENCES departamentos(id)
);