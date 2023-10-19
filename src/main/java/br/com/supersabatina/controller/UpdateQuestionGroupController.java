package br.com.supersabatina.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import br.com.supersabatina.model.dao.QuestionGroupDao;
import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.QuestionGroupService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateQuestionGroup")
public class UpdateQuestionGroupController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UpdateQuestionGroupController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Getting values from the view layer
		String stringQuestionGroupId = request.getParameter("questionGroupId");
		long questionGroupId = Long.parseLong(stringQuestionGroupId);
		
		// Getting user from session
		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");

		// Creating dependency objects
		QuestionGroup questionGroup = new QuestionGroup();
		QuestionGroupService questionGroupService = new QuestionGroupService();
		
		// Variable to check how many questions are recorded by question group
		int numberQuestions = 0;
		
		if (authenticated != null) {
			questionGroup = questionGroupService.retrieveById(questionGroupId, authenticated.getUserId());
			numberQuestions = questionGroupService.count(questionGroupId);
		}

		if (StringUtils.isNotEmpty(questionGroup.getTitle())) {
			request.setAttribute("questionGroup", questionGroup);
			request.setAttribute("numberQuestions", numberQuestions);
			request.getRequestDispatcher("modules/questionGroup/updateQuestionGroup.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Getting values from the view layer
		String stringQuestionGroupId = (String) request.getParameter("txtQuestionGroupId");
		String title = (String) request.getParameter("txtTitle");
		String description = (String) request.getParameter("txtDescription");

		// Getting user from session
		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");

		QuestionGroup questionGroup = new QuestionGroup();
		questionGroup.setDescription(description);
		questionGroup.setQuestionGroupId(Long.parseLong(stringQuestionGroupId));
		questionGroup.setTitle(title);
		questionGroup.setUser(authenticated);

		QuestionGroupService questionGroupService = new QuestionGroupService();

		if (authenticated != null) {
			questionGroupService.update(questionGroup);
			request.getRequestDispatcher("modules/questionGroup/retrieveQuestionGroup.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
