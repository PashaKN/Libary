package by.htp.library.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.service.ServiceException;

public interface Command  {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
