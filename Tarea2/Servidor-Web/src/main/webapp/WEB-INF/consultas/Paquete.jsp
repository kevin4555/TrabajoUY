<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="logica.classes.PaquetePublicacion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
		rel="stylesheet" />
	<link href="../../resource/css/general.css" />
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
	<title>Paquete</title>
	
	
	<jsp:include page="../include/Head.jsp" />
</head>
  <body>
  	
	<jsp:include page="../include/NavBar.jsp" />
    <main class="container pt-5">
      <div class="row">
        <div class="col-3">
          <section class="text-center">
            <div class="table-responsive overflow-auto tablaKeyword">
              <table class="table table-hover table-bordered">
                <thead class="table-primary">
                  <tr>
                    <th class="cabezaTabla">Menú</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="d-flex">
                      <a class="btn" href="./consultaUsuario.html"
                        >Consulta usuarios</a
                      >
                    </td>
                  </tr>
                  <tr>
                    <td class="d-flex">
                      <a class="btn" href="./consultaDeTipoDePublicacionDeOfertaLaboral.html">Ver tipos de publicaciones</a>
                    </td>
                  </tr>
                  <tr>
                    <td class="d-flex">
                      <a class="btn" href="./consultaDePaqueteDeTiposDePublicacionDeOfertaLaboral.html">Ver tipos de paquetes</a>
                    </td>
                  </tr>
                  <tr>
                    <td class="d-flex">
                      <a class="btn" href="./consultaOfertaLaboralVisitante.html">Consulta ofertas laborales</a>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </section>
          <section class="text-center">
            <div class="tablaKeyword table-responsive overflow-auto">
              <table class="table table-hover table-bordered">
                <thead class="table-primary">
                  <tr>
                    <th class="cabezaTabla">Keywords</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>
                      <button class="btnKeyword btn">Tiempo Completo</button>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <button class="btnKeyword btn">Medio tiempo</button>
                    </td>
                  </tr>
                  <tr>
                    <td><button class="btnKeyword btn">Remoto</button></td>
                  </tr>
                  <tr>
                    <td><button class="btnKeyword btn">Freelance</button></td>
                  </tr>
                  <tr>
                    <td><button class="btnKeyword btn">Temporal</button></td>
                  </tr>
                  <tr>
                    <td><button class="btnKeyword btn">Permanente</button></td>
                  </tr>
                  <tr>
                    <td><button class="btnKeyword btn">Computación</button></td>
                  </tr>
                  <tr>
                    <td>
                      <button class="btnKeyword btn">Administración</button>
                    </td>
                  </tr>
                  <tr>
                    <td><button class="btnKeyword btn">Logística</button></td>
                  </tr>
                  <tr>
                    <td>
                      <button class="btnKeyword btn">Contabilidad</button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </section>
        </div>
        <div class="col-9 d-flex flex-column align-items-center flex-grow-1">
          <div class="card mb-3" style="max-width: 540px">
            <div class="row g-0">
              <div class="col-4 d-flex align-items-center">
                <img
                  src="data:image/png;base64,${paquete.imagenBase64}"
                  class="img-fluid rounded-start"
                  alt="Imagen Paquete"
                />
              </div>
              <div class="col-8">
                <div class="card-body">
                  <div class="card-header bg-white px-0">
                    <h1 class="m-0 fs-2">
                      <i class="bi bi-box-seam text-primary"></i> Paquete ${paquete.nombre}
                    </h1>
                  </div>
                  <p class="card-text py-2 fst-italic">
                    ${paquete.descripcion}
                  </p>
                  <ul class="list-unstyled">
                    <li class="text-secondary">
                      <i class="bi bi-arrow-right-circle text-primary"></i>
                      Período: ${paquete.periodoValidez} días
                    </li>
                    <li class="text-secondary">
                      <i class="bi bi-arrow-right-circle text-primary"></i>
                      Descuento del ${paquete.descuento} % por publicación
                    </li>
                    <li class="text-secondary">
                      <i class="bi bi-arrow-right-circle text-primary"></i>
                      <fmt:parseDate value="${paquete.fechaAlta}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
						<fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd/MM/yyyy" />
                      Fecha ${newParsedDate}
                    </li>
                  </ul>
                  <div class="publicacionesPaquete">
                    <table class="table borden border-0">
                      <thead>
                        <tr>
                          <th scope="col">Tipo Publicaciones</th>
                          <th scope="col">Cant.</th>
                        </tr>
                      </thead>
                      <tbody>
                      	<c:forEach var="cantidadTipoPublicacion" items="${paquete.cantidadTipoPublicaciones}">
                      		<tr>
                      		<c:url var="tipoPublicacionUrl" value="/consultaTipoPostulacion">
							<c:param name="tipoPublicacion" value="${cantidadTipoPublicacion.nombreTipoPublicacion}" />
							</c:url>
                          		<td><a href="${tipoPublicacionUrl}">${cantidadTipoPublicacion.nombreTipoPublicacion}</a></td>
                          		<td>${cantidadTipoPublicacion.cantidad}</td>
                        	</tr>
                      	</c:forEach>
                      </tbody>
                    </table>
                  </div>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <h2 class="">$${paquete.costo}</h2>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
</body>
</html>