-- Project_Phase 2--
     --Views--

-- 7-1 --
CREATE OR REPLACE VIEW Employee_History
AS
SELECT
Ename AS Employee_Name,Dname AS Department_Name,Hiredate
FROM Employee JOIN Department ON (DeptNo=Dnumber);

-- 7-2 --
CREATE OR REPLACE VIEW Vehicle_History
AS
SELECT
Ename AS Employee_Name,VModel AS Vehicle_Model,Next_Maintenance_Date
FROM Employee JOIN Vehicle ON (Employee.Emp_ID=Vehicle.Emp_ID);

-- 7-3 --
CREATE OR REPLACE VIEW Project_History
AS
SELECT
PName AS Project_Name,PDesc AS Project_Description,DName AS Department_Name
FROM Projects JOIN Dept_Project ON (Projects.PNumber=Dept_Project.ProjectNo) JOIN Department ON (Department.DNumber=Dept_Project.DeptNo);

-- 7-4 --
CREATE OR REPLACE VIEW Project_History
AS
SELECT Ename AS Employee_Name,No_Of_Hours*Hourly_Pay AS Overtime_Pay 
FROM Employee join Overtime on (Employee.Emp_ID=Overtime.Emp_ID);
