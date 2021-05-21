/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Auth;
import DAO.PhoneDAO;
import DTO.Mobile;
import DTO.User;
import Utils.Helper;

/**
 *
 * @author heaty566
 */
@WebServlet(name = "ServletController", urlPatterns = { "/ServletController" })
public class ServletController extends HttpServlet {

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
		response.setContentType("text/html;charset=UTF-8");
		String action = (String) request.getParameter("action");
		String loginPage = "login.jsp";
		String registerPage = "register.jsp";
		String listItemPage = "listItem.jsp";
		String addPhonePage = "addPhone.jsp";

		Auth auth = new Auth();
		PhoneDAO phoneDAO = new PhoneDAO();
		// if (action != null) {

		// RequestDispatcher rd = request.getRequestDispatcher(loginPage);
		// rd.forward(request, response);
		// return;
		// }
		if (action.equals("register")) {
			// ----------- Get Params
			String fullName = Helper.getStringParam(request, "fullName", "Full name", 1, 50);
			Integer password = Helper.getIntParams(request, "password", "Password", 1, Integer.MAX_VALUE);
			Integer confirmPassword = Helper.getIntParams(request, "confirmPassword", "Confirm Password", 1,
					Integer.MAX_VALUE);
			Integer role = Helper.getIntParams(request, "role", "Role", 0, 2);

			if (password != null && confirmPassword != null && fullName != null && role != null) {
				// ----------- Checking exist user
				User isExistUser = auth.getUserByUsername(fullName);
				if (isExistUser != null) {
					request.setAttribute("fullNameError", "Fullname is taken");
				} else if (confirmPassword != (password)) {
					// ----------- Checking correct password
					request.setAttribute("confirmPasswordError", "confirmPassword is not matches password");
				} else {
					// ------------ Add new user
					auth.addUser(fullName, password, role);
					RequestDispatcher rd = request.getRequestDispatcher(loginPage);
					rd.forward(request, response);
					return;
				}
			}

			RequestDispatcher rd = request.getRequestDispatcher(registerPage);
			rd.forward(request, response);
			return;
		}

		if (action.equals("login")) {
			// ----------- Get Params
			String fullName = Helper.getStringParam(request, "fullName", "Full name", 1, 50);
			Integer password = Helper.getIntParams(request, "password", "Password", 1, Integer.MAX_VALUE);

			if (password != null && fullName != null) {
				// ----------- Checking exist user
				User isExistUser = auth.getUserByUsername(fullName);

				if (isExistUser == null) {
					request.setAttribute("errorMessage", "Username or password are invalid");
				} else if (isExistUser.getPassword() != password) {
					// ----------- Checking correct password
					request.setAttribute("errorMessage", "Username or password are invalid");
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("fullName", isExistUser.getFullName());
					session.setAttribute("role", isExistUser.getRole());

					RequestDispatcher rd = request.getRequestDispatcher(listItemPage);
					rd.forward(request, response);
					return;
				}
			}

			RequestDispatcher rd = request.getRequestDispatcher(loginPage);
			rd.forward(request, response);
			return;

		}
		if (action.equals("addPhone")) {

			// ----------- Get Params
			Helper.protectedRouter(request, response, 1, loginPage);
			request.setAttribute("firstLoad", false);

			String mobileName = Helper.getStringParam(request, "mobileName", "Mobile's name", 1, 50);
			String description = Helper.getStringParam(request, "description", "Description", 1, 50);
			Integer quantity = Helper.getIntParams(request, "quantity", "Quantity", 1, 10);
			Integer yearOfProduction = Helper.getIntParams(request, "yearOfProduction", "Year of production", 1950,
					2100);
			Integer notSaleInt = Helper.getIntParams(request, "notSale", "Not Sale", 0, 1);
			Float price = Helper.getFloatParams(request, "price", "Price", 1, 99999999);

			if (mobileName != null && description != null && description != null && quantity != null
					&& yearOfProduction != null && notSaleInt != null && price != null) {
				boolean notSale = notSaleInt == 1;

				phoneDAO.addPhone(description, price, mobileName, quantity, yearOfProduction, notSale);
				RequestDispatcher rd = request.getRequestDispatcher(listItemPage);
				rd.forward(request, response);
				return;
			}

			RequestDispatcher rd = request.getRequestDispatcher(addPhonePage);
			rd.forward(request, response);
			return;
		}
		if (action.equals("listItem")) {
			// ----------- Get Params
			Helper.protectedRouter(request, response, 0, loginPage);
			request.setAttribute("firstLoad", false);

			Float minPrice = Helper.getFloatParams(request, "minPrice", "Min Price", 0, Float.MAX_VALUE);
			if (minPrice == null) {
				minPrice = (float) 0;
			}
			Float maxPrice = Helper.getFloatParams(request, "maxPrice", "Max Price", minPrice, Float.MAX_VALUE);
			if (maxPrice == null) {
				maxPrice = (float) 0;
			}
			ArrayList<Mobile> mobiles = phoneDAO.getPhoneByPrice(minPrice, maxPrice);
		
			request.setAttribute("mobiles", mobiles);
			RequestDispatcher rd = request.getRequestDispatcher(listItemPage);
			rd.forward(request, response);
			return;
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
