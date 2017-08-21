package by.htp.library.commandImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.bean.User;
import by.htp.library.command.Command;
import by.htp.library.service.ServiceException;
import by.htp.library.service.ServiceFactory;
import by.htp.library.service.UserService;

public class Registration implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

		String login = request.getParameter("Login");
		String password = request.getParameter("Password1");
		String password2 = request.getParameter("Password2");
		if (!password.equals(password2)) {

			request.setAttribute("Messege", "You entred a wrong password");

			try {
				request.getRequestDispatcher("WEB-INF/Registration.jsp").include(request, response);
			} catch (ServletException | IOException e) {
				try {
					response.sendRedirect("Eror.jsp");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			ServiceFactory fc = ServiceFactory.getInstance();
			UserService userService = fc.getUserService();
			User user = userService.registration(login, password);
			request.setAttribute("login", user.getLogin());
			try {
				request.setAttribute("Messege", user.getMessege());
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

}
