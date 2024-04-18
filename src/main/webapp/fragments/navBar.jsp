<!-- Navbar -->
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
                            <a class="nav-link" href="/supersabatina/dashboard?action=dashboard">Painel</a>
                        </li>
                        
                        <li class="nav-item dropdown p-3">
                            <a class="nav-link dropdown-toggle" href="#" id="questionGroup" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                              Grupo de perguntas
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="questionGroup">
                              <li><a class="dropdown-item" href="/supersabatina/questionGroup?action=retrieveQuestionGroup">Pesquisar grupo de perguntas</a></li>
                              <li><a class="dropdown-item" href="/supersabatina/questionGroup?action=createQuestionGroup">Criar grupo de perguntas</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown p-3">
                            <a class="nav-link dropdown-toggle" href="#" id="question" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                              Perguntas
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="question">
                              <li><a class="dropdown-item" href="/supersabatina/question?action=retrieveQuestion">Pesquisar perguntas</a></li>
                              <li><a class="dropdown-item" href="/supersabatina/question?action=createQuestion">Criar perguntas</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown p-3">
                            <a class="nav-link dropdown-toggle" href="#" id="tutorial" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                              Tutoriais
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="tutorial">
                              <li><a class="dropdown-item" href="<%=request.getContextPath()%>/modules/tutorial/Como o sistema funciona.jsp">Como o sistema funciona?</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown p-3">
                            <a class="nav-link dropdown-toggle" href="#" id="profile" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                              ${authenticated.email}
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="perfil">
                              <li><a class="dropdown-item" href="#">Configurações</a></li>
                              <li><a class="dropdown-item" href="/supersabatina/login?action=logout">Sair</a></li>
                            </ul>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>
        <!-- End Navbar -->