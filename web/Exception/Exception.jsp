<%-- 
    Document   : Exception
    Created on : Jan 5, 2022, 1:22:33 PM
    Author     : Lucien
--%>
<%!
    Exception exception = null;
    StackTraceElement[] elementError = null;
%>
<%
    exception = (Exception) request.getAttribute("exception");
    elementError = exception.getStackTrace();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exception</title>
    </head>
    <body>
        <h1 style="color: red">Exception</h1>
        
        <p style="color: red"><%= exception.getMessage() %></p>
        <table>
            <% for (int i=0; i< elementError.length; i++) { %>
            <tr><td><%= elementError[i] %></td></tr>
            <% }%>
        </table>
        <p><%= exception.getCause()!= null ?exception.getCause() : ""  %></p>
    </body>
</html>
