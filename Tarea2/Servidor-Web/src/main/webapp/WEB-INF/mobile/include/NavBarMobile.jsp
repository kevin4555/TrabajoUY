<%@page import="logica.datatypes.Dtusuario"%>
<%@page import="model.TipoUsuario"%>
<%@page import="model.EstadoSesion"%>

<%
EstadoSesion estadoSesion = (EstadoSesion) session.getAttribute("estadoSesion");
Dtusuario usuario = (Dtusuario) session.getAttribute("usuarioLogueado");
TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuairo");
%>
<nav class="navbar navbar-expand-lg">
	<div class="container">
		<div class="row w-100 justify-content-between align-items-center">
			<div class="d-flex justify-content-center align-items-center w-50">
				<a class="nav-link text-white"
					href="<%=request.getContextPath()%>/home"> <img
					src="<%=request.getContextPath()%>/resource/img/Logo.svg"
					alt="Logo" class="navbar-brand img-fluid">
				</a>
			</div>
			<div class="col-3">
				<%
				if (usuario != null)
				{
				%>
				<li class="nav-item">
					<%
					if (usuario.getImagen() != null)
					{
					%> <img class="rounded-pill imgPerfil"
					src="data:image/png;base64,<%=usuario.getImagenBase64()%>" /> <%
 }
 %>
					<%
					}
					%>
				
			</div>
		</div>
		<div class="row w-100 justify-content-between align-items-center">
			<div class="col-5">
				<form class="form-inline">
					<div class="input-group">
						<input class="form-control" type="search"
							placeholder="Buscar oferta" aria-label="Search">
						<button class="btn btn-outline-success" disabled>
							<i class="bi bi-search text-white"></i>
						</button>
					</div>
				</form>
			</div>

			<div class="col-3">
				<ul class="navbar-nav justify-content-end fs-3">
					<%
						if (usuario != null)
						{
						%>
					<li class="nav-item">

						<div class="dropdown selectUsuario">
							<button type="button" class="btn dropdown-toggle"
								data-bs-toggle="dropdown" aria-expanded="true">
								<%=usuario.getNickname()%>
							</button>
							<ul class="dropdown-menu">
								<li>
									<%
									String contextPath = request.getContextPath();
									%> <a class="dropdown-item"
									href="<%=contextPath%>/perfil?nicknameUsuario=<%=usuario.getNickname()%>">Perfil</a>
								</li>
								<li><a class="dropdown-item btn btn-primary"
									href="<%=contextPath%>/logout">Cerrar Sesion</a></li>
							</ul>
						</div> <%
 }
 else
 {
 %> <%
 String contextPath = request.getContextPath();
 %> <a class="nav-link text-white" href="<%=contextPath%>/login">Acceder</a>
						<%
 }
 %>
					</li>
				</ul>
			</div>
		</div>
	</div>
</nav>
</body>
</html>