/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import java.util.Objects;

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
		String updatePhonePage = "updatePhone.jsp";
		String cartItemPage = "cartItem.jsp";
		String notFoundPage = "notFound.jsp";
		String errorPage = "error.jsp";

		Auth auth = new Auth();
		PhoneDAO phoneDAO = new PhoneDAO();

		if (action.equals("loginPage")) {
			response.sendRedirect(loginPage);
			return;
		}
		if (action.equals("registerPage")) {
			response.sendRedirect(registerPage);
			return;
		}

		if (action.equals("addPhonePage")) {
			response.sendRedirect(addPhonePage);
			return;
		}

		try {
			if (action.equals("register")) {
				String fullName = Helper.getStringParam(request, "fullName", "Full name", 1, 50);
				Integer password = Helper.getIntParams(request, "password", "Password", 1, Integer.MAX_VALUE);
				Integer confirmPassword = Helper.getIntParams(request, "confirmPassword", "Confirm Password", 1,
						Integer.MAX_VALUE);
				Integer role = Helper.getIntParams(request, "role", "Role", 0, 2);

				if (password != null && confirmPassword != null && fullName != null && role != null) {

					User isExistUser = auth.getUserByUsername(fullName);
					if (isExistUser != null) {
						request.setAttribute("fullNameError", "Fullname is taken");
					} else if (!Objects.equals(confirmPassword, password)) {

						request.setAttribute("confirmPasswordError", "confirmPassword is not matches password");
					} else {

						auth.addUser(fullName, password, role);
						RequestDispatcher rd = request.getRequestDispatcher(loginPage);
						rd.forward(request, response);
						return;
					}
				}

				RequestDispatcher rd = request.getRequestDispatcher(registerPage);
				rd.forward(request, response);

			} else if (action.equals("login")) {
				String fullName = Helper.getStringParam(request, "fullName", "Full name", 1, 50);
				Integer password = Helper.getIntParams(request, "password", "Password", 1, Integer.MAX_VALUE);

				if (password != null && fullName != null) {

					User isExistUser = auth.getUserByUsername(fullName);

					if (isExistUser == null) {
						request.setAttribute("errorMessage", "Username or password are invalid");
					} else if (isExistUser.getPassword() != password) {
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

			} else

			if (Helper.protectedRouter(request, response, 0, loginPage)) {
				if (action.equals("listItemPage")) {

					request.setAttribute("firstLoad", false);

					Float minPrice = Helper.getFloatParams(request, "minPrice", "Min Price", 0, Float.MAX_VALUE, 0);
					Float maxPrice = Helper.getFloatParams(request, "maxPrice", "Max Price", minPrice, Float.MAX_VALUE,
							9999999);
					String mobileId = Helper.getStringParam(request, "mobileId", "Mobile's id", 0, 50, "");
					String mobileName = Helper.getStringParam(request, "mobileName", "Mobile's id", 0, 50, "");

					ArrayList<Mobile> mobiles = phoneDAO.getPhones(minPrice, maxPrice, mobileId, mobileName);
					request.setAttribute("mobiles", mobiles);

					RequestDispatcher rd = request.getRequestDispatcher(listItemPage);
					rd.forward(request, response);

				}
				if (action.equals("addCartItem")) {

					String mobileId = Helper.getStringParam(request, "mobileId", "Mobile's name", 1, 50);
					Mobile mobile = phoneDAO.getOnePhone(mobileId);
					if (mobileId != null && mobile != null && mobile.isNotSale()) {
						HttpSession session = request.getSession(false);

						List<String> cartItems = (List<String>) Helper.getSessionAttribute(request, "cart",
								new ArrayList());
						cartItems.add(mobileId);
						session.setAttribute("cart", cartItems);

					}

					RequestDispatcher rd = request.getRequestDispatcher(listItemPage);
					rd.forward(request, response);

				}
				if (action.equals("deleteCartPhone") && Helper.protectedRouter(request, response, 0, loginPage)) {
					// ----------- Get Params

					String mobileId = Helper.getStringParam(request, "mobileId", "Mobile's name", 1, 50);
					if (mobileId != null && phoneDAO.getOnePhone(mobileId) != null) {
						HttpSession session = request.getSession(false);
						List<String> cartItems = (List<String>) Helper.getSessionAttribute(request, "cart",
								new ArrayList());
						cartItems.remove(mobileId);
						session.setAttribute("cart", cartItems);
					}

					response.sendRedirect("/Test/ServletController?action=cartItemPage");

				}
				if (action.equals("cartItemPage")) {
					ArrayList<Mobile> myCart = new ArrayList();
					List<String> cartItems = (List<String>) Helper.getSessionAttribute(request, "cart",
							new ArrayList());
					Float total = 0f;
					for (int i = 0; i < cartItems.size(); i++) {
						Mobile mobile = phoneDAO.getOnePhone(cartItems.get(i));
						if (mobile != null) {
							myCart.add(mobile);
							total += mobile.getPrice();
						}
					}

					request.setAttribute("mobiles", myCart);
					request.setAttribute("total", total);
					RequestDispatcher rd = request.getRequestDispatcher(cartItemPage);
					rd.forward(request, response);

				} else if (action.equals("logout") && Helper.protectedRouter(request, response, 0, loginPage)) {
					HttpSession session = request.getSession();
					session.invalidate();
					RequestDispatcher rd = request.getRequestDispatcher(loginPage);
					rd.forward(request, response);
				}
			} else if (Helper.protectedRouter(request, response, 1, loginPage)) {
				if (action.equals("addPhone")) {

					String mobileName = Helper.getStringParam(request, "mobileName", "Mobile's name", 1, 50);
					String description = Helper.getStringParam(request, "description", "Description", 1, 50);
					Integer quantity = Helper.getIntParams(request, "quantity", "Quantity", 1, 10);
					Integer yearOfProduction = Helper.getIntParams(request, "yearOfProduction", "Year of production",
							1950, 2100);
					Integer notSaleInt = Helper.getIntParams(request, "notSale", "Not Sale", 0, 1);
					Float price = Helper.getFloatParams(request, "price", "Price", 1, 99999999);

					if (mobileName != null && description != null && quantity != null && yearOfProduction != null
							&& notSaleInt != null && price != null) {
						boolean notSale = notSaleInt == 1;

						phoneDAO.addPhone(description, price, mobileName, quantity, yearOfProduction, notSale);
						response.sendRedirect(listItemPage);
						return;
					}

					RequestDispatcher rd = request.getRequestDispatcher(addPhonePage);
					rd.forward(request, response);

				}
				if (action.equals("updatePhonePage")) {

					String mobileId = Helper.getStringParam(request, "mobileId", "Mobile's name", 1, 50);
					Mobile mobile = phoneDAO.getOnePhone(mobileId);
					if (mobileId != null && mobile != null) {
						request.setAttribute("mobile", mobile);
						RequestDispatcher rd = request.getRequestDispatcher(updatePhonePage);
						rd.forward(request, response);
						return;

					}

					RequestDispatcher rd = request.getRequestDispatcher(listItemPage);
					rd.forward(request, response);

				}
				if (action.equals("updatePhone")) {

					String mobileId = Helper.getStringParam(request, "mobileId", "Mobile's name", 1, 50);
					String description = Helper.getStringParam(request, "description", "Description", 1, 50);
					Integer quantity = Helper.getIntParams(request, "quantity", "Quantity", 1, 10);
					Integer notSaleInt = Helper.getIntParams(request, "notSale", "Not Sale", 0, 1);
					Float price = Helper.getFloatParams(request, "price", "Price", 1, 99999999);

					if (mobileId != null) {
						Mobile mobile = phoneDAO.getOnePhone(mobileId);
						if (mobile != null) {
							request.setAttribute("mobile", mobile);
						}
					}
					if (mobileId != null && description != null && quantity != null && notSaleInt != null
							&& price != null) {
						boolean notSale = notSaleInt == 1;

						phoneDAO.updateOnePhone(mobileId, description, price, quantity, notSale);
						RequestDispatcher rd = request.getRequestDispatcher(listItemPage);
						rd.forward(request, response);
						return;
					}

					RequestDispatcher rd = request.getRequestDispatcher(updatePhonePage);
					rd.forward(request, response);

				}
				if (action.equals("deletePhone")) {

					String mobileId = Helper.getStringParam(request, "mobileId", "Mobile's name", 1, 50);

					if (mobileId != null) {
						phoneDAO.deleteOne(mobileId);
					}
					RequestDispatcher rd = request.getRequestDispatcher(listItemPage);
					rd.forward(request, response);

				}
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(notFoundPage);
				rd.forward(request, response);
			}

		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher(errorPage);
			rd.forward(request, response);
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
