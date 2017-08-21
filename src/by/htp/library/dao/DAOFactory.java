package by.htp.library.dao;

import by.htp.library.dao.impl.SQLBookDAO;
import by.htp.library.dao.impl.SQLUserDAO;

public class DAOFactory {

	private static DAOFactory INSTANCE = new DAOFactory();

	private DAOFactory() {
	}

	private BookDAO bookDAO = new SQLBookDAO();
	private UserDAO userDAO = new SQLUserDAO();

	public BookDAO getBookDAO() {

		return bookDAO;
	}

	public UserDAO getUserDAO() {

		return userDAO;
	}

	public static DAOFactory getInstance() {

		return INSTANCE;
	}

}
