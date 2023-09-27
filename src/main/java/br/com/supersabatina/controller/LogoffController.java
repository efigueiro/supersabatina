package br.com.supersabatina.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.com.supersabatina.model.entity.User;

@WebServlet("/logoff")
public class LogoffController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogoffController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");
		String action = request.getParameter("action");

		if (authenticated == null || action.equals("logout")) {
			request.getSession().invalidate();
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
