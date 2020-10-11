<%@ page import="Books.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Podsumowanie</title>
</head>
<body>
    <p>Potwierdzamy wybór książki</p>
    <%Book chosedBook = (Book)session.getAttribute("chosedBook");%>
    <p><%=chosedBook.getTitle()%></p>
    <img src="<%=chosedBook.getImagePath()%>" width="400" height="500">
    <p>Zapraszamy po odbiór do naszej wypożyczalni</p>
    <p><iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d2445.102570575638!2d20.9368153!3d52.2051835!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x471ecc6fe00228ad%3A0x509aff7947af42e3!2sPWN%20Wydawnictwo%20Szkolne%20sp.%20z%20o.o.%20sp.k.!5e0!3m2!1spl!2spl!4v1595180548414!5m2!1spl!2spl" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe></p>
    <tr><td colspan="2"><a href="index.jsp">POWRÓT</a></td></tr>
</body>
</html>
