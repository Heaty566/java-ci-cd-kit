
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ComputerDAO;
import dtos.ComputerDTO;
import dtos.ComputerErrorObject;
import dtos.RoomDTO;


@WebServlet(name = "CreateServlet", urlPatterns = { "/CreateServlet" })
public class CreateServlet extends HttpServlet {

	private static final String SUCCESS = "LoadListComputerServlet";
	private static final String ERROR = "error.jsp";
	private static final String INVALID = "CreateFormServlet";

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String  url = ERROR;
		try {
			ComputerDAO dao = new ComputerDAO();
			System.out.println("hello");
			String id = request.getParameter("txtID");
			String cpu = request.getParameter("txtCPU");
			String vga = request.getParameter("txtHardDisk");
			String ram = request.getParameter("txtRAM");
			String hardDisk = request.getParameter("txtVGA");
			String monitor = request.getParameter("txtMonitor");
			String room = request.getParameter("cboRoom");
			boolean valid = true;
			ComputerErrorObject errorObj = new ComputerErrorObject();

			if (id.trim().isEmpty()) {
				errorObj.setIdError("ID is not support be empty");
				valid = false;
			}
			if (cpu.trim().isEmpty()) {
				errorObj.setCpuError("ID is not support be empty");
				valid = false;
			}
			if (vga.trim().isEmpty()) {
				errorObj.setVgaError("ID is not support be empty");
				valid = false;
			}
			if (ram.trim().isEmpty()) {
				errorObj.setRamError("ID is not support be empty");
				valid = false;
			}
			if (hardDisk.trim().isEmpty()) {
				errorObj.setHardDiskError("ID is not support be empty");
				valid = false;
			}

			if (monitor.trim().isEmpty()) {
				errorObj.setMonitorError("ID is not support be empty");
				valid = false;
			}

			if (dao.getComputerByID(id) != null) {
				errorObj.setIdError("ID is not existed");
				valid = false;
			}

			RoomDTO roomToDB = new RoomDTO(room.split("-")[0].trim(), room.split("-")[1].trim(), "", 0);
			ComputerDTO computerObj = new ComputerDTO(id, cpu, hardDisk, ram, vga, monitor, roomToDB);

			if (valid) {
				if (dao.insert(computerObj)) {
					url = SUCCESS;
				} else {
					request.setAttribute("errMessage", "Insert failed");
				}
			} else {
				url = INVALID;
				request.setAttribute("INVALID", errorObj);
				request.setAttribute("computerObj", computerObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
