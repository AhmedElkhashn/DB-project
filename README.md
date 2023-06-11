# Human Resources Information System 
## This project is a simple database application for a recruitment firm
(This project was for Fundamentals of Database Systems course)

We were given a prompt and tasked to:
* Create a suitable ERD and relational schema that comply with the given constraints 
* Normalise it to 3rd normal form 
* Create the corresponding SQL script to create the tables and insert some data
* Create a GUI that connects to the DBMS and provides specified functionalities
## Phase 1
### Entity Relationship Diagram
![entity relationship diagram](https://github.com/AhmedElkhashn/DB-project/blob/main/Phase%201/ERD.drawio.png)
### Relational Schema
![relational schema](https://github.com/AhmedElkhashn/DB-project/blob/main/Phase%201/Relational%20Schema.drawio.png)
## Phase 2
### Tables
![Tables](https://github.com/AhmedElkhashn/DB-project/blob/main/Phase%202/Tables.drawio.png)
## How to run this project:
1. Run the Script.sql file (found in Phase 2 folder) in Orcale
2. Import the java project HRSystemApp (found in Phase 2 folder)
3. Add all the jar files from ojfbc8-full (found in Phase 2 folder) to the classpath 
4. In HRSystemApp go to the class Uitility and set dburl variable to your Database url 

   It should be in the following format: jdbc:oracle:thin:@hostname:port/service_name
5. Run Login.java
7. Add use the same username and password of the orcale account that you ran the script on to login to the GUI
