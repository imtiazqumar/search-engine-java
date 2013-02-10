/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.sql.*;

/**
 *
 * @author M Imtiaz
 */
public class DB {

    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "db_searchengine";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "";
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public DB() {
        try {
            Class.forName(driver).newInstance();
            connect = DriverManager.getConnection(url + dbName, userName, password);
        } catch (Exception ex) {
            System.out.println("Unable to connect to database");
        }


    }

    void InsertURL(String Url, int Alias) {
        String Table = "uns";
        try {
            statement = connect.createStatement();
            statement.execute("Insert into " + Table + " values(NULL,'" + Url + "','" + Alias + "')");
        } catch (Exception ex) {
            System.out.println("Unable to Insert Records");
        }


    }

    void InsertToken(String Url, String Token) {
        String Table = "crawlresults";
        try {
            statement = connect.createStatement();
            statement.execute("Insert into " + Table + " values(NULL,'" + Url + "','" + Token + "')");
        } catch (Exception ex) {
            System.out.println("Unable to Insert Records");
        }


    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
        }
    }
}
