package com.karapada.teacher;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InsertDataUsingStoredProcedure {

    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@//localhost:1521/xe";
        String user = "hr";
        String password = "hr";

        int newPsMessageId = 12345; 
        String systemID = "101";
        String inventoryId = "919191";
        String unitName = "ccms";
        String address = "hyderabad";
        String names = "ram";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "{call insert_student_data2(?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);

            stmt.setInt(1, newPsMessageId);
            stmt.setString(2, systemID);
            stmt.setString(3, inventoryId);
            stmt.setString(4, unitName);
            stmt.setString(5, address);
            stmt.setString(6, names);

            stmt.execute();

            System.out.println("Data inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
