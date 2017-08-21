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

public class LogIn implements Command {
	private static final String MASSAGE_INVALID_LOGIN = "Invalid logi or Password";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

		String login = null;
		String password = null;

		login = request.getParameter("Login");
		password = request.getParameter("Password");

		ServiceFactory sf = ServiceFactory.getInstance();
		UserService userService = sf.getUserService();
		User user = userService.logIn(login, password);

		
		HttpSession session = request.getSession();
		
		if (user == null) {

			try {
				session.setAttribute("isEntered", false);
				request.getRequestDispatcher("\\index.jsp").include(request, response);
				response.getWriter().println(MASSAGE_INVALID_LOGIN);
			} catch (IOException | ServletException e) {
				try {
					response.sendRedirect("Eror.jsp");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} 
		} else {

			session.setAttribute("isEntered", true);
			session.setAttribute("Login", user.getLogin());
			session.setAttribute("Password", user.getPassword());
			request.setAttribute("login", login);

			try {
				request.getRequestDispatcher("WEB-INF/Hello.jsp").forward(request, response);
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
