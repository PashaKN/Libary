package by.htp.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.command.Command;
import by.htp.library.command.CommandProvider;
import by.htp.library.dao.pool.PoolConnection;
import by.htp.library.dao.pool.PoolFactory;
import by.htp.library.dao.pool.Pool_Exception;
import by.htp.library.service.ServiceException;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		System.out.println("You in init");
		PoolFactory sqlFactory = PoolFactory.getInstance();
		PoolConnection sqlPool = sqlFactory.getPool();
		try {
			sqlPool.initPool();
		} catch (Pool_Exception e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommandProvider provider = new CommandProvider();
		String commandName = request.getParameter("Command");
		Command command = provider.getCommand(commandName);
		try {
			command.execute(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	public void destroy() {
		PoolFactory sqlFactory = PoolFactory.getInstance();
		PoolConnection sqlPool = sqlFactory.getPool();
		sqlPool.destroyPool();
	}

}