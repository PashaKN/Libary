package by.htp.library.dao.pool_impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import by.htp.library.dao.pool.PoolConnection;
import by.htp.library.dao.pool.Pool_Exception;

public class MySQL_Pool implements PoolConnection {

	static {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	private static final String CONNECTION_NAME = "jdbc:mysql://127.0.0.1/test?useSSL=false";
	private static final String LOGIN_CONNECTION = "root";
	private static final String PASSWORD_CONNECTION = "123456";
	private BlockingQueue<Connection> poolFree = new ArrayBlockingQueue<>(3);
	private BlockingQueue<Connection> poolBusy = new ArrayBlockingQueue<>(3);

	@Override
	public void initPool() throws Pool_Exception {

		for (int i = 0; i < 3; i++) {
			try {
				poolFree.put(DriverManager.getConnection(CONNECTION_NAME, LOGIN_CONNECTION, PASSWORD_CONNECTION));
			} catch (InterruptedException | SQLException e) {
				throw new Pool_Exception(e);
			}
		}
	}

	@Override
	public Connection getConnection() throws Pool_Exception {

		Connection connection = null;
		try {
			connection = poolFree.take();
			poolBusy.put(connection);
		} catch (InterruptedException e) {
			throw new Pool_Exception(e);
		}
		return connection;
	}

	@Override
	public void returnConnection(Connection connection) throws Pool_Exception {

		// Добавить сброс соединения в исходное состояние
		poolBusy.remove(connection);
		try {
			poolFree.put(connection);
		} catch (InterruptedException e) {
			throw new Pool_Exception(e);
		}

	}

	@Override
	public void destroyPool() {

		for (int i = 0; i < 3; i++) {
			try {
				Connection connection = poolFree.take();
				connection.close();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
