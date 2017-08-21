package by.htp.library.serviceImpl;

import by.htp.library.bean.User;
import by.htp.library.dao.DAOException;
import by.htp.library.dao.DAOFactory;
import by.htp.library.dao.UserDAO;
import by.htp.library.service.ServiceException;
import by.htp.library.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User logIn(String login, String password) throws ServiceException {
		if (login == null | password == null) {
			return null;
		}
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		User user = null;
		try {
			user = userDAO.logIn(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public void logOut() {

	}

	@Override
	public User registration(String login, String password) throws ServiceException {
		User user = null;
		if (login != null | password != null) {
			DAOFactory fc = DAOFactory.getInstance();
			UserDAO userDAO = fc.getUserDAO();
			try {
				user = userDAO.registration(login, password);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return user;
	}

	@Override
	public User changePassword(String login, String password, String password1, String password2)
			throws ServiceException {
		User user = null;
		if (!password1.equals(password2)) {
			return null;
		} else {

			DAOFactory fc = DAOFactory.getInstance();
			UserDAO userDAO = fc.getUserDAO();
			try {
				user = userDAO.changePassword(login, password, password1);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return user;
	}

}
