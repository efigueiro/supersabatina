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
                              Perfil
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