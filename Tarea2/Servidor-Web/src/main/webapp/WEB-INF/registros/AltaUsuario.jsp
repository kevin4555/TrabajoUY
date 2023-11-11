<%@page import="main.java.webservices.DtUsuario"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrar usuario</title>
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
		action="<%=request.getContextPath()%>/altaUsuario" method="post"
		id="formAltaUsuario"
		class="bg-white rounded container py-3 mx-3 shadow-sm"
		enctype="multipart/form-data">
		<div class="row mt-3">
			<div class="col">
				<label class="form-label"><strong>*Nombre</strong></label> <input
					type="text" class="form-control" id="inputNombreUsuario"
					name="nombre" placeholder="Ingrese su nombre" required />
			</div>
			<div class="col">
				<label for="inputCity" class="form-label"><strong>*Apellido</strong></label>
				<input type="text" class="form-control nom"
					id="inputApellidoUsuario" name="apellido"
					placeholder="Ingrese su apellido" required />
			</div>
		</div>
		<div class="row mt-3">
			<div class="col">
				<label for="inputNickname" class="form-label"><strong>*Nickname</strong></label>
				<input type="text" class="form-control" id="inputNickname"
					name="nickname" placeholder="Ingrese su nickname" required />
			</div>
			<div class="col alert alert-sm" id="bloqueAviso">
				<span id="chequeoNickname"></span>
			</div>
		</div>

		<div class="row mt-3">
			<div class="col">
				<label for="inputEmail4" class="form-label"><strong>*Email</strong></label>
				<input type="email" class="form-control" id="inputEmail"
					name="email" placeholder="Ingrese su email" required />
			</div>
			<div class="col alert alert-sm" id="bloqueAvisoEmail">
				<span id="chequeoEmail"></span>
			</div>
		</div>

		<div class="col-md-6 mt-3"></div>
		<div class="col-md-6 mt-3">
			<label for="inputPassword" class="form-label"><strong>*Contraseña</strong></label>
			<input type="password" class="form-control" id="inputPassword"
				name="contrasenia" placeholder="Ingrese su contraseÃ±a" required />
		</div>
		<div class="row mt-3">
			<div class="col">
				<label for="inputPassword4" class="form-label"><strong>*Confirmar
					contraseña</strong></label> <input type="password" class="form-control"
				id="inputPasswordConfirm" name="contraseniaConf"
				placeholder="Ingrese su contraseña" required />
			</div>
			<div class="col alert alert-sm" id="bloqueAvisoContrasenia">
				<span id="chequeoContrasenia"></span>
			</div>
		</div>
		<div class="col-md-6 mt-3">
			<label for="imagenUsuario" class="form-label"><strong>Foto
					de perfil</strong></label> <input type="file" name="imagen"
				accept="image/jpg, image/png" />
		</div>
		<div class="row mt-3">
			<div class="col">
				<label for="inputTipoUsuario" class="form-label"><strong>*Tipo
						de usuario</strong></label>
			</div>
			<div class="col">
				<div class="form-check">
					<input type="radio" class="form-check-input" id="radioPostulante"
						name="tipoUsuario" value="postulante" /><strong>Postulante</strong>
					<label class="form-check-label" for="radio1"></label>
				</div>
				<div class="form-check">
					<input type="radio" class="form-check-input" id="radioEmpresa"
						name="tipoUsuario" value="empresa" /><strong>Empresa</strong>
					<label class="form-check-label" for="radio2"></label>
				</div>
			</div>
		</div>
		<div class="col-md-6 mt-3" id="divNacionalidad">
			<label class="form-label"><strong>*Nacionalidad</strong></label> <input
				type="text" class="form-control"
				placeholder="Ingrese su nacionalidad" id="inputNacionalidad"
				name="nacionalidad" required />
		</div>
		<div class="col-md-6 mt-3" id="divFechaNacimiento">
			<label class="form-label"><strong>*Fecha de
					Nacimiento</strong></label> <input name="fechaNacimiento" type="date"
				class="form-control" id="inputFechaNacimiento" required />
		</div>
		<div class="col-md-6 mt-3" id="divSitioWeb">
			<label class="form-label"><strong>Sitio Web</strong></label> <input
				type="text" class="form-control" placeholder="Ingrese su sitio web"
				id="inputSitioWeb" name="sitioWeb" />
		</div>
		<div class="col-md-6 mt-3" id="divDescripcionEmpresa">
			<label class="form-label"><strong>*DescripciÃ³n</strong></label> <input
				type="text" class="form-control"
				placeholder="Ingrese una descripciÃ³n" id="inputDescripcion"
				name="descripcion" required />
		</div>
		<div class="col-6 mt-3" id="radioTipoUsuario">
			<input class="btn btn-primary" type="submit" value="Registrarse" />
		</div>
	</form>

	<script src="<%=request.getContextPath()%>/resource/js/app.js"></script>
	<!--link archivo js-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
	<!--InstalaciÃ³n bootstrap-->
</body>

</body>
</html>
