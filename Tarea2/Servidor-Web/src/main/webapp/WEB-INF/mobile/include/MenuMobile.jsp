<%@page import="model.TipoUsuario"%>
<%@page import="java.util.ArrayList"%>

<section class="text-center">
    <div class="tableKeyword table-responsive"> <!-- Ajusta la altura máxima según tus necesidades -->
        <table class="table table-bordered">
            <thead class="table-primary">
            </thead>
            <tbody>
                <tr>
                    <td>
                        <div class="d-flex flex-nowrap overflow-auto">
                            <%
                            ArrayList<String> listaKeywords = (ArrayList) session.getAttribute("listaKeywords");
                            for (String key : listaKeywords) { %>
                                <%
                                String contextPath = request.getContextPath();
                                %>
                                <div class="m-2">
                                    <a class="btnKeyword btn" href="<%= contextPath %>/consultaOfertas?keyword=<%= key %>"> <%= key %></a>
                                </div>
                            <% } %>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</section>

<div class="col-2">
	<div class="collapse bg-light" id="navbarToggleExternalContent">
		<div class="border border-dark shadow-3 p-2 d-inline-block w-auto">
			<a class="btn btn-block border-bottom m-0"
				href="<%=request.getContextPath()%>/consultaOfertas">Ver
				ofertas</a> <a class="btn btn-block border-bottom m-0"
				href="<%=request.getContextPath()%>/postulacion">Postularse</a>
			<a class="btn btn-block m-0"
				href="<%=request.getContextPath()%>/listarOfertas">Ver
				Postulacion</a>
		</div>
	</div>
</div>