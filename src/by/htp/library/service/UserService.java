package by.htp.library.service;

import by.htp.library.bean.User;

public interface UserService {
	public User logIn(String login, String Password) throws ServiceException;

	public void logOut();

	public User registration(String login, String password) throws ServiceException;

	public User changePassword(String login,String password, String password1, String password2) throws ServiceException;
}
