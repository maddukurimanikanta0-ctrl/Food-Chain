package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/foodchain";

    private static final String USER =
            "postgres";

    private static final String PASSWORD =
            "2007";

    public static Connection getConnection() {

        try {

            Class.forName(
                    "org.postgresql.Driver");

            Connection con =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD);

            System.out.println(
                    "DATABASE CONNECTED SUCCESSFULLY");

            return con;

        } catch(Exception e) {

            System.out.println(
                    "DATABASE CONNECTION FAILED");

            e.printStackTrace();

            return null;
        }
    }

    public static void main(String[] args) {

        getConnection();
    }
}