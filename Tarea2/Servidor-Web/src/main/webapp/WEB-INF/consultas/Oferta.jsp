<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles de la Oferta</title>
</head>
<body>
    <h1>Detalles de la Oferta Laboral</h1>

    <table>
        <tr>
            <th>Nombre:</th>
            <td>${oferta.nombre}</td>
        </tr>
        <tr>
            <th>Descripción:</th>
            <td>${oferta.descripcion}</td>
        </tr>
        <tr>
            <th>Ciudad:</th>
            <td>${oferta.ciudad}</td>
        </tr>
        <tr>
            <th>Departamento:</th>
            <td>${oferta.departamento}</td>
        </tr>
        <tr>
            <th>Horario de Inicio:</th>
            <td>${oferta.horarioInicio}</td>
        </tr>
        <tr>
            <th>Horario Final:</th>
            <td>${oferta.horarioFinal}</td>
        </tr>
        <tr>
            <th>Remuneración:</th>
            <td>${oferta.remuneracion}</td>
        </tr>
        <tr>
            <th>Fecha de Alta:</th>
            <td>${oferta.fechaAlta}</td>
        </tr>
        <tr>
            <th>Fecha de Resolución:</th>
            <td>${oferta.fechaResolucion}</td>
        </tr>
        <tr>
            <th>Estado de la Oferta:</th>
            <td>${oferta.estadoOferta}</td>
        </tr>
        <c:if test="${not empty oferta.imagen}">
            <tr>
                <th>Imagen:</th>
                <td>
                    <img src="data:image/png;base64,${oferta.imagenBase64}" alt="Imagen de la Oferta" />
                </td>
            </tr>
        </c:if>
		

		<c:if test="${not empty oferta.paqueteAsociado}">
        <tr>
            <th>Paquete Asociado:</th>
            <td>${oferta.paqueteAsociado}</td>
        </tr>
        </c:if>
        
        <tr>
            <th>Palabras Clave:</th>
            <td>
                <c:forEach var="keyword" items="${oferta.keywords}">
                    ${keyword}<br/>
                </c:forEach>
            </td>
        </tr>
    </table>
	<table>
		<h2>Postulaciones</h2>

		<table border="1">
			<tr>
				<th>Nickname Postulante</th>
				<th>Descripción de Motivación</th>
				<th>Fecha de Postulación</th>
				<th>CV Reducido</th>
			</tr>
			<c:forEach var="postulacion" items="${oferta.postulaciones}">
				<tr>
					<td><c:url var="perfilURL" value="/perfil">
							<c:param name="nicknameUsuario"
								value="${postulacion.nicknamePostulante}" />
						</c:url> <a href="${perfilURL}">${postulacion.nicknamePostulante}</a></td>
					<td>${postulacion.descripMotivacion}</td>
					<td>${postulacion.fechaPostulacion}</td>
					<td>${postulacion.cvReducido}</td>
				</tr>
			</c:forEach>
		</table>

	</table>


</body>
</html>