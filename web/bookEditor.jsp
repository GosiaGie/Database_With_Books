<%@ page import="Books.Book" %>
<%@ page import="pl.bookstore.database.DatabaseDAO" %>
<%@ page import="pl.bookstore.database.ServicesDatabase" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edycja książek</title>
</head>
<body>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<% if(request.getAttribute("message") !=null){ %>
<div style="text-align: center;  background-color: red;"><%=request.getAttribute("message")%></div>
<%}%>
<tr>
    <a href="bookEditorAddBook.jsp">DODAJ KSIĄŻKĘ</a>
</tr>
<%DatabaseDAO databaseDAO = new ServicesDatabase();%>
<%ArrayList<Book> books = databaseDAO.findAllBooks();%>
<%for (Book book : books) { %>
<form action="bookEditor" method="post">
    <p><text-align:center><%=book.getTitle()%></text-align:center></p>
    <img src="<%=book.getImagePath()%>" width="400" height="500">
    <p>Cena rynkowa: <%=book.getPrice()%> zł</p>
    <input type="hidden" name="book" value="<%=book.getId()%>">
    <p><button type="submit" name="action" value="delete">USUŃ</button></p>
    <p><button type="submit" name="action" value="changeImage">ZMIEŃ GRAFIKĘ</button> Podaj ścieżkę <input type="text" name="path"></p>
    <p><button type="submit" name="action" value="changePrice">ZMIEŃ CENĘ RYNKOWĄ</button> Podaj nową cenę <input type="text" name="price"></p>
    <hr size="6" width="50%" align="left" color="blue">
    </form>
    <% } %>
</body>
</html>
