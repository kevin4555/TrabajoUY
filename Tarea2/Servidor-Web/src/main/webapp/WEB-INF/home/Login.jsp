<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="../../webapp/recourse/css/general.css">
    <jsp:include page="../include/Head.jsp"/>
</head>
<body>

<form class="formLogin" action="">
    <a href="../../index.html"><img src="../img/Logo.svg" alt="Logo" class="navbar-brand img-fluid" /></a>
    <div class="mb-3 mt-3">
        <label for="email" class="form-label"><strong>*Email o nickname:</strong></label>
        <input type="email" class="form-control" id="email" name="email" />
    </div>
    <div class="mb-3">
        <label for="pwd" class="form-label"><strong>*Contraseña:</strong></label>
        <input type="password" class="form-control" id="pwd" name="pswd" />
    </div>
    <div class="mb-3">
        <label class="form-label mb-3"><strong>¿No tienes cuenta?</strong></label>
        <a class="btn btn-primary" href="./altaUsuario.html">Registrarse</a>
    </div>
</form>
<script src="../js/app.js"></script>
</body>
</html>