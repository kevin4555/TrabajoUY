<%@page import="logica.datatypes.Dtusuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Lista de Empresas</title>
    <link rel="stylesheet" type="text/css" href="../../webapp/resource/css/general.css">
    <jsp:include page="../include/Head.jsp"/>
</head>
<body>
    <jsp:include page="../include/NavBar.jsp"/>
    <main class="container pt-5">
        <div class="row">
            <jsp:include page="../include/Menu.jsp"/>
            <div class="col-8">
                <section>
                    <c:forEach var="empresa" items="${listaEmpresas}">
                        <div class="card">
                            <div class="row g-0">
                                <div class="col-md-3 justify-content-center align-items-center d-flex">
                                    
                                    <img
                                        src="${empresa.getImagen()}"
                                        class="img-fluid rounded-start"
                                        alt="Imagen PerfilUsuario"
                                    />
                                </div>
                                <div class="col-md-9">
                                    <div class="card-body">
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            ${empresa.getNickname()}
                                        </h5>

                                    </div>
                                    <div class="card-footer border-0 bg-white text-end">
                                        <c:url var="perfilURL" value="/consultaOfertas">
                                            <c:param name="nicknameEmpresa" value="${empresa.getNickname()}" />
                                        </c:url>
                                        <a href="${perfilURL}" class="btn btn-primary">Ver Ofertas</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                    </c:forEach>
                </section>
            </div>
        </div>
    </main>
</body>
</html>