package net.java.inventoryapp.test;

import net.java.inventoryapp.dao.InventoryDao;
import net.java.inventoryapp.dao.UserDao;
import net.java.inventoryapp.model.InventoryItem;
import net.java.inventoryapp.model.LoginBean;
import net.java.inventoryapp.model.User;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Tester {
	
	public static void main(String args[]) throws SQLException {
		UserDao agnivo = new UserDao();
		User user = new User();
		user.setFirstName("agnivo");
		user.setLastName("neogi");
		user.setPassword("1234");
		user.setUsername("agnivon");
		//agnivo.registerUser(user);
		
		LoginBean bean = new LoginBean();
		bean.setUsername(user.getUsername());
		bean.setPassword(user.getPassword());
		System.out.println(agnivo.validate(bean));
		
		LocalDateTime  localTimeStamp = LocalDateTime.now();
		InventoryItem InvItem = new InventoryItem(1, "a", "agnivon", localTimeStamp, "add");
		
		InventoryDao InvDao = new InventoryDao();
		InvDao.insertItem(InvItem);
		
		// Insert Test
		// inventoryDao.insertItem(item);
		
		// Select Test
		
		//InventoryItem selctedItem = inventoryDao.selectItem(2);
		//System.out.println(selctedItem);
		
		// Select All Test
		
		//List<InventoryItem> allItems = inventoryDao.selectAllItems();
		//System.out.println(allItems);
		
		// Delete Test
		//System.out.println(inventoryDao.deleteItem(2));
		
		// StoreDao Tests
		
		//StoreDao storeDao = new StoreDao();
		
		// Update Count Test
		//storeDao.updateCount(4, 1);
		
		 // Select All Test
		 // List<StoreItem> allItems = storeDao.selectAllItems();
		 // System.out.println(allItems);
		
		
	}
}
