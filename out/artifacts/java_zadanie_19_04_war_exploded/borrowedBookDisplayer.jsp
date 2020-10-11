<%@ page import="Books.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Niezwrócona książka!</title>
</head>
<body>
<div style="text-align: center">Masz niezwróconą książkę</div>
<%Book borrowedBook = (Book)request.getAttribute("borrowedBook");%>
<div style="text-align: center"><%=borrowedBook.getTitle()%></div>
<div style="text-align: center"><img src="<%=borrowedBook.getImagePath()%>" width="400" height="500"></div>
<p></p>
<div style="text-align: center;  background-color: pink;"><td colspan="2">POWRÓT DO KATALOGU KSIĄŻEK <a href="displayAll.jsp">KLIKNIJ TUTAJ</a></td></div>
</body>
</html>
