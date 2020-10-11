<%@ page import="Books.Book" %>
<%@ page import="pl.bookstore.database.DatabaseDAO" %>
<%@ page import="pl.bookstore.database.ServicesDatabase" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Wybierz książkę</title>
</head>
<body>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%DatabaseDAO databaseDAO = new ServicesDatabase();%>
<%ArrayList<Book> books = databaseDAO.findAllBooks();%>
<p>${message}</p>
<div style="text-align: center;  background-color: pink;"><td colspan="2">WYSZUKIWARKA   <a href="search.jsp">KLIKNIJ TUTAJ</a></td></div>
<%for (Book book : books) { %>
<div style="text-align: center"><%=book.getTitle()%></div>
<form action="displayAll" method="get">
    <div style="text-align: center"><img src="<%=book.getImagePath()%>" width="400" height="500"></div>
    <div style="text-align: center">Cena rynkowa: <%=book.getPrice()%> zł</div>
    <input type="hidden" name="book" value="<%=book.getId()%>"> <!--przekazanie id wybranej ksiazki -->
    <div style="text-align: center"><input type="submit" value="wypożycz"></div>
    <hr size="10" width="100%" align="left" color="blue">
</form>
<% } %>
</body>
</html>
