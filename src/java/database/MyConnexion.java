/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tolotra
 */
public class MyConnexion {

    private Connection conn;
    private Statement statement;

    public Connection openConnection() throws Exception {
        if (conn == null) {
            String url = "jdbc:mysql://localhost:3308/";
            String dbName = "gestioncontenuv2";
            String driver = "com.mysql.cj.jdbc.Driver";
            String userName = "root";
            String password = "root";
            try {
                Class.forName(driver).newInstance();
                this.conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
                System.out.println("CONNECTION SUCCESFUL");
            } catch (SQLException sql) {
                throw sql;
            }
        }
        conn.setAutoCommit(false);
        return conn;
    }

    public Connection openConnectionPostgresql() throws Exception {
        if (conn == null) {
            String url = "jdbc:postgresql://localhost:5432/";
            String dbName = "pharmacie";
            String user = "postgres";
            //String userName = "root";
            String password = "root";
            try {
                conn = DriverManager.getConnection(url+ dbName, user, password);
                System.out.println("Connected to the PostgreSQL server successfully.");
            } catch (SQLException e) {
                throw e;
            }
        }
        return conn;
    }
    
    //jdbc:mariadb:User=myUser;Password=myPassword;Database=NorthWind;Server=myServer;Port=3306
    public Connection openConnectionMariaDB() throws Exception {
        if (conn == null) {
            String url = "jdbc:mariadb://localhost:3307/";
            String dbName = "web_dynamic";
            String user = "root";
            String password = "root";
            try {
                conn = DriverManager.getConnection(url+ dbName, user, password);
                System.out.println("Connected to the mariaDB server successfully.");
            } catch (SQLException e) {
                throw e;
            }
        }
        return conn;
    }
    
    public void closeConnection(Connection con) throws SQLException {
        if(con !=null)
            try {
                con.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void closeWorkSpace(Statement stmt, Connection con) throws SQLException {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public void closeWorkSpace(PreparedStatement stmt, ResultSet res, Connection con) throws SQLException {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (res != null) {
                res.close();
            }
            if (con != null) {
                con.close();
            }

        } catch (SQLException e) {
            throw e;
        }
    }

    public void closeWorkSpace(Statement stmt, ResultSet res, Connection con) throws SQLException {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (res != null) {
                res.close();
            }
            if (con != null) {
                con.close();
            }

        } catch (SQLException e) {
            throw e;
        }
    }
}
