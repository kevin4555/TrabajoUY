<%@page import="main.java.webservices.DtUsuario"%>
<%@page import="main.java.webservices.DtPostulante"%>
<%@page import="main.java.webservices.DtEmpresa"%>
<%@page import="model.TipoUsuario"%>
<%@page import="model.EstadoSesion"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar perfil</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resource/css/general.css">
<%@ include file="../include/Head.jsp"%>
</head>
<body>
<body class="login">
<%
	String mensaje = (String) request.getAttribute("mensajeError");
	if (mensaje != null) {
	%>
	<div class="alert alert-danger alert-dismissible fade show"
		role="alert">
		<strong>Error:</strong>
		<%=mensaje%>
		<button type="button" class="btn-close" data-bs-dismiss="alert"
			aria-label="Close"></button>
	</div>
	<%
	}
	%>
	<form class="formLogin"
		action="<%=request.getContextPath()%>/editarPerfil" method="post"
		id="formAltaUsuario"
		class="bg-white rounded container py-3 mx-3 shadow-sm"
		enctype="multipart/form-data">
		<div class="row mt-3">
			<%
			TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
						DtUsuario usuario = (DtUsuario) session.getAttribute("usuarioLogueado");
			%>
			<div class="col">
				<label class="form-label"><strong>*Nombre</strong></label> <input
					type="text" class="form-control" id="inputNombreUsuario"
					name="nombre" value="<%=usuario.getNombre()%>"
					placeholder="Ingrese su nombre" required />
			</div>
			<div class="col">
				<label for="inputCity" class="form-label"><strong>*Apellido</strong></label>
				<input type="text" class="form-control nom"
					id="inputApellidoUsuario" name="apellido"
					value="<%=usuario.getApellido()%>"
					placeholder="Ingrese su apellido" required />
			</div>
		</div>
		<div class="col-md-6 mt-3">
			<label for="inputNickname" class="form-label"><strong>*Nickname</strong></label>
			<input type="text" class="form-control" value="<%=usuario.getNickname()%>" readonly>
		</div>
		<div class="col-md-6 mt-3">
			<label for="inputEmail4" class="form-label"><strong>*Email</strong></label>
			<input type="text" class="form-control" value="<%=usuario.getEmail()%>" readonly>
		</div>
		<div class="col-md-6 mt-3">
			<label for="inputPassword" class="form-label"><strong>*Contraseña</strong></label>
			<input type="password" class="form-control" id="inputPassword"
				name="contrasenia" value="<%=usuario.getContrasenia()%>"
				placeholder="Ingrese su contraseña" required />
		</div>
		<div class="col-md-6 mt-3">
			<label for="inputPassword4" class="form-label"><strong>*Confirmar
					contraseña</strong></label> <input type="password" class="form-control"
				id="inputPasswordConfirm" name="contraseniaConf"
				placeholder="Ingrese su contraseña" required />
		</div>
		<div class="col-md-6 mt-3">
			<label for="imagenUsuario" class="form-label"><strong>Foto
					de perfil</strong></label> <input type="file" name="imagen"
				accept="image/jpg, image/png" />
		</div>
		<%
		if (tipoUsuario.equals(TipoUsuario.POSTULANTE)) {
				  DtPostulante postulante = (DtPostulante) session.getAttribute("usuarioLogueado");
		%>

		<div class="col-md-6 mt-3" id="divNacionalidad">
			<label class="form-label"><strong>*Nacionalidad</strong></label> <input
				type="text" class="form-control"
				placeholder="Ingrese su nacionalidad" id="inputNacionalidad"
				value="<%=postulante.getNacionalidad()%>"
				name="nacionalidad" required />
		</div>
		<div class="col-md-6 mt-3" id="divFechaNacimiento">
			<label class="form-label"><strong>*Fecha de
					Nacimiento</strong></label> <input name="fechaNacimiento" type="date"
				class="form-control" id="inputFechaNacimiento" value="<%=postulante.getFechaNacimientoString()%>" required />
		</div>
		<%
		}
		%>
		<%
		if (tipoUsuario.equals(TipoUsuario.EMPRESA)) {
				  DtEmpresa empresa = (DtEmpresa) session.getAttribute("usuarioLogueado");
		%>
		<div class="col-md-6 mt-3" id="divSitioWeb">
			<label class="form-label"><strong>Sitio Web</strong></label> <input
				type="text" class="form-control" placeholder="Ingrese su sitio web"
				id="inputSitioWeb" name="sitioWeb" value="<%=empresa.getSitioWeb()%>"/>
		</div>
		<div class="col-md-6 mt-3" id="divDescripcionEmpresa">
			<label class="form-label"><strong>*Descripción</strong></label> <input
				type="text" class="form-control"
				placeholder="Ingrese una descripción" id="inputDescripcion" value="<%=empresa.getDescripcion()%>"
				name="descripcion" required />
		</div>
		<%
		}
		%>
		<div class="col-6 mt-3" id="radioTipoUsuario">
			<input class="btn btn-primary" type="submit" value="Modificar Datos" />
		</div>
	</form>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>

</body>
</html>