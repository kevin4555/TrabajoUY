<%@page import="model.TipoUsuario"%>
<%@page import="java.util.ArrayList"%>

<section class="text-center">
	<div class="tableKeyword table-responsive">
		<!-- Ajusta la altura máxima según tus necesidades -->
		<table class="table table-bordered">
			<thead class="table-primary">
			</thead>
			<tbody>
				<tr>
					<td>
						<div class="d-flex flex-nowrap overflow-auto">
							<%
							ArrayList<String> listaKeywords = (ArrayList) session.getAttribute("listaKeywords");
							for (String key : listaKeywords)
							{
							%>
							<%
							String contextPath = request.getContextPath();
							%>
							<div class="m-2">
								<a class="btnKeyword btn"
									href="<%=contextPath%>/consultaOfertas?keyword=<%=key%>">
									<%=key%></a>
							</div>
							<%
							}
							%>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</section>