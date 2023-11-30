package br.com.supersabatina.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.com.supersabatina.model.entity.Question;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.QuestionService;

@WebServlet("/game")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GameController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			
			case "goToAnswer": {
				String strQuestionGroupId = (String) request.getParameter("txtQuestionGroupId");
				String strQuestionId = (String) request.getParameter("txtQuestionId");
				long questionGroupId = Long.parseLong(strQuestionGroupId);
				long questionId = Long.parseLong(strQuestionId);

				Question question = new Question();
				QuestionService questionService = new QuestionService();

				question = questionService.retrieveQuestionById(questionId);

				request.setAttribute("question", question);
				request.setAttribute("questionGroupId", questionGroupId);

				request.getRequestDispatcher("modules/game/gameAnswer.jsp").forward(request, response);
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
