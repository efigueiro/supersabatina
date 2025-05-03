package br.com.supersabatina.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.supersabatina.model.entity.Option;
import br.com.supersabatina.model.entity.Question;
import br.com.supersabatina.model.entity.QuestionGroup;
import br.com.supersabatina.model.entity.User;
import br.com.supersabatina.service.QuestionGroupService;
import br.com.supersabatina.service.QuestionService;
import br.com.supersabatina.util.DropDownComponentUtil;
import br.com.supersabatina.util.Messenger;
import br.com.supersabatina.util.PaginatorUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/question")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QuestionController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");
		String action = request.getParameter("action");

		if (authenticated != null) {

			switch (action) {

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

				if (questionGroupList.isEmpty()) {
					Messenger.addWarningMessage("Você não possui grupo de perguntas ativo, por este motivo foi direcionado "
									+ "para a tela de criação de grupo de perguntas.");
					request.getRequestDispatcher("modules/questionGroup/createQuestionGroup.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("modules/question/createQuestion.jsp").forward(request, response);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");
		String action = request.getParameter("txtAction");

		if (authenticated != null) {

			switch (action) {

			case "createQuestion": {
				String optQuestionGroup = (String) request.getParameter("optQuestionGroup");
				String optVisibility = (String) request.getParameter("optVisibility");
				String txtSubject = (String) request.getParameter("txtSubject");
				String txtQuestion = (String) request.getParameter("txtQuestion");
				String txtAnswer = (String) request.getParameter("txtAnswer");
				long questionGroupId = Long.parseLong(optQuestionGroup);

				QuestionService questionService = new QuestionService();
				Question question = new Question();

				question.setAnswer(txtAnswer);
				question.setQuestion(txtQuestion);
				question.setQuestionId(questionGroupId);
				question.setSubject(txtSubject);
				question.setUser(authenticated);
				question.setVisibility(optVisibility);

				questionService.create(question, questionGroupId);

				QuestionGroupService questionGroupService = new QuestionGroupService();
				List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();
				questionGroupList = questionGroupService.retrieveAllByUserId(authenticated);
				request.setAttribute("questionGroupList", questionGroupList);
				request.getRequestDispatcher("modules/question/createQuestion.jsp").forward(request, response);
				break;
			}

			case "retrieveQuestion": {
				String search = (String) request.getParameter("txtSearch");
				String visibilitySelected = (String) request.getParameter("optVisibility");
				
				int currentPage = 1;
				int totalRecords = 0;

				List<Option> visibilityOptionList = DropDownComponentUtil.getRetrieveQuestionScreenVisibilityOptionList();
				QuestionService questionService = new QuestionService();
				List<Question> questionList = new ArrayList<Question>();
				
				totalRecords = questionService.retrieveByQuestionCount(search, authenticated, visibilitySelected);
				PaginatorUtil paginator = new PaginatorUtil(totalRecords, currentPage);
				questionList = questionService.retrieveByQuestion(search, authenticated, visibilitySelected, paginator.getOffset());
				
				request.setAttribute("authenticated", authenticated);
				request.setAttribute("questionList", questionList);
				request.setAttribute("visibilitySelected", visibilitySelected);
				request.setAttribute("visibilityOptionList", visibilityOptionList);
				request.setAttribute("search", search);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("totalPages", paginator.getTotalPages());
				request.getRequestDispatcher("modules/question/retrieveQuestion.jsp").forward(request, response);
				break;
			}
			
			case "goToRetrieveQuestion": {
				String search = (String) request.getParameter("txtSearch");
				String visibilitySelected = (String) request.getParameter("txtVisibilitySelected");
				String stringCurrentPage = (String) request.getParameter("txtCurrentPage");
				int currentPage = Integer.parseInt(stringCurrentPage);
				
				int totalRecords = 0;

				List<Option> visibilityOptionList = DropDownComponentUtil.getRetrieveQuestionScreenVisibilityOptionList();
				QuestionService questionService = new QuestionService();
				List<Question> questionList = new ArrayList<Question>();
				
				totalRecords = questionService.retrieveByQuestionCount(search, authenticated, visibilitySelected);
				PaginatorUtil paginator = new PaginatorUtil(totalRecords, currentPage);
				questionList = questionService.retrieveByQuestion(search, authenticated, visibilitySelected, paginator.getOffset());
				
				request.setAttribute("questionList", questionList);
				request.setAttribute("visibilitySelected", visibilitySelected);
				request.setAttribute("visibilityOptionList", visibilityOptionList);
				request.setAttribute("search", search);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("totalPages", paginator.getTotalPages());
				request.getRequestDispatcher("modules/question/retrieveQuestion.jsp").forward(request, response);
				break;
			}
			
			case "goToUpdateQuestion": {
				// Getting values from the view layer
				String search = (String) request.getParameter("txtSearch");
				String visibilitySelected = (String) request.getParameter("txtVisibilitySelected");
				String currentPage = (String) request.getParameter("txtCurrentPage");
				String stringQuestionId = (String) request.getParameter("txtQuestionId");
				long questionId = Long.parseLong(stringQuestionId);

				// Creating dependency objects
				Question question = new Question();
				QuestionService questionService = new QuestionService();
				
				question = questionService.retrieveQuestionById(questionId);
				
				request.setAttribute("question", question);
				request.setAttribute("search", search);
				request.setAttribute("visibilitySelected", visibilitySelected);
				request.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("modules/question/updateQuestion.jsp").forward(request, response);
				break;
			}
			
			case "updateQuestion": {
				String stringQuestionId = (String) request.getParameter("txtQuestionId");
				String optVisibility = (String) request.getParameter("optVisibility");
				String subject = (String) request.getParameter("txtSubject");
				String question = (String) request.getParameter("txtQuestion");
				String answer = (String) request.getParameter("txtAnswer");
				long questionId = Long.parseLong(stringQuestionId);

				Question q = new Question();
				QuestionService questionService = new QuestionService();
				
				q = questionService.retrieveQuestionById(questionId);
				q.setAnswer(answer);
				q.setQuestion(question);
				q.setSubject(subject);
				q.setVisibility(optVisibility);
				questionService.update(q);
				
				List<Option> visibilityOptionList = DropDownComponentUtil.getRetrieveQuestionScreenVisibilityOptionList();
				request.setAttribute("visibilityOptionList", visibilityOptionList);
				request.getRequestDispatcher("modules/question/retrieveQuestion.jsp").forward(request, response);
				break;
			}
			
			case "deleteQuestion": {
				String stringQuestionId = (String) request.getParameter("txtQuestionId");
				long questionId = Long.parseLong(stringQuestionId);

				Question question = new Question();
				QuestionService questionService = new QuestionService();
				
				question = questionService.retrieveQuestionById(questionId);
				questionService.delete(question);
				
				List<Option> visibilityOptionList = DropDownComponentUtil.getRetrieveQuestionScreenVisibilityOptionList();
				request.setAttribute("visibilityOptionList", visibilityOptionList);
				request.getRequestDispatcher("modules/question/retrieveQuestion.jsp").forward(request, response);
				break;
			}
			
			case "retrieveQuestionNext": {
				String search = (String) request.getParameter("txtSearch");
				String visibilitySelected = (String) request.getParameter("txtVisibilitySelected");
				String stringCurrentPage = (String) request.getParameter("txtCurrentPage");
				int currentPage = Integer.parseInt(stringCurrentPage) + 1;
				
				int totalRecords = 0;

				List<Option> visibilityOptionList = DropDownComponentUtil.getRetrieveQuestionScreenVisibilityOptionList();
				QuestionService questionService = new QuestionService();
				List<Question> questionList = new ArrayList<Question>();
				
				totalRecords = questionService.retrieveByQuestionCount(search, authenticated, visibilitySelected);
				PaginatorUtil paginator = new PaginatorUtil(totalRecords, currentPage);
				questionList = questionService.retrieveByQuestion(search, authenticated, visibilitySelected, paginator.getOffset());
				
				request.setAttribute("questionList", questionList);
				request.setAttribute("visibilitySelected", visibilitySelected);
				request.setAttribute("visibilityOptionList", visibilityOptionList);
				request.setAttribute("search", search);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("totalPages", paginator.getTotalPages());
				request.getRequestDispatcher("modules/question/retrieveQuestion.jsp").forward(request, response);
				break;
			}
			
			case "retrieveQuestionPrevious": {
				String search = (String) request.getParameter("txtSearch");
				String visibilitySelected = (String) request.getParameter("txtVisibilitySelected");
				String stringCurrentPage = (String) request.getParameter("txtCurrentPage");
				int currentPage = Integer.parseInt(stringCurrentPage) - 1;
				
				int totalRecords = 0;

				List<Option> visibilityOptionList = DropDownComponentUtil.getRetrieveQuestionScreenVisibilityOptionList();
				QuestionService questionService = new QuestionService();
				List<Question> questionList = new ArrayList<Question>();
				
				totalRecords = questionService.retrieveByQuestionCount(search, authenticated, visibilitySelected);
				PaginatorUtil paginator = new PaginatorUtil(totalRecords, currentPage);
				questionList = questionService.retrieveByQuestion(search, authenticated, visibilitySelected, paginator.getOffset());
				
				request.setAttribute("questionList", questionList);
				request.setAttribute("visibilitySelected", visibilitySelected);
				request.setAttribute("visibilityOptionList", visibilityOptionList);
				request.setAttribute("search", search);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("totalPages", paginator.getTotalPages());
				request.getRequestDispatcher("modules/question/retrieveQuestion.jsp").forward(request, response);
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
