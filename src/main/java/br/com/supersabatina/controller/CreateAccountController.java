package br.com.supersabatina.controller;

import java.io.IOException;

import br.com.supersabatina.model.dao.UserDao;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.UserService;
import br.com.supersabatina.util.Messenger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createAccountController")
public class CreateAccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CreateAccountController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = (String) request.getParameter("txtUserName");
		String email = (String) request.getParameter("txtEmail");
		String password = (String) request.getParameter("txtPassword");

		User user = new User();
		user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(password);
		//user.setAvatar("");
		user.setPublicProfile("no");
		//user.setDescription("");

		UserService userService = new UserService();
		userService.createUser(user);
		
		if(Messenger.success) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("createAccount.jsp").forward(request, response);
		}
		
		Messenger.setSuccessFalse();

	}
}
