package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.library.bean.User;
import by.htp.library.dao.DAOException;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.pool.PoolConnection;
import by.htp.library.dao.pool.PoolFactory;
import by.htp.library.dao.pool.Pool_Exception;

public class SQLUserDAO implements UserDAO {

	private static final String GET_USER_REQUEST = "SELECT id,Login,Password,Root From Users WHERE Login=(?) and Password = (?)";
	private static final String ADD_USER_IN_DB = "INSERT INTO test.users (login, password, root) VALUES ((?), (?), (?))";
	private static final String CHANGE_PASSWORD = "UPDATE test.users SET password=(?) WHERE id=(?)";

	@Override
	public User logIn(String login, String password) throws DAOException {
		User user = getUserFromDB(login, password);
		return user;
	}

	@Override
	public User registration(String login, String password) throws DAOException {
		User user = null;
		Connection connection = null;
		user = getUserFromDB(login, password);
		if (user == null) {
			PoolFactory poolFactory = PoolFactory.getInstance();
			PoolConnection pool = poolFactory.getPool();

			try {
				connection = pool.getConnection();
			} catch (Pool_Exception e1) {
				throw new DAOException(e1);
			}
			try {
				PreparedStatement ps = connection.prepareStatement(ADD_USER_IN_DB);
				ps.setString(1, login);
				ps.setString(2, password);
				ps.setString(3, "0");
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
			try {
				pool.returnConnection(connection);
			} catch (Pool_Exception e) {
				throw new DAOException(e);
			}
			user = getUserFromDB(login, password);
		} else {
			user = getUserFromDB(login, password);
			user.setMessege("You alredy registered on this site");
		}

		return user;
	}

	@Override
	public User changePassword(String login, String password, String newPassword) throws DAOException {
		User user = getUserFromDB(login, password);
		int id = user.getId();
		PoolFactory poolFactory = PoolFactory.getInstance();
		PoolConnection pool = poolFactory.getPool();
		Connection connection = null;
		try {
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(CHANGE_PASSWORD);
			ps.setString(1, newPassword);
			ps.setLong(2, id);
			int rs = ps.executeUpdate();
			if (rs == 0)
				return null;
			ps.close();
		} catch (Pool_Exception | SQLException e) {
			throw new DAOException(e);
		}
		try {
			pool.returnConnection(connection);
		} catch (Pool_Exception e) {
			// Записать в лог
		}
		return user;
	}

	private User getUserFromDB(String login, String password) throws DAOException {

		User user = null;
		PoolFactory poolFactory = PoolFactory.getInstance();
		PoolConnection pool = poolFactory.getPool();
		try {
			Connection connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(GET_USER_REQUEST);
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("Id"));
				user.setLogin(rs.getString("Login"));
				user.setPassword(rs.getString("password"));
				user.setRoot(rs.getBoolean("root"));
			}
			rs.close();
			ps.close();
			pool.returnConnection(connection);
		} catch (Pool_Exception | SQLException e) {
			throw new DAOException(e);
		}
		return user;
	}

}
