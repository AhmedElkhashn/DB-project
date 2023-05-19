--Triggers--

--For some reason it sometimes disconnects while runing this file run the statement below to avoid this error
ALTER SESSION SET PLSCOPE_SETTINGS = 'IDENTIFIERS:NONE';

SET SERVEROUTPUT ON;

CREATE OR REPLACE TRIGGER trg_emp_age_ch
    BEFORE INSERT OR UPDATE ON Employee
    FOR EACH ROW
DECLARE
    Age NUMBER(3);
BEGIN
    SELECT ((SYSDATE-:NEW.Birthdate)/365.2422)
    INTO Age
    FROM DUAL;
    
    IF(Age>60 OR Age<18)THEN
        RAISE_APPLICATION_ERROR(-20001, 'Invalid Age - Age must be between 18-60');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_emp_sal_ch
    BEFORE INSERT OR UPDATE ON Employee
    FOR EACH ROW
DECLARE
    Max_sal NUMBER(8);
    Min_sal NUMBER(8);
BEGIN
    SELECT MAX(Hisal) 
    INTO Max_sal 
    FROM Sal_Grade;
    
    SELECT MIN(Losal) 
    INTO Min_sal
    FROM Sal_Grade;
    
    IF( :NEW.Salary > Max_sal OR :NEW.Salary < Min_sal)THEN
        RAISE_APPLICATION_ERROR(-20001, 'Salary must belong to a grade');
    END IF;    
END;
/

CREATE OR REPLACE TRIGGER trg_vehicle_maintenance_ch
    BEFORE INSERT OR UPDATE ON Vehicle
    FOR EACH ROW
DECLARE
    MonthsBetween NUMBER(4);
BEGIN
    SELECT MONTHS_BETWEEN(:NEW.Next_Maintenance_Date,SYSDATE)
    INTO MonthsBetween
    FROM DUAL;
    IF( MonthsBetween>3 OR MonthsBetween<0)THEN
        RAISE_APPLICATION_ERROR(-20001, 'Maintenance date must be within 3 month');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_login_pos_ch
    BEFORE INSERT OR UPDATE ON Login
    FOR EACH ROW
DECLARE
    Emp_pos VARCHAR2(22);
BEGIN
    SELECT Pos 
    INTO Emp_pos 
    FROM Employee
    WHERE Emp_ID = :NEW.Emp_ID;
    IF(Emp_pos != 'HR')THEN
        RAISE_APPLICATION_ERROR(-20001, 'Login is only given to HR');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER trg_transaction
    AFTER INSERT OR UPDATE OR DELETE ON Employee
    FOR EACH ROW
BEGIN
    CASE
        WHEN INSERTING  THEN
            INSERT INTO Transactions VALUES(USER,SYSDATE,'INSERTION',:NEW.Emp_ID,:NEW.Pos);
        WHEN UPDATING THEN
            INSERT INTO Transactions VALUES(USER,SYSDATE,'UPDATE',:NEW.Emp_ID,:NEW.Pos);
        WHEN DELETING THEN
            INSERT INTO Transactions VALUES(USER,SYSDATE,'DELETE',:OLD.Emp_ID,:OLD.Pos);
    END CASE;
END;
/

ALTER TRIGGER trg_transaction DISABLE;




CREATE OR REPLACE TRIGGER trg_Department_count
    AFTER INSERT OR UPDATE OR DELETE ON Employee
    FOR EACH ROW
BEGIN
    CASE
        WHEN INSERTING  THEN
        
            UPDATE Department
            SET No_Of_Workers=No_Of_Workers+1
            WHERE DNumber=:NEW.DeptNo;
                  
        WHEN UPDATING THEN
        
            UPDATE Department
            SET No_Of_Workers=No_Of_Workers+1
            WHERE DNumber=:NEW.DeptNo;
            
            UPDATE Department
            SET No_Of_Workers=No_Of_Workers-1
            WHERE DNumber=:OLD.DeptNo;    
            
        WHEN DELETING THEN
        
            UPDATE Department
            SET No_Of_Workers=No_Of_Workers-1
            WHERE DNumber=:OLD.DeptNo; 
            
    END CASE;
END;
/

CREATE OR REPLACE TRIGGER trg_emp_sal
    AFTER INSERT OR UPDATE ON Employee
    FOR EACH ROW
DECLARE
    EGrade VARCHAR2(2);
    Sal NUMBER(8);
BEGIN
    SELECT :NEW.Salary
    INTO Sal
    FROM DUAL;
    
    SELECT Grade
    INTO EGrade
    FROM Sal_Grade
    WHERE Sal>Losal AND Sal<Hisal;
    
    CASE
        WHEN INSERTING  THEN
            INSERT INTO Emp_Grade VALUES
            (:NEW.Emp_ID,EGrade);
        WHEN UPDATING THEN
            UPDATE Emp_Grade
            SET Grade=EGrade
            WHERE Emp_ID=:NEW.Emp_ID; 
    END CASE;
END;
/

CREATE OR REPLACE TRIGGER trg_overtime 
    BEFORE INSERT OR UPDATE ON Overtime
    FOR EACH ROW
DECLARE
    EGrade VARCHAR2(2);
    Sal NUMBER(8);
    Type_multiplier NUMBER(2):=1;
    Grade_rate NUMBER(8);
BEGIN
    SELECT Salary
    INTO Sal
    FROM Employee
    WHERE Emp_ID=:NEW.Emp_ID;
    
    SELECT Grade
    INTO EGrade
    FROM Sal_Grade
    WHERE Sal>Losal AND Sal<Hisal;  
    :NEW.Grade:=EGrade;
    
    IF (:NEW.OTYPE LIKE 'NIGHT') THEN              
        SELECT 2
        INTO Type_multiplier
        FROM DUAL;
    END IF;
            
    SELECT Overtime_rate
    INTO Grade_rate 
    FROM Sal_Grade
    WHERE Grade=EGrade;
    
    :NEW.Hourly_Pay:=(Grade_rate*Type_multiplier);
    
END;
/

