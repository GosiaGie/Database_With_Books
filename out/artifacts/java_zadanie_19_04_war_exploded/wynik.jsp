
<%@ page import="java.util.ArrayList" %>
<%@ page import="static sun.misc.Version.println" %>
<%@ page import="java.util.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><% ArrayList<String> lista = (ArrayList<String>) request.getAttribute("lista");%>

            <%for(int i=0; i<lista.size(); i++){%>
            <p><%=lista.get(i)%></p>
            <%} %>
    </h1>
    </body>
</html>
