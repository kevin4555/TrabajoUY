<%@page import="logica.datatypes.DtOfertaLaboral"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logica.datatypes.Dtusuario"%>
<%@page import="model.TipoUsuario"%>
<%@page import="model.EstadoSesion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="<%= request.getContextPath() %>/recourse/css/general.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
    <title>Detalles de la Oferta</title>
    <jsp:include page="../include/Head.jsp" />
</head>
<body>
    <jsp:include page="../include/NavBar.jsp" />
	<main class="container pt-5">
		<div class="row">
			<jsp:include page="../include/Menu.jsp" />
			<div class="col-8">
				<section>
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="row g-0">
									<c:if test="${not empty oferta.imagen}">
										<div
											class="col-md-4 justify-content-center align-items-center d-flex">
											<img src="data:image/png;base64,${oferta.imagenBase64}"
												class="img-fluid rounded-start" alt="Imagen Oferta" />
										</div>
									</c:if>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-header p-0 border-0 bg-white text-start">
												${oferta.nombre}</h5>
											<p class="card-text">${oferta.descripcion}</p>
										</div>
										<div class="card-body">
											<ul class="list-group list-group-flush">
												<li class="list-group-item"><b>Remuneración:</b> ${oferta.remuneracion}
												</li>
												<li class="list-group-item"><b>Horario:</b>
													${oferta.horarioInicio} - ${oferta.horarioFinal}</li>
												<li class="list-group-item"><b>Departamento:</b>
													${oferta.departamento}</li>
												<li class="list-group-item"><b>Ciudad:</b>
													${oferta.ciudad}</li>
												<li class="list-group-item"><b>Fecha de alta:</b>
													${oferta.fechaResolucion}</li>
												<li class="list-group-item"><b>Keywords:</b>
                                                    <c:forEach var="keyword" items="${oferta.keywords}">${keyword}, </c:forEach></li>
												<c:if
													test="${tipoUsuario == TipoUsuaio.TipoUsuario.EMPRESA }">
													<li class="list-group-item"><b>Estado de la
															Oferta:</b> ${oferta.estadoOferta}</li>

												</c:if>

												
											</ul>
										</div>
									</div>
								</div>
							</div>

							<c:if test="${tipoUsuario == TipoUsuaio.TipoUsuario.EMPRESA }">
								<c:if test="${not empty oferta.paqueteAsociado}">
									<div>
										<h1>Paquete</h1>
										<div class="card cardOferta mb-3">
											<div class="row g-0">
												<div class="col-md-4">
													<img src="" class="img-fluid rounded-start" alt="..." />
												</div>
												<div class="col-md-8">
													<div class="card-body">
														<h1 class="card-title">${oferta.paqueteAsociado}</h1>
														<p class="card-text">
															<a href="" class="btn btn-primary">Ir
																a Paquete</a>
														</p>
													</div>
												</div>
											</div>
										</div>
									</div>
								</c:if>
							</c:if>
						</div>
						<c:if test="${tipoUsuario == TipoUsuaio.TipoUsuario.EMPRESA }">
							<div class="col-md-3">
								<div>
									<h1>Postulaciones</h1>
								</div>
								<c:forEach var="postulacion" items="${oferta.postulaciones}">
									<div class="card">
										<img src="" class="card-img-top" />
										<div class="card-body">
											<p class="card-text">
												<c:url var="perfilURL" value="/perfil">
													<c:param name="nicknameUsuario"
														value="${postulacion.nicknamePostulante}" />
												</c:url>
												<a href="${perfilURL}">${postulacion.nicknamePostulante}</a>
											</p>
											<a href="" class="btn btn-primary">Postulacion</a>
										</div>
									</div>
								</c:forEach>
							</div>


						</c:if>
                        </div>
				</section>
			</div>
		</div>
		</main>

<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>