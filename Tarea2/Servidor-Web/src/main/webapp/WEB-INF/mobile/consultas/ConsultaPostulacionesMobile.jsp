<%@page import="logica.webservices.DtOfertaLaboral"%>
<%@page import="logica.webservices.DtUsuario"%>
<%@page import="logica.webservices.DtPostulacion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<%=request.getContextPath()%>/recourse/css/general.css" />
<title>Home</title>
<%
ArrayList<String> listaKeywords = (ArrayList<String>) session.getAttribute("listaKeywords");
%>

<jsp:include page="../../mobile/include/HeadMobile.jsp" />
</head>
<body class="mb-4">
	<jsp:include page="../../mobile/include/NavBarMobile.jsp" />
	<main class="container pt-2">
		<div class="espacio">
			<jsp:include page="../../mobile/include/MenuMobile.jsp" />
		</div>
		<div class="row justify-content-center">

			<div class="col-8">
				<section>
					<%
					DtUsuario usuario = (DtUsuario) request.getAttribute("usuario");
					ArrayList<DtPostulacion> postulaciones = (ArrayList<DtPostulacion>) request.getAttribute("postulaciones");
					ArrayList<DtOfertaLaboral> ofertasPostuladas = (ArrayList<DtOfertaLaboral>) request.getAttribute("ofertasPostuladas");
					
					int indice = postulaciones.size()-1;
					int indiceRecorrer = 0;
					DtOfertaLaboral ofertaLaboral = null;
					for (DtPostulacion postulacion : postulaciones) {
						if(indiceRecorrer <= indice)
						{
							ofertaLaboral = ofertasPostuladas.get(indiceRecorrer);
							indiceRecorrer++;
						}
					%>

					<div class="card mb-3">
						<div class="row g-0">
							<div
								class="col-md-4 justify-content-center align-items-center d-flex">
								<img
									src="data:image/png;base64,<%=ofertaLaboral.getImagenBase64()%>"
									class="img-fluid rounded-start" alt="Imagen Oferta" />
							</div>
							<div class="col-md-8">
								<div class="card-body">
									<h5 class="card-header p-0 border-0 bg-white text-start">
										<p><%=ofertaLaboral.getNombre()%></p>
									</h5>
									<p class="card-text"><%=ofertaLaboral.getDescripcion()%></p>
								</div>
								<div class="card-footer border-0 bg-white text-end">
									<%
									String contextPath = request.getContextPath();
									%>
									<a
										href="<%=request.getContextPath()%>/verPostulacion?nombreOferta=<%=postulacion.getNombreOferta()%>&nicknamePostulante=<%=usuario.getNickname()%>"
										class="btn btn-primary">Ir a Mi Postulacion</a>
								</div>
							</div>
						</div>
					</div>
					<%
					}
					
					%>
				</section>
			</div>
		</div>
	</main>
</body>
</html>
