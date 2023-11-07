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
                    <h3>Buscar grupo de perguntas.</h3>
                    
                    <c:forEach var="message" items="${Messenger.messageList}">
						<div class="${Messenger.divClass}" role="${Messenger.divRole}">
  							${message}
						</div>
					</c:forEach>
                        
                    <% Messenger.resetMessenger(); %>
                    
                    <form action="/supersabatina/controller" method="post">
                      <div class="mb-3 mt-3">
                      	<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="retrieveQuestionGroup" required/>
                      </div> 
                      <div class="mb-1 mt-3">
                      	<input type="text" class="form-control" id="txtSearch" name="txtSearch" placeholder="Digite o título do grupo para buscar"/>
                      </div> 
                      <button type="submit" class="btn btn-success mt-2">Buscar</button>
                    </form> 
                    
                    <div>
                    	<c:if test="${not empty questionGroupList}">
                    		<table class="table mt-4">
					 			<thead>
					    			<tr>
					      				<th scope="col">Título</th>
					      				<th scope="col">Ações</th>
					    			</tr>
					  			</thead>
					  		
					  			<tbody>
					  				<c:forEach var="questionGroup" items="${questionGroupList}">
					    				<tr>
					      					<td>${questionGroup.title}</td>
					      					<td><a class="btn btn-outline-secondary btn-sm" href="/supersabatina/controller?action=updateQuestionGroup&questionGroupId=${questionGroup.questionGroupId}" role="button">Detalhes</a></td>
					    				</tr>
					    			</c:forEach>
					  			</tbody>
							</table>
						</c:if>
					</div>
                                                        
                </div>

                <div class="col-md-4 p-4 justify-content-center">
                	<div class="card">
					  <div class="card-body">
					    <h5 class="card-title">Dicas</h5>
					    <p class="card-text">
					    	Se você executar uma busca sem digitar nenhum título, o sistema buscará todos os grupos de perguntas
					    	que você possui.
					    </p>
					    <p class="card-text">
					    	Cada letra ou palavra digitada será levada em conta para executar a busca, portanto você pode executar
					    	buscas pelo título parcial ou completo.
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