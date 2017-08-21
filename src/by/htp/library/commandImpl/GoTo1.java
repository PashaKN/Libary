package by.htp.library.commandImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.command.Command;
import by.htp.library.service.ServiceException;

public class GoTo1 implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies){
			if (c.getName().equals("login")){
				request.setAttribute("login", c.getValue());
				try {
					request.getRequestDispatcher("page1.jsp").include(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
