<%@ page import="User.User" %>
<%@ page import="pl.bookstore.database.DatabaseDAO" %>
<%@ page import="pl.bookstore.database.ServicesDatabase" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Przyjęcie zwrotu</title>
</head>
<body>
<p>${message}</p>
<% DatabaseDAO databaseDAO = new ServicesDatabase();%>
<% ArrayList<User> users = databaseDAO.findAllUsers();%>
<%for (User user : users) { %>
<form action="borrowedBooks" method="get">
    <p><%=user.getMail()%><input type="hidden" name="id" value="<%=user.getId()%>"></p>
    <input type="hidden" name="mail" value="<%=user.getMail()%>">
    <input type="submit" name="borrBook" value="przyjmij zwrot">
</form>
<%}%>
<tr><td colspan="2"><a href="adminPanel.jsp">POWRÓT</a></td></tr>
</body>
</html>
