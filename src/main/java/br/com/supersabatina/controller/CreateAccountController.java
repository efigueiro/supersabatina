package br.com.supersabatina.controller;

import java.io.IOException;

import br.com.supersabatina.model.dao.UserDao;
import br.com.supersabatina.model.entity.User;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = "";
		
		String userName = (String) request.getParameter("txtUserName");
		String email = (String) request.getParameter("txtEmail");
		String password = (String) request.getParameter("txtPassword");
		
		Messenger.addInfoMessage("Usuário criado com sucesso. Retorne para tela de login.");
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setUserName(userName);
		
		User selectedUser = new User();
		
		UserDao userDao = new UserDao();
		//selectedUser = userDao.retrieveByUserName(user);
		
		request.getRequestDispatcher("createAccount.jsp").forward(request, response);
		
	}
}
