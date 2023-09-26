<%@page import="logica.DataTypes.DTOfertaLaboral"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%
	ArrayList<String> listaKeywords =(ArrayList<String>) request.getAttribute("listaKeywords");
	ArrayList<DTOfertaLaboral> listaOfertas = (ArrayList<DTOfertaLaboral>) request.getAttribute("listaOfertasConfirmadas");
%> 
</head>
<body>
<table>
<tr>
<td>KeyWords </td> 
 </tr>

<% for(String key : listaKeywords) {%>

<tr>
	<td><%= key %></td>
</tr>

<%} %>
</table>

</body>
</html>