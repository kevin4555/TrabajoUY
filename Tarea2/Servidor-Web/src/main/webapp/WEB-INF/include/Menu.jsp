<%@page import="model.TipoUsuario"%>
<%@page import="java.util.ArrayList"%>

<div class="col-3">
    <section class="text-center">
        <div class="table-responsive overflow-auto tablaKeyword">
            <table class="table table-hover table-bordered">
                <thead class="table-primary">
                    <tr>
                        <th class="cabezaTabla">Men�</th>
                    </tr>
                </thead>
                <tbody>
                
                    <%
                    TipoUsuario tipoUsuarioSesion = (TipoUsuario) session.getAttribute("tipoUsuario");
                    if (tipoUsuarioSesion == TipoUsuario.EMPRESA) { %>
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
                        String contextPath = request.getContextPath();
                        %>
                        <tr>
                            <td><a class="btnKeyword btn" href="<%= contextPath %>/consultaOfertas?keyword=<%= key %>"> <%= key %></a></td>            
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </section>
</div>