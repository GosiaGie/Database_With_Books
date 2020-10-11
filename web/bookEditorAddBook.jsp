<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie książki do bazy</title>
</head>
<body>
<p>${message}</p>
<p><%=request.getAttribute("gatunek")%></p>
<form action="bookEditorAddBook" method="get">
    <p>tytuł <input type="text" name="title"></p>
    <p>imię autora <input type="text" name="first_name"></p>
    <p>nazwisko autora <input type="text" name="last_name"></p>
    <p>ilość stron <input type="text" name="page_number"></p>
    <p>cena <input type="text" name="price"></p>
    <p>obrazek <input type="text" name="image"></p>
    <p>Wybierz kategorię</p>
    <select name="category" multiple>
        <option>kryminał</option>
        <option>literatura naukowa</option>
        <option>powieść społeczna</option>
        <option>powieść historyczna</option>
    </select>
    <p> <input type="submit" name="addBook" value="Dodaj książkę"></p>
</form>
</body>
</html>
