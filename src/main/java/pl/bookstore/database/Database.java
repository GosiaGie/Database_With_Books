package pl.bookstore.database;

import Books.Book;
import User.User;

import java.sql.*;
import java.util.ArrayList;

public class Database implements DatabaseDAO {

    public static String addApo(String string) {//dodaje apostrof na poczatku i koncu

        return "'" + string + "'";
    }

    public Connection getConnection() throws Exception {

        String url = "jdbc:mysql://localhost:3306/sakila?allowPublicKeyRetrieval=true&useSSL=false";
        String username = "root";
        String password = "kochamJava";
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, username, password);

        if (connection == null) {
            throw new Exception("nie udalo sie polaczyc");
        } else {
            return connection;
        }
    }


    @Override
    public User checkLogin(String mail, String pass) throws Exception {

        User user = new User();
        String foundMail = null;
        String foundPass = null;
        int id = 0;
        String role = null;

        PreparedStatement preparedStatement = getConnection().prepareStatement("select id, mail, pass, role from users_data where mail = ? and pass = ?");
        preparedStatement.setString(1, mail);
        preparedStatement.setString(2, pass);

        ResultSet resultSet = preparedStatement.executeQuery();


        while (resultSet.next()) {
            foundMail = resultSet.getString("mail");
            foundPass = resultSet.getString("pass");
            id = resultSet.getInt("id");
            role = resultSet.getString("role");
        }

        if (foundMail == null && foundPass == null) {
            return null;
        } else {
            user.setMail(mail);
            user.setId(id);
            user.setRole(role);
            System.out.println(role);
            return user;
        }
    }


    @Override
    public boolean findMail(String mail) throws Exception {

        String result = null;
        PreparedStatement preparedStatement = getConnection().prepareStatement("select mail from users_data where mail = ?");

        preparedStatement.setString(1, mail);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            result = resultSet.getString("mail");
        }

        if (result != null) {
            return true;
        } else {
            return false;
        }

    }


    @Override
    public void register(String mail, String pass) throws Exception {

        PreparedStatement preparedStatement = getConnection().prepareStatement("insert into users_data (mail, pass, role) values (?,?,?)");

        String role = "user";

        preparedStatement.setString(1, mail);
        preparedStatement.setString(2, pass);
        preparedStatement.setString(3, role);

        preparedStatement.executeUpdate();

    }



    @Override
    public ArrayList<Book> findAllBooks() throws Exception {

        Statement statement = getConnection().createStatement();

        ArrayList<Book> books = new ArrayList();
        Book book = null;
        String result = null;
        Boolean found = false;

        ResultSet resultSet = statement.executeQuery("select b.id, b.title, b.category, i.image_path, p.price from books b, images i, prices p where b.id = i.id and b.id = p.id and b.id not in (select id from borrowed_books)");

        while (resultSet.next()) {
            book = new Book();
            book.setId(Integer.parseInt(resultSet.getString("b.id")));
            book.setTitle(resultSet.getString("b.title"));
            book.setImagePath(resultSet.getString("i.image_path"));
            book.setPrice(resultSet.getString("p.price"));
            book.setCategory(resultSet.getString("b.category"));
            books.add(book);
        }

        return books;

    }

    @Override
    public int findUserByMail(String mail) throws Exception {

        PreparedStatement preparedStatement = getConnection().prepareStatement("select id from users_data where mail= ?");

        preparedStatement.setString(1, mail);

        int id = 0;
        Boolean found = false;

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            id = Integer.parseInt(resultSet.getString("id"));
        }

        return id;
    }



    @Override
    public Book findBookById(int id) throws Exception {

        Statement statement = getConnection().createStatement();

        Book book = new Book();
        Boolean found = false;

        ResultSet resultSet = statement.executeQuery("select b.id, b.title, i.image_path, p.price from books b, images i, prices p where b.id = i.id and b.id = p.id and b.id =" + id);

        while (resultSet.next()) {
            book.setId(Integer.parseInt(resultSet.getString("b.id")));
            book.setTitle(resultSet.getString("b.title"));
            book.setImagePath(resultSet.getString("i.image_path"));
            book.setPrice(resultSet.getString("p.price"));
        }

        return book;
    }


    @Override
    public boolean saveBorrowedBook(int id, int id_user) throws Exception {

        Statement statement = getConnection().createStatement();

        statement.executeUpdate("insert into borrowed_books (id, id_user, borrow_date) values (" + id + "," + id_user + "," + "curdate()" + ")");

        return true;
    }


    @Override
    public boolean checkUsersBooks(int id_user) throws Exception {

        Statement statement = getConnection().createStatement();
        int found_id = 0;

        ResultSet resultSet = statement.executeQuery("select id_user from borrowed_books where id_user =" + id_user);

        while (resultSet.next()) {
            found_id = resultSet.getInt("id_user");
        }

        if (found_id == 0) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void removeBook(int id) throws Exception {

        Statement statement = getConnection().createStatement();

        statement.executeUpdate("delete from prices where id=" + id);
        statement.executeUpdate("delete from images where id=" + id);
        statement.executeUpdate("delete from books where id=" + id);

    }

    @Override
    public void changeImage(int id, String path) throws Exception {

        Statement statement = getConnection().createStatement();

        path = addApo(path);
        statement.executeUpdate("update images set image_path = (" + path + ")" + "where id=" + id);

    }

    @Override
    public void changePrice(int id, String price) throws Exception {

        Statement statement = getConnection().createStatement();

        if (price.indexOf('.') != -1) {
            price = price.replace('.', ',');
        }

        price = addApo(price);

        statement.executeUpdate("update prices set price =" + price + "where id=" + id);

    }

    @Override
    public void changeCategory(int id, String category) throws Exception {

        Statement statement = getConnection().createStatement();

        category = addApo(category);

        statement.executeUpdate("update books set category =" + category + "where id=" + id);

    }


    @Override
    public String addBook(String title, String first_name, String last_name, int page_number, String price, String image, String category) throws Exception {

        Statement statement = getConnection().createStatement();

        title = addApo(title);
        first_name = addApo(first_name);
        last_name = addApo(last_name);
        image = addApo(image);
        category = addApo(category);

        Character comma = ',';
        if (price.indexOf(',') != -1) {
            price = price.replace(',', '.');
        }

        statement.executeUpdate("insert into books (first_name, last_name, title, page_number, category) values(" + first_name + "," + last_name + "," + title + "," + page_number + ","+category+ ")");

        int id = 0; //szukamy id ostatniej ksiazki potrzebne do zapisu ceny i obrazka w oddzielnych tabelach
        ResultSet resultSet = statement.executeQuery("select id from books order by id desc limit 1");

        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }

        statement.executeUpdate("insert into prices (id, price) values(" + id + "," + price + ")");
        statement.executeUpdate("insert into images (id, image_path) values(" + id + "," + image + ")");

        return "dodano";

    }

    @Override
    public boolean deleteUser(int id) throws Exception {

        Statement statement = getConnection().createStatement();
        statement.executeUpdate("delete from users_data where id=" + id);

        return true;
    }


    @Override
    public ArrayList<User> findAllUsers() throws Exception {

        Statement statement = getConnection().createStatement();

        ArrayList<User> users_data = new ArrayList();
        int id = 0;
        User user = null;

        ResultSet resultSet = statement.executeQuery("select * from users_data where role<>'admin'");

        while (resultSet.next()) {
            user = new User();
            user.setId(Integer.parseInt(resultSet.getString("id")));
            user.setMail(resultSet.getString("mail"));
            users_data.add(user);
        }
        return users_data;

    }


    @Override
    public Book getUsersBooks(int id) throws Exception {

        Statement statement = getConnection().createStatement();

        Book book = null;
        ResultSet resultSet = statement.executeQuery("select b.id, b.title, b.first_name, b.last_name, i.image_path from books b, borrowed_books bb, images i where b.id = bb.id and i.id = b.id and bb.id_user = " + id);

        while (resultSet.next()) {
            book = new Book();
            book.setId(Integer.parseInt(resultSet.getString("b.id")));
            book.setTitle(resultSet.getString("b.title"));
            book.setFirstName(resultSet.getString("b.first_name"));
            book.setLastName(resultSet.getString("b.last_name"));
            book.setImagePath(resultSet.getString("i.image_path"));
        }

        return book;

    }

    @Override
    public boolean returnBook(int id) throws Exception {

        Statement statement = getConnection().createStatement();

        statement.executeUpdate("delete from borrowed_books where id_user=" + id);
        return true;

    }


    @Override
    public ArrayList<Book> findBooksByName(String name) throws Exception {

        ArrayList<Book> books = new ArrayList();
        Book book = null;
        String result = null;
        Boolean found = false;
        String first_name = null;
        String last_name = null;

        Statement statement = getConnection().createStatement();
        ResultSet resultSet;


        if (!(name.indexOf(" ") == -1)) { //to oznacza imie i nazwisko

            String[] first_name_last_name = name.split(" ");
            first_name = addApo(first_name_last_name[0]);
            last_name = addApo(first_name_last_name[1]);

            resultSet = statement.executeQuery("select b.id, b.title, i.image_path, p.price from books b, images i, prices p where b.id = i.id and b.id = p.id and b.first_name ="+ first_name + "and b.last_name = "+ last_name);

        } else { //szukamy po nazwisku
            name = addApo(name);
            resultSet = statement.executeQuery("select b.id, b.title, i.image_path, p.price from books b, images i, prices p where b.id = i.id and b.id = p.id and b.last_name = "+name);
        }

        while (resultSet.next()) {
            book = new Book();
            book.setId(Integer.parseInt(resultSet.getString("b.id")));
            book.setTitle(resultSet.getString("b.title"));
            book.setImagePath(resultSet.getString("i.image_path"));
            book.setPrice(resultSet.getString("p.price"));
            books.add(book);
        }

        return books;


    }

    @Override
    public ArrayList<Book> findBooksByCategory(String category) throws Exception {

        Statement statement = getConnection().createStatement();

        ArrayList<Book> books = new ArrayList();
        Book book = null;
        String result = null;
        Boolean found = false;
        category = addApo(category);

        ResultSet resultSet = statement.executeQuery("select b.id, b.title, i.image_path, p.price from books b, images i, prices p where b.category ="+ category + "and b.id = i.id and b.id = p.id and b.id not in (select id from borrowed_books)");

        while (resultSet.next()) {
            book = new Book();
            book.setId(Integer.parseInt(resultSet.getString("b.id")));
            book.setTitle(resultSet.getString("b.title"));
            book.setImagePath(resultSet.getString("i.image_path"));
            book.setPrice(resultSet.getString("p.price"));
            books.add(book);
        }

        return books;

    }






}
