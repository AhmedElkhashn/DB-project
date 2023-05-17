-- Project_Phase 2--
     --Create--
     
--Drop--
--Drop Constraints
ALTER TABLE Emp_Grade
DROP CONSTRAINT Emp_Grade_ID_pk
DROP CONSTRAINT Emp_Grade_fk
DROP CONSTRAINT Grade_Emp_ID_fk;

ALTER TABLE Transactions
DROP CONSTRAINT Transactions_Username_Date_pk
DROP CONSTRAINT Transactions_username_fk 
DROP CONSTRAINT Transactions_Emp_ID_fk
DROP CONSTRAINT Transactions_Pos_fk;

ALTER TABLE Login
DROP CONSTRAINT Login_Emp_ID_fk;

ALTER TABLE Overtime
DROP CONSTRAINT Overtime_Emp_ID_Date_Type_pk
DROP CONSTRAINT Overtime_Type_ch
DROP CONSTRAINT Overtime_No_Of_Hours_ch
DROP CONSTRAINT Overtime_Emp_ID_fk
DROP CONSTRAINT Overtime_Grade_fk;

ALTER TABLE Sal_Grade
DROP CONSTRAINT Sal_Grade_pk;

ALTER TABLE Dept_Project
DROP CONSTRAINT Dept_Project_pk
DROP CONSTRAINT Dept_Project_fk
DROP CONSTRAINT Project_Dept_fk;

ALTER TABLE Projects
DROP CONSTRAINT Project_Pnumber_pk;

ALTER TABLE Vehicle
DROP CONSTRAINT Vehicle_ID_pk
DROP CONSTRAINT Vehicle_Emp_ID_fk;

ALTER TABLE Position_Allowance
DROP CONSTRAINT Positions_Pos_name_Aname_pk
DROP CONSTRAINT Position_Allowance_Pos_fk 
DROP CONSTRAINT Position_Allowance_Aname_fk;


ALTER TABLE Allowance
DROP CONSTRAINT Allowance_Aname_pk
DROP CONSTRAINT Allowance_Aname_ch;

ALTER TABLE Employee
DROP CONSTRAINT Employee_Emp_ID_pk
DROP CONSTRAINT Employee_Gender_ch
DROP CONSTRAINT Employee_Pos_fk
DROP CONSTRAINT Employee_Dept_No_fk;

ALTER TABLE Positions
DROP CONSTRAINT Positions_Pos_name_pk
DROP CONSTRAINT Positions_Pos_name_ch;

ALTER TABLE Department
DROP CONSTRAINT Dept_number_pk;

ALTER TABLE Login
DROP CONSTRAINT Login_username_pk;

--Drop Tables
DROP TABLE Employee;
DROP TABLE Positions;
DROP TABLE Allowance;
DROP TABLE Position_Allowance;
DROP TABLE Vehicle;
DROP TABLE Login;
DROP TABLE Transactions;
DROP TABLE Overtime;
DROP TABLE Department;
DROP TABLE Projects;
DROP TABLE Dept_Project;
DROP TABLE Sal_Grade;
DROP TABLE Emp_Grade;

-- Creating Tables --
CREATE TABLE Employee(
    Emp_ID NUMBER(4),
    Ename VARCHAR2(22) NOT NULL,
    Gender VARCHAR2(1),
    Birthdate DATE NOT NULL,
    Hiredate DATE NOT NULL,
    Salary NUMBER(8) NOT NULL,
    Commission NUMBER(8),
    PhoneNo VARCHAR2(18) UNIQUE NOT NULL,
    Pos VARCHAR2(22),
    DeptNo NUMBER(2));
    
CREATE TABLE Positions(
    Pos_Name VARCHAR2(22));

CREATE TABLE Allowance(
    Aname VARCHAR2(22),
    ADesc VARCHAR(50) NOT NULL);
    
CREATE TABLE Position_Allowance(
    Pos_Name VARCHAR2(22),
    Aname VARCHAR2(22));
    
CREATE TABLE Vehicle(
    Vehicle_ID NUMBER(6),
    VModel VARCHAR2(22) NOT NULL,
    Make VARCHAR2(22) NOT NULL,
    Next_Maintenance_Date DATE NOT NULL,
    Emp_ID NUMBER(4));
    
CREATE TABLE Login(
    Username VARCHAR2(22),
    Pass VARCHAR2(22) NOT NULL,
    Emp_ID NUMBER(4) NOT NULL);

CREATE TABLE Transactions(
    Username VARCHAR2(22),
    Date_Time DATE NOT NULL,
    Emp_ID NUMBER(4),
    Pos VARCHAR2(22));

CREATE TABLE Overtime(
    Emp_ID NUMBER(4),
    ODate DATE NOT NULL,
    OType VARCHAR2(22),
    No_Of_Hours NUMBER(3) NOT NULL,
    Hourly_Pay NUMBER(8), -- ADD TRIGGER
    Grade NUMBER(1)); --Trigger
    

CREATE TABLE Department(
    DNumber NUMBER(2),
    DName VARCHAR2(22) NOT NULL,
    DDesc VARCHAR2(50) NOT NULL,
    No_Of_Workers NUMBER(2));

CREATE TABLE Projects(
    PNumber NUMBER(3),
    PName VARCHAR2(22) NOT NULL,
    PDesc VARCHAR2(50) NOT NULL);

CREATE TABLE Dept_Project(
    DeptNo NUMBER(2),
    ProjectNo NUMBER(3));

CREATE TABLE Sal_Grade(
    Grade NUMBER(1),
    Losal NUMBER(8) NOT NULL,
    Hisal NUMBER(8) NOT NULL);

CREATE TABLE Emp_Grade(
    Emp_ID NUMBER(4),
    Grade NUMBER(1) NOT NULL);

--Adding Constraints--

--Adding PKs and checks
ALTER TABLE Employee
ADD(
CONSTRAINT Employee_Emp_ID_pk PRIMARY KEY (Emp_ID), 
CONSTRAINT Employee_Gender_ch CHECK (Gender='M' or Gender='F'));

ALTER TABLE Positions
ADD(
CONSTRAINT Positions_Pos_name_pk PRIMARY KEY (Pos_name),
CONSTRAINT Positions_Pos_name_ch CHECK(Pos_name IN ('Manager', 'Accountant', 'Administrator', 'Clerk', 'HR')));

ALTER TABLE Allowance
ADD(
CONSTRAINT Allowance_Aname_pk PRIMARY KEY (Aname),
CONSTRAINT Allowance_Aname_ch CHECK(Aname IN ('Fuel','House','Social','Manager','Uniform')));

ALTER TABLE Position_Allowance
ADD(
CONSTRAINT Positions_Pos_name_Aname_pk PRIMARY KEY (Pos_name,Aname));

ALTER TABLE Vehicle
ADD(
CONSTRAINT Vehicle_ID_pk PRIMARY KEY (Vehicle_ID));

ALTER TABLE Login
ADD(
CONSTRAINT Login_username_pk PRIMARY KEY (Username));

ALTER TABLE Transactions
ADD(
CONSTRAINT Transactions_Username_Date_pk PRIMARY KEY (Username,Date_Time));

ALTER TABLE Overtime
ADD(
CONSTRAINT Overtime_Emp_ID_Date_Type_pk PRIMARY KEY (Emp_ID,ODate,OType),
CONSTRAINT Overtime_Type_ch CHECK (OType in ('Day','Night')),
CONSTRAINT Overtime_No_Of_Hours_ch CHECK (No_Of_Hours <= 8));

ALTER TABLE Department
ADD(
CONSTRAINT Dept_number_pk PRIMARY KEY (DNumber));

ALTER TABLE Projects
ADD(
CONSTRAINT Project_Pnumber_pk PRIMARY KEY (PNumber));

ALTER TABLE Dept_Project
ADD(
CONSTRAINT Dept_Project_pk PRIMARY KEY (DeptNo,ProjectNo));

ALTER TABLE Sal_Grade
ADD(
CONSTRAINT Sal_Grade_pk PRIMARY KEY (Grade));

ALTER TABLE Emp_Grade
ADD(
CONSTRAINT Emp_Grade_ID_pk PRIMARY KEY (Emp_ID));

--Adding FKs

ALTER TABLE Employee
ADD(
CONSTRAINT Employee_Pos_fk FOREIGN KEY (Pos) REFERENCES Positions (Pos_name),
CONSTRAINT Employee_Dept_No_fk FOREIGN KEY (DeptNo) REFERENCES Department (Dnumber));

ALTER TABLE Position_Allowance
ADD(
CONSTRAINT Position_Allowance_Pos_fk FOREIGN KEY (Pos_Name) REFERENCES Positions (Pos_name),
CONSTRAINT Position_Allowance_Aname_fk FOREIGN KEY (Aname) REFERENCES Allowance (Aname));

ALTER TABLE Vehicle
ADD(
CONSTRAINT Vehicle_Emp_ID_fk FOREIGN KEY (Emp_ID) REFERENCES Employee (Emp_ID));

ALTER TABLE Transactions
ADD(
CONSTRAINT Transactions_username_fk FOREIGN KEY (Username) REFERENCES Login (Username),
CONSTRAINT Transactions_Emp_ID_fk FOREIGN KEY (Emp_ID) REFERENCES Employee (Emp_ID),
CONSTRAINT Transactions_Pos_fk FOREIGN KEY (Pos) REFERENCES Positions (Pos_name));

ALTER TABLE Overtime
ADD(
CONSTRAINT Overtime_Emp_ID_fk FOREIGN KEY (Emp_ID) REFERENCES Employee (Emp_ID),
CONSTRAINT Overtime_Grade_fk FOREIGN KEY (Grade) REFERENCES Sal_Grade (Grade));

ALTER TABLE Dept_Project
ADD(
CONSTRAINT Dept_Project_fk FOREIGN KEY (ProjectNo) REFERENCES Projects (PNumber),
CONSTRAINT Project_Dept_fk FOREIGN KEY (DeptNo) REFERENCES Department (DNumber));

ALTER TABLE Emp_Grade
ADD(
CONSTRAINT Emp_Grade_fk FOREIGN KEY (Grade) REFERENCES Sal_Grade (Grade),
CONSTRAINT Grade_Emp_ID_fk FOREIGN KEY (Emp_ID) REFERENCES Employee (Emp_ID));

ALTER TABLE Login
ADD(
CONSTRAINT Login_Emp_ID_fk FOREIGN KEY (Emp_ID) REFERENCES Employee (Emp_ID));


--Insertion--

COMMIT;













