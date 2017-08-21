package by.htp.library.dao.pool;

public class Pool_Exception extends Exception {

	private static final long serialVersionUID = 1L;

	public Pool_Exception() {
		super();
	}

	public Pool_Exception(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public Pool_Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public Pool_Exception(String message) {
		super(message);
	}

	public Pool_Exception(Throwable cause) {
		super(cause);
	}

}
