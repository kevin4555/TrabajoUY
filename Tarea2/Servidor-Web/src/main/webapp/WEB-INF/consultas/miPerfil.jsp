<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="logica.webservices.DtUsuario"%>
<%@page import="logica.webservices.DtOfertaLaboral"%>
<%@page import="logica.webservices.DtPostulante"%>
<%@page import="logica.webservices.DtEmpresa"%>
<%@page import="logica.webservices.DtCompraPaquete"%>
<%@page import="logica.webservices.DtPostulacion"%>
<%@page import="logica.webservices.DtPaquetePublicacion"%>
<%@page import="logica.webservices.DtCantidadTipoPublicacionRestante"%>
<%@page import="logica.webservices.DtTipoPublicacion"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mi perfil</title>
<jsp:include page="../include/Head.jsp" />
</head>
<body>
	<jsp:include page="../include/NavBar.jsp" />
	<main class="container pt-5">
		<div class="row">
			<jsp:include page="../include/Menu.jsp" />
			<%
			DtUsuario usuario = (DtUsuario) request.getAttribute("usuario");
									String tipoUsuario = (String) request.getAttribute("tipoUsuario");
									List<DtCompraPaquete> paquetesComprados = (List<DtCompraPaquete>) request.getAttribute("compraPaquetes");
			%>
			<div class="menuMiPerfil container mt-3 col-8">
				<!-- Tab panes -->
				<div class="tab-content">
					<section>
						<div class="row">
							<div class="col-4">
								<%
								if (usuario.getImagenBase64() != null) {
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
								} else {
																																						if (tipoUsuario.equals("postulante")) {
								%>
								<li class="nav-item"><a class="nav-link"
									data-bs-toggle="tab" href="#postulaciones">Postulaciones</a></li>
								<%
								}
								%>
								<%
								}
								%>
							</ul>

							<!-- Tab panes -->
							<div class="tab-content">
								<div id="miPerfilUsuario" class="container tab-pane active">
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
																																																								  DtEmpresa empresa = (DtEmpresa) request.getAttribute("usuario");
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
																																															  DtPostulante postulante = (DtPostulante) request.getAttribute("usuario");
											%>
											<tr>
												<td><strong>Nacionalidad</strong></td>
												<td><%=postulante.getNacionalidad()%></td>
											</tr>
											<tr>
												<td><strong>Fecha de nacimiento</strong></td>
												<td><%=postulante.getFechaNacimientoString()%></td>
											</tr>
											<%
											}
											%>
											<tr>
												<td><strong>Editar perfil</strong></td>
												<td><a class="btn btn-primary"
													href="<%=request.getContextPath()%>/editarPerfil">Modificar</a></td>
											</tr>

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
													<th>Tipos/cantidad</th>
												</tr>
											</thead>
											<tbody>
												<%
												if (paquetesComprados != null) {
																																										  for (DtCompraPaquete paquete : paquetesComprados) {
																																										    DtPaquetePublicacion dtPaquete = paquete.getPaquete();
																																										    List<DtCantidadTipoPublicacionRestante> tiposPublicaciones = (List<DtCantidadTipoPublicacionRestante>) paquete
																																										    .getPublicacionesRestantes();
												%>
												<tr>
													<td><a
														href="<%=request.getContextPath()%>/paquete?nombrePaquete=<%=dtPaquete.getNombre()%>"><%=dtPaquete.getNombre()%></a></td>
													<td><%=dtPaquete.getDescripcion()%></td>
													<td><%=dtPaquete.getPeriodoValidez()%></td>
													<td><%=dtPaquete.getDescuento()%>%</td>
													<td><%=paquete.getFechaCompraString()%></td>
													<td>

														<ul class="navbar-nav justify-content-evenly">
															<%
															for (DtCantidadTipoPublicacionRestante cantidadTiposPublicaciones : tiposPublicaciones) {
																																									  DtTipoPublicacion dtTiposPublicaciones = cantidadTiposPublicaciones.getTipoPublicacion();
															%>
															<li class="nav-item"><a
																href="<%=request.getContextPath()%>/tipoPublicacion?nombreTipoPublicacion=<%=dtTiposPublicaciones.getNombre()%>"><%=dtTiposPublicaciones.getNombre()%></a>
																: <%=cantidadTiposPublicaciones.getCantidad()%></li>
															<%
															}
															%>
														</ul>
													</td>
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
											List<DtPostulacion> postulaciones = (List<DtPostulacion>) request.getAttribute("postulaciones");
																				for (DtPostulacion postulacion : postulaciones) {
											%>
											<tr>
												<td><a
													href="<%=request.getContextPath()%>/oferta?nombreOferta=<%=postulacion.getNombreOferta()%>"><%=postulacion.getNombreOferta()%></a></td>
												<td><%=postulacion.getDescripMotivacion()%></td>
												<td><%=postulacion.getCvReducido()%></td>
												<td><%=postulacion.getFechaPostulacionString()%></td>

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