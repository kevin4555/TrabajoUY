<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="logica.datatypes.Dtusuario"%>
<%@page import="logica.datatypes.DtOfertaLaboral"%>
<%@page import="logica.datatypes.Dtpostulante"%>
<%@page import="logica.datatypes.Dtempresa"%>
<%@page import="logica.datatypes.DtpaquetePublicacion"%>
<%@page import="logica.datatypes.Dtpostulacion"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/recourse/css/general.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Mi perfil</title>
<jsp:include page="../include/Head.jsp" />
</head>
<body>
	<jsp:include page="../include/NavBar.jsp" />
	<main class="container pt-5">
		<div class="row">
			<jsp:include page="../include/Menu.jsp" />
			<%
			Dtusuario usuario = (Dtusuario) request.getAttribute("usuario");
			String tipoUsuario = (String) request.getAttribute("tipoUsuario");
			%>
			<div class="menuMiPerfil container mt-3 col-8">
				<!-- Tab panes -->
				<div class="tab-content">
					<section>
						<div class="row">
							<div class="col-4">
								<%
								if (usuario.getImagen() != null) {
								%>
								<img src="data:image/png;base64,<%=usuario.getImagenBase64()%>"
									alt="Imagen de Perfil" width="200" height="200">
								<%
								}
								%>
							</div>
							<div class="col-8">
								<h2 class="mb-3"><%=usuario.getNombre()%>
									<%=usuario.getApellido()%></h2>
								<label for="" class="labelNicknameEmail mb-3"><%=usuario.getNickname()%>
									/ <%=usuario.getEmail()%></label>
							</div>
						</div>
						<div class="menuMiPerfil container mt-3">
							<!-- Nav tabs -->
							<ul class="nav nav-tabs" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									data-bs-toggle="tab" href="#miPerfilUsuario">Perfil</a></li>
								<%
								if (tipoUsuario.equals("empresa")) {
								%>
								<li class="nav-item"><a class="nav-link"
									data-bs-toggle="tab" href="#ofertaLaboralesEmpresa">Ofertas
										Laborales</a></li>
								<li class="nav-item"><a class="nav-link"
									data-bs-toggle="tab" href="#paquetes">Paquetes comprados</a></li>
								<%
								}
								%>
								<%
								if (tipoUsuario.equals("postulante")) {
								%>
								<li class="nav-item"><a class="nav-link"
									data-bs-toggle="tab" href="#postulaciones">Postulaciones</a></li>
								<%
								}
								%>
							</ul>

							<!-- Tab panes -->
							<div class="tab-content">
								<div id="#miPerfilUsuario" class="container tab-pane active">
									<section class="">
										<table class="table table-hover table-bordered">
											<tr>
												<td><strong>Nickname</strong></td>
												<td><%=usuario.getNickname()%></td>
											</tr>
											<tr>
												<td><strong>Nombre</strong></td>
												<td><%=usuario.getNombre()%></td>
											</tr>
											<tr>
												<td><strong>Apellido</strong></td>
												<td><%=usuario.getApellido()%></td>
											</tr>
											<tr>
												<td><strong>E-mail</strong></td>
												<td><%=usuario.getEmail()%></td>
											</tr>
											<%
											if (tipoUsuario.equals("empresa")) {
											  Dtempresa empresa = (Dtempresa) request.getAttribute("usuario");
											%>
											<tr>
												<td><strong>Sitio web</strong></td>
												<td><%=empresa.getSitioWeb()%></td>
											</tr>
											<tr>
												<td><strong>Descripción</strong></td>
												<td><%=empresa.getDescripcion()%></td>
											</tr>
											<%
											}
											%>
											<%
											if (tipoUsuario.equals("postulante")) {
											  Dtpostulante postulante = (Dtpostulante) request.getAttribute("usuario");
											%>
											<tr>
												<td><strong>Nacionalidad</strong></td>
												<td><%=postulante.getNacionalidad()%></td>
											</tr>
											<tr>
												<td><strong>Fecha de nacimiento</strong></td>
												<td><%=postulante.getFechaNacimiento()%></td>
											</tr>
											<%
											}
											%>
											
										</table>
									</section>
								</div>
								<%
								if (tipoUsuario.equals("empresa")) {
								%>
								<div id="ofertaLaboralesEmpresa" class="container tab-pane fade">
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>Nombre</th>
												<th>Horario</th>
												<th>Remuneración</th>
												<th>Descripción</th>
												<th>Departamento</th>
												<th>Ciudad</th>
												<th>Tipo de publicación</th>
												<th>Estado</th>
											</tr>
										</thead>
										<tbody>
											<%
											List<DtOfertaLaboral> ofertasColeccion = usuario.getOfertasColeccion();
											for (DtOfertaLaboral oferta : ofertasColeccion) {
											%>
											<tr>
												<td><a
													href="<%=request.getContextPath()%>/oferta?nombreOferta=<%=oferta.getNombre()%>"><%=oferta.getNombre()%></a></td>
												<td><%=oferta.getHorarioInicio()%> - <%=oferta.getHorarioFinal()%></td>
												<td><%=oferta.getRemuneracion()%></td>
												<td><%=oferta.getDescripcion()%></td>
												<td><%=oferta.getDepartamento()%></td>
												<td><%=oferta.getCiudad()%></td>
												<td><a
													href="<%=request.getContextPath()%>/tipoPublicacion?nombreTipoPublicacion=<%=oferta.getNombreTipoPublicacion()%>"><%=oferta.getNombreTipoPublicacion()%></a></td>
												<td><%=oferta.getEstadoOferta()%></td>
											</tr>
											<%
											}
											%>
										</tbody>
									</table>
								</div>
								<div id="paquetes" class="container tab-pane fade">
									<section class="">
										<table class="table table-hover table-bordered">
											<thead>
												<tr>
													<th>Nombre</th>
													<th>Descripción</th>
													<th>Período</th>
													<th>Descuento</th>
													<th>Fecha de compra</th>
												</tr>
											</thead>
											<tbody>
												<%
												List<DtpaquetePublicacion> paquetesComprados = (List<DtpaquetePublicacion>) request.getAttribute("paquetes");
												if (paquetesComprados != null) {
												  for (DtpaquetePublicacion paquete : paquetesComprados) {
												%>
												<tr>
													<td><a
														href="<%=request.getContextPath()%>/paquete?nombrePaquete=<%=paquete.getNombre()%>"><%=paquete.getNombre()%></a></td>
													<td><%=paquete.getDescripcion()%></td>
													<td><%=paquete.getPeriodoValidez()%></td>
													<td><%=paquete.getDescuento()%>%</td>
													<td></td>
												</tr>
												<%
												}
												%>
												<%
												}
												%>
											</tbody>
										</table>
									</section>
								</div>
								<%
								}
								%>
								<%
								if (tipoUsuario.equals("postulante")) {
								%>
								<div id="postulaciones" class="container tab-pane fade">
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>Oferta laboral</th>
												<th>Motivacion</th>
												<th>CV reducido</th>
												<th>Fecha</th>
											</tr>
										</thead>
										<tbody>
											<%
											List<Dtpostulacion> postulaciones = (List<Dtpostulacion>) request.getAttribute("postulaciones");
											for (Dtpostulacion postulacion : postulaciones) {
											%>
											<tr>
												<td><a
													href="<%=request.getContextPath()%>/oferta?nombreOferta=<%=postulacion.getNombreOferta()%>"><%=postulacion.getNombreOferta()%></a></td>
												<td><%=postulacion.getDescripMotivacion()%></td>
												<td><%=postulacion.getCvReducido()%></td>
												<td><%=postulacion.getFechaPostulacion()%></td>

											</tr>
											<%
											}
											%>
										</tbody>
									</table>
								</div>
								<%
								}
								%>
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</main>

</body>
</html>