<%-- 
    Document   : verclima
    Created on : 20-dic-2017, 10:36:20
    Author     : luis
--%>

<%@page import="paqueteaemet.CreaHTML"%>
<%@page import="paqueteaemet.ObjetoClima"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    List<ObjetoClima> lista_objetos_clima=(List<ObjetoClima>)request.getAttribute("lista_objetos_clima");
    String html_tabla=CreaHTML.crearTabla(lista_objetos_clima);
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Predicción</h1>
        <%=html_tabla%>
    </body>
</html>
