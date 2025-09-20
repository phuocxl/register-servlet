package org.example;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    public UserDaoImpl() {
        createUsersTableIfNotExists();
    }

    // Tạo bảng Users
    private void createUsersTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS Users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "email VARCHAR(255),"
                + "username VARCHAR(255),"
                + "fullname VARCHAR(255),"
                + "password VARCHAR(255),"
                + "avatar VARCHAR(255),"
                + "roleid INT,"
                + "phone VARCHAR(20),"
                + "createddate TIMESTAMP"
                + ")";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Users table created (if not exists)");
        } catch (SQLException e) {
            System.err.println("Error creating Users table");
            e.printStackTrace();
        }
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO Users(email, username, fullname, password, avatar, roleid, phone, createddate) "
                + "VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassWord());
            ps.setString(5, user.getAvatar());
            ps.setInt(6, user.getRoleid());
            ps.setString(7, user.getPhone());

            // Chuyển java.util.Date sang java.sql.Date
            if (user.getCreatedDate() != null) {
                ps.setDate(8, new java.sql.Date(user.getCreatedDate().getTime()));
            } else {
                ps.setDate(8, null);
            }

            ps.executeUpdate();
            System.out.println("User inserted successfully!");

        } catch (SQLException e) {
            System.err.println("Error inserting user into H2 DB");
            e.printStackTrace();
        }
    }


    @Override
    public boolean checkExistEmail(String email) {
        boolean duplicate = false;
        String query = "select * from [user] where email = ?";
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
            ps.close();
            conn.close();
        } catch (Exception ex) {}
        return duplicate;

    }

    @Override
    public boolean checkExistUsername(String username) {
        boolean duplicate = false;
        String query = "select * from [User] where username = ?";
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
            ps.close();
            conn.close();
        } catch (Exception ex) {}
        return duplicate;

    }

    @Override
    public boolean checkExistPhone(String phone) {
        return false;
    }
}
