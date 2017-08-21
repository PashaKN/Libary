package by.htp.library.dao;

import by.htp.library.bean.User;

public interface UserDAO {

	public User logIn(String login, String password) throws DAOException;

	public User registration(String login, String password) throws DAOException;
	
	public User changePassword(String login, String password, String newPassword)throws DAOException;

}
