<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="br.com.supersabatina.model.entity.User"%>

<%
// get authenticated user
User authenticated = new User();
authenticated = (User) request.getSession().getAttribute("authenticated");
%>

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
                    <h1>Olá, bem-vindo ao Super Sabatina.</h1>
                    <br>
                    <p class="lead">
                        O Super Sabatina é um sistema simples, criado para auxiliá-lo em seus estudos.<br>
                        O sistema funciona de forma similar ao sistema de estudos por flashcards, onde o usuário cria perguntas com suas respectivas respostas 
                        no sistema e, depois disso, é testado periodicamente para facilitar a memorização do conteúdo.
                    </p>   
                    
                    <p class="lead">
                        No Super Sabatina, a primeira coisa que o usuário deve fazer é criar um grupo de perguntas, que servirá para agrupar as perguntas do usuário. 
                        O sistema usará o grupo de perguntas para criar uma sessão de perguntas e testar o usuário. Por este motivo, o grupo de perguntas é tão importante.
                    </p>
                    
                    <p class="lead">
                        O segundo passo deve ser a criação das perguntas para o grupo de perguntas já criado. Para o melhor funcionamento do método de estudos, 
                        é necessário que o usuário possua muitas perguntas em seu grupo de perguntas.
                    </p>
                    
                    <p class="lead">
                        O terceiro passo será a execução da sessão de perguntas, para isso, basta entrar no sistema, escolher para qual grupo você gostaria de testar 
                        seus conhecimentos e clicar no botão iniciar. Você também pode escolher a opção chamada Painel no menu superior para voltar para a tela de criação 
                        de sessão de perguntas a qualquer momento.
                    </p>
                    
                   <h4>Como criar um grupo de perguntas?</h4>
                    <p class="lead">
                        Para criar um grupo de perguntas, basta acessar o menu superior chamado grupo de perguntas e escolher a opção criar grupo de perguntas.<br> 
                        Na tela de criação de novo grupo de perguntas, defina um título para seu grupo e uma breve descrição (opcional). Depois disso, basta clicar 
                        no botão chamado Criar grupo.
                    </p>   
                    
                    <h4>Como criar perguntas?</h4>
                    <p class="lead">
                        Para criar perguntas, basta acessar o menu superior chamado perguntas e escolher a opção criar perguntas. Na tela de criação de perguntas 
                        os seguintes campos serão apresentados para o usuário:
                    </p>
                    	<ul class="lead">
    						<li>No primeiro campo, escolha o grupo de perguntas.</li>
    						<li>No segundo campo, defina se a pergunta será privada ou pública.</li>
    						<li>No terceiro campo, escolha um assunto para agrupar suas perguntas.</li>
    						<li>No quarto campo, defina o enunciado da pergunta.</li>
    						<li>No quinto campo, adicione a resposta correta da pergunta.</li>
						</ul>
                     <p class="lead">
                        Tenha em mente que para o campo assunto, você pode adicionar mais de um assunto separado por espaço ou vírgula. Você também pode adicionar 
                        uma breve justificativa para a resposta correta no campo de resposta, desta forma ao conferir a resposta, você terá mais informações para estudo. 
                        Uma pergunta definida como pública poderá ser usada por todos os usuários do sistema, mas não poderá ser alterada ou deletada por eles. 
                        Somente o criador da questão poderá alterar ou deletar a questão.
                    </p>
                    
                    <h4>Como executar uma sessão de perguntas?</h4>
                    <p class="lead">
                        Para executar uma sessão de perguntas, o usuário deverá primeiramente ter um grupo de perguntas criado com perguntas disponíveis. 
                        Tenha em mente que para executar uma sessão de perguntas, muitas perguntas devem estar disponíveis no seu grupo de perguntas. Um número aceitável 
                        de perguntas para um estudo eficiente seria por volta de 50 perguntas.
                    </p>
                    
                    <p class="lead">
                        Quando o usuário entra no sistema, já é direcionado para a página de execução de sessão de perguntas. Caso isso não aconteça, ou o usuário queira 
                        acessar esta tela, basta clicar no menu superior chamado Painel. Para executar uma sessão de perguntas, basta escolher o grupo de perguntas no painel 
                        de controle e clicar no botão iniciar. Logo depois disso, será apresentada para o usuário a primeira pergunta para ser respondida. Neste momento, 
                        leia atentamente a pergunta, responda mentalmente e clique no botão chamado Ver a resposta. Uma nova tela com a resposta da questão será apresentada 
                        para o usuário. Neste momento, é muito importante informar corretamente para o sistema se você acertou ou errou a questão apresentada. 
                        Basta clicar no botão verde, caso tenha respondido mentalmente a questão de forma correta, ou o botão vermelho, caso tenha respondido a questão de forma errada. 
                    </p>
                    
                    <h4>Como funcionam as estatísticas?</h4>
                    <p class="lead">
                        Após finalizar uma pergunta, informando ao sistema se você errou ou acertou a questão, o sistema apresentará uma tela informando sua taxa de sucesso para 
                        aquela pergunta em particular. Dessa forma, você saberá se você está com um bom conhecimento ou não para aquela determinada questão. 
                        Ao acessar o menu superior chamado Painel (Painel de controle), o sistema apresentará sua taxa de sucesso diária, informando quantas questões 
                        foram respondidas na data atual e sua taxa de sucesso geral. 
                    </p>
                                                         
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