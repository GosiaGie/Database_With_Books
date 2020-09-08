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

@WebServlet (urlPatterns = "/displayAll")

public class DisplayAll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id_of_chosed_book = Integer.parseInt(request.getParameter("book"));
        String users_mail = request.getParameter("mail");
        int users_id = 0;
        Book chosedBook = null;

        DatabaseDAO databaseDAO = new ServicesDatabase();


        try {
            users_id = databaseDAO.findUserByMail(request.getParameter("mail"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            chosedBook = databaseDAO.findBookById(id_of_chosed_book);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            databaseDAO.saveBorrowedBook(id_of_chosed_book, users_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("chosedBook", chosedBook);
        request.getRequestDispatcher("/summary.jsp").forward(request, response);





    }
}

