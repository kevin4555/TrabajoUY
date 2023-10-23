<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="logica.datatypes.Dtusuario"%>
<%@page import="logica.datatypes.DtOfertaLaboral"%>
<%@page import="logica.datatypes.Dtpostulante"%>
<%@page import="logica.datatypes.Dtempresa"%>
<%@page import="logica.datatypes.DtCompraPaquete"%>
<%@page import="logica.datatypes.Dtpostulacion"%>
<%@page import="logica.datatypes.DtpaquetePublicacion"%>
<%@page import="logica.datatypes.DtCantidadTipoPublicacionRestante"%>
<%@page import="logica.datatypes.DttipoPublicacion"%>
<%@page import="logica.controllers.ControladorOferta"%>
<%@page import="logica.controllers.Fabrica"%>
<%@page import="logica.interfaces.IcontroladorOferta"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mi perfil</title>
<jsp:include page="../../mobile/include/HeadMobile.jsp" />
</head>
<body>
	<jsp:include page="../../mobile/include/NavBarMobile.jsp" />
	<main class="container pt-2">
		<div class="row">
		<div class="espacio">
			<jsp:include page="../../mobile/include/MenuMobile.jsp" />
			</div>
			<%
			Dtusuario usuario = (Dtusuario) request.getAttribute("usuario");
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
								if (usuario.getImagen() != null)
								{
								%>
								<img src="data:image/png;base64,<%=usuario.getImagenBase64()%>"
									alt="Imagen de Perfil" width="200" height="200">
								<%
								}
								%>
							</div>
						</div>
						<div class="col-8">
							<h2 class="mb-3"><%=usuario.getNombre()%>
								<%=usuario.getApellido()%></h2>
							<label for="" class="labelNicknameEmail mb-3"><%=usuario.getNickname()%>
								/ <%=usuario.getEmail()%></label>
						</div>
						<div class="menuMiPerfil container mt-3">
							<!-- Nav tabs -->
							<ul class="nav nav-tabs" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									data-bs-toggle="tab" href="#miPerfilUsuario">Perfil</a></li>
								<%
								if (tipoUsuario.equals("postulante"))
								{
								%>
								<li class="nav-item"><a class="nav-link"
									data-bs-toggle="tab" href="#postulaciones">Postulaciones</a></li>
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
											if (tipoUsuario.equals("postulante"))
											{
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
											<tr>
												<td><strong>Editar perfil</strong></td>
												<td><a class="btn btn-primary"
													href="<%=request.getContextPath()%>/editarPerfil">Modificar</a></td>
											</tr>
										</table>
									</section>
								</div>
								<%
								if (tipoUsuario.equals("postulante"))
								{
								%>
								<div id="postulaciones" class="container tab-pane fade">
									<table class="table table-hover table-bordered">

										<tbody>
											<%
											List<Dtpostulacion> postulaciones = (List<Dtpostulacion>) request.getAttribute("postulaciones");
											for (Dtpostulacion postulacion : postulaciones)
											{
											%>
											<div class="card-body">
												<%
												IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
												DtOfertaLaboral ofertaLaboral = controladorOferta.obtenerDtOfertaLaboral(postulacion.getNombreOferta());
												%>
												<img
													src="data:image/png;base64,<%=ofertaLaboral.getImagenBase64()%>"
													class="card-img-top" alt="Imagen Oferta">
												<%
												String ofertaUrl = request.getContextPath() + "/oferta?nombreOferta="
														+ java.net.URLEncoder.encode(postulacion.getNombreOferta(), "UTF-8");
												%>
												<h5 class="card-title text-center">
													<a href="<%=ofertaUrl%>" class="card-link"><%=postulacion.getNombreOferta()%></a>
												</h5>
												<br>
											</div>
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