package com.jdbc.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection instance;
    
    private ConnectDB(){
    }
    
    public static Connection getInstance() throws SQLException {
        if (instance == null) {
            instance = DriverManager.getConnection(MYSQLConnection.url, MYSQLConnection.username, MYSQLConnection.password);
            System.out.println("Connection to database initialized");
        }
        
        return instance;
    }
    
    public static void closeConnection() throws SQLException {
        if (instance != null) {
            instance.close();
            instance = null;
            System.out.println("Connection to database ended");
        }
    }
}
