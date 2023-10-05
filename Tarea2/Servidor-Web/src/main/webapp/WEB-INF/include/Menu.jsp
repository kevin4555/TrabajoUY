<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                    <c:if test="${tipoUsuario == TipoUsuario.EMPRESA}">
                        <tr>
                            <td class="d-flex"><a class="btn" href="${pageContext.request.contextPath}/altaOferta">Alta Oferta</a></td>
                        </tr>
                    </c:if>
                    <tr>
                        <td class="d-flex"><a class="btn" href="${pageContext.request.contextPath}/consultaUsuarios">Consulta usuarios</a></td>
                    </tr>
                    <tr>
                        <td class="d-flex"><a class="btn" href="${pageContext.request.contextPath}/consultaTipoPublicacion">Ver tipos de publicaciones</a></td>
                    </tr>
                    <tr>
                        <td class="d-flex"><a class="btn" href="${pageContext.request.contextPath}/consultaPaquetes">Ver tipos de paquetes</a></td>
                    </tr>
                    <tr>
                        <td class="d-flex"><a class="btn" href="${pageContext.request.contextPath}/consultaOfertas">Consulta ofertas laborales</a></td>
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
                    <c:forEach var="key" items="${listaKeywords}">
                        <c:url var="keywordUrl" value="/consultaOfertas">
                            <c:param name="keyword" value="${key}" />
                        </c:url>
                        <tr>
                            <td><a class="btnKeyword btn" href="${keywordUrl}">${key}</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</div>