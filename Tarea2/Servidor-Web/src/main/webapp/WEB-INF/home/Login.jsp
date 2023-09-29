

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <!-- Agrega aquí tus enlaces a hojas de estilo CSS si es necesario -->
</head>
<body>
    <h1>Iniciar Sesión</h1>
    <form action="LoginServlet" method="post">
        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="usuario" required>
        <br><br>
        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" required>
        <br><br>
        <input type="submit" value="Iniciar Sesión">
    </form>
</body>
</html>