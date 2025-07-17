package com.example.library_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    private static final String url = "jdbc:mysql://localhost:3306/library_management_system";
    private static final String user = "root";
    private static final String password = "Silent@2004_";

    private static Connection connection;

    public static void connect() throws SQLException {
        if(connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(url,user,password);
        }
    }

    public static Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()){
            connect();
        }
        return connection;
    }

    public static void closeConnection() throws SQLException{
        if(connection !=null || !connection.isClosed()){
            connection.close();
        }
    }
}
