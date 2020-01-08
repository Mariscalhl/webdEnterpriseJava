--faculty.sql
--January 31 2019
--WEBD4201
--This sql file establishes the faculty table to store all of the data related to a faculty
-- plus a foreign key to the to the user table to get the rest of the data.

DROP TABLE IF EXISTS faculty CASCADE;

CREATE TABLE faculty(
	id			BIGINT			PRIMARY KEY REFERENCES users(id),
	schoolCode 		VARCHAR(10)		NOT NULL,
	schoolDescription	VARCHAR(250)		NOT NULL,
	office			VARCHAR(20)		NOT NULL,
	extension		VARCHAR(10)		NOT NULL
);

INSERT INTO faculty (id, schoolCode, schoolDescription, office, extension) 
VALUES 
    (100123456, 'SET', 'School of Engineering & Technology', 'H-140', 1234),
    (100222222, 'BIT', 'School of Business & IT Management', 'C-120', 5678),
    (100333333, 'BIT', 'School of Business & IT Management', 'C-120', 1290);