package br.com.supersabatina.controller;

import java.io.IOException;

import br.com.supersabatina.model.entity.Question;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.GameService;
import br.com.supersabatina.service.QuestionService;
import br.com.supersabatina.util.Statistic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

				Question question = new Question();
				QuestionService questionService = new QuestionService();

				question = questionService.retrieveQuestionRandom(questionGroupId, authenticated);

				request.setAttribute("question", question);
				request.setAttribute("questionGroupId", questionGroupId);

				request.getRequestDispatcher("modules/game/gameQuestion.jsp").forward(request, response);
				break;
			}
			
			case "gameAnswer": {
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
			
			case "correctAnswer": {
				String strQuestionGroupId = (String) request.getParameter("txtQuestionGroupId");
				String strQuestionId = (String) request.getParameter("txtQuestionId");
				long questionGroupId = Long.parseLong(strQuestionGroupId);
				long questionId = Long.parseLong(strQuestionId);

				Question question = new Question();
				QuestionService questionService = new QuestionService();
				GameService gameService = new GameService();
				
				gameService.ProcessNumberCorrectAnswerByQuestion(questionId, questionGroupId);
				gameService.ProcessNumberCorrectAnswerByDate(authenticated);
				question = questionService.retrieveQuestionById(questionId);
				
				request.setAttribute("question", question);
				request.setAttribute("questionGroupId", questionGroupId);
				request.setAttribute("successRate", gameService.sucessRateByQuestion(questionId, questionGroupId));
				request.setAttribute("failureRate", gameService.failureRateByQuestion(questionId, questionGroupId));

				request.getRequestDispatcher("modules/game/gameSummary.jsp").forward(request, response);
				break;
			}
			
			case "incorrectAnswer": {
				String strQuestionGroupId = (String) request.getParameter("txtQuestionGroupId");
				String strQuestionId = (String) request.getParameter("txtQuestionId");
				long questionGroupId = Long.parseLong(strQuestionGroupId);
				long questionId = Long.parseLong(strQuestionId);

				Question question = new Question();
				QuestionService questionService = new QuestionService();
				GameService gameService = new GameService();
				
				gameService.ProcessNumberIncorrectAnswerByQuestion(questionId, questionGroupId);
				gameService.ProcessNumberIncorrectAnswerByDate(authenticated);
				question = questionService.retrieveQuestionById(questionId);
				
				request.setAttribute("question", question);
				request.setAttribute("questionGroupId", questionGroupId);
				request.setAttribute("successRate", gameService.sucessRateByQuestion(questionId, questionGroupId));
				request.setAttribute("failureRate", gameService.failureRateByQuestion(questionId, questionGroupId));

				request.getRequestDispatcher("modules/game/gameSummary.jsp").forward(request, response);
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
