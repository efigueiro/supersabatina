package br.com.supersabatina.controller;

import java.io.IOException;

import br.com.supersabatina.model.dao.QuestionGroupDao;
import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.CreateQuestionGroupService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editQuestionGroup")
public class EditQuestionGroupController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public EditQuestionGroupController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");

		String stringQuestionGroupId = request.getParameter("questionGroupId");
		long questionGroupId = Long.parseLong(stringQuestionGroupId);

		QuestionGroup selectedQuestionGroup = new QuestionGroup();
		QuestionGroupDao questionGroupDao = new QuestionGroupDao();

		// need to call service layer, i need to fix it some day.
		if (authenticated != null) {
			selectedQuestionGroup = questionGroupDao.retrieveById(questionGroupId);
		}

		if (selectedQuestionGroup != null) {
			request.setAttribute("questionGroup", selectedQuestionGroup);
			request.getRequestDispatcher("modules/questionGroup/editQuestionGroup.jsp").forward(request, response);
		}

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
