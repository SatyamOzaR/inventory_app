package net.java.inventoryapp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.inventoryapp.dao.InventoryDao;
import net.java.inventoryapp.dao.StoreDao;
import net.java.inventoryapp.model.InventoryItem;
import net.java.inventoryapp.model.StoreItem;


@WebServlet("/")
public class InventoryController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private InventoryDao inventoryDao;
	private StoreDao storeDao;
	
	public void init() {
		inventoryDao = new InventoryDao();
		storeDao = new StoreDao();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/items":
				listItem(request, response);
				break;
			case "/logs":
				listInventory(request, response);
				break;
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listInventory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<InventoryItem> listInventory = inventoryDao.selectAllItems();
		request.setAttribute("listInventory", listInventory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("inventory/inventory_list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void listItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<StoreItem> listItem = storeDao.selectAllItems();
		request.setAttribute("listItem", listItem);
		RequestDispatcher dispatcher = request.getRequestDispatcher("inventory/inventory_list.jsp");
		dispatcher.forward(request, response);
	}

}
