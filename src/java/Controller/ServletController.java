/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
		Auth auth = new Auth();
		PhoneDAO phoneDAO = new PhoneDAO();

		if (action.equals("loginPage")) {
			response.sendRedirect(loginPage);
		}
		if (action.equals("registerPage")) {
			response.sendRedirect(registerPage);
		}
		// if (action.equals("listItemPage")) {
		// response.sendRedirect(listItemPage);
		// }

		if (action.equals("addPhonePage")) {
			response.sendRedirect(addPhonePage);
		}

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

			Helper.protectedRouter(request, response, 1, loginPage);

			// ----------- Get Params
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
				response.sendRedirect(listItemPage);
				return;
			}

			RequestDispatcher rd = request.getRequestDispatcher(addPhonePage);
			rd.forward(request, response);
			return;
		}

		if (action.equals("updatePhonePage")) {

			Helper.protectedRouter(request, response, 1, loginPage);

			// ----------- Get Params
			String mobileId = Helper.getStringParam(request, "mobileId", "Mobile's name", 1, 50);
			if (mobileId != null) {
				Mobile mobile = phoneDAO.getOnePhone(mobileId);
				if (mobile != null) {
					request.setAttribute("mobile", mobile);
					RequestDispatcher rd = request.getRequestDispatcher(updatePhonePage);
					rd.forward(request, response);
					return;
				}
			}

			RequestDispatcher rd = request.getRequestDispatcher(listItemPage);
			rd.forward(request, response);
			return;
		}

		if (action.equals("updatePhone")) {
			Helper.protectedRouter(request, response, 1, loginPage);

			// ----------- Get Params
			String mobileId = Helper.getStringParam(request, "mobileId", "Mobile's name", 1, 50);
			String description = Helper.getStringParam(request, "description", "Description", 1, 50);
			System.out.println(description);
			Integer quantity = Helper.getIntParams(request, "quantity", "Quantity", 1, 10);
			Integer notSaleInt = Helper.getIntParams(request, "notSale", "Not Sale", 0, 1);
			Float price = Helper.getFloatParams(request, "price", "Price", 1, 99999999);

			if (mobileId != null) {
				Mobile mobile = phoneDAO.getOnePhone(mobileId);
				if (mobile != null) {
					request.setAttribute("mobile", mobile);
				}
			}
			if (mobileId != null && description != null && description != null && quantity != null && notSaleInt != null
					&& price != null) {
				boolean notSale = notSaleInt == 1;

				phoneDAO.updateOnePhone(mobileId, description, price, quantity, notSale);
				RequestDispatcher rd = request.getRequestDispatcher(listItemPage);
				rd.forward(request, response);
				return;
			}

			RequestDispatcher rd = request.getRequestDispatcher(updatePhonePage);
			rd.forward(request, response);
			return;
		}
		if (action.equals("deletePhone")) {
			Helper.protectedRouter(request, response, 1, loginPage);

			// ----------- Get Params
			String mobileId = Helper.getStringParam(request, "mobileId", "Mobile's name", 1, 50);

			if (mobileId != null) {
				phoneDAO.deleteOne(mobileId);
			}
			RequestDispatcher rd = request.getRequestDispatcher(listItemPage);
			rd.forward(request, response);
			return;
		}

		if (action.equals("listItemPage")) {
			// ----------- Get Params
			request.setAttribute("firstLoad", false);
			Helper.protectedRouter(request, response, -1, loginPage);
			Float minPrice = Helper.getFloatParams(request, "minPrice", "Min Price", 0, Float.MAX_VALUE, 0);
			Float maxPrice = Helper.getFloatParams(request, "maxPrice", "Max Price", minPrice, Float.MAX_VALUE,
					9999999);
			String mobileId = Helper.getStringParam(request, "mobileId", "Mobile's id", 0, 50, "");
			String mobileName = Helper.getStringParam(request, "mobileName", "Mobile's id", 0, 50, "");

			ArrayList<Mobile> mobiles = phoneDAO.getPhones(minPrice, maxPrice, mobileId, mobileName);
			request.setAttribute("mobiles", mobiles);

			RequestDispatcher rd = request.getRequestDispatcher(listItemPage);
			rd.forward(request, response);
			return;
		}

		if (action.equals("addCartItem")) {
			// ----------- Get Params
			Helper.protectedRouter(request, response, -1, loginPage);

			String mobileId = Helper.getStringParam(request, "mobileId", "Mobile's name", 1, 50);
			if (mobileId != null && phoneDAO.getOnePhone(mobileId) != null) {
				HttpSession session = request.getSession(false);

				List<String> cartItems = (List<String>) session.getAttribute("cart");
				List<String> myCart;
				if (cartItems == null) {
					myCart = new ArrayList<String>();
				} else {
					myCart = cartItems;
				}

				myCart.add(mobileId);
				session.setAttribute("cart", myCart);

			}

			RequestDispatcher rd = request.getRequestDispatcher(listItemPage);
			rd.forward(request, response);
			return;
		}
		if (action.equals("deleteCartPhone")) {
			// ----------- Get Params
			Helper.protectedRouter(request, response, -1, loginPage);

			String mobileId = Helper.getStringParam(request, "mobileId", "Mobile's name", 1, 50);
			if (mobileId != null && phoneDAO.getOnePhone(mobileId) != null) {
				HttpSession session = request.getSession(false);

				List<String> cartItems = (List<String>) session.getAttribute("cart");
				List<String> myCart;
				if (cartItems == null) {
					myCart = new ArrayList<String>();
				} else {
					myCart = cartItems;
				}

				myCart.remove(mobileId);
				session.setAttribute("cart", myCart);

			}
			response.sendRedirect("/Test/ServletController?action=cartItemPage");
			return;
		}
		if (action.equals("cartItemPage")) {
			// ----------- Get Params
			Helper.protectedRouter(request, response, -1, loginPage);
			ArrayList<Mobile> myCart = new ArrayList<Mobile>();
			HttpSession session = request.getSession(false);
			List<String> cartItems = (List<String>) session.getAttribute("cart");
			Float total = 0f;
			if (cartItems != null) {

				for (int i = 0; i < cartItems.size(); i++) {
					Mobile mobile = phoneDAO.getOnePhone(cartItems.get(i));
					if (mobile != null) {
						myCart.add(mobile);
						total += mobile.getPrice();
					}
				}
				request.setAttribute("mobiles", myCart);
				request.setAttribute("total", total);
			}
			RequestDispatcher rd = request.getRequestDispatcher(cartItemPage);
			rd.forward(request, response);
			return;
		}

		if (action.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher(loginPage);
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
