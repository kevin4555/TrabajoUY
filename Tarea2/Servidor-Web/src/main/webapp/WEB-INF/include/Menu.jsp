<%@page import="model.TipoUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
%>

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
                    <%if (tipoUsuario == TipoUsuario.EMPRESA) { %>
                        <tr>
                            <td class="d-flex"><a class="btn" href="<%= request.getContextPath() %>/altaOferta">Alta Oferta</a></td>
                        </tr>
                    <% } %>
                    <tr>
                        <td class="d-flex"><a class="btn" href="<%= request.getContextPath() %>/consultaUsuarios">Consulta usuarios</a></td>
                    </tr>
                    <tr>
                        <td class="d-flex"><a class="btn" href="<%= request.getContextPath() %>/consultaTipoPublicacion">Ver tipos de publicaciones</a></td>
                    </tr>
                    <tr>
                        <td class="d-flex"><a class="btn" href="<%= request.getContextPath() %>/consultaPaquetes">Ver tipos de paquetes</a></td>
                    </tr>
                    <tr>
                        <td class="d-flex"><a class="btn" href="<%= request.getContextPath() %>/consultaOfertas">Consulta ofertas laborales</a></td>
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
                    <%
                    ArrayList<String> listaKeywords = (ArrayList) session.getAttribute("listaKeywords");
                    for (String key : listaKeywords) { %>
                        <%
                        String keywordUrl = request.getContextPath() + "/consultaOfertas?keyword=" + key;
                        %>
                        <tr>
                            <td><a class="btnKeyword btn" href="<%= keywordUrl %>"><%= key %></a></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </section>
</div>