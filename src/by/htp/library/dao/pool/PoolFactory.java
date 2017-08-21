package by.htp.library.dao.pool;

import by.htp.library.dao.pool_impl.MySQL_Pool;

public class PoolFactory {
	private static PoolFactory instance = new PoolFactory();

	private PoolFactory() {
	}

	private PoolConnection poolConnection = new MySQL_Pool();

	public static PoolFactory getInstance() {
		return instance;
	}

	public PoolConnection getPool() {
		return poolConnection;
	}

}
