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

@WebServlet("/deleteQuestionGroup")
public class DeleteQuestionGroupController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DeleteQuestionGroupController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String stringQuestionGroupId = request.getParameter("questionGroupId");
		long questionGroupId = Long.parseLong(stringQuestionGroupId);

		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");

		QuestionGroupService questionGroupService = new QuestionGroupService();

		if (authenticated != null) {
			questionGroupService.delete(questionGroupId, authenticated.getUserId());
			request.getRequestDispatcher("modules/questionGroup/retrieveQuestionGroup.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
