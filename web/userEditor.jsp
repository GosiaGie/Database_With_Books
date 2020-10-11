<%@ page import="User.User" %>
<%@ page import="pl.bookstore.database.DatabaseDAO" %>
<%@ page import="pl.bookstore.database.ServicesDatabase" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edycja użytkowników</title>
</head>
<body>
<% DatabaseDAO databaseDAO = new ServicesDatabase();%>
<% ArrayList<User> users_data = databaseDAO.findAllUsers();%>
<%for (User user : users_data) { %>
<form action="userEditor" method="get">
<p><text-align:center>Numer karty: <%=user.getId()%>
    <p><%=user.getMail()%></p>
    <input type="hidden" name="id" value="<%=user.getId()%>"> <!--przekazanie id usera -->
    <input type="hidden" name="mail" value="<%=user.getMail()%>"> <!--przekazanie maila usera -->
    <p><input type="submit" name="action" value="USUŃ UŻYTKOWNIKA"></p>
    <hr size="6" width="100%" align="left" color="blue">
    </form>
    <% } %>
    <%if (request.getAttribute("result") != null) {%>
    <%=request.getAttribute("result")%>
    <%}%>
    <tr><td colspan="2"><a href="adminPanel.jsp">POWRÓT</a></td></tr>
    </body>
    </html>
