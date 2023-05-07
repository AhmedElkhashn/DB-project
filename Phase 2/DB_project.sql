-- Project_Phase 2
-- Tables
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
    
CREATE TABLE Position(
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
    Pass VARCHAR2(22) NOT NULL);

CREATE TABLE Transaction(
    TUser VARCHAR2(22),
    Date_Time DATE NOT NULL,
    Emp_ID VARCHAR2(22),
    Pos VARCHAR2(22));

CREATE TABLE Overtime(
    Emp_ID NUMBER(4),
    ODate DATE NOT NULL,
    OType VARCHAR2(22),
    No_Of_Hours NUMBER(3) NOT NULL,
    Hourly_Pay NUMBER(8), -- ADD TRIGGER
    Grade NUMBER(1));
    

CREATE TABLE Department(
    DNumber NUMBER(2),
    DName VARCHAR2(22) NOT NULL,
    DDesc VARCHAR2(50) NOT NULL,
    No_Of_Workers NUMBER(2));

CREATE TABLE Project(
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
    




















