package net.java.inventoryapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import net.java.inventoryapp.model.InventoryItem;
import net.java.inventoryapp.utils.JDBCUtils;

public class InventoryDao {
	private static final String INSERT_ITEM_SQL = "INSERT INTO inventory_item"
			+ "  ( item, username, timeStamp, action) VALUES " + " ( ?, ?, ?, ?);";
	private static final String SELECT_ALL_ITEMS = "select * from inventory_item";


	public void insertItem(InventoryItem item) throws SQLException {
		System.out.println(INSERT_ITEM_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM_SQL)) {
			preparedStatement.setString(1, item.getItem());
			preparedStatement.setString(2, item.getUsername());
			preparedStatement.setTimestamp(3, JDBCUtils.getSQLTimestamp(item.getTimeStamp()));
			preparedStatement.setString(4, item.getAction());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
	}

	public List<InventoryItem> selectAllItems() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<InventoryItem> items = new ArrayList<>();

		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long Id = rs.getLong("id");
				String Item = rs.getString("item");
				String username = rs.getString("username");
				LocalDateTime timeStamp = rs.getTimestamp("timeStamp").toLocalDateTime();
				String action = rs.getString("action");
				items.add( new InventoryItem(Id, Item, username, timeStamp, action));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return items;
	}

}
