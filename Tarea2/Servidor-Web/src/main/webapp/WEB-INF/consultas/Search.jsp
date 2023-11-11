<%@page import="main.java.webservices.DtPostulante"%>
<%@page import="model.TipoUsuario"%>
<%@page import="main.java.webservices.DtOfertaLaboral"%>
<%@page import="main.java.webservices.DtEmpresa"%>
<%@page import="main.java.webservices.DtUsuario"%>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resource/css/general.css">
    <jsp:include page="../include/Head.jsp"/>
</head>
<body>
    <jsp:include page="../include/NavBar.jsp"/>
    <main class="container pt-5">
        <div class="row">
            <jsp:include page="../include/Menu.jsp"/>
            <div class="col-8">
            <div class="d-flex justify-content-end">
            <% String sort = request.getContextPath() + "/search?toSearch="+request.getAttribute("toSearch"); 
            	Boolean alfabetico = (Boolean) request.getAttribute("alfabetico");
            %>
		        <a href="<%= sort %>" class="btn btn-<%= alfabetico ?"secondary":"primary" %> mx-2"><i class="bi bi-sort-down"></i></a>
		        <a href="<%= sort + "&orden=alfabetico" %>"class="btn btn-<%= alfabetico ?"primary":"secondary" %>"><i class="bi bi-sort-alpha-down"></i></a>
		    </div>
                <section>
                <%ArrayList<DtEmpresa> listaEmpresas = (ArrayList<DtEmpresa>) request.getAttribute("listaEmpresas");
                
                if(listaEmpresas.isEmpty()){
                  %>
                  <h2 >No hay empresas disponibles</h2>  
                  <%
                }else{
                	%>
                    <h2 >Empresas</h2>  
                    <%
                }
                    for (DtEmpresa empresa : listaEmpresas) {
                    %>
                        <div class="card">
                            <div class="row g-0">
                                <div class="col-md-3 justify-content-center align-items-center d-flex">
                                    <img
                                    	src="data:image/png;base64,<%= empresa.getImagenBase64() %>"
                                        class="img-fluid rounded-start"
                                        alt="Imagen PerfilUsuario"
                                    />
                                </div>
                                <div class="col-md-9">
                                <div class="card-header d-flex justify-content-between align-items-start">
                                	<h5  class=" card-title">
                                            <%= empresa.getNickname() %>
	                                </h5>
	                                </div>                
                                    <div class="card-body">
                                        
                                        <p class="card-text"><%=empresa.getNombre() + " " + empresa.getApellido()%></p>
                                        <p class="card-text"><%=empresa.getDescripcion()%></p>
                                    </div>
                                    <div class="card-footer border-0 bg-white text-end">
                                    <%
                                    String contextPath = request.getContextPath();
                                    %>
                                       <a href="<%= contextPath %>/perfil?nicknameUsuario=<%= empresa.getNickname() %>" class="btn btn-primary">Perfil</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                    <%
                    }
                    %>
                </section>
                <section>
                    <%
                    ArrayList<DtOfertaLaboral> listaOfertas = (ArrayList<DtOfertaLaboral>) request.getAttribute("listaOfertas");
                    if(listaOfertas.isEmpty()){
                      %>
                      <h2 >No hay ofertas disponibles</h2>  
                      <%
                    }else{
                    	%>
                        <h2 >Ofertas laborales</h2>  
                        <%
                    }
                    for (DtOfertaLaboral oferta : listaOfertas) {
                    %>
                        <div class="card">
                            <div class="row g-0">
                                <div class="col-md-4 justify-content-center align-items-center d-flex">
                                    <img src="data:image/png;base64,<%= oferta.getImagenBase64() %>" class="img img-fluid rounded-start" alt="Imagen Oferta" />
                                </div>
                                <div class="col-md-8">
                                <div class="card-header d-flex justify-content-between align-items-start">
                                	<h5  class=" card-title">
                                            <%= oferta.getNombre() %>
	                                </h5>
	                                <%
				                    TipoUsuario tipoUsuarioSesion = (TipoUsuario) session.getAttribute("tipoUsuario");
				                    if (tipoUsuarioSesion == TipoUsuario.POSTULANTE) { 
				                    	DtPostulante postulante = (DtPostulante) session.getAttribute("usuarioLogueado"); %>
				                    		<div class="star-icon">
			     								<i class="bi bi-star<%= postulante.getOfertasFavoritas().contains(oferta.getNombre())? "-fill":"" %> fs-5 orange-star"></i>
			    							</div>	                        
				                    <% } %>	                                
			    				</div>    
                                    <div class="card-body">                
                                        <p class="card-text"><%= oferta.getDescripcion() %></p>
                                    </div>
                                    <div class="card-footer border-0 bg-white text-end">
                                        <%
                                    		String contextPath = request.getContextPath();
                                    	%>
                                       <a href="<%= contextPath %>/oferta?nombreOferta=<%= oferta.getNombre() %>" class="btn btn-primary">Mas Info</a>

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
    </main>
</body>
</html>