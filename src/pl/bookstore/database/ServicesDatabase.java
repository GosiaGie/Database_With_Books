package pl.bookstore.database;

import Books.Book;
import User.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServicesDatabase implements DatabaseDAO {

    private static final DatabaseDAO databaseDAO = new Database();

    @Override
    public ArrayList<Book> findAllBooks() throws Exception {
        return databaseDAO.findAllBooks();
    }

    @Override
    public void removeBook(int id) throws Exception {
        databaseDAO.removeBook(id);
    }

    @Override
    public void changeImage(int id, String path) throws Exception {
        databaseDAO.changeImage(id, path);
    }

    @Override
    public void changePrice(int id, String price) throws Exception {
        databaseDAO.changePrice(id, price);

    }

    @Override
    public String addBook(String title, String first_name, String last_name, int page_number, String price, String image) throws Exception {
        return databaseDAO.addBook(title, first_name, last_name, page_number, price, image);
    }


    @Override
    public boolean returnBook(int id) throws Exception {
       return databaseDAO.returnBook(id);
    }


    @Override
    public boolean saveBorrowedBook(int id, int id_user) throws Exception {
        return databaseDAO.saveBorrowedBook(id, id_user);
    }

    @Override
    public Book findBookById(int id) throws Exception {
        return databaseDAO.findBookById(id);
    }


    @Override
    public boolean findMail(String mail) throws Exception {
        return databaseDAO.findMail(mail);
    }


    @Override
    public boolean checkLogin(String mail, String pass) throws Exception {
        return databaseDAO.checkLogin(mail, pass);
    }


    @Override
    public void register(String mail, String pass) throws Exception {
        databaseDAO.register(mail, pass);
    }


    @Override
    public boolean deleteUser(int id) throws Exception {
        return databaseDAO.deleteUser(id);
    }


    @Override
    public ArrayList<User> findAllUsers() throws Exception {
        return databaseDAO.findAllUsers();
    }


    @Override
    public Book getUsersBooks(int id) throws Exception {
        return databaseDAO.getUsersBooks(id);
    }

    @Override
    public int findUserByMail(String mail) throws Exception {
       return databaseDAO.findUserByMail(mail);
    }

    @Override
    public boolean checkUsersBooks(int id_user) throws Exception {
        return databaseDAO.checkUsersBooks(id_user);
    }

    @Override
    public boolean checkAdminRole(String mail) throws Exception {
        return databaseDAO.checkAdminRole(mail);
    }
}
