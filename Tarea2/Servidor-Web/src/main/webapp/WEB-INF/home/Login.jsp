<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Iniciar Sesión</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resource/css/general.css" />
    <jsp:include page="../include/Head.jsp" />
</head>
<body class="login">
	<%
    String contextPath = request.getContextPath();
    %>
    
    <form action="<%= contextPath %>/login" method="post" class="formLogin">
        <a class="nav-link text-white" href="<%= contextPath %>/home">
            <img src="/Servidor-Web/resource/img/Logo.svg" alt="Logo" class="navbar-brand img-fluid" />
        </a>

        <div class="mb-3 mt-3">
            <label for="email" class="form-label"><strong>*Email o nickname:</strong></label>
            <input type="text" class="form-control" id="email" name="nombreEmail" />
        </div>
        <div class="mb-3">
            <label for="pwd" class="form-label"><strong>*Contraseña:</strong></label>
            <input type="password" class="form-control" id="pwd" name="contrasenia" />
        </div>
        <div class="mb-3">
            <input class="btn btn-primary" type="submit" value="Confirmar" />
            <a class="btn btn-primary" href="<%= contextPath %>/home">Cancelar</a>
        </div>
        <div class="mb-3">
            <label class="form-label mb-3"><strong>¿No tienes cuenta?</strong></label>
            <a class="btn btn-primary" href="<%= contextPath %>/altaUsuario">Registrarse</a>
        </div>
    </form>
</body>
</html>
