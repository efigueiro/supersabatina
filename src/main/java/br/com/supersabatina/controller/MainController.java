package br.com.supersabatina.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.supersabatina.model.entity.Option;
import br.com.supersabatina.model.entity.Question;
import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.QuestionGroupService;
import br.com.supersabatina.service.QuestionService;
import br.com.supersabatina.util.DropDownComponentUtil;
import br.com.supersabatina.util.Messenger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class MainController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public MainController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");
		String action = request.getParameter("action");

		if (authenticated != null) {

			switch (action) {

			case "retrieveQuestionGroup": {
				request.setAttribute("authenticated", authenticated);
				request.getRequestDispatcher("modules/questionGroup/retrieveQuestionGroup.jsp").forward(request, response);
				break;
			}

			case "createQuestionGroup": {
				request.setAttribute("authenticated", authenticated);
				request.getRequestDispatcher("modules/questionGroup/createQuestionGroup.jsp").forward(request, response);
				break;
			}

			case "retrieveQuestion": {
				List<Option> visibilityOptionList = DropDownComponentUtil.getRetrieveQuestionScreenVisibilityOptionList();
				request.setAttribute("visibilityOptionList", visibilityOptionList);
				request.getRequestDispatcher("modules/question/retrieveQuestion.jsp").forward(request, response);
				break;
			}

			case "createQuestion": {
				QuestionGroupService questionGroupService = new QuestionGroupService();
				List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();

				questionGroupList = questionGroupService.retrieveAllByUserId(authenticated);
				request.setAttribute("questionGroupList", questionGroupList);
				
				if(questionGroupList.isEmpty()) {
					Messenger.addWarningMessage(
							"Você não possui grupo de perguntas ativo, por este motivo foi direcionado "
							+ "para a tela de criação de grupo de perguntas."
							);
					request.getRequestDispatcher("modules/questionGroup/createQuestionGroup.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("modules/question/createQuestion.jsp").forward(request, response);
				}
				break;
			}
				
			case "updateQuestionGroup": {
				// Getting values from the view layer
				String stringQuestionGroupId = request.getParameter("questionGroupId");
				long questionGroupId = Long.parseLong(stringQuestionGroupId);
				
				// Creating dependency objects
				QuestionGroup questionGroup = new QuestionGroup();
				QuestionGroupService questionGroupService = new QuestionGroupService();
				
				// Variable to check how many questions are recorded by question group
				int numberQuestions = 0;
				
				questionGroup = questionGroupService.retrieveById(questionGroupId, authenticated.getUserId());
				numberQuestions = questionGroupService.count(questionGroupId);

				if (StringUtils.isNotEmpty(questionGroup.getTitle())) {
					request.setAttribute("questionGroup", questionGroup);
					request.setAttribute("numberQuestions", numberQuestions);
					request.getRequestDispatcher("modules/questionGroup/updateQuestionGroup.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				break;
			}

			case "removeQuestion": {
				String srtQuestionGroupId = request.getParameter("questionGroupId");
				long questionGroupId = Long.parseLong(srtQuestionGroupId);
				
				List<Question> questionList = new ArrayList<Question>();
				QuestionService questionService = new QuestionService();
				questionList = questionService.retrieveByQuestionGroup(questionGroupId, authenticated);
				request.setAttribute("questionList", questionList);
				request.setAttribute("questionGroupId", questionGroupId);
				request.getRequestDispatcher("modules/questionGroup/removeQuestion.jsp").forward(request, response);
				break;
			}

			case "deleteQuestionGroup": {
				String stringQuestionGroupId = request.getParameter("questionGroupId");
				long questionGroupId = Long.parseLong(stringQuestionGroupId);
				
				QuestionGroupService questionGroupService = new QuestionGroupService();
				questionGroupService.delete(questionGroupId, authenticated.getUserId());
				request.getRequestDispatcher("modules/questionGroup/retrieveQuestionGroup.jsp").forward(request, response);
				break;
			}

			case "next":
				break;


			case "logout": {
				if (authenticated == null || action.equals("logout")) {
					request.getSession().invalidate();
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
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

			case "retrieveQuestionGroup": {
				String search = (String) request.getParameter("txtSearch");
				List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();

				QuestionGroupService retrieveQuestionGroupService = new QuestionGroupService();
				questionGroupList = retrieveQuestionGroupService.retrieveByTitle(search, authenticated);
				
				request.setAttribute("questionGroupList", questionGroupList);
				request.getRequestDispatcher("modules/questionGroup/retrieveQuestionGroup.jsp").forward(request, response);
				break;
			}

			case "createQuestionGroup": {
				String title = (String) request.getParameter("txtTitle");
				String description = (String) request.getParameter("txtDescription");
				
				QuestionGroup questionGroup = new QuestionGroup();
				questionGroup.setUser(authenticated);
				questionGroup.setTitle(title);
				questionGroup.setDescription(description);

				QuestionGroupService questionGroupService = new QuestionGroupService();
				questionGroupService.create(questionGroup);

				request.getRequestDispatcher("modules/questionGroup/createQuestionGroup.jsp").forward(request, response);
				break;
			}

			case "retrieveQuestion": {
				List<Option> visibilityOptionList = DropDownComponentUtil.getRetrieveQuestionScreenVisibilityOptionList();
				request.setAttribute("visibilityOptionList", visibilityOptionList);
				request.getRequestDispatcher("modules/question/retrieveQuestion.jsp").forward(request, response);
				break;
			}

			case "createQuestion": {
				QuestionGroupService questionGroupService = new QuestionGroupService();
				List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();

				questionGroupList = questionGroupService.retrieveAllByUserId(authenticated);
				request.setAttribute("questionGroupList", questionGroupList);
				
				if(questionGroupList.isEmpty()) {
					Messenger.addWarningMessage(
							"Você não possui grupo de perguntas ativo, por este motivo foi direcionado "
							+ "para a tela de criação de grupo de perguntas."
							);
					request.getRequestDispatcher("modules/questionGroup/createQuestionGroup.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("modules/question/createQuestion.jsp").forward(request, response);
				}
				break;
			}
				
			case "updateQuestionGroup": {
				String stringQuestionGroupId = (String) request.getParameter("txtQuestionGroupId");
				String title = (String) request.getParameter("txtTitle");
				String description = (String) request.getParameter("txtDescription");

				QuestionGroup questionGroup = new QuestionGroup();
				questionGroup.setDescription(description);
				questionGroup.setQuestionGroupId(Long.parseLong(stringQuestionGroupId));
				questionGroup.setTitle(title);
				questionGroup.setUser(authenticated);

				QuestionGroupService questionGroupService = new QuestionGroupService();
				questionGroupService.update(questionGroup);
				request.getRequestDispatcher("modules/questionGroup/retrieveQuestionGroup.jsp").forward(request, response);
				break;
			}

			case "removeQuestion": {
				String srtQuestionGroupId = request.getParameter("questionGroupId");
				long questionGroupId = Long.parseLong(srtQuestionGroupId);
				
				List<Question> questionList = new ArrayList<Question>();
				QuestionService questionService = new QuestionService();
				questionList = questionService.retrieveByQuestionGroup(questionGroupId, authenticated);
				request.setAttribute("questionList", questionList);
				request.setAttribute("questionGroupId", questionGroupId);
				request.getRequestDispatcher("modules/questionGroup/removeQuestion.jsp").forward(request, response);
				break;
			}

			case "deleteQuestionGroup": {
				String stringQuestionGroupId = request.getParameter("questionGroupId");
				long questionGroupId = Long.parseLong(stringQuestionGroupId);
				
				QuestionGroupService questionGroupService = new QuestionGroupService();
				questionGroupService.delete(questionGroupId, authenticated.getUserId());
				request.getRequestDispatcher("modules/questionGroup/retrieveQuestionGroup.jsp").forward(request, response);
				break;
			}

			case "7":
				break;

			case "8":
				break;

			case "9":
				break;

			case "logout": {
				if (authenticated == null || action.equals("logout")) {
					request.getSession().invalidate();
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
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
