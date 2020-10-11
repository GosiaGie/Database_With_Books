import Books.Book;
import User.User;
import pl.bookstore.database.DatabaseDAO;
import pl.bookstore.database.ServicesDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/displayAll")

public class DisplayAll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        DatabaseDAO databaseDAO = new ServicesDatabase();
        User user = (User) session.getAttribute("user");


        //checking that an user has unreturned book
        try {
            if (databaseDAO.checkUsersBooks(user.getId())) {
                System.out.println(user.getId());
                System.out.println("wykonuje sie");
                Book borrowedBook = databaseDAO.getUsersBooks(user.getId());
                request.setAttribute("borrowedBook", borrowedBook);
                request.getRequestDispatcher("/borrowedBookDisplayer.jsp").forward(request, response);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        int chosedBookId = Integer.parseInt(request.getParameter("book"));
        Book chosedBook = null;

        try {
            chosedBook = databaseDAO.findBookById(chosedBookId);
            session.setAttribute("chosedBook", chosedBook);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(chosedBookId);

        try {
            databaseDAO.saveBorrowedBook(chosedBook.getId(), user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("chosedBook", chosedBook);
        request.getRequestDispatcher("/summary.jsp").forward(request, response);


    }
}

