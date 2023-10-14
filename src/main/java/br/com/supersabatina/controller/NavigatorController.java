package br.com.supersabatina.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.QuestionGroupService;
import br.com.supersabatina.util.Messenger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/navigator")
public class NavigatorController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public NavigatorController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");
		String action = request.getParameter("action");

		if (authenticated != null) {

			switch (action) {

			case "retrieveQuestionGroup":
				request.setAttribute("authenticated", authenticated);
				request.getRequestDispatcher("modules/questionGroup/retrieveQuestionGroup.jsp").forward(request, response);
				break;

			case "createQuestionGroup":
				request.setAttribute("authenticated", authenticated);
				request.getRequestDispatcher("modules/questionGroup/createQuestionGroup.jsp").forward(request, response);
				break;

			case "retrieveQuestion":
				break;

			case "createQuestion":
				QuestionGroupService questionGroupService = new QuestionGroupService();
				List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();

				questionGroupList = questionGroupService.retrieveAllByUserId(authenticated);
				request.setAttribute("questionGroupList", questionGroupList);
				
				if(questionGroupList.isEmpty()) {
					Messenger.addWarningMessage(
							"Você não possui grupo de questões ativo, por este motivo foi direcionado "
							+ "para a tela de criação de grupo de questões. Você deve criar um grupo de questões antes"
							+ " de criar suas perguntas."
							);
					request.getRequestDispatcher("modules/questionGroup/createQuestionGroup.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("modules/question/createQuestion.jsp").forward(request, response);
				}
				break;

			case "5":
				break;

			case "6":
				break;

			case "7":
				break;

			case "8":
				break;

			case "9":
				break;

			case "10":
				break;

			default:
				break;
			}

		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
