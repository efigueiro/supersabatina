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
                <div class="col-md-8 p-4 justify-content-center">
                    <h3>Sessão de perguntas</h3>
                    
                    <c:forEach var="message" items="${Messenger.messageList}">
						<div class="${Messenger.divClass}" role="${Messenger.divRole}">
  							${message}
						</div>
					</c:forEach>
                        
                    <% Messenger.resetMessenger(); %>
                    
					<div class="card text-dark bg-light mb-3 mt-4">
  						<div class="card-header">Cartão de resposta</div>
  						<div class="card-body">
    						<h5 class="card-title"></h5>
    						<p class="card-text">
    							${question.answer}
    						</p>
  						</div>
					</div>
					
					<div>
						<form id="correctAnswer" action="/supersabatina/game" method="post">
							<div class="mb-3 mt-3">
                      			<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="correctAnswer" required/>
                      			<input type="hidden" class="form-control" id="txtQuestionId" name="txtQuestionId" value="${question.questionId}" required/>
                      			<input type="hidden" class="form-control" id="txtQuestionGroupId" name="txtQuestionGroupId" value="${questionGroupId}" required/>
                      		</div>
						</form>
						
						<form id="incorrectAnswer" action="/supersabatina/game" method="post">
							<div class="mb-3 mt-3">
                      			<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="incorrectAnswer" required/>
                      			<input type="hidden" class="form-control" id="txtQuestionId" name="txtQuestionId" value="${question.questionId}" required/>
                      			<input type="hidden" class="form-control" id="txtQuestionGroupId" name="txtQuestionGroupId" value="${questionGroupId}" required/>
                      		</div>
						</form>
					
						<div>
							<button form="correctAnswer" type="submit" class="btn btn-success mt-1">Acertei a pergunta</button>
							<button form="incorrectAnswer" type="submit" class="btn btn-danger mt-1 ms-2">Errei a pergunta</button>
						</div>
					</div>
					
                </div>

                <div class="col-md-4 p-4 justify-content-center">
                    <img src="<%=request.getContextPath()%>/img/girlbook.png" alt="" class="img-fluid d-none d-md-block w-100" />
                </div>
            </div>
        </div>
        <!-- End Content -->

		<jsp:include page="/fragments/footer.jsp" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    </body>
</html>