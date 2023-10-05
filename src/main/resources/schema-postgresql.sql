CREATE TABLE IF NOT EXISTS tbl_test(
   ID INT PRIMARY KEY     NOT NULL,
   NAME           VARCHAR(50)    NOT NULL
);

--CREATE TABLE IF NOT EXISTS tbl_accounts (
--	user_id serial PRIMARY KEY,
--	username VARCHAR ( 50 ) UNIQUE NOT NULL,
--	password VARCHAR ( 50 ) NOT NULL,
--	email VARCHAR ( 255 ) UNIQUE NOT NULL,
--	created_on TIMESTAMP NOT NULL,
--    last_login TIMESTAMP
--);