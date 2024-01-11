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
                    <h3>Painel de controle.</h3>
                    
                    <c:forEach var="message" items="${Messenger.messageList}">
						<div class="${Messenger.divClass}" role="${Messenger.divRole}">
  							${message}
						</div>
					</c:forEach>
                        
                    <% Messenger.resetMessenger(); %>
                    
					<div class="row">
  						<div class="col-sm-6 mt-3">
    						<div class="card">
    							<c:choose>
        							<c:when test="${empty questionGroupList}">
            						<div class="card-body">
        								<h5 class="card-title">Ops!</h5>
        								<p class="card-text">
        									Você não possui grupo de perguntas para iniciar os estudos.
        									Crie ao menos um grupo de perguntas para iniciar. Tenha em mente que um grupo de perguntas deve
        									ter muitas perguntas para que seu estudo seja efetivo. 
        								</p>
      								</div>
        							</c:when>
        							<c:otherwise>
            						<div class="card-body">
        								<h5 class="card-title">Iniciar os estudos!</h5>
        								<p class="card-text">
        									Selecione o grupo de perguntas abaixo e comece agora seus estudos.
        								</p>
        								<form action="/supersabatina/game" method="post">
        									<div class="mt-3">
        										<input type="hidden" class="form-control" id="txtAction" name="txtAction" value="gameQuestion" required/>
                      							<select class="form-select" aria-label="optQuestionGroup" name="optQuestionGroup">
                      								<c:forEach var="questionGroup" items="${questionGroupList}">
														<option value="${questionGroup.questionGroupId}">${questionGroup.title}</option>
													</c:forEach>
												</select>
                      						</div>
        									<button type="submit" class="btn btn-success mt-3">Iniciar</button>
        								</form>
      								</div>
        							</c:otherwise>
    							</c:choose>
    						</div>
  						</div>
  						
  						<div class="col-sm-6 mt-3">
    						<div class="card">
    							<c:choose>
        							<c:when test="${totalQuestionByDate > 0}">
	        							<div class="card-body">
		        							<h5 class="card-title">Estatísticas do dia</h5>
		        							<div class="mt-3">
		        								<div>Perguntas respondidas hoje: ${totalQuestionByDate} </div>
		        							</div>
		        							<div class="mt-3">
							                    Sua taxa de sucesso hoje:
							    				<div class="progress">
							  						<div class="progress-bar bg-success" role="progressbar" style="width: ${successRateByDate}%;" aria-valuenow="${successRateByDate}" aria-valuemin="0" aria-valuemax="100">${successRateByDate}%</div>
												</div>
							    				<br>
							    							
							    				Sua taxa de falha hoje:
							    				<div class="progress">
							  						<div class="progress-bar bg-danger" role="progressbar" style="width: ${failureRateByDate}%;" aria-valuenow="${failureRateByDate}" aria-valuemin="0" aria-valuemax="100">${failureRateByDate}%</div>
												</div>
											</div>
	      								</div>
        							</c:when>
        							
        							<c:otherwise>
	        							<div class="card-body">
		        							<h5 class="card-title">Estatísticas do dia</h5>
		        							<div class="mt-3">
		        								<p>Você ainda não respondeu perguntas hoje, estudar diariamente é a chave para ao sucesso! </p>
											</div>
	      								</div>
        							</c:otherwise>
    							</c:choose>
    						</div>
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