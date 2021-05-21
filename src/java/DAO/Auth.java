/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import DTO.User;
import Utils.Connector;

/**
 *
 * @author heaty566
 */
public class Auth {
    public boolean addUser(String fullName, Integer password, Integer role) {

        try {
            String userId = UUID.randomUUID().toString().substring(0, 7);
            Connection conn = Connector.getConnection();
            String sql = "INSERT INTO tbl_user (userId, fullName, password, role) VALUES(?,?,?,?)";
            PreparedStatement state = conn.prepareStatement(sql);
            state.setString(1, userId);
            state.setString(2, fullName);
            state.setInt(3, password);
            state.setInt(4, role);
            state.executeUpdate();
            state.close();

            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public User getUserByUsername(String fullName) {
        try {

            Connection conn = Connector.getConnection();
            String sql = "SELECT * FROM tbl_user WHERE fullName = ?";
            PreparedStatement state = conn.prepareStatement(sql);
            state.setString(1, fullName);
            ResultSet result = state.executeQuery();
            while (result.next()) {
                String un = result.getString("fullName");
                Integer password = result.getInt("password");
                Integer role = result.getInt("role");
                String userId = result.getString("userId");

                User user = new User(un, password, role, userId);
                state.close();
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

}
