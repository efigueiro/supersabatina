<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="br.com.supersabatina.model.entity.User"%>
<%@ page import="br.com.supersabatina.util.Messenger"%>

<%
// get authenticated user
User authenticated = new User();
authenticated = (User) request.getSession().getAttribute("authenticated");
%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Super Sabatina</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous" />
        <link rel="icon" href="<%=request.getContextPath()%>/img/question.png" />
    </head>

    <body class="bg-white">

		<jsp:include page="/fragments/navBar.jsp" />

        <!-- Content -->
        <div class="container">
            <div class="row">
            	<div><a href="<%=request.getContextPath()%>/modules/questionGroup/retrieveQuestionGroup.jsp" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Voltar</a></div>
                <div class="col-md-8 p-4 justify-content-center">
                    <h3>Detalhes e edição de grupo de perguntas.</h3>
                    
                    <c:forEach var="message" items="${Messenger.messageList}">
						<div class="${Messenger.divClass}" role="${Messenger.divRole}">
  							${message}
						</div>
					</c:forEach>
                        
                    <% Messenger.resetMessenger(); %>
                    
                    <form id="updateQuestionGroup" action="/supersabatina/questionGroup" method="post">
                      <div class="mb-3 mt-3">
                      	<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="updateQuestionGroup" required/>
                      </div>
                      <div class="mb-3 mt-3">
                      	<input type="hidden" class="form-control" id="txtQuestionGroupId" name="txtQuestionGroupId" value="${questionGroup.questionGroupId}" required/>
                      </div> 
                      <div class="mb-3 mt-3">
                      	<input type="text" class="form-control" id="txtTitle" name="txtTitle" value="${questionGroup.title}" required/>
                      </div> 
                      <div>
                      	<textarea class="form-control" id="txtDescription" name="txtDescription" rows="3" >${questionGroup.description}</textarea>
                      </div>
                    </form>
                    
                    <form id="deleteQuestionGroup" action="/supersabatina/questionGroup" method="post">
                      <div class="mb-3 mt-3">
                      	<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="deleteQuestionGroup" required/>
                      </div>
                      <div class="mb-3 mt-3">
                      	<input type="hidden" class="form-control" id="txtQuestionGroupId" name="txtQuestionGroupId" value="${questionGroup.questionGroupId}" required/>
                      </div> 
                    </form>
                    
                    <div>
                      <button form="updateQuestionGroup" type="submit" class="btn btn-success mt-3">Salvar</button>
                      <button form="deleteQuestionGroup" type="submit" class="btn btn-danger mt-3 ms-2">Deletar</button>
                    </div>
                                                        
                </div>

                <div class="col-md-4 p-4 justify-content-center">
                    <div class="card">
					  <div class="card-body">
					    <h5 class="card-title">Perguntas Associadas</h5>
					    <p class="card-text">Este grupo de perguntas possui ${numberQuestions} pergunta(s) associada(s).</p>
					    <p class="card-text">Você pode adicionar ou remover perguntas desde grupo de perguntas clicando nos botões abaixo.</p>
					    <form id="goToQuestionGroupRemoveQuestion" action="/supersabatina/questionGroup" method="post">
                      		<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="goToQuestionGroupRemoveQuestion" required/>
                      		<input type="hidden" class="form-control" id="txtQuestionGroupId" name="txtQuestionGroupId" value="${questionGroup.questionGroupId}" required/>
					    </form>
					    
					    <form id="goToQuestionGroupAddQuestion" action="/supersabatina/questionGroup" method="post">
                      		<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="goToQuestionGroupAddQuestion" required/>
                      		<input type="hidden" class="form-control" id="txtQuestionGroupId" name="txtQuestionGroupId" value="${questionGroup.questionGroupId}" required/>
					    </form>
					    
					    <button type="submit" form="goToQuestionGroupAddQuestion" class="btn btn-outline-secondary btn-sm mt-3">Adicionar Perguntas</button>
					    <c:if test = "${numberQuestions > 0}">
         					<button type="submit" form="goToQuestionGroupRemoveQuestion" class="btn btn-outline-secondary btn-sm mt-3 ms-2">Remover Perguntas</button>
      					</c:if>
					    
					  </div>
					</div>
                </div>
            </div>
        </div>
        <!-- End Content -->

		<jsp:include page="/fragments/footer.jsp" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    </body>
</html>