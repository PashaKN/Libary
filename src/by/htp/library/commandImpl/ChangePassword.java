package by.htp.library.commandImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.library.bean.User;
import by.htp.library.command.Command;
import by.htp.library.service.ServiceException;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;

public class ChangePassword implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

		User user = this.getParamFromSession(request);
		String login = user.getLogin();
		String password = user.getPassword();
		String password1 = request.getParameter("newPassword1");
		String password2 = request.getParameter("newPassword2");
		ServiceFactory fc = ServiceFactory.getInstance();
		UserService userService = fc.getUserService();
		user = userService.changePassword(login, password, password1, password2);
		if (user == null | !password.equals(request.getParameter("oldPassword"))) {
			request.setAttribute("Messege", "You passwords are different");
			try {
				request.getRequestDispatcher("WEB-INF/ChangePasswordPage.jsp").include(request, response);
			} catch (ServletException | IOException e) {
				try {
					response.sendRedirect("Eror.jsp");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			request.setAttribute("login", login);
			request.setAttribute("Messege", "You password successfull changed");
			try {
				request.getRequestDispatcher("WEB-INF/Hello.jsp").include(request, response);
			} catch (ServletException | IOException e) {
				try {
					response.sendRedirect("Eror.jsp");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private User getParamFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("Login");
		String password = (String) session.getAttribute("Password");
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		return user;
	}

}
