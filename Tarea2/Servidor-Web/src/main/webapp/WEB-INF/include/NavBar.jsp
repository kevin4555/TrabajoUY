<%@page import="main.java.webservices.DtUsuario"%>
<%@page import="model.TipoUsuario"%>
<%@page import="model.EstadoSesion"%>

<%
EstadoSesion estadoSesion = (EstadoSesion) session.getAttribute("estadoSesion");
DtUsuario usuario = (DtUsuario) session.getAttribute("usuarioLogueado");
TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuairo");
%>

<nav class="navbar navbar-expand-lg">
	<div class="container">
		<div class="row w-100 justify-content-between align-items-center">
			<div class="col-2">
				<a class="nav-link text-white"
					href="<%=request.getContextPath()%>/home"> <img
					src="<%=request.getContextPath()%>/resource/img/Logo.svg"
					alt="Logo" class="navbar-brand img-fluid">
				</a>
			</div>
			<div class="col">
				<div class="row justify-content-center">
					<div class="col-5">
						<form class="form-inline" action="<%=request.getContextPath()%>/search" method="post">
							<div class="input-group">
								<input class="form-control" type="search"
									placeholder="Buscar" aria-label="Search" name="toSearch">
								<button class="btn btn-outline-success" >
									<i class="bi bi-search text-white"></i>
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-3">
				<ul class="navbar-nav justify-content-evenly fs-3">
					<%
					if (usuario != null)
					{
					%>
					<li class="nav-item">
						<%
						if (usuario.getImagenBase64() != null)
						{
						%> <img class="rounded-pill imgPerfil"
						src="data:image/png;base64,<%=usuario.getImagenBase64()%>" /> <%
 }
 %>
					</li>
					<li>
                     <div class="btn-group">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <%=usuario.getNickname()%>
                                </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" href="<%=request.getContextPath()%>/perfil?nicknameUsuario=<%=usuario.getNickname()%>">Perfil</a>
                                    <a class="dropdown-item" href="<%=request.getContextPath()%>/logout">Cerrar Sesión</a>
                                </div>
                            </div>
                        </li>
					<%
					}
					else
					{
					%>
					<li class="nav-item">
						<%
						String contextPath = request.getContextPath();
						%> <a class="nav-link text-white" href="<%=contextPath%>/login">Acceder</a>
					</li>
					<%
					}
					%>
				</ul>
			</div>
		</div>
	</div>
</nav>
</body>
</html>