--students.sql
--January 31 2019
--WEBD4201
--This sql file establishes the student table to store all of the data related to a student
-- plus a foreign key to the to the user table to get the rest of the data.

DROP TABLE IF EXISTS students CASCADE;

CREATE TABLE students(
	id			BIGINT			PRIMARY KEY REFERENCES users(id),
	programCode 		CHARACTER VARYING(40)	NOT NULL,
	programDescription	VARCHAR(250)		NOT NULL,
	year			INT			NOT NULL
);

INSERT INTO students (id, programCode, programDescription, year) 
VALUES 
    (100393373, 'CPA', 'Computer Programmer Analyst', 2),
    (100111111, 'CSTY', 'Computer System Technology', 3),
    (100765543, 'CP', 'Computer Programmer', 1);

	