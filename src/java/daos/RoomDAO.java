
package daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dtos.RoomDTO;
import utils.DBContext;


public class RoomDAO implements Serializable {

	private Connection conn;
	private PreparedStatement preStm;
	private ResultSet rs;

	private void closeConnection() throws Exception {
		if (rs != null) {
			rs.close();
		}

		if (preStm != null) {
			preStm.close();
		}

		if (conn != null) {

			conn.close();
		}
	}

	public RoomDTO getRoomByID(String id) throws Exception {

		RoomDTO result = null;
		try {
			String sql = "Select RoomName, Building, FloorNumber From RoomTBL Where RoomID = ?";
			DBContext db = new DBContext();
			conn = db.getConnection();
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, id);
			rs = preStm.executeQuery();
			if (rs.next()) {
				String name = rs.getString("RoomName");
				String building = rs.getString("building");
				int floor = rs.getInt("FloorNumber");
				result = new RoomDTO(id, name, building, floor);
			}
		} finally {
			this.closeConnection();
		}

		return result;
	}

	public List<RoomDTO> getAllRooms() throws Exception {

		List<RoomDTO> result = null;
		try {
			String sql = "Select RoomID ,RoomName, Building, FloorNumber From RoomTBL";
			DBContext db = new DBContext();
			conn = db.getConnection();
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<RoomDTO>();
			while (rs.next()) {
				String id = rs.getString("RoomID");
				String name = rs.getString("RoomName");
				String building = rs.getString("building");
				int floor = rs.getInt("FloorNumber");
				RoomDTO room = new RoomDTO(id, name, building, floor);

				result.add(room);

			}
		} finally {
			this.closeConnection();
		}

		return result;
	}

}
