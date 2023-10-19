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
      							<div class="card-body">
        							<h5 class="card-title">Special title treatment</h5>
        							<p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
        							<a href="#" class="btn btn-success">Go somewhere</a>
      							</div>
    						</div>
  						</div>
  						<div class="col-sm-6 mt-3">
    						<div class="card">
      							<div class="card-body">
        							<h5 class="card-title">Special title treatment</h5>
        							<p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
        							<a href="#" class="btn btn-success">Go somewhere</a>
      							</div>
    						</div>
  						</div>
					</div>
					
					<div class="row">
  						<div class="col-sm-6 mt-3">
    						<div class="card">
      							<div class="card-body">
        							<h5 class="card-title">Special title treatment</h5>
        							<p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
        							<a href="#" class="btn btn-success"">Go somewhere</a>
      							</div>
    						</div>
  						</div>
  						<div class="col-sm-6 mt-3">
    						<div class="card">
      							<div class="card-body">
        							<h5 class="card-title">Special title treatment</h5>
        							<p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
        							<a href="#" class="btn btn-success"">Go somewhere</a>
      							</div>
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