<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Iniciar Sesión</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      type="text/css"
      href="resource/css/general.css"
    />
    <jsp:include page="../include/Head.jsp" />
  </head>
  <body class="login">
    <form action="/login" method="post" class="formLogin" onsubmit="return validarFormulario();">
      <a
        class="nav-link text-white"
        href="${pageContext.request.contextPath}/home"
      >
        <img
          src="/Servidor-Web/resource/img/Logo.svg"
          alt="Logo"
          class="navbar-brand img-fluid"
        />
      </a>

      <div class="mb-3 mt-3">
        <label for="email" class="form-label"
          ><strong>*Email o nickname:</strong></label
        >
        <input
          type="email"
          class="form-control"
          id="email"
          name="nombreEmail"
        />
      </div>
      <div class="mb-3">
        <label for="pwd" class="form-label"
          ><strong>*Contraseña:</strong></label
        >
        <input
          type="password"
          class="form-control"
          id="pwd"
          name="contrasenia"
        />
      </div>
      <div class="mb-3">
        <label class="form-label mb-3"
          ><strong>¿No tienes cuenta?</strong></label>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/altaUsuario">Registrarse</a>     
      </div>
    </form>
    <!--  <script src="resource/javaScript/app.js"></script>-->
    <script src="resource/javaScript/enviarFormularioLogin.js"></script>
  </body>
</html>
