<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="br.com.supersabatina.util.Messenger"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Super Sabatina</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous" />
        <link rel="icon" href="img/question.png" />
    </head>

    <body class="bg-white">
        <div class="container">
            <div class="row p-md-5">
                <div class="col-sm-6 p-4 justify-content-center">
                    <img src="img/girlbooklogin.png" alt="" class="img-fluid d-none d-sm-block" />
                </div>

                <div class="col-sm-6 p-4 justify-content-center">
                    <form action="/supersabatina/login" method="post">
                        <h1 class="mb-5">Login</h1>

                        <c:forEach var="message" items="${Messenger.messageList}">
							<div class="${Messenger.divClass}" role="${Messenger.divRole}">
  								${message}
							</div>
						</c:forEach>
                        
                        <% Messenger.resetMessenger(); %>

                        <div class="mb-3 mt-3">
                            <input type="email" class="form-control" id="txtEmail" name="txtEmail" placeholder="Email" required/>
                        </div>
                        <div class="mb-4">
                            <input type="password" class="form-control" id="txtPassword" name="txtPassword" placeholder="Senha" required="required"/>
                        </div>
                        <button type="submit" class="btn btn-success">Entrar</button>
                        <a class="btn btn-secondary" href="index.jsp" role="button">Voltar</a>
                    </form>

                    <figure class="text-end mt-5">
                        <blockquote class="blockquote">
                            <p>Você é mais corajoso do que acredita, mais forte do que parece e mais inteligente do que pensa.</p>
                        </blockquote>
                        <figcaption class="blockquote-footer">A.A Milne <cite title="Source Title"></cite></figcaption>
                    </figure>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    </body>
</html>
