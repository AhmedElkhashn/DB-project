-- Project_Phase 2--
     --Views--

CREATE OR REPLACE VIEW Employee_History
AS
SELECT
Ename,Dname,Hiredate
FROM Employee JOIN Department ON (DeptNo=Dnumber);

CREATE OR REPLACE VIEW Vehicle_History
AS
SELECT
Ename,VModel,Next_Maintenance_Date
FROM Employee JOIN Vehicle ON (Employee.Emp_ID=Vehicle.Emp_ID);

CREATE OR REPLACE VIEW Project_History
AS
SELECT
PName,PDesc,DName
FROM Projects JOIN Dept_Project ON (Projects.PNumber=Dept_Project.ProjectNo) JOIN Department ON (Department.DNumber=Dept_Project.DeptNo);

CREATE OR REPLACE VIEW Project_History
AS
SELECT Ename,No_Of_Hours*Hourly_Pay AS Overtime_Pay 
FROM Employee join Overtime on (Employee.Emp_ID=Overtime.Emp_ID);
