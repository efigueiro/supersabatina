package br.com.supersabatina.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.supersabatina.model.dao.UserDao;
import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.GameService;
import br.com.supersabatina.service.LoginService;
import br.com.supersabatina.service.QuestionGroupService;
import br.com.supersabatina.util.Messenger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginController() {
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

		// Getting values from the view layer
		String email = (String) request.getParameter("txtEmail");
		String password = (String) request.getParameter("txtPassword");

		// Creating user
		User user = new User();
		User authenticated = new User();
		LoginService loginService = new LoginService();
		UserDao userDao = new UserDao();

		user.setEmail(email);
		user.setPassword(password);

		// Trying login
		authenticated = loginService.login(user);

		if (Messenger.success) {

			HttpSession session = request.getSession();
			session.setAttribute("authenticated", authenticated);

			if (authenticated.getTutorial().equals("yes")) {
				request.getRequestDispatcher("modules/tutorial/main.jsp").forward(request, response);
				userDao.disableTutorial(authenticated.getUserId());
			} else {
				GameService gameService = new GameService();
				QuestionGroupService questionGroupService = new QuestionGroupService();
				List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();
				questionGroupList = questionGroupService.retrieveAllByUserId(authenticated);
				
				request.setAttribute("successRateByDate", gameService.sucessRateByDate(authenticated));
				request.setAttribute("failureRateByDate", gameService.failureRateByDate(authenticated));
				request.setAttribute("totalQuestionByDate", gameService.totalQuestionByDate(authenticated));
				request.setAttribute("questionGroupList", questionGroupList);
				request.getRequestDispatcher("/modules/dashboard.jsp").forward(request, response);
			}
			Messenger.setSuccessFalse();
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
