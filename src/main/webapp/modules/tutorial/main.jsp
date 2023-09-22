<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="br.com.supersabatina.config.MessageConfig"%>
<%@ page import="br.com.supersabatina.model.entity.User"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title><%=MessageConfig.getProperty("application.name")%></title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous" />
        <link rel="icon" href="<%=request.getContextPath()%>/img/question.png" />
    </head>
    
    
    <!--java-->
  	<%
  		// get authenticated user
  		User authenticated = new User();
  		authenticated = (User) request.getSession().getAttribute("authenticated");
	%>
 	 <!--end java-->

    <body class="bg-white">

        <!--Navbar-->

        <nav class="navbar navbar-expand-lg bg-white shadow p-2 mb-5">
            <div class="container-fluid">
            	<img src="<%=request.getContextPath()%>/img/logo.png" alt="" class="img-fluid ms-2"/>
            	<a class="navbar-brand p-2 text-black fs-3" href="#">Super Sabatina</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#topMenu" aria-controls="topMenu" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="topMenu">
                    <ul class="navbar-nav me-auto mb-2 mb-sm-0 lead">
                    	<li class="nav-item p-3">
                            <a class="nav-link" href="#"><strong></strong></a>
                        </li>
                        
                        <li class="nav-item dropdown p-3">
                            <a class="nav-link dropdown-toggle" href="#" id="questionGroup" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                              Grupo de Perguntas
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="questionGroup">
                              <li><a class="dropdown-item" href="#">Pesquisar grupo de perguntas</a></li>
                              <li><a class="dropdown-item" href="#">Criar grupo de perguntas</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown p-3">
                            <a class="nav-link dropdown-toggle" href="#" id="question" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                              Perguntas
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="question">
                              <li><a class="dropdown-item" href="#">Pesquisar perguntas</a></li>
                              <li><a class="dropdown-item" href="#">Criar perguntas</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown p-3">
                            <a class="nav-link dropdown-toggle" href="#" id="tutorial" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                              Tutoriais
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="tutorial">
                              <li><a class="dropdown-item" href="#">Como o sistema funciona?</a></li>
                              <li><a class="dropdown-item" href="#">Como funciona o grupo de perguntas?</a></li>
                              <li><a class="dropdown-item" href="#">Como funciona as perguntas?</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown p-3">
                            <a class="nav-link dropdown-toggle" href="#" id="profile" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                              ${authenticated.email}
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="perfil">
                              <li><a class="dropdown-item" href="#">Configurações</a></li>
                              <li><a class="dropdown-item" href="#">Sair</a></li>
                            </ul>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>

        <!--End Navbar-->

        <!--Content-->

        <div class="container">
            <div class="row">
                <div class="col-md-8 p-4 justify-content-center">
                    <h1>Olá, bem vindo ao Super Sabatina.</h1>
                    <p class="lead">
                        Posso ver que esta é a primeira vez que você utiliza o sistema. Antes de mais nada, recomendo você acesse o menu chamado tutoriais. 
                        Aproveite para dar uma olhada nas orientações sobre como utilizar o sistema. Inicialmente você deve criar um grupo de perguntas. 
                        Depois disso, você deve criar as perguntas para que possa começar a utilizar o sistema de fato. 
                    </p>                                     
                </div>

                <div class="col-md-4 p-4 justify-content-center">
                    <img src="<%=request.getContextPath()%>/img/teacher.png" alt="" class="img-fluid d-none d-md-block w-100" />
                </div>
            </div>
        </div>

        <!--End Content-->

        <!--Footer-->

        <footer class="p5 mt-4 text-secondary text-center position-relative">
            <p class="">Copyright &copy; 2023 Super Sabatina</p>
        </footer>

        <!--End Footer-->

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    </body>
</html>