package org.example;

import java.sql.*;

public class DBWorker {
    private Connection conn;
    private Statement statement;
    public DBWorker() {

        connectionBDSelection();
    }

    private void connectionBDSelection() {

        String userName = "postgres";
        String password = "postgres";
        String url = "jdbc:postgresql://localhost:5432/library2";
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        dbConnector(userName, password, url);
    }
    private void dbConnector(String userName, String password, String dbUrl)
    {
        try {
            conn = DriverManager.getConnection(dbUrl, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("connection establishment");
    }
    public String getAll(String query) {
        StringBuilder lineres = new StringBuilder();
        ResultSetMetaData rsmd = null;
        try {
            ResultSet rs = null;
            rs = statement.executeQuery(query);
            rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    lineres.append(rs.getString(i) + " | "); //
                }
                lineres.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineres.toString();
    }

    public String getById(String query, int id) {
        StringBuilder lineres = new StringBuilder();
        ResultSetMetaData rsmd = null;
        try {
            ResultSet rs = null;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();

            rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    lineres.append(rs.getString(i) + " | "); //
                }
                lineres.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineres.toString();
    }

    public void closer() {
        try {
            conn.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}