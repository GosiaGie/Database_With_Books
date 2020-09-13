package pl.bookstore.database;

import Books.Book;
import User.User;

import java.util.ArrayList;

public interface DatabaseDAO {

    ArrayList<Book> findAllBooks() throws Exception;
    void removeBook(int id) throws Exception;
    void changeImage(int id, String path) throws Exception;
    void changePrice(int id, String price) throws Exception;
    String addBook(String title, String first_name, String last_name, int page_number, String price, String image) throws Exception;
    boolean returnBook(int id) throws Exception;
    boolean saveBorrowedBook(int id, int id_user) throws Exception;
    Book findBookById (int id) throws Exception;


    boolean findMail(String mail) throws Exception;//sprawdz, czy mail jest w bazie
    boolean checkLogin(String mail, String pass) throws Exception;
    void register(String mail, String pass) throws Exception;
    boolean deleteUser(int id) throws Exception;
    ArrayList<User> findAllUsers() throws Exception;
    Book getUsersBooks(int id) throws Exception;
    int findUserByMail(String mail) throws Exception;
    boolean checkUsersBooks (int id_user) throws Exception;
    boolean checkAdminRole(String mail) throws Exception;


}
