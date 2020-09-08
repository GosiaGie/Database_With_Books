import pl.bookstore.database.DatabaseDAO;
import pl.bookstore.database.ServicesDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/userEditor")
public class UserEditor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean result = false;
        int id = Integer.parseInt(request.getParameter("id"));

        DatabaseDAO databaseDAO = new ServicesDatabase();


        try {
            result = databaseDAO.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result) {
            request.setAttribute("result", "usunięto");
        } else {
            request.setAttribute("result", "błąd");
        }


        request.getRequestDispatcher("userEditor.jsp").forward(request, response);
    }
}
