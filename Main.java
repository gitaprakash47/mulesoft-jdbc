package Mulesoft;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

public class Main {

    String url = "jdbc:sqlite:Main.db";

    private Connection connect() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll() {
        String sql = "SELECT * FROM movies";
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                System.out.println(rs.getString("name") + "\t" + rs.getString("actor") + "\t"
                        + rs.getString("actress")
                        + "\t" + rs.getString("director") + "\t" + rs.getInt("yor"));

            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    public void insert(String name, String actor, String actress, String director, int yor) {
        String sql = "INSERT INTO movies VALUES(?,?,?,?,?)";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, actor);
            pstmt.setString(3, actress);
            pstmt.setString(4, director);
            pstmt.setInt(5, yor);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createtb() {
        String sql = "CREATE TABLE movies(\n" + "name varchar(20),\n" + "actor varchar(20),\n"
                + "actress varchar(20),\n" + "director varchar(20),\n" + "yor integer\n" + ");";
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String args[]) {
        Main app = new Main();
        app.createtb();
        app.insert("vikram", "Kamal", "gayathri", "Lokesh", 2021);
        app.selectAll();
    }
}
