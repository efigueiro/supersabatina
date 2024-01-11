<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="br.com.supersabatina.model.entity.User"%>
<%@ page import="br.com.supersabatina.model.entity.QuestionGroup"%>
<%@ page import="br.com.supersabatina.service.QuestionGroupService"%>
<%@ page import="br.com.supersabatina.util.Messenger"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

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
            	<div>
            		<form action="/supersabatina/question" method="post">
            			<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="goToRetrieveQuestion" required/>
            			<input type="hidden" class="form-control" id="txtSearch" name="txtSearch" value="${search}" required/>
						<input type="hidden" class="form-control" id="txtVisibilitySelected" name="txtVisibilitySelected" value="${visibilitySelected}" required/>
						<input type="hidden" class="form-control" id="txtCurrentPage" name="txtCurrentPage" value="${currentPage}" required/>
            			<button type="submit" class="btn btn-link">Voltar</button>
            		</form>
            	</div>
                <div class="col-md-8 p-4 justify-content-center">
                    <h3>Detalhes e edição de perguntas.</h3>
                    
                    <c:forEach var="message" items="${Messenger.messageList}">
						<div class="${Messenger.divClass}" role="${Messenger.divRole}">
  							${message}
						</div>
					</c:forEach>
                        
                    <% Messenger.resetMessenger(); %>
                    
                    <form id="updateQuestion" action="/supersabatina/question" method="post">
                      <div class="mb-3 mt-3">
                      	<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="updateQuestion" required/>
                      </div>
                      <div class="mb-3 mt-3">
                      	<input type="hidden" class="form-control" id="txtQuestionId" name="txtQuestionId" value="${question.questionId}" required/>
                      </div>  	
                      <div class="mt-3">
						<select class="form-select" aria-label="optPrivate" name="optVisibility">
						    <c:choose>
						        <c:when test="${question.visibility eq 'private'}">
						            <option value="private" selected>Privada</option>
						            <option value="public">Pública</option>
						        </c:when>
						        <c:when test="${question.visibility eq 'public'}">
						            <option value="private">Privada</option>
						            <option value="public" selected>Pública</option>
						        </c:when>
						    </c:choose>
						</select>
                      </div>
                      <div class="mt-3">
                      	<input type="text" class="form-control" id="txtSubject" name="txtSubject" value="${question.subject}" required/>
                      </div> 
                      <div class="mt-3">
                      	<textarea class="form-control" id="txtQuestion" name="txtQuestion" rows="3" required>${question.question}</textarea>
                      </div>
                      
                      <div class="mt-3">
                      	<textarea class="form-control" id="txtAnswer" name="txtAnswer" rows="3" required>${question.answer}</textarea>
                      </div>
                    </form>
                    
                    <form id="deleteQuestion" action="/supersabatina/question" method="post">
                      <div class="mb-3 mt-3">
                      	<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="deleteQuestion" required/>
                      </div>
                      <div class="mb-3 mt-3">
                      	<input type="hidden" class="form-control" id="txtQuestionId" name="txtQuestionId" value="${question.questionId}" required/>
                      </div>
                    </form>
                    
                    <div>
                      <button form="updateQuestion" type="submit" class="btn btn-success mt-3">Salvar</button>
                      <button form="deleteQuestion" type="submit" class="btn btn-danger mt-3 ms-2">Deletar</button>
                    </div>
                    
                </div>

                <div class="col-md-4 p-4 justify-content-center">
                	<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">Dicas</h5>
					    <p class="card-text">
					    	A ação de deletar uma pergunta não pode ser revertida. 
					    	Sua pergunta será deletada permanentemente do sistema e de todos os grupos de perguntas em que ela esta presente.
					    </p>
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