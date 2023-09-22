<%@ page import="br.com.supersabatina.config.MessageConfig"%>
		<!-- Navbar -->
		<nav class="navbar navbar-expand-lg bg-white shadow p-2 mb-5">
            <div class="container-fluid">
            	<img src="<%=request.getContextPath()%>/img/logo.png" alt="" class="img-fluid ms-2"/>
            	<a class="navbar-brand p-2 text-black fs-3" href="#"><%=MessageConfig.getProperty("application.name")%></a>
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
                              <%=MessageConfig.getProperty("page.title.questionGroup")%>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="questionGroup">
                              <li><a class="dropdown-item" href="#"><%=MessageConfig.getProperty("page.title.searchQuestionGroup")%></a></li>
                              <li><a class="dropdown-item" href="#"><%=MessageConfig.getProperty("page.title.createQuestionGroup")%></a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown p-3">
                            <a class="nav-link dropdown-toggle" href="#" id="question" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                              <%=MessageConfig.getProperty("page.title.questions")%>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="question">
                              <li><a class="dropdown-item" href="#"><%=MessageConfig.getProperty("page.title.searchQuestion")%></a></li>
                              <li><a class="dropdown-item" href="#"><%=MessageConfig.getProperty("page.title.createQuestion")%></a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown p-3">
                            <a class="nav-link dropdown-toggle" href="#" id="tutorial" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                              <%=MessageConfig.getProperty("page.title.tutorial")%>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="tutorial">
                              <li><a class="dropdown-item" href="#"><%=MessageConfig.getProperty("page.title.howSystemWorks")%></a></li>
                              <li><a class="dropdown-item" href="#"><%=MessageConfig.getProperty("page.title.howCreateQuestionGroup")%></a></li>
                              <li><a class="dropdown-item" href="#"><%=MessageConfig.getProperty("page.title.howCreateQuestion")%></a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown p-3">
                            <a class="nav-link dropdown-toggle" href="#" id="profile" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                              ${authenticated.email}
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="perfil">
                              <li><a class="dropdown-item" href="#"><%=MessageConfig.getProperty("page.title.config")%></a></li>
                              <li><a class="dropdown-item" href="/supersabatina/login?action=logout"><%=MessageConfig.getProperty("page.title.logoff")%></a></li>
                            </ul>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>
        <!-- End Navbar -->