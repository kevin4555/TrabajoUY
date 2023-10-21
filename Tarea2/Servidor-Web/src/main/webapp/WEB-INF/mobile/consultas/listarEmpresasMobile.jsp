<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="logica.datatypes.Dtusuario"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta de Ofertas</title>
<jsp:include page="../../mobile/include/HeadMobile.jsp" />
</head>
<body>
	<jsp:include page="../../mobile/include/NavBarMobile.jsp" />
	<main class="container pt-5">
		<div class="row">
			<jsp:include page="../../mobile/include/MenuMobile.jsp" />
			<div class="row justify-content-center">
				<div class="col-8">
					<section>
						<%
						ArrayList<Dtusuario> listaEmpresas = (ArrayList<Dtusuario>) request.getAttribute("listaEmpresas");
						for (Dtusuario empresa : listaEmpresas)
						{
						%>
						<div class="card">
							<div class="row g-0">
								<div
									class="col-md-3 justify-content-center align-items-center d-flex">
									<img src="data:image/png;base64,<%=empresa.getImagenBase64()%>"
										class="img-fluid rounded-start" alt="Imagen PerfilUsuario" />
								</div>
								<div class="col-md-9">
									<div class="card-body">
										<h5 class="card-header p-0 border-0 bg-white text-start">
											<%=empresa.getNickname()%>
										</h5>
									</div>
									<div class="card-footer border-0 bg-white text-end">
										<%
										String contextPath = request.getContextPath();
										%>
										<a
											href="<%=contextPath%>/consultaOfertas?nicknameEmpresa=<%=empresa.getNickname()%>"
											class="btn btn-primary">Ver Ofertas</a>
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