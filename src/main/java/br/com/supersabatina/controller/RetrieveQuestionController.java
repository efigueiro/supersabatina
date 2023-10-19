package br.com.supersabatina.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.supersabatina.model.dao.QuestionDao;
import br.com.supersabatina.model.entity.Option;
import br.com.supersabatina.model.entity.Question;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.QuestionGroupService;
import br.com.supersabatina.service.QuestionService;
import br.com.supersabatina.util.DropDownComponentUtil;
import br.com.supersabatina.util.VisibilityUti;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/retrieveQuestion")
public class RetrieveQuestionController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public RetrieveQuestionController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Getting values from the view layer
		String search = (String) request.getParameter("txtSearch");
		String visibilitySelected = (String) request.getParameter("optVisibility");
		
		// Getting user from session
		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");
		
		// Getting data from database
		List<Question> questionList = new ArrayList<Question>();
		
		// List to populate visibility select component on the screen
		List<Option> visibilityOptionList = DropDownComponentUtil.getRetrieveQuestionScreenVisibilityOptionList();

		if (authenticated != null) {
			QuestionService questionService = new QuestionService();
			questionList = questionService.retrieveByQuestion(search, authenticated, visibilitySelected);

			request.setAttribute("questionList", questionList);
			request.setAttribute("visibilitySelected", visibilitySelected);
			request.setAttribute("visibilityOptionList", visibilityOptionList);
			request.setAttribute("search", search);
			request.getRequestDispatcher("modules/question/retrieveQuestion.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
