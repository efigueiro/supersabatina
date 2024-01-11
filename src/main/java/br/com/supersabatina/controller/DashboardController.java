package br.com.supersabatina.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.supersabatina.model.entity.Question;
import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.GameService;
import br.com.supersabatina.service.QuestionGroupService;
import br.com.supersabatina.service.QuestionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DashboardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");
		String action = request.getParameter("action");

		if (authenticated != null) {

			switch (action) {

			case "dashboard": {
				QuestionGroupService questionGroupService = new QuestionGroupService();
				GameService gameService = new GameService();
				
				List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();
				questionGroupList = questionGroupService.retrieveAllByUserId(authenticated);
				
				request.setAttribute("questionGroupList", questionGroupList);
				request.setAttribute("successRateByDate", gameService.sucessRateByDate(authenticated));
				request.setAttribute("failureRateByDate", gameService.failureRateByDate(authenticated));
				request.setAttribute("totalQuestionByDate", gameService.totalQuestionByDate(authenticated));
				request.getRequestDispatcher("/modules/dashboard.jsp").forward(request, response);
				
				break;
			}

			case "createQuestion": {
				break;
			}

			default:
				break;
			}

		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");
		String action = request.getParameter("txtAction");

		if (authenticated != null) {

			switch (action) {

			case "gameQuestion": {
				String optQuestionGroup = (String) request.getParameter("optQuestionGroup");
				long questionGroupId = Long.parseLong(optQuestionGroup);
				
				Question selectedQuestion = new Question();
				QuestionService questionService = new QuestionService();
				
				selectedQuestion = questionService.retrieveQuestionRandom(questionGroupId, authenticated);
				
				request.setAttribute("selectedQuestion", selectedQuestion);
				request.setAttribute("questionGroupId", questionGroupId);

				request.getRequestDispatcher("modules/game/gameQuestion.jsp").forward(request, response);
				break;
			}

			default:
				break;
			}

		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
