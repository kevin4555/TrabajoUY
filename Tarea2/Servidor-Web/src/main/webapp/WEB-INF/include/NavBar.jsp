
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
            <a href="${pageContext.request.contextPath}/home"
              ><img
                src="${pageContext.request.contextPath}/resource/img/Logo.svg"
                alt="Logo"
                class="navbar-brand img-fluid"
            /></a>
          </div>

          <div class="col">
            <div class="row justify-content-center">
              <div class="col-5">
                <form class="form-inline">
                  <div class="input-group">
                    <input
                      class="form-control"
                      type="search"
                      placeholder="Buscar oferta"
                      aria-label="Search"
                    />
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
                <a class="nav-link text-white" href="${pageContext.request.contextPath}/home"
                  >Inicio</a
                >
              </li>
              <c:choose>
              
              <c:when test="${usuario != null }">
              <li class="nav-item">
              <c:if test="${not empty usuario.imagen}">
               <img
                  class="rounded-pill imgPerfil"
                  src="data:image/png;base64,${oferta.imagenBase64}"
                />
              </c:if>
               
              </li>
              <li class="nav-item">
                <div class="dropdown selectUsuario">
                  <button
                    type="button"
                    class="btn dropdown-toggle"
                    data-bs-toggle="dropdown"
                  >
                    ${usuario.nickname}
                  </button>
                  <ul class="dropdown-menu">
                    <li>
                    <c:url var="usuarioUrl" value="/perfil">
					<c:param name="nicknameUsuairo" value="${usuario.nickname}" />
					</c:url>

					
                      <a
                        class="dropdown-item"
                        href="${usuarioUrl}"
                        >Perfil</a
                      >
                    </li>
                    <li>
                      <a class="dropdown-item" href="/logout"
                        >Cerrar Sessi�n</a
                      >
                    </li>
                  </ul>
                </div>
              </li>
              
              
              </c:when>
              <c:otherwise>
              
               <li class="nav-item">
               <c:url var="loginUrl" value="/login"></c:url>
				
              <a class="nav-link text-white" href="${loginUrl}">Acceder</a>
            </li>
              
              </c:otherwise>
              
              </c:choose>
            </ul>
          </div>
        </div>
      </div>
    </nav>
  </body>
</html>