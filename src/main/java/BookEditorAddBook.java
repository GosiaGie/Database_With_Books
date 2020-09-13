import pl.bookstore.database.DatabaseDAO;
import pl.bookstore.database.ServicesDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/bookEditorAddBook")

public class BookEditorAddBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String page_number_string = request.getParameter("page_number");
        String price = request.getParameter("price");
        String image = request.getParameter("image");

        if (title.length()==0 || first_name.length()==0 || last_name.length()==0|| page_number_string.length()==0
            ||price.length()==0 || image.length()==0){

            request.setAttribute("mesaage", "brak danych");
            request.getRequestDispatcher("/bookEditorAddBook.jsp").forward(request, response);
        }

        else {
            int page_number = Integer.parseInt(page_number_string);

            DatabaseDAO databaseDAO = new ServicesDatabase();

            try {
                databaseDAO.addBook(title, first_name, last_name, page_number, price, image);
            } catch (Exception e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("bookEditorAddBook.jsp").forward(request, response);
        }
    }
}
