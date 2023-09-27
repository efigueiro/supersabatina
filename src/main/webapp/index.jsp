<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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

        <!--Navbar-->

        <nav class="navbar navbar-expand-sm bg-white shadow p-2 mb-5">
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
                        
                        <li class="nav-item p-3">
                            <a class="nav-link" href="#"><strong></strong></a>
                        </li>
                        
                        <li class="nav-item p-3">
                            <a class="nav-link" href="#"><strong></strong></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!--End Navbar-->

        <!-- Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-8 p-4 justify-content-center">
                    <h1>Memorize com mais eficiência.</h1>
                    <p class="lead">
                        Experimente um sistema de estudos inteligente baseado na técnica de cartões de memorização. Seja testado diariamente através de revisões periódicas de seus estudos. Acompanhe seu progresso, memorize com mais
                        facilidade e obtenha maior taxa de sucesso nas suas provas e avaliações.
                    </p>
                    <a class="btn btn-success" href="login.jsp" role="button">Login</a>
                    <a class="btn btn-success" href="createAccount.jsp" role="button">Inscreva-se</a>
                </div>

                <div class="col-md-4 p-4 justify-content-center">
                    <img src="<%=request.getContextPath()%>/img/girlbook.png" alt="" class="img-fluid d-none d-md-block w-100" />
                </div>
            </div>
        </div>

        <section class="p-2 p-lg-0" id="questions">
            <div class="container">
                <div class="row">
                    <h4 class="pb-3">Perguntas frequentes:</h4>
                </div>
                <div class="accordion accordion-flush" id="accordion">
                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                                Como o sistema funciona?
                            </button>
                        </h2>
                        <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">
                                O Super Sabatina é um sistema baseado em uma variação do uso de cartões de memória, mais popularmente conhecidos como flash cards. O sistema possibilita avaliações periódicas do conteúdo estudado por meio de
                                rodadas de perguntas criadas pelo próprio usuário.
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                                Como funciona o compartilhamento de perguntas entre outros usuários?
                            </button>
                        </h2>
                        <div id="flush-collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">
                                Ao criar uma pergunta com suas respectivas alternativas de resposta, o usuário tem a opção de tornar a sua pergunta pública. Desta forma, o usuário pode ajudar outros estudantes aumentando a quantidade de
                                perguntas existentes no sistema.
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                                Como funciona o monitoramento do progresso do estudante?
                            </button>
                        </h2>
                        <div id="flush-collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">
                                O usuário pode verificar seu progresso a qualquer momento acessando o grupo de perguntas selecionado. Também é possível verificar a taxa de sucesso para cada pergunta individualmente. No caso de perguntas
                                públicas é possível até mesmo visualizar um ranking dos usuários com melhor taxa de sucesso para aquela questão.
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--End Content-->

        <!--Footer-->

        <footer class="p5 mt-4 text-secondary text-center position-relative">
            <p class="">Copyright &copy; 2023 Super Sabatina</p>
        </footer>

        <!--End Footer-->

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    </body>
</html>