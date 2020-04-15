import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {



    
  /*  protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            
            String dane=request.getParameter("daneZFormularza");
            
            String daneWynikowe=dane+"@@"+dane;
            request.setAttribute("daneZSerwletu",daneWynikowe);

            request.getRequestDispatcher("/wynik.jsp").forward(request,response);
        
    }

    */

}
