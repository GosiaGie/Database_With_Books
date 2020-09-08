<%@ page import="Books.Book" %>
<%@ page import="pl.bookstore.database.DatabaseDAO" %>
<%@ page import="pl.bookstore.database.ServicesDatabase" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wybierz książkę</title>
</head>
<body>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<%DatabaseDAO databaseDAO = new ServicesDatabase();%>
<%ArrayList<Book> books = databaseDAO.findAllBooks();%>
<%for (Book book : books) { %>
<p><%=book.getTitle()%></p>
<form action="displayAll" method="get">
    <input type="hidden" name="book" value="<%=book.getId()%>"> <!--przekazanie id wybranej ksiazki -->
    <input type="hidden" name="mail" value="<%=request.getAttribute("mail")%>"> <!--przekazanie maila usera -->
    <div style="text-align: center"><img src="<%=book.getImagePath()%>" width="400" height="500"></div>
    <div style="text-align: center">Cena rynkowa: <%=book.getPrice()%> zł</div>
    <div style="text-align: center"><input type="submit" value="wypozycz"></div>
    <hr size="10" width="100%" align="left" color="blue">
</form>
<% } %>
</body>
</html>
