package net.java.inventoryapp.web;

import java.io.IOException;
import java.time.LocalDateTime;
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

@WebServlet("/store")
public class StoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoreDao storeDao;
	private InventoryDao inventoryDao;

	public void init() {
		storeDao = new StoreDao();
		inventoryDao = new InventoryDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		storeItem(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("inventory/item_store.jsp");
	}

	private void storeItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String item = request.getParameter("item");
		
		try {
			// get all items
			List<StoreItem> listItem = storeDao.selectAllItems();
			int flag = 0;
			for(StoreItem i : listItem) {
				if(item.equalsIgnoreCase(i.getItemName())) {
					flag = 1;
				}
			}
			// if item present
			if(flag == 1) {
				
				// true : add item
				storeDao.updateCount(item, true);
			} else {
				// false : remove item
				storeDao.AddItem(item);
			}
			flag = 0;
			
			InventoryItem InvItem = new InventoryItem(
					1, item, LoginController.currentUser, LocalDateTime.now(), "Added");
			inventoryDao.insertItem(InvItem);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("inventory/inventory_list.jsp");
		dispatcher.forward(request, response);
	}
}

