package net.java.inventoryapp.web;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.java.inventoryapp.dao.UserDao;
import net.java.inventoryapp.model.LoginBean;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	public static String currentUser = "";

	public void init() {
		userDao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		authenticate(request, response);
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		if (userDao.validate(loginBean)) {
			currentUser = loginBean.getUsername();
			RequestDispatcher dispatcher = request.getRequestDispatcher("inventory/inventory_list.jsp");
			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", username);
			response.sendRedirect("login/login.jsp");
		}

	}
}
