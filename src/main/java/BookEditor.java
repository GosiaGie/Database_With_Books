import pl.bookstore.database.DatabaseDAO;
import pl.bookstore.database.ServicesDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/bookEditor")
public class BookEditor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int action = Integer.parseInt(request.getParameter("action")); //pobieramy akcje
        int id = Integer.parseInt(request.getParameter("book")); //szukamy ksiazki po id

        DatabaseDAO databaseDAO = new ServicesDatabase();

        switch (action) {
            case 1://usuwanie
                try {
                    databaseDAO.removeBook(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("message", "usunięto");
                request.getRequestDispatcher("/bookEditor.jsp").forward(request, response);

                break;

            case 2://zmien grafike
                String path = request.getParameter("path");

                if (path.length() == 0) {
                    request.setAttribute("message", "ścieżka jest pusta");
                    request.getRequestDispatcher("/bookEditor.jsp").forward(request, response);
                } else {

                    try {
                        databaseDAO.changeImage(id, path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    request.setAttribute("message", "zmieniono ścieżkę");
                    request.getRequestDispatcher("/bookEditor.jsp").forward(request, response);
                }
                break;


            case 3:
                String price = request.getParameter("price").replace('.', ',');
                if (price.length() == 0) {
                    request.setAttribute("message", "cena jest pusta");
                    request.getRequestDispatcher("/bookEditor.jsp").forward(request, response);
                }
                else {
                    try {
                        databaseDAO.changePrice(id, price);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    request.setAttribute("message", "zmieniono cenę");
                    request.getRequestDispatcher("/bookEditor.jsp").forward(request, response);

                    break;
                }

            case 4:
                String category = request.getParameter("category");

                try {
                    databaseDAO.changeCategory(id, category);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                request.setAttribute("message", "zmieniono gatunek");
                request.getRequestDispatcher("/bookEditor.jsp").forward(request, response);

                break;


        }
    }

}

