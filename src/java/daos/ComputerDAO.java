package daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dtos.ComputerDTO;
import dtos.RoomDTO;
import utils.DBContext;

public class ComputerDAO implements Serializable {

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

	public List<ComputerDTO> getAllComputer() throws Exception {

		List<ComputerDTO> result = null;
		try {
			String sql = "SELECT ComputerID,CPU,HardDisk ,RAM ,VGA ,Monitor  ,RoomID FROM ComputerTBL";
			DBContext db = new DBContext();
			conn = db.getConnection();
			preStm = conn.prepareStatement(sql);
			result = new ArrayList();
			RoomDAO dao = new RoomDAO();
			
			rs = preStm.executeQuery();
			while (rs.next()) {
				System.out.println("123");
				String id = rs.getString("ComputerID");
				String cpu = rs.getString("CPU");
				String hardDisk = rs.getString("HardDisk");
				String ram = rs.getString("RAM");
				String vga = rs.getString("VGA");
				String monitor = rs.getString("Monitor");
				String roomID = rs.getString("RoomID");
				RoomDTO room = dao.getRoomByID(roomID);
				ComputerDTO computer = new ComputerDTO(id, cpu, hardDisk, ram, vga, monitor, room);
				result.add(computer);
			}
		} finally {
			this.closeConnection();
		}

		return result;
	}

	public boolean delete(String id) throws Exception {

		boolean check = false;

		try {
			String sql = "Delete From ComputerTBL Where ComputerID = ?";
			DBContext db = new DBContext();
			conn = db.getConnection();
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, id);
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}

		return check;

	}

	public boolean insert(ComputerDTO input) throws Exception {

		boolean check = false;

		try {
			String sql = "INSERT INTO ComputerTBL (ComputerID,CPU,HardDisk ,RAM ,VGA ,Monitor  ,RoomID) VALUES(?,?,?,?,?,?,?) ";
			DBContext db = new DBContext();
			conn = db.getConnection();
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, input.getId());
			preStm.setString(2, input.getCpu());
			preStm.setString(3, input.getHardDisk());
			preStm.setString(4, input.getRam());
			preStm.setString(5, input.getVga());
			preStm.setString(6, input.getMonitor());
			preStm.setString(7, input.getRoom().getId());

			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}

		return check;

	}

	public ComputerDTO getComputerByID(String id) throws Exception {

		ComputerDTO result = null;
		try {
			String sql = "SELECT ComputerID,CPU,HardDisk ,RAM ,VGA ,Monitor  ,RoomID FROM ComputerTBL WHERE ComputerID=?";
			DBContext db = new DBContext();
			conn = db.getConnection();
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, id);
			rs = preStm.executeQuery();
			RoomDAO dao = new RoomDAO();
			if (rs.next()) {
				String cpu = rs.getString("CPU");
				String hardDisk = rs.getString("HardDisk");
				String ram = rs.getString("RAM");
				String vga = rs.getString("VGA");
				String monitor = rs.getString("Monitor");
				String roomID = rs.getString("RoomID");
				RoomDTO room = dao.getRoomByID(roomID);
				result = new ComputerDTO(id, cpu, hardDisk, ram, vga, monitor, room);

			}
		} finally {
			this.closeConnection();
		}

		return result;
	}
}
