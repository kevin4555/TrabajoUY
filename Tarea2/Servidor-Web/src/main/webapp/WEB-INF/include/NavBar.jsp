<%@page import="logica.datatypes.Dtusuario"%>
<%@page import="model.TipoUsuario"%>
<%@page import="model.EstadoSesion"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
EstadoSesion estadoSesion = (EstadoSesion) session.getAttribute("estadoSesion");
Dtusuario usuario = (Dtusuario) session.getAttribute("usuarioLogueado");
TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuairo");
%> 
<nav class="navbar navbar-expand-lg">
  <div class="container">
    <div class="row w-100 justify-content-between align-items-center">
      <div class="col-2">
        <a class="nav-link text-white" href="<%= request.getContextPath() %>/home">
          <img src="/Servidor-Web/resource/img/Logo.svg" alt="Logo" class="navbar-brand img-fluid">
        </a>
      </div>
      <div class="col">
        <div class="row justify-content-center">
          <div class="col-5">
            <form class="form-inline">
              <div class="input-group">
                <input class="form-control" type="search" placeholder="Buscar oferta" aria-label="Search"/>
                <button class="btn btn-outline-success" type="submit">
                  <i class="bi bi-search text-white"></i>
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="col-3">
        <ul class="navbar-nav justify-content-evenly fs-3">
          <li class="nav-item">
            <a class="nav-link text-white" href="<%= request.getContextPath() %>/home">Inicio</a>
          </li>
          <% if (usuario != null) { %>
              <li class="nav-item">
                <% if (usuario.getImagen() != null) { %>
                  <img class="rounded-pill imgPerfil" src="data:image/png;base64,<%= usuario.getImagen() %>"/>
                <% } %>
              </li>
             
              <li class="nav-item">
                <div class="dropdown selectUsuario">
                  <button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown">
                    <%= usuario.getNickname() %>
                  </button>
                  <ul class="dropdown-menu">
                    <li>
                      <a class="dropdown-item" href="/perfil?nicknameUsuairo=<%= usuario.getNickname() %>">Perfil</a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="/logout">Cerrar Sesi�n</a>
                    </li>
                  </ul>
                </div>
              </li>
          <% } else { %>
            <li class="nav-item">
              <a class="nav-link text-white" href="<%= request.getContextPath() %>/login">Acceder</a>
            </li>
          <% } %>
        </ul>
      </div>
    </div>
  </div>
</nav>
</body>
</html>