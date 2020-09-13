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
        String password = "JavaRocks";
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, username, password);

        if (connection == null) {
            throw new Exception("nie udalo sie polaczyc");
        } else {
            return connection;
        }
    }

    @Override
    public boolean checkLogin(String mail, String pass) throws Exception {

        String foundMail = null;
        String foundPass = null;

        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select mail, pass from users_data where mail =" + addApo(mail) + "and pass =" + addApo(pass));

        while (resultSet.next()) {
            foundMail = resultSet.getString("mail");
            foundPass = resultSet.getString("pass");
        }

        try {
            foundMail.equals(null);
            foundPass.equals(null);
        } catch (NullPointerException e) {
            return false;
        }


        if (foundMail.equals(mail) && foundPass.equals(pass)) {
            System.out.println("true");
            return true;
        } else {
            System.out.println("false");
            return false;
        }
    }


    @Override
    public boolean findMail(String mail) throws Exception {

        Statement statement = getConnection().createStatement();
        String result = null;

        ResultSet resultSet = statement.executeQuery("select mail from users_data where mail =" + mail);

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

        Statement statement = getConnection().createStatement();
        mail = addApo(mail);
        pass = addApo(pass);
        String role = addApo("user");


        statement.executeUpdate("insert into users_data (mail, pass, role) values (" + mail + "," + pass + "," + role + ")");

    }


    @Override
    public ArrayList<Book> findAllBooks() throws Exception {

        Statement statement = getConnection().createStatement();

        ArrayList<Book> books = new ArrayList();
        Book book = null;
        String result = null;
        Boolean found = false;

        ResultSet resultSet = statement.executeQuery("select b.id, b.title, i.image_path, p.price from books b, images i, prices p where b.id = i.id and b.id = p.id and b.id not in (select id from borrowed_books)");

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
    public int findUserByMail(String mail) throws Exception {

        Statement statement = getConnection().createStatement();

        mail = addApo(mail);

        int id = 0;
        Boolean found = false;

        ResultSet resultSet = statement.executeQuery("select id from users_data where mail=" + mail);

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
    public boolean checkAdminRole(String mail) throws Exception {

        Statement statement = getConnection().createStatement();

        mail = addApo(mail);
        String foundMail = null;

        ResultSet resultSet = statement.executeQuery("select role from users_data where mail=" + mail);

        while (resultSet.next()) {
            foundMail = resultSet.getString("role");
        }

        if (foundMail.equals("admin")) {
            return true;
        } else {
            return false;
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
    public String addBook(String title, String first_name, String last_name, int page_number, String price, String image) throws Exception {

        Statement statement = getConnection().createStatement();

        title = addApo(title);
        first_name = addApo(first_name);
        last_name = addApo(last_name);
        image = addApo(image);

        Character comma = ',';
        if (price.indexOf(',') != -1) {
            price = price.replace(',', '.');
        }

        statement.executeUpdate("insert into books (first_name, last_name, title, page_number) values(" + first_name + "," + last_name + "," + title + "," + page_number + ")");

        int id = 0; //id ostatniej ksiazki potrzebne do zapisu ceny i obrazka w oddzielnych tablicach
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
        ;
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


}
