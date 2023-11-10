<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="main.java.webservices.DtOfertaLaboral"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script
	src="<%=request.getContextPath()%>/resource/javaScript/testLink.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/recourse/css/general.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
<title>Postulacion a oferta laboral</title>
<jsp:include page="../../mobile/include/HeadMobile.jsp" />
</head>
<body>
	<jsp:include page="../../mobile/include/NavBarMobile.jsp" />
	<main class="container pt-5">
		<div class="row">
			<jsp:include page="../../mobile/include/MenuMobile.jsp" />
			<div class="col">
				<%
				DtOfertaLaboral oferta = (DtOfertaLaboral) request.getAttribute("oferta");
				%>
				<div class="card">
					<div class="row g-0">
						<%
						if (oferta != null && oferta.getImagenBase64() != null)
						{
						%>
						<div
							class="col-md-4 justify-content-center align-items-center d-flex">
							<img src="data:image/png;base64,<%=oferta.getImagenBase64()%>"
								class="img-fluid rounded-start" alt="Imagen Oferta" />
						</div>
						<%
						}
						%>
						<div class="col-md-8">
							<div class="card-body">
								<h5 class="card-header p-0 border-0 bg-white text-start">
									<%=oferta.getNombre()%>
								</h5>
								<p class="card-text"><%=oferta.getDescripcion()%></p>
							</div>
							<div class="card-body">
								<ul class="list-group list-group-flush">
									<li class="list-group-item"><b>Remuneración:</b> <%=oferta.getRemuneracion()%></li>
									<li class="list-group-item"><b>Horario:</b> <%=oferta.getHorarioInicio()%>
										- <%=oferta.getHorarioFinal()%></li>
									<li class="list-group-item"><b>Departamento:</b> <%=oferta.getDepartamento()%></li>
									<li class="list-group-item"><b>Ciudad:</b> <%=oferta.getCiudad()%></li>
									<li class="list-group-item"><b>Fecha de alta:</b> <%=oferta.getFechaAltaString()%></li>
									<li class="list-group-item"><b>Keywords:</b> <%
 if (oferta.getKeywords() != null)
 {
 	for (String keyword : oferta.getKeywords())
 	{
 		out.print(keyword + ", ");
 	}
 }
 %></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<form
					action="<%=request.getContextPath()%>/postulacion?nombreOferta=<%=oferta.getNombre()%>"
					method="post" onsubmit="return validateForm()">
					<div class="camposForm">
						<div class="form-group">
							<label for="textAreaCV">*CV reducido</label>
							<textarea class="form-control" id="textAreaDescripcionCV"
								name="cVReducido" rows="3" required></textarea>
						</div>
					</div>
					<div class="camposForm mb-3">
						<div class="form-group">
							<label for="textAreaMotivacion">*Motivacion</label>
							<textarea class="form-control" id="textAreaDescripcionMotivacion"
								name="motivacion" rows="3" required></textarea>
						</div>
					</div>
					<div class="camposForm mb-3">
						<div class="form-group">
							<label for="textAreaMotivacion">Video</label>
							<textarea class="form-control" id="textAreaVideo" name="video"
								rows="3"></textarea>
						</div>
					</div>
					<div class="col" id="botonesConfirmarCancelar">
						<input class="btn btn-primary" type="submit" value="Postularse" />
					</div>
				</form>
			</div>
		</div>
	</main>
</body>
</html>