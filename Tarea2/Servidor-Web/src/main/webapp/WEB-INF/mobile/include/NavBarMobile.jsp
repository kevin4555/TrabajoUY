<%@page import="logica.webservices.DtUsuario"%> <%@page
import="model.TipoUsuario"%> <%@page import="model.EstadoSesion"%> <%@page
import="java.util.ArrayList"%> <% EstadoSesion estadoSesion = (EstadoSesion)
session.getAttribute("estadoSesion"); DtUsuario usuario = (DtUsuario)
session.getAttribute("usuarioLogueado"); TipoUsuario tipoUsuario = (TipoUsuario)
session.getAttribute("tipoUsuairo"); %>
<nav class="navbar navbar-expand-lg">
  <div class="container">
    <div class="row w-100 justify-content-between align-items-center">
      <div class="d-flex justify-content-center align-items-center w-50">
        <a
          class="nav-link text-white"
          href="<%=request.getContextPath()%>/home"
        >
          <img
            src="<%=request.getContextPath()%>/resource/img/Logo.svg"
            alt="Logo"
            class="navbar-brand img-fluid"
          />
        </a>
      </div>
      <div class="col-4">
        <% if (usuario != null) { %> <% if (usuario.getImagenBase64() != null) { %>
        <ul class="list-unstyled">
          <li class="nav-item">
            <img
              class="rounded-pill imgPerfil"
              src="data:image/png;base64,<%=usuario.getImagenBase64()%>"
            />
            <% } %>
          </li>
        </ul>
        <% } %>
      </div>
    </div>
    <div class="row w-100 justify-content-between align-items-center">
      <div class="col-5">
        <div>
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarToggleExternalContent"
            aria-controls="navbarToggleExternalContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
        </div>
      </div>

      <div class="col-4">
        <ul class="navbar-nav justify-content-end fs-3">
          <% String contextPath = request.getContextPath(); %>
          <li>
            <a
              class="cerrarSesionBtn btn btn-primary"
              href="<%=contextPath%>/logout"
              >Cerrar Sesion</a
            >
          </li>
        </ul>
      </div>
    </div>
  </div>
</nav>
