import pl.bookstore.database.DatabaseDAO;
import pl.bookstore.database.ServicesDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DatabaseDAO databaseDAO = new ServicesDatabase();

        String mail = request.getParameter("mail");
        String pass = request.getParameter("pass");
        int id = 0;
        boolean logIn = false;
        boolean borrowed_book = false;

        try {
            logIn = databaseDAO.checkLogin(mail, pass); //sprawdz login i haslo
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (logIn) {
              try {
                  if (databaseDAO.checkAdminRole(mail)){
                      request.getRequestDispatcher("/adminPanel.jsp").forward(request, response);
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }


            try {
                id =  databaseDAO.findUserByMail(mail);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
              borrowed_book = databaseDAO.checkUsersBooks(id);
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (borrowed_book){

                request.setAttribute("result_of_checking_account", "Nie zwrociles poprzedniej ksiazki. ");
                request.getRequestDispatcher("/index.jsp").forward(request,response);

            }

            else {
                request.setAttribute("mail", mail);
                request.getRequestDispatcher("/displayAll.jsp").forward(request, response);
            }

          }


        else if (!logIn) {
              request.setAttribute("message", "Niepoprawne dane logowania");
              request.getRequestDispatcher("/index.jsp").forward(request, response);

        }



    }
}
