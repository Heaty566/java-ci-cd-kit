CREATE DATABASE ComputerManagement
GO
 
USE ComputerManagement
GO
 
CREATE TABLE StaffLoginTBL
(
	UserName nvarchar(10) PRIMARY KEY,
	UserPassword nvarchar(50),
	FullName nvarchar(50)
);
GO
 
CREATE TABLE RoomTBL
(
	RoomID nvarchar(10) PRIMARY KEY,
	RoomName nvarchar(50),
	Building nvarchar(50),
	FloorNumber int
);
GO
 
CREATE TABLE ComputerTBL
(
	ComputerID nvarchar(10) PRIMARY KEY,
	CPU nvarchar(100),
	HardDisk nvarchar(100),
	RAM nvarchar(100),
	VGA nvarchar(100),
	Monitor nvarchar(100),	
	RoomID nvarchar(10) FOREIGN KEY REFERENCES RoomTBL(RoomID) ON DELETE CASCADE ON UPDATE CASCADE	
);
 
GO
INSERT INTO StaffLoginTBL VALUES ('van','123456','Thanh Van');
INSERT INTO StaffLoginTBL VALUES ('dung','123456','Kim Dung');
GO
INSERT INTO RoomTBL VALUES('119','Lab 001','H Building',2);
INSERT INTO RoomTBL VALUES('120','Lab 002','H Building',2);
INSERT INTO RoomTBL VALUES('123','Training Room 1','C Building',3);
INSERT INTO RoomTBL VALUES('169','Training Room 2','C Building',3);
INSERT INTO RoomTBL VALUES('170','Research Zoom 1','A Building',4);
INSERT INTO RoomTBL VALUES('171','Research Zoom 2','A Building',5);
INSERT INTO RoomTBL VALUES('172','Lab 003','A Building',5);
GO
 
INSERT INTO ComputerTBL VALUES('01','Intel P4 3.2 GB','120GB','1024 MB','onboard','Samsung','119');
INSERT INTO ComputerTBL VALUES('02','AMD K7 3200+','160GB','2048 MB','Ati','Sony LCD 15"','119');
INSERT INTO ComputerTBL VALUES('03','Intel P5 6.4 GB','240GB','2048 MB','onboard','Dell','119');
INSERT INTO ComputerTBL VALUES('04','Intel P4 3.2 GB','120GB','1024 MB','onboard','Samsung','120');
INSERT INTO ComputerTBL VALUES('05','AMD K7 3200+','160GB','2048 MB','Ati','Sony LCD 15"','120');
INSERT INTO ComputerTBL VALUES('06','Intel P5 6.4 GB','240GB','2048 MB','onboard','Dell','120');
INSERT INTO ComputerTBL VALUES('07','Intel P4 3.2 GB','120GB','1024 MB','onboard','Samsung','120');
INSERT INTO ComputerTBL VALUES('08','AMD K7 3200+','160GB','2048 MB','Ati','Sony LCD 15"','172');
INSERT INTO ComputerTBL VALUES('09','Intel P5 6.4 GB','240GB','2048 MB','onboard','Dell','172');