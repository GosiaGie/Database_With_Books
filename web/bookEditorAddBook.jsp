<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodawanie książki do bazy</title>
</head>
<body>
<form action="bookEditorAddBook" method="get">
    <p>tytuł <input type="text" name="title"></p>
    <p>imię autora <input type="text" name="first_name"></p>
    <p>nazwisko autora <input type="text" name="last_name"></p>
    <p>ilość stron <input type="text" name="page_number"></p>
    <p>cena <input type="text" name="price"></p>
    <p>obrazek <input type="text" name="image"></p>
    <p> <input type="submit" name="addBook" value="Dodaj ksiazke"></p>
</form>
</body>
</html>
