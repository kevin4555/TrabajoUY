<%@page import="model.EstadoSesion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <% HttpSession sesion = request.getSession(); 
    EstadoSesion estado = (EstadoSesion) sesion.getAttribute("estadoSesion");
    %>
    
</head>
<body>
    <h1>Iniciar Sesión</h1>
    <h1>PEPE <%= estado %></h1>
    <h1> ${mensajeError}</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="nombreEmail" required>
        <br><br>
        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasenia" required>
        <br><br>
        <input type="submit" value="Iniciar Sesión">
    </form>
</body>
</html>