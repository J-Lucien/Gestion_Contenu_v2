<%-- 
    Document   : list
    Created on : Dec 13, 2021, 10:50:36 AM
    Author     : Lucien
--%>
<%@page import="Employe.Employe"%>
<%@page import="java.util.List"%>
<%!
    List<Object> listemp = null;
%>
<%
    listemp = (List<Object>) request.getAttribute("list");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List employe!</h1>
        <% for(int i=0; i<listemp.size(); i++) { %>
        <p>
        <%= ((Employe)listemp.get(i)).getEmail() %>
        </p>
        <% } %>
    </body>
</html>
