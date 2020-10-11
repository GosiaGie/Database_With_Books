import Books.Book;
import pl.bookstore.database.DatabaseDAO;
import pl.bookstore.database.ServicesDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/search")
public class Search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DatabaseDAO databaseDAO = new ServicesDatabase();
        int action = Integer.parseInt(request.getParameter("action")); //sprawdzamy

        ArrayList <Book> books = null;
        String categoryString = null; //string na kategorie
        String name = null;


        switch (action){

            case 1:
                categoryString = "kryminał";
                try {
                     books = databaseDAO.findBooksByCategory(categoryString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("books", books);
                request.getRequestDispatcher("/searchResult.jsp").forward(request, response);


            case 2:
                categoryString = "powieść społeczna";
                try {
                    books = databaseDAO.findBooksByCategory(categoryString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("books", books);
                request.getRequestDispatcher("/searchResult.jsp").forward(request, response);

            case 3:
                categoryString = "powieść historyczna";
                try {
                    books = databaseDAO.findBooksByCategory(categoryString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("books", books);
                request.getRequestDispatcher("/searchResult.jsp").forward(request, response);

            case 4:
                categoryString = "literatura naukowa";
                try {
                    books = databaseDAO.findBooksByCategory(categoryString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("books", books);
                request.getRequestDispatcher("/searchResult.jsp").forward(request, response);


            case 5:
                name = request.getParameter("name"); //string na imie+nazwisko lub nazwisko
                try {
                    books = databaseDAO.findBooksByName(name);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                request.setAttribute("books", books);
                request.getRequestDispatcher("searchResult.jsp").forward(request, response);

        }





    }



}
