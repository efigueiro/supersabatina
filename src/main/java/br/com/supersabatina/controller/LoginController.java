package br.com.supersabatina.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.LoginService;
import br.com.supersabatina.util.Messenger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginController")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = (String) request.getParameter("txtEmail");
		String password = (String) request.getParameter("txtPassword");

		User user = new User();
		User authenticated = new User();
		LoginService loginService = new LoginService();

		user.setEmail(email);
		user.setPassword(password);

		authenticated = loginService.login(user);

		if (StringUtils.isNotEmpty(authenticated.getEmail())) {
			// add to session and redirect to the system
			System.out.println(authenticated.getEmail());
			System.out.println(authenticated.getPassword());
			for (String message : Messenger.messageList) {
				System.out.println(message);
			}
			System.out.println(Messenger.success);
		} else {
			System.out.println(authenticated.getEmail());
			System.out.println(authenticated.getPassword());
			for (String message : Messenger.messageList) {
				System.out.println(message);
			}
			
			System.out.println(Messenger.success);
			
		}
	}
}
