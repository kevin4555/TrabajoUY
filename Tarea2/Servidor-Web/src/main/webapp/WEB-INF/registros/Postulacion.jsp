<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="logica.datatypes.DtOfertaLaboral"%>
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
<title>Postulacion a oferta laboral</title>
<jsp:include page="../include/Head.jsp" />
</head>
<body>
<jsp:include page="../include/NavBar.jsp" />
	<main class="container pt-5">
		<div class="row">
<jsp:include page="../include/Menu.jsp" />
			<div class="col">
			<%
	DtOfertaLaboral oferta = (DtOfertaLaboral) request.getAttribute("oferta");
	%>
	<h3>Postulación a la Oferta <%=oferta.getNombre() %></h3>
				<form action="<%=request.getContextPath()%>/postulacion">
					<div class="camposForm">
						<div class="form-group">
							<label for="textAreaCV">*CV reducido</label>
							<textarea class="form-control" id="textAreaDescripcionCV" name="cVReducido"
								rows="3" required></textarea>
						</div>
					</div>
					<div class="camposForm mb-3">
						<div class="form-group">
							<label for="textAreaMotivacion">*Motivacion</label>
							<textarea class="form-control" id="textAreaDescripcionMotivacion" name="motivacion"
								rows="3" required></textarea>
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