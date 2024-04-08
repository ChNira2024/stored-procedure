package com.karapada.teacher;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InsertDataUsingStoredProcedure2 {

    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@//localhost:1521/xe";
        String user = "hr";
        String password = "hr";

        // Input parameters for the stored procedure
        String systemID = "101";
        String inventoryId = "919191";
        String unitName = "ccms";
        String address = "hyderabad";
        String names = "ram";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Prepare the CallableStatement to call the stored procedure
            String sql = "{call insert_student_data3(?, ?, ?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);

            // Set the input parameters
            stmt.setString(1, systemID);
            stmt.setString(2, inventoryId);
            stmt.setString(3, unitName);
            stmt.setString(4, address);
            stmt.setString(5, names);

            stmt.execute();

            System.out.println("Data inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/*
 CREATE OR REPLACE PROCEDURE insert_student_data3(
    systemID IN VARCHAR2,
    inventiryId IN VARCHAR2,
    unitName IN VARCHAR2,
    address IN VARCHAR2,
    names IN VARCHAR2
)
IS
    new_ps_msg_id NUMBER;
BEGIN
    -- Get the next value from the sequence
    SELECT PS_MESSAGE_ID_SEQ.NEXTVAL INTO new_ps_msg_id FROM DUAL;
    
    -- Insert data into PS_INPUT_MESSAGE_STATUS_UPDATE table using the generated ID
    INSERT INTO PS_INPUT_MESSAGE_STATUS_UPDATE (PS_MESSAGE_ID, SYSTEM_ID, PS_INVENTIRY_ID) 
    VALUES (new_ps_msg_id, systemID, inventiryId);
    
    -- Insert data into PS_MESSAGE_DETAILS using the generated ID
    INSERT INTO PS_MESSAGE_DETAILS (PS_MESSAGE_ID, SYSTEM_ID, UNIT_NAME) 
    VALUES (new_ps_msg_id, systemID, unitName);
    
    -- Insert data into PS_HIT_DETAILS using the generated ID
    INSERT INTO PS_HIT_DETAILS (PS_MESSAGE_ID, ADDRESS, NAMES) 
    VALUES (new_ps_msg_id, address, names);
    
    -- Display the generated ID
    DBMS_OUTPUT.PUT_LINE('Generated PS_MESSAGE_ID: ' || new_ps_msg_id);
END;

*/
//CREATE SEQUENCE PS_MESSAGE_ID_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
