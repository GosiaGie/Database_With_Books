<%@ page import="pl.bookstore.database.Database" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Witamy w naszej wypożyczalni</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body bgcolor="#f0ffff">
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>
<form action="login" method="post">
    <p>Zaloguj się:</p>
    <p><% if(request.getAttribute("message") !=null){ %>
        <%=request.getAttribute("message")%></p>
    <%}%>
    <p>mail: <input type="text" name="mail"></p>
    <p>haslo: <input type="password" name="pass"></p>
    <p><input type="submit" value="zaloguj"></p>
    <p>${result_of_checking_account}</p>
    <tr>
        <td colspan="2">Niezarejestrowany? <a href="register.jsp">Kliknij tutaj</a></td>
    </tr>
</form>
<p>Korzystanie z naszej wypozyczalni wymaga akceptacji regulaminu:</p>
<p>1. Możesz wypożyczyć tylko 1 książkę.</p>
<p>2. Po zatwierdzeniu wyboru możesz ja od razu odebrac w godzinach pracy wypozyczalni</p>
</body>
</html>