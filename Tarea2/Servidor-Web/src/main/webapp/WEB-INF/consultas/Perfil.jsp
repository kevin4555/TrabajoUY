<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Perfil de Usuario</title>
</head>
<body>
	<h1>Perfil de Usuario</h1>

	<h2>Datos Generales</h2>
	<p>
		<strong>Nickname:</strong> ${usuario.nickname}
	</p>
	<p>
		<strong>Nombre:</strong> ${usuario.nombre}
	</p>
	<p>
		<strong>Apellido:</strong> ${usuario.apellido}
	</p>
	<p>
		<strong>Email:</strong> ${usuario.email}
	</p>

	<c:if test="${not empty oferta.imagen}">
		<h2>Imagen de Perfil</h2>
		<img src="data:image/jpeg;base64,${usuario.imagen}"
			alt="Imagen de Perfil" width="200" height="200">
	</c:if>


	<h2>Ofertas Laborales</h2>
	<ul>
		<c:forEach var="oferta" items="${usuario.ofertasColeccion}">
			<li>${oferta.nombre}</li>
		</c:forEach>
	</ul>

	<c:choose>
        
        <c:when test="${tipoUsuario eq 'empresa'}">
            <h2>Datos Específicos de Empresa</h2>
            <p><strong>Descripción:</strong> ${usuario.descripcion}</p>
            <p><strong>Sitio Web:</strong> ${usuario.sitioWeb}</p>
            
           
        </c:when>
        
       
        <c:when test="${tipoUsuario eq 'postulante'}">
            <h2>Datos Específicos de Postulante</h2>
            <p><strong>Fecha de Nacimiento:</strong> ${usuario.fechaNacimiento}</p>
            <p><strong>Nacionalidad:</strong> ${usuario.nacionalidad}</p>
            
           
        </c:when>
    </c:choose>
</body>
</html>