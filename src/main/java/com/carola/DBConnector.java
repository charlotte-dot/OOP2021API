package com.carola;

import java.sql.*;
import java.util.Properties;

public class DBConnector{

    public static final  String LOCAL_HOST_URL = "jdbc:postgresql://localhost:5432/";
    public static final  String DB_NAME = "CurrencyAPI";
    public static  final String USERNAME = "postgres";
    public static  final String PASSWORD = "password";
    public Connection connect() throws SQLException {
        String url = LOCAL_HOST_URL + DB_NAME;
        Properties props = new Properties();
        props.setProperty("user",USERNAME);
        props.setProperty("password",PASSWORD);
        return DriverManager.getConnection(url, props);
    }
    public void insertUpdateQuery(String query) {
        try {
            Connection conn = connect();
            Statement stm = conn.createStatement();
            stm.execute(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet selectQuery(String query) {
        try {
            Connection conn = connect();
            Statement stm = conn.createStatement();
            return stm.executeQuery(query);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}