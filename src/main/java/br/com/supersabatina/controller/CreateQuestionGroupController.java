package br.com.supersabatina.controller;

import java.io.IOException;

import br.com.supersabatina.model.dao.QuestionGroupDao;
import br.com.supersabatina.model.dao.UserDao;
import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.CreateQuestionGroupService;
import br.com.supersabatina.service.UserService;
import br.com.supersabatina.util.Messenger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createQuestionGroup")
public class CreateQuestionGroupController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CreateQuestionGroupController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String title = (String) request.getParameter("txtTitle");
		String description = (String) request.getParameter("txtDescription");
		
		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");
		
		QuestionGroup questionGroup = new QuestionGroup();
		questionGroup.setUser(authenticated);
		questionGroup.setTitle(title);
		questionGroup.setDescription(description);
		
		
		CreateQuestionGroupService createQuestionGroupService = new CreateQuestionGroupService();
		createQuestionGroupService.create(questionGroup);

		request.getRequestDispatcher("modules/questionGroup/createQuestionGroup.jsp").forward(request, response);
		
	}
}
