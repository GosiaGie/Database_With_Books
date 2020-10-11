<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Szukaj</title>
</head>

<body>
<form action="search" method="get">
    <p><button type="submit" name="action" value="1">KRYMINAŁ</button></p>
    <p><button type="submit" name="action" value="2">POWIEŚĆ SPOŁECZNA</button></p>
    <p><button type="submit" name="action" value="3">POWIEŚĆ HISTORYCZNA</button></p>
    <p><button type="submit" name="action" value="4">LITERATURA NAUKOWA</button></p>
    <p>WYSZUKIWANIE AUTORA: wpisz nazwisko lub imię i nazwisko <input type="text" name="name"><button type="submit" name="action" value="5">SZUKAJ</button></p>
</form>
<tr><td colspan="2"><a href="displayAll.jsp">POWRÓT</a></td></tr>
</body>
</html>
