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

@WebServlet("/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoreDao storeDao;
	private InventoryDao inventoryDao;

	public void init() {
		storeDao = new StoreDao();
		inventoryDao = new InventoryDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		deleteItem(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("inventory/item_store.jsp");
	}

	private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String item = request.getParameter("item");
		
		try {
			// get all items
			List<StoreItem> listItem = storeDao.selectAllItems();
			int flag = 0;
			int count = 0;
			for(StoreItem i : listItem) {
				if(item.equalsIgnoreCase(i.getItemName())) {
					flag = 1;
					count = (int) i.getItemCount();
				}
			}
			// if item present
			if(flag == 1) {
				if(count > 1) {
					storeDao.updateCount(item, false);
				} else if(count == 1) {
					storeDao.DeleteItem(item);
				}
			}
			flag = 0;
			count = 0;
			InventoryItem InvItem = new InventoryItem(
					1, item, LoginController.currentUser, LocalDateTime.now(), "Deleted");
			inventoryDao.insertItem(InvItem);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("inventory/item_store.jsp");
		dispatcher.forward(request, response);
	}
}
