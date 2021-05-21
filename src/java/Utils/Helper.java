/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author heaty566
 */
public class Helper {

    public static String getStringParam(HttpServletRequest request, String field, String label, int min, int max) {
        String value = (String) request.getParameter(field);
        if (value == null) {
            request.setAttribute(field + "Error", label + " is required");
            return null;
        }
        if (value.length() > max) {
            request.setAttribute(field + "Error", label + " is less than " + max + " character(s)");
            return null;
        }
        if (value.length() < min) {
            request.setAttribute(field + "Error", label + " is greater than " + min + " character(s)");
            return null;
        }

        return value;
    }

    public static Integer getIntParams(HttpServletRequest request, String field, String label, int min, int max) {

        String value = (String) request.getParameter(field);
        Integer realValue;
        if (value == null) {
            request.setAttribute(field + "Error", label + " is required");
            return null;
        }
        try {
            realValue = Integer.parseInt(value);
        } catch (Exception e) {
            request.setAttribute(field + "Error", label + " must be a number");
            return null;
        }
        if (realValue > max) {
            request.setAttribute(field + "Error", label + " is less than " + max);
            return null;
        }
        if (realValue < min) {
            request.setAttribute(field + "Error", label + " is greater than " + min);
            return null;
        }

        return realValue;
    }

    public static Float getFloatParams(HttpServletRequest request, String field, String label, float min, float max) {

        String value = (String) request.getParameter(field);
        Float realValue;
        if (value == null) {
            request.setAttribute(field + "Error", label + " is required");
            return null;
        }
        try {
            realValue = Float.parseFloat(value);
        } catch (Exception e) {
            request.setAttribute(field + "Error", label + " must be a number");
            return null;
        }
        if (realValue > max) {
            request.setAttribute(field + "Error", label + " is less than " + max);
            return null;
        }
        if (realValue < min) {
            request.setAttribute(field + "Error", label + " is greater than " + min);
            return null;
        }

        return realValue;
    }

    public static boolean protectedRouter(HttpServletRequest request, HttpServletResponse response, int role,
            String page) {

        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("fullName");
        Integer roleR = (Integer) session.getAttribute("role");
        RequestDispatcher rd = request.getRequestDispatcher(page);
        try {
            if (username == null || roleR == null || roleR < role) {
                request.setAttribute("errorMessage", "action is not allow, please login first");
                rd.forward(request, response);
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
