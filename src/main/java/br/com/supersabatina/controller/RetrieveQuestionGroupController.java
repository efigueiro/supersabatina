package br.com.supersabatina.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.QuestionGroupService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/retrieveQuestionGroup")
public class RetrieveQuestionGroupController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public RetrieveQuestionGroupController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String search = (String) request.getParameter("txtSearch");
		
		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");
		
		List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();
		
		QuestionGroupService retrieveQuestionGroupService = new QuestionGroupService();
		questionGroupList = retrieveQuestionGroupService.retrieveByTitle(search, authenticated);
		
		request.setAttribute("questionGroupList", questionGroupList);
		request.getRequestDispatcher("modules/questionGroup/retrieveQuestionGroup.jsp").forward(request, response);
		
	}
}
