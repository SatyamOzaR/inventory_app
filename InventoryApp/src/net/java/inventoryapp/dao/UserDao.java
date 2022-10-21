package net.java.inventoryapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.java.inventoryapp.model.User;
import net.java.inventoryapp.utils.JDBCUtils;
import net.java.inventoryapp.model.LoginBean;

public class UserDao {

	public int registerUser(User user)  {
		String INSERT_USERS_SQL = "INSERT INTO users"
				+ "  (first_name, last_name, username, password) VALUES "
				+ " (?, ?, ?, ?);";

		int result = 0;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getPassword());

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return result;
	}
	
	public boolean validate(LoginBean loginBean) {
		boolean status = false;
		
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from users where username = ? and password = ? ")) {
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return status;
	}
	
}
