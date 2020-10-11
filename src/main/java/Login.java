import Books.Book;
import User.User;
import pl.bookstore.database.Database;
import pl.bookstore.database.DatabaseDAO;
import pl.bookstore.database.ServicesDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DatabaseDAO databaseDAO = new ServicesDatabase();
        HttpSession session = request.getSession(true);
        User user = null;

        String mail = request.getParameter("mail");
        String pass = request.getParameter("pass");


        try {
            user = databaseDAO.checkLogin(mail, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user==null){
            request.setAttribute("message", "Niepoprawne dane logowania");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        else {
            session.setAttribute("user", user);

            if (user.getRole().equals("user")){
                request.getRequestDispatcher("/displayAll.jsp").forward(request, response);
            }
            else if (user.getRole().equals("admin"))  {
                request.getRequestDispatcher("/adminPanel.jsp").forward(request, response);
            }
            else {
                request.setAttribute("message", "coś poszło nie tak, skontaktuj się z nami pod nr (22) 777 777 777");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }


        }

    }


}
