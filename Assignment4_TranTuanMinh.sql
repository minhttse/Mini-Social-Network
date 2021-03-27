CREATE DATABASE Assignment4_TranTuanMinh
go
USE Assignment4_TranTuanMinh
go

CREATE TABLE tblRoles(
	roleID int NOT NULL PRIMARY KEY ,
	roleName varchar(20),
	status bit
)

CREATE TABLE tblUsers(
	email varchar(50) NOT NULL PRIMARY KEY,
	fullName nvarchar(50),
	password varchar(100) NOT NULL,
	registerdate date,
	roleID int NOT NULL FOREIGN KEY REFERENCES tblRoles(roleID),
	status varchar(15)
)

CREATE TABLE tblArticles(
	articleID varchar(20) NOT NULL PRIMARY KEY,
	email varchar(50) FOREIGN KEY REFERENCES tblUsers(email),
	title nvarchar(100),
	descripton nvarchar(1000),
	img varchar(1000),
	createdate datetime,
	upvote int,
	downvote int,
	isVisible bit,
)
 
CREATE TABLE tblComment(
	id varchar(20) NOT NULL PRIMARY KEY,
	articleID varchar(20) NOT NULL FOREIGN KEY REFERENCES tblArticles(articleID),
	email varchar(50) NOT NULL FOREIGN KEY REFERENCES tblUsers(email),
	descripton nvarchar(200),
	commentdate datetime,
	isVisible bit,
	isSeen bit,
)

CREATE TABLE tblVote(
	articleID varchar(20) NOT NULL FOREIGN KEY REFERENCES tblArticles(articleID),
	email varchar(50) NOT NULL FOREIGN KEY REFERENCES tblUsers(email),
	vote int,
	votedate datetime,
	isSeen bit,
	PRIMARY KEY(articleID, email),
)

CREATE TABLE tblNotification(
	frommail varchar(50) NOT NULL FOREIGN KEY REFERENCES tblUsers(email),
	receivermail varchar(50) NOT NULL FOREIGN KEY REFERENCES tblUsers(email),
	notitype bit,
	notidate datetime,
	isSeen bit,
	articleID varchar(20) NOT NULL FOREIGN KEY REFERENCES tblArticles(articleID),
)

INSERT INTO tblRoles
VALUES (1,'Admin',1)
INSERT INTO tblRoles
VALUES (2,'Member',1)

INSERT INTO tblUsers
VALUES ('admin@fpt.edu.vn','Tester','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','3/23/2021',1,'active')
INSERT INTO tblUsers
VALUES ('minhttse140690@fpt.edu.vn','Tran Tuan Minh','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','3/23/2021',2,'active')

INSERT INTO tblArticles
VALUES ('A1','minhttse140690@fpt.edu.vn','Article 1 title','Article 1 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A2','minhttse140690@fpt.edu.vn','Article 2 title','Article 2 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A3','minhttse140690@fpt.edu.vn','Article 3 title','Article 3 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A4','minhttse140690@fpt.edu.vn','Article 4 title','Article 4 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A5','minhttse140690@fpt.edu.vn','Article 5 title','Article 5 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A6','minhttse140690@fpt.edu.vn','Article 6 title','Article 6 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A7','minhttse140690@fpt.edu.vn','Article 7 title','Article 7 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A8','minhttse140690@fpt.edu.vn','Article 8 title','Article 8 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A9','minhttse140690@fpt.edu.vn','Article 9 title','Article 9 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A10','minhttse140690@fpt.edu.vn','Article 10 title','Article 10 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A11','minhttse140690@fpt.edu.vn','Article 11 title','Article 11 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A12','minhttse140690@fpt.edu.vn','Article 12 title','Article 12 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A13','minhttse140690@fpt.edu.vn','Article 13 title','Article 13 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A14','minhttse140690@fpt.edu.vn','Article 14 title','Article 14 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A15','minhttse140690@fpt.edu.vn','Article 15 title','Article 15 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A16','minhttse140690@fpt.edu.vn','Article 16 title','Article 16 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A17','minhttse140690@fpt.edu.vn','Article 17 title','Article 17 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A18','minhttse140690@fpt.edu.vn','Article 18 title','Article 18 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A19','minhttse140690@fpt.edu.vn','Article 19 title','Article 19 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A20','minhttse140690@fpt.edu.vn','Article 20 title','Article 20 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A21','minhttse140690@fpt.edu.vn','Article 21 title','Article 21 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)
INSERT INTO tblArticles
VALUES ('A22','minhttse140690@fpt.edu.vn','Article 22 title','Article 22 description','images/sinhtoxoai.jpg',GETDATE(),1, 0, 1)







