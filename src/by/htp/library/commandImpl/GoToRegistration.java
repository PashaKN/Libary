package by.htp.library.commandImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.command.Command;
import by.htp.library.service.ServiceException;

public class GoToRegistration implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

		try {
			request.getRequestDispatcher("WEB-INF/Registration.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			try {
				response.sendRedirect("Eror.jsp");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

}
