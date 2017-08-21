package by.htp.library.service;

import by.htp.library.serviceImpl.UserServiceImpl;

public class ServiceFactory {
	private static ServiceFactory INSTANCE = new ServiceFactory();

	private ServiceFactory() {
	}

	private UserService userService = new UserServiceImpl();

	public UserService getUserService() {
		return userService;
	}

	public static ServiceFactory getInstance() {

		return INSTANCE;
	}
}
