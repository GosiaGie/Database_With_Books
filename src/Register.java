import pl.bookstore.database.DatabaseDAO;
import pl.bookstore.database.ServicesDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")

public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String mail = request.getParameter("mail");
        String pass = request.getParameter("pass");
        String pass_2 = request.getParameter("pass_2");
        String message = null;

        DatabaseDAO databaseDAO = new ServicesDatabase();

        boolean duplicated_mail = false;

        try {
            duplicated_mail = databaseDAO.findMail(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (duplicated_mail){
            message = "jest konto z takim mail";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/register.jsp").forward(request, response);

        }

        else if(pass.equals(pass_2)==false) {
            message = "hasla nie pasuja do siebie";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }


        else if (mail.contains("@")==false)  {
            message = "Twoj adres email nie wyglada poprawnie";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/register.jsp").forward(request, response);

        }

        else if (pass.length()<8) {
            message= "Twoje haslo jest za krotkie";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }



        else {

            try {
                databaseDAO.register(mail,pass);
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/displayAll.jsp").forward(request, response);
        }



    }
}
