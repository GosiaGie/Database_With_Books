<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zarejestruj się</title>
</head>
<body>
<form action="register" method="post">
    <p><% if(request.getAttribute("message") !=null){ %>
        <%=request.getAttribute("message")%></p>
    <%}%>
<p><input type="text" name="mail"> podaj mail</p>
<p><input type="password" name="pass"> podaj hasło</p>
<p><input type="password" name="pass_2"> powtórz hasło</p>
<p><input type="submit" value="zarejestruj się"></p>
</form>
</body>
</html>
