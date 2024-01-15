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
import br.com.supersabatina.util.PaginatorUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/questionGroup")
public class QuestionGroupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QuestionGroupController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User authenticated = new User();
		authenticated = (User) request.getSession().getAttribute("authenticated");
		String action = request.getParameter("action");

		if (authenticated != null) {

			switch (action) {

			case "retrieveQuestionGroup": {
				request.setAttribute("authenticated", authenticated);
				request.getRequestDispatcher("modules/questionGroup/retrieveQuestionGroup.jsp").forward(request,
						response);
				break;
			}

			case "createQuestionGroup": {
				request.setAttribute("authenticated", authenticated);
				request.getRequestDispatcher("modules/questionGroup/createQuestionGroup.jsp").forward(request,
						response);
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

			case "retrieveQuestionGroup": {
				String search = (String) request.getParameter("txtSearch");
				List<QuestionGroup> questionGroupList = new ArrayList<QuestionGroup>();

				QuestionGroupService retrieveQuestionGroupService = new QuestionGroupService();
				questionGroupList = retrieveQuestionGroupService.retrieveByTitle(search, authenticated);

				request.setAttribute("questionGroupList", questionGroupList);
				request.getRequestDispatcher("modules/questionGroup/retrieveQuestionGroup.jsp").forward(request, response);
				break;
			}

			case "goToUpdateQuestionGroup": {
				// Getting values from the view layer
				String stringQuestionGroupId = (String) request.getParameter("txtQuestionGroupId");
				long questionGroupId = Long.parseLong(stringQuestionGroupId);

				// Creating dependency objects
				QuestionGroup questionGroup = new QuestionGroup();
				QuestionGroupService questionGroupService = new QuestionGroupService();

				// Variable to check how many questions are recorded by question group
				int numberQuestions = 0;
				questionGroup = questionGroupService.retrieveById(questionGroupId, authenticated);
				numberQuestions = questionGroupService.count(questionGroupId);

				request.setAttribute("questionGroup", questionGroup);
				request.setAttribute("numberQuestions", numberQuestions);
				request.getRequestDispatcher("modules/questionGroup/updateQuestionGroup.jsp").forward(request, response);
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

			case "deleteQuestionGroup": {
				String stringQuestionGroupId = (String) request.getParameter("txtQuestionGroupId");
				long questionGroupId = Long.parseLong(stringQuestionGroupId);

				QuestionGroupService questionGroupService = new QuestionGroupService();
				questionGroupService.delete(questionGroupId, authenticated);
				request.getRequestDispatcher("modules/questionGroup/retrieveQuestionGroup.jsp").forward(request, response);
				break;
			}

			case "goToQuestionGroupRemoveQuestion": {
				String stringQuestionGroupId = (String) request.getParameter("txtQuestionGroupId");
				long questionGroupId = Long.parseLong(stringQuestionGroupId);

				int currentPage = 1;
				int totalRecords = 0;

				List<Question> questionList = new ArrayList<Question>();
				QuestionService questionService = new QuestionService();

				totalRecords = questionService.retrieveQuestionListCount(questionGroupId, authenticated);

				PaginatorUtil paginator = new PaginatorUtil(totalRecords, currentPage);
				questionList = questionService.retrieveQuestionList(questionGroupId, authenticated, paginator.getOffset());

				request.setAttribute("questionList", questionList);
				request.setAttribute("questionGroupId", questionGroupId);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("totalPages", paginator.getTotalPages());
				request.getRequestDispatcher("modules/questionGroup/removeQuestion.jsp").forward(request, response);
				break;
			}
			
			// --Working
			case "goToQuestionGroupAddQuestion": {
				String stringQuestionGroupId = (String) request.getParameter("txtQuestionGroupId");
				long questionGroupId = Long.parseLong(stringQuestionGroupId);
				
				List<Option> visibilityOptionList = DropDownComponentUtil.getRetrieveQuestionScreenVisibilityOptionList();
				request.setAttribute("visibilityOptionList", visibilityOptionList);
				
				QuestionGroup questionGroup = new QuestionGroup();
				QuestionGroupService questionGroupService =  new QuestionGroupService();
				questionGroup = questionGroupService.retrieveById(questionGroupId, authenticated);

				request.setAttribute("questionGroup", questionGroup);
				request.getRequestDispatcher("modules/questionGroup/addQuestion.jsp").forward(request, response);
				break;
			}
			
			// --working
			case "questionGroupRetrieveQuestion": {
				String stringQuestionGroupId = (String) request.getParameter("txtQuestionGroupId");
				long questionGroupId = Long.parseLong(stringQuestionGroupId);
				String search = (String) request.getParameter("txtSearch");
				String visibilitySelected = (String) request.getParameter("optVisibility");
				
				int currentPage = 1;
				int totalRecords = 0;
				
				QuestionGroup questionGroup = new QuestionGroup();
				QuestionGroupService questionGroupService =  new QuestionGroupService();
				questionGroup = questionGroupService.retrieveById(questionGroupId, authenticated);

				List<Option> visibilityOptionList = DropDownComponentUtil.getRetrieveQuestionScreenVisibilityOptionList();
				QuestionService questionService = new QuestionService();
				List<Question> questionList = new ArrayList<Question>();
				
				totalRecords = questionService.retrieveByQuestionCount(search, authenticated, visibilitySelected);
				PaginatorUtil paginator = new PaginatorUtil(totalRecords, currentPage);
				questionList = questionService.retrieveByQuestion(search, authenticated, visibilitySelected, paginator.getOffset());
				
				request.setAttribute("questionGroup", questionGroup);
				request.setAttribute("authenticated", authenticated);
				request.setAttribute("questionList", questionList);
				request.setAttribute("visibilitySelected", visibilitySelected);
				request.setAttribute("visibilityOptionList", visibilityOptionList);
				request.setAttribute("search", search);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("totalPages", paginator.getTotalPages());
				request.getRequestDispatcher("modules/questionGroup/addQuestion.jsp").forward(request, response);
				break;
			}
			
			case "questionGroupRemoveQuestionNext": {
				String stringQuestionGroupId = (String) request.getParameter("txtQuestionGroupId");
				String stringCurrentPage = (String) request.getParameter("txtCurrentPage");
				long questionGroupId = Long.parseLong(stringQuestionGroupId);
				int currentPage = Integer.parseInt(stringCurrentPage) + 1;

				int totalRecords = 0;

				List<Question> questionList = new ArrayList<Question>();
				QuestionService questionService = new QuestionService();
				PaginatorUtil paginator;

				totalRecords = questionService.retrieveQuestionListCount(questionGroupId, authenticated);

				paginator = new PaginatorUtil(totalRecords, currentPage);
				questionList = questionService.retrieveQuestionList(questionGroupId, authenticated, paginator.getOffset());

				request.setAttribute("questionList", questionList);
				request.setAttribute("questionGroupId", questionGroupId);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("totalPages", paginator.getTotalPages());
				request.getRequestDispatcher("modules/questionGroup/removeQuestion.jsp").forward(request, response);
				break;
			}
			
			case "questionGroupRemoveQuestionPrevious": {
				String stringQuestionGroupId = (String) request.getParameter("txtQuestionGroupId");
				String stringCurrentPage = (String) request.getParameter("txtCurrentPage");
				long questionGroupId = Long.parseLong(stringQuestionGroupId);
				int currentPage = Integer.parseInt(stringCurrentPage) - 1;

				int totalRecords = 0;

				List<Question> questionList = new ArrayList<Question>();
				QuestionService questionService = new QuestionService();
				PaginatorUtil paginator;

				totalRecords = questionService.retrieveQuestionListCount(questionGroupId, authenticated);

				paginator = new PaginatorUtil(totalRecords, currentPage);
				questionList = questionService.retrieveQuestionList(questionGroupId, authenticated, paginator.getOffset());

				request.setAttribute("questionList", questionList);
				request.setAttribute("questionGroupId", questionGroupId);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("totalPages", paginator.getTotalPages());
				request.getRequestDispatcher("modules/questionGroup/removeQuestion.jsp").forward(request, response);
				break;
			}

			case "questionGroupRemoveQuestion": {
				String stringQuestionGroupId = (String) request.getParameter("txtQuestionGroupId");
				String stringQuestionId = (String) request.getParameter("txtQuestionId");
				String stringCurrentPage = (String) request.getParameter("txtCurrentPage");
				long questionGroupId = Long.parseLong(stringQuestionGroupId);
				int currentPage = Integer.parseInt(stringCurrentPage);
				long questionId = Long.parseLong(stringQuestionId);
				
				int totalRecords = 0;

				PaginatorUtil paginator;
				List<Question> questionList = new ArrayList<Question>();
				QuestionService questionService = new QuestionService();
				
				questionService.removeQuestion(questionGroupId, questionId, authenticated);

				totalRecords = questionService.retrieveQuestionListCount(questionGroupId, authenticated);
				
				paginator = new PaginatorUtil(totalRecords, currentPage);
				questionList = questionService.retrieveQuestionList(questionGroupId, authenticated, paginator.getOffset());
				
				request.setAttribute("questionList", questionList);
				request.setAttribute("questionGroupId", questionGroupId);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("totalPages", paginator.getTotalPages());
				request.getRequestDispatcher("modules/questionGroup/removeQuestion.jsp").forward(request, response);
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
