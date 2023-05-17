--Triggers--

CREATE OR REPLACE TRIGGER trg_emp_age_ch
    BEFORE INSERT OR UPDATE ON Employee
    FOR EACH ROW
BEGIN
    IF( ((:NEW.Birthdate-SYSDATE)/365.2422)>60 OR ((:NEW.Birthdate-SYSDATE)/365.2422)<18)THEN
        RAISE_APPLICATION_ERROR(-20001, 'Invalid Age');
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
BEGIN
    IF( MONTHS_BETWEEN(:NEW.Next_Maintenance_Date,SYSDATE)<3)THEN
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
            INSERT INTO Transactions VALUES(USER,SYSDATE,:NEW.Emp_ID,:NEW.Pos);
        WHEN UPDATING THEN
            INSERT INTO Transactions VALUES(USER,SYSDATE,:NEW.Emp_ID,:NEW.Pos);
        WHEN DELETING THEN
            INSERT INTO Transactions VALUES(USER,SYSDATE,:OLD.Emp_ID,:OLD.Pos);
    END CASE;
END;
/