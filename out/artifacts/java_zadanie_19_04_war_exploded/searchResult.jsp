<%@ page import="Books.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search result</title>
</head>
<body>
<style>
div {text-align: center;}
</style>
<%ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books");%>
<%for (Book book : books) { %>
<div><p><%=book.getTitle()%></p></div>
<form action="displayAll" method="get">
    <input type="hidden" name="book" value="<%=book.getId()%>"> <!--przekazanie id wybranej ksiazki -->
    <input type="hidden" name="mail" value="<%=session.getAttribute("mail")%>"> <!--przekazanie maila usera -->
    <div style="text-align: center"><img src="<%=book.getImagePath()%>" width="400" height="500"></div>
    <div style="text-align: center">Cena rynkowa: <%=book.getPrice()%> zł</div>
    <div style="text-align: center"><input type="submit" value="wypożycz"></div>
    <hr size="10" width="100%" align="left" color="blue">
</form>
<% } %>
<tr><td colspan="2"><a href="search.jsp">POWRÓT</a></td></tr>
</body>
</html>
