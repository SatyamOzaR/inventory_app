package net.java.inventoryapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import net.java.inventoryapp.model.StoreItem;
import net.java.inventoryapp.utils.JDBCUtils;

public class StoreDao {
	
	private static final String UPDATE_INCREMENT_COUNT = "update store_item set itemCount = itemCount + 1 where itemName = ?";
	private static final String UPDATE_DECREMENT_COUNT = "update store_item set itemCount = itemCount - 1 where itemName = ? and itemCount > 0";
	private static final String SELECT_ALL_ITEMS = "select * from store_item";
	private static final String INSERT_ITEM_SQL = "INSERT INTO store_item (itemName, itemCount) VALUES (?, 1);";
	private static final String DELETE_ITEM_SQL = "DELETE FROM store_item WHERE itemName = ?;";
	
	public void updateCount(String itemName, boolean increment) throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (Connection connection = JDBCUtils.getConnection()){
			if(increment) {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INCREMENT_COUNT);
				preparedStatement.setString(1, itemName);
				preparedStatement.executeUpdate();
			} else {
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DECREMENT_COUNT);
				preparedStatement.setString(1, itemName);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
	}
	
	public List<StoreItem> selectAllItems() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<StoreItem> items = new ArrayList<>();

		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long Id = rs.getLong("itemId");
				String Item = rs.getString("itemName");
				long Count = rs.getLong("itemCount");
				StoreItem item = new StoreItem();
				item.setItemId(Id);
				item.setItemName(Item);
				item.setItemCount(Count);
				items.add(item);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return items;
	}
	
	public void AddItem(String itemName)  {
		try (Connection connection = JDBCUtils.getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM_SQL)) {
			preparedStatement.setString(1, itemName );
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
	}
	
	public void DeleteItem(String itemName)  {
		try (Connection connection = JDBCUtils.getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEM_SQL)) {
			preparedStatement.setString(1, itemName );
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
	}
	
}
