<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Books.Book" %>
<html>
<head>
    <title>Przyjmij zwrot</title>
</head>
<body>
<form action="borrowedBooksManager" method="get">
    <%Book book = (Book) request.getAttribute("book"); %>
    <div style="text-align: center">Wypożyczona książka</div>
    <div style="text-align: center"><%=book.getTitle()%></div>
    <div style="text-align: center"><img src="<%=book.getImagePath()%>" width="400" height="500"></div>
    <input type="hidden" name ="id" value="<%=request.getAttribute("id")%>">

    <div style="text-align: center; height: 300px;"><input type="submit" name="return" value="przyjmij zwrot"></div>
</form>
</body>
</html>
