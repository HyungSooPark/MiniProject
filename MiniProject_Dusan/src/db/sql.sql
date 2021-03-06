
CREATE SEQUENCE TESTSEQ NOCACHE;

INSERT INTO TEST values(TESTSEQ.NEXTVAL, 'TEST');

--
CREATE TABLE TICKETDATA(
	TICKETNO NUMBER(8),
	
	NAME VARCHAR2(100),
	ID VARCHAR2(100),
	PW VARCHAR2(100),
	
	GAME VARCHAR2(100),
	BLOCK VARCHAR2(20),
	SEAT VARCHAR2(200),
	
	PRICE NUMBER(38),
	PAYTIME VARCHAR2(100)
);

SELECT * FROM TICKETDATA;

DELETE FROM TICKETDATA WHERE ID='ID';

DROP TABLE TICKETDATA;
--

--
CREATE TABLE GAMELIST(
	GAMEDATE VARCHAR2(100),
	AWAYTEAM VARCHAR2(100)
);

SELECT * FROM GAMELIST;

DROP TABLE GAMELIST;
--

--
CREATE TABLE BLOCK(
	BLOCK VARCHAR2(100)
);

SELECT * FROM BLOCK;
--
CREATE TABLE USERINFO(
	NAME VARCHAR2(100),
	ID VARCHAR2(100),
	PW VARCHAR2(100),
	PHONE VARCHAR2(100)
);

SELECT * FROM USERINFO;

DROP TABLE USERINFO;
