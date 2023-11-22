<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="br.com.supersabatina.model.entity.User"%>
<%@ page import="br.com.supersabatina.util.Messenger"%>

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
            		<form action="/supersabatina/questionGroup" method="post">
            			<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="goToUpdateQuestionGroup" required/>
            			<input type="hidden" class="form-control" id="txtQuestionGroupId" name="txtQuestionGroupId" value="${questionGroupId}" required/>
            			<button type="submit" class="btn btn-link">Voltar</button>
            		</form>
            	</div>
                <div class="col-md-8 p-4 justify-content-center">
                    <h3>Remover perguntas do grupo.</h3>
                    
                    <c:forEach var="message" items="${Messenger.messageList}">
						<div class="${Messenger.divClass}" role="${Messenger.divRole}">
  							${message}
						</div>
					</c:forEach>
                        
                    <% Messenger.resetMessenger(); %>
                    
                    <div>
                    	<c:if test="${not empty questionList}">
                    		<table class="table mt-4">
					 			<thead>
					    			<tr>
					      				<th scope="col">Pergunta</th>
					      				<th scope="col">Ações</th>
					    			</tr>
					  			</thead>
					  		
					  			<tbody>
					  				<c:forEach var="question" items="${questionList}">
					    				<tr>
					      					<td>${question.question}</td>
					      					<td>
					      						<form action="/supersabatina/questionGroup" method="post">
					      							<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="questionGroupRemoveQuestion" required/>
					      							<input type="hidden" class="form-control" id="txtQuestionGroupId" name="txtQuestionGroupId" value="${questionGroupId}" required/>
					      							<input type="hidden" class="form-control" id="txtQuestionId" name="txtQuestionId" value="${question.questionId}" required/>
					      							<input type="hidden" class="form-control" id="txtCurrentPage" name="txtCurrentPage" value="${currentPage}" required/>
					      							<button type="submit" class="btn btn-outline-secondary btn-sm">Remover</button>
					      						</form>
					      					</td>
					    				</tr>
					    			</c:forEach>
					  			</tbody>
							</table>
						</c:if>
					</div>
					
					<div class="d-flex align-items-center flex-row-reverse bd-highlight">
						<form id="questionGroupRemoveQuestionNext" action="/supersabatina/questionGroup" method="post">
							<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="questionGroupRemoveQuestionNext" required/>
							<input type="hidden" class="form-control" id="txtQuestionGroupId" name="txtQuestionGroupId" value="${questionGroupId}" required/>
							<input type="hidden" class="form-control" id="txtCurrentPage" name="txtCurrentPage" value="${currentPage}" required/>
						</form>
						<form id="questionGroupRemoveQuestionPrevious" action="/supersabatina/questionGroup" method="post">
							<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="questionGroupRemoveQuestionPrevious" required/>
							<input type="hidden" class="form-control" id="txtQuestionGroupId" name="txtQuestionGroupId" value="${questionGroupId}" required/>
							<input type="hidden" class="form-control" id="txtCurrentPage" name="txtCurrentPage" value="${currentPage}" required/>
						</form>
  						<div class="p-2 bd-highlight">
      						<c:choose>
    							<c:when test = "${currentPage > 1}">
                      				<button type="submit" form="questionGroupRemoveQuestionPrevious" class="btn btn-outline-secondary btn-sm ms-2">Página anterior</button>
    							</c:when>
    							<c:otherwise> 
                      				<button type="submit" form="questionGroupRemoveQuestionPrevious" class="btn btn-outline-secondary btn-sm ms-2" disabled data-bs-toggle="button">Página anterior</button>
    							</c:otherwise>
					  		</c:choose>
      						
      						<c:choose>
    							<c:when test = "${totalPages > currentPage}">
                      				<button type="submit" form="questionGroupRemoveQuestionNext" class="btn btn-outline-secondary btn-sm ms-2">Próxima página</button>
    							</c:when>
    							<c:otherwise> 
                      				<button type="submit" form="questionGroupRemoveQuestionNext" class="btn btn-outline-secondary btn-sm ms-2" disabled data-bs-toggle="button">Próxima página</button>
    							</c:otherwise>
					  		</c:choose>
						</div>
  						<div class="p-2 bd-highlight">
  							<c:if test = "${totalPages > 0}">
  								Página ${currentPage} de ${totalPages}
      						</c:if>
						</div>
					</div>
                                                        
                </div>

                <div class="col-md-4 p-4 justify-content-center">
                	<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">Dicas</h5>
					    <p class="card-text">
					    	Basta clicar no botão remover, para remover a pergunta selecionada do seu grupo de perguntas. 
					    </p>
					    <p class="card-text">
					    	A pergunta removida deste grupo não será excluída do banco de dados. Você poderá adicionar a pergunta removida novamente
					    	ao grupo de perguntas quando quiser.
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