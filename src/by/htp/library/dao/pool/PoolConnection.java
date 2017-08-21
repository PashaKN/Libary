package by.htp.library.dao.pool;

import java.sql.Connection;

public interface PoolConnection {
	public void initPool() throws Pool_Exception;
	public Connection getConnection() throws Pool_Exception;
	public void returnConnection(Connection connection) throws Pool_Exception;
	public void destroyPool();
}
