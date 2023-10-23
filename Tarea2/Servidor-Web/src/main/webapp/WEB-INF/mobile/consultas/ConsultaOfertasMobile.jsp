<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="logica.datatypes.DtOfertaLaboral"%>
<%@page import="logica.datatypes.Dtusuario"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<jsp:include page="../../mobile/include/HeadMobile.jsp" />
</head>
<body class="mb-4">
	<jsp:include page="../../mobile/include/NavBarMobile.jsp" />

	<main class="container pt-2">
		<div class="row">
			<div class="espacio">
				<jsp:include page="../../mobile/include/MenuMobile.jsp" />
			</div>
			<div class="row justify-content-center">
				<div class="col-8">
					<section>
						<%
						Dtusuario usuario = (Dtusuario) session.getAttribute("usuarioLogueado");
						ArrayList<DtOfertaLaboral> listaOfertas = (ArrayList<DtOfertaLaboral>) request.getAttribute("listaOfertas");
						if (listaOfertas.isEmpty())
						{
						%>
						<h1 class="card-text">No hay ofertas disponibles</h1>
						<%
						}
						for (DtOfertaLaboral oferta : listaOfertas)
						{
						%>
						<div class="card">
							<div class="row g-0">
								<div
									class="col-md-4 justify-content-center align-items-center d-flex">
									<img src="data:image/png;base64,<%=oferta.getImagenBase64()%>"
										class="img img-fluid rounded-start" alt="Imagen Oferta" />
								</div>
								<div class="col-md-8">
									<div class="card-body">
										<h5 class="card-header p-0 border-0 bg-white text-start">
											<p><%=oferta.getNombre()%></p>
										</h5>
										<p class="card-text"><%=oferta.getDescripcion()%></p>
									</div>
									<div class="card-footer border-0 bg-white text-end">
										<%
										String contextPath = request.getContextPath();
										
										%>
										<a
											href="<%=contextPath%>/verPostulacion?nombreOferta=<%=oferta.getNombre()%>&nicknamePostulante=<%=usuario.getNickname()%>"
											class="btn btn-primary">Mas Info</a>

									</div>
								</div>
							</div>
						</div>
						<br>
						<%
						}
						%>
					</section>
				</div>
			</div>
		</div>
	</main>
</body>
</html>