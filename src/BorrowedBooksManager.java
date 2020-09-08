import pl.bookstore.database.DatabaseDAO;
import pl.bookstore.database.ServicesDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/borrowedBooksManager")
public class BorrowedBooksManager extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DatabaseDAO databaseDAO = new ServicesDatabase();
        int id = Integer.parseInt(request.getParameter("id"));
        boolean removed = false;


        try {
            removed = databaseDAO.returnBook(id);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (removed) {
            request.setAttribute("message", "zwrot przyjęty");
        }

        else {
            request.setAttribute("message", "wystapil blad");

        }
        request.getRequestDispatcher("borrowedBooks.jsp").forward(request, response);
    }
}
