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
<p>${message}</p>
<tr>
    <a href="bookEditorAddBook.jsp">DODAJ KSIĄŻKĘ</a>
</tr>
<%DatabaseDAO databaseDAO = new ServicesDatabase();%>
<%ArrayList<Book> books = databaseDAO.findAllBooks();%>
<%for (Book book : books) { %>
<form action="bookEditor" method="post">
    <p><b><text-align:center><%=book.getTitle()%></b></text-align:center></p>
    <img src="<%=book.getImagePath()%>" width="400" height="500">
    <p><b>Cena rynkowa: </b><%=book.getPrice()%> zł</p>
    <p><b>Kategoria: </b><%=book.getCategory()%></p>
    <input type="hidden" name="book" value="<%=book.getId()%>">
    <p><button type="submit" name="action" value="1">USUŃ</button></p>
    <p><button type="submit" name="action" value="2">ZMIEŃ GRAFIKĘ</button> Podaj ścieżkę <input type="text" name="path"></p>
    <p><button type="submit" name="action" value="3">ZMIEŃ CENĘ RYNKOWĄ</button> Podaj nową cenę <input type="text" name="price"></p>
    <p><button type="submit" name="action" value="4">ZMIEŃ KATEGORIĘ</button>
    <select name="category" multiple>
        <option>kryminał</option>
        <option>literatura naukowa</option>
        <option>powieść społeczna</option>
        <option>powieść historyczna</option>
    </select>
    <hr size="6" width="50%" align="left" color="blue">
    </form>
    <% } %>
<tr><td colspan="2"><a href="adminPanel.jsp">POWRÓT</a></td></tr>
</body>
</html>
