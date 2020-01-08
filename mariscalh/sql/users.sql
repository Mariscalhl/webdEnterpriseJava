--users.sql
--January 31 2019
--WEBD4201
--This sql file establishes the user table to store all of the data related to a user.

DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
	id		BIGINT			PRIMARY KEY,
	password 	CHARACTER VARYING(40)	NOT NULL,
	firstName 	VARCHAR(40)		NOT NULL,
	lastName 	VARCHAR(50)		NOT NULL,
	emailAddress	CHARACTER VARYING(256)	NOT NULL,
	lastAccess	DATE			NOT NULL,
	enrolDate	DATE			NOT NULL,
	enabled		BOOLEAN			NOT NULL,
	type		CHAR			NOT NULL
);

INSERT INTO users (id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type) 
VALUES 
    (100393373, encode(digest('password', 'sha1'), 'hex'), 'Hector', 'Mariscal', 'hector.mariscal@dcmail.ca', '2019-01-31', '2017-09-7', true, 's'),

    (100111111, encode(digest('password', 'sha1'), 'hex'), 'Mike', 'Jones', 'mike.jones@dcmail.ca', '2019-01-31', '2015-09-11', true, 's'),

    (100765543, encode(digest('testPassword', 'sha1'), 'hex'), 'Test', 'Student', 'test.student@dcmail.ca', '2019-01-31', '2019-01-31', true, 's'),

    (100123456, encode(digest('testPassword1', 'sha1'), 'hex'), 'Test', 'Faculty', 'test.faculty@dcmail.ca', '2019-01-31', '2019-01-31', true, 'f'),

    (100222222, encode(digest('password', 'sha1'), 'hex'), 'Deerrald', 'Rat', 'Deerrold.rat@dcmail.ca', '2019-01-31', '2019-01-31', true, 'f'),

    (100333333, encode(digest('testPassword', 'sha1'), 'hex'), 'Aproova', 'Tell', 'aproova.tell@dcmail.ca', '2019-01-31', '2019-01-31', true, 'f')



;