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
                    <h3>Buscar perguntas.</h3>
                    
                    <c:forEach var="message" items="${Messenger.messageList}">
						<div class="${Messenger.divClass}" role="${Messenger.divRole}">
  							${message}
						</div>
					</c:forEach>
                        
                    <% Messenger.resetMessenger(); %>
                    
                    <form action="/supersabatina/retrieveQuestion" method="post">
                      <c:choose>
    					<c:when test="${empty search}">
        					<div class="mb-1 mt-3">
                      			<input type="text" class="form-control" id="txtSearch" name="txtSearch" placeholder="Digite a pergunta para buscar" required/>
                      		</div> 
    					</c:when>
    					<c:otherwise> 
        					<div class="mb-1 mt-3">
                      			<input type="text" class="form-control" id="txtSearch" name="txtSearch" value="${search}" required/>
                      		</div> 
    					</c:otherwise>
					  </c:choose>
                      
                      <div class="mt-3">
                      	<select class="form-select" aria-label="optVisibility" name="optVisibility">
                      		<c:forEach var="option" items="${visibilityOptionList}">
                				<option value="${option.value}"
                    				<c:if test="${option.value eq visibilitySelected}">
                    					selected="selected"
                    				</c:if>>${option.label}
                				</option>
            				</c:forEach>
						</select>
                      </div>
                      
                      <button type="submit" class="btn btn-success mt-2">Buscar</button>
                    </form> 
                    
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
					      					<td><a class="btn btn-outline-secondary btn-sm" href="/supersabatina/updateQuestion?questionGroupId=${question.questionId}" role="button">Detalhes</a></td>
					    				</tr>
					    			</c:forEach>
					  			</tbody>
							</table>
						</c:if>
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