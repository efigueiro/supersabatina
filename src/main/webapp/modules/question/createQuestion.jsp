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
                <div class="col-md-8 p-4 justify-content-center">
                    <h3>Criação de perguntas.</h3>
                    
                    <c:forEach var="message" items="${Messenger.messageList}">
						<div class="${Messenger.divClass}" role="${Messenger.divRole}">
  							${message}
						</div>
					</c:forEach>
                        
                    <% Messenger.resetMessenger(); %>
                    
                    <form action="/supersabatina/createQuestion" method="post">
                      <div class="mt-3">
                      	<select class="form-select" aria-label="optQuestionGroup" name="optQuestionGroup">
                      		<c:forEach var="questionGroup" items="${questionGroupList}">
								<option value="${questionGroup.questionGroupId}">${questionGroup.title}</option>
							</c:forEach>
						</select>
                      </div>
                      <div class="mt-3">
                      	<select class="form-select" aria-label="optPrivate" name="optVisibility">
  							<option value="private">Privada</option>
  							<option value="public">Pública</option>
						</select>
                      </div>
                      <div class="mt-3">
                      	<input type="text" class="form-control" id="txtSubject" name="txtSubject" placeholder="Assuntos" required/>
                      </div> 
                      <div class="mt-3">
                      	<textarea class="form-control" id="txtQuestion" name="txtQuestion" rows="3" placeholder="Escreva sua pergunta" ></textarea>
                      </div>
                      
                      <div class="mt-3">
                      	<textarea class="form-control" id="txtAnswer" name="txtAnswer" rows="3" placeholder="Escreva a resposta da pergunta" ></textarea>
                      </div>
                      
                      <button type="submit" class="btn btn-success mt-3">Criar pergunta</button>
                    </form> 
                                                        
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