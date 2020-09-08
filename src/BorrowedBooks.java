import Books.Book;
import pl.bookstore.database.Database;
import pl.bookstore.database.DatabaseDAO;
import pl.bookstore.database.ServicesDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet (urlPatterns = "/borrowedBooks")
public class BorrowedBooks extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int id =  Integer.parseInt(request.getParameter("id"));
        String mail =  request.getParameter("mail");
        request.setAttribute("id", id);
        request.setAttribute("mail", mail);
        Book book = null;

        DatabaseDAO databaseDAO = new ServicesDatabase();


        try {
            book = databaseDAO.getUsersBooks(id);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (book==null){ //jezeli user nie ma ksiazki:
           request.setAttribute("message", "ten użytkownik nie ma książek");
           request.getRequestDispatcher("borrowedBooks.jsp").forward(request,response);
        }

        else { //jezeli ma ksiazke przekzuja do innego jsp i przekieruj admina do zarzadzania ksiazkami
            request.setAttribute("book", book);
            request.getRequestDispatcher("borrowedBooksManager.jsp").forward(request, response);

        }




    }
}
