package br.com.supersabatina.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.supersabatina.model.entity.Question;
import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.QuestionGroupService;
import br.com.supersabatina.service.QuestionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createQuestion")
public class CreateQuestionController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CreateQuestionController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String optQuestionGroup = (String) request.getParameter("optQuestionGroup");
		String visibility = (String) request.getParameter("optVisibility");
		String subject = (String) request.getParameter("txtSubject");
		String question = (String) request.getParameter("txtQuestion");
		String answer = (String) request.getParameter("txtAnswer");
		long questionGroupId = Long.parseLong(optQuestionGroup);

		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");
		
		if (authenticated != null) {
			Question q = new Question();
			q.setAnswer(answer);
			q.setQuestion(question);
			q.setSubject(subject);
			q.setVisibility(visibility);
			q.setUser(authenticated);
			
			QuestionService questionService = new QuestionService();
			questionService.create(q, questionGroupId);

			QuestionGroupService questionGroupService = new QuestionGroupService();
			List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();
			questionGroupList = questionGroupService.retrieveAllByUserId(authenticated);
			request.setAttribute("questionGroupList", questionGroupList);
			request.getRequestDispatcher("modules/question/createQuestion.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
