/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import DTO.Mobile;
import Utils.Connector;

/**
 *
 * @author heaty566
 */
public class PhoneDAO {
	public boolean addPhone(String description, Float price, String mobileName, Integer quantity,
			Integer yearOfProduction, Boolean notSale) {

		try {
			String mobileId = UUID.randomUUID().toString().substring(0, 7);
			Connection conn = Connector.getConnection();
			String sql = "INSERT INTO tbl_mobile (mobileId, description, price, mobileName , quantity , yearOfProduction,notSale) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, mobileId);
			state.setString(2, description);
			state.setFloat(3, price);
			state.setString(4, mobileName);
			state.setInt(5, quantity);
			state.setInt(6, yearOfProduction);
			state.setBoolean(7, notSale);
			state.executeUpdate();
			state.close();

			return false;

		} catch (Exception e) {
			return false;
		}

	}

	public ArrayList<Mobile> getPhoneByPrice(Float minPrice, Float maxPrice) {
		ArrayList<Mobile> mobiles = new ArrayList<Mobile>();
		try {

			Connection conn = Connector.getConnection();
			String sql = "SELECT * FROM tbl_mobile WHERE price >= ? and price <= ? ";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setFloat(1, minPrice);
			state.setFloat(2, maxPrice);
			ResultSet result = state.executeQuery();
			while (result.next()) {
				String mobileId = result.getString("mobileId");
				String description = result.getString("description");
				Float price = result.getFloat("price");
				String mobileName = result.getString("mobileName");
				Integer quantity = result.getInt("quantity");
				Integer yearOfProduction = result.getInt("yearOfProduction");
				Boolean notSale = result.getBoolean("notSale");

				Mobile mobile = new Mobile(mobileId, description, price, mobileName, quantity, yearOfProduction,
						notSale);
				mobiles.add(mobile);
			}

			state.close();
			return mobiles;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
