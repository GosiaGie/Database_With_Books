import javax.jws.WebResult;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "add", urlPatterns = "/add")
public class Add extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

            int c = Integer.parseInt(request.getParameter("n1"));
            int d = Integer.parseInt(request.getParameter("n2"));

            int w = c + d;

            PrintWriter pw = response.getWriter();
            pw.println(w);


            RequestDispatcher requestDispatcher = request.getRequestDispatcher("square");
            requestDispatcher.forward(request, response);







    }

   /* public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int c = Integer.parseInt(request.getParameter("n1"));
        int d = Integer.parseInt(request.getParameter("n2"));

        int w = c + d;

        PrintWriter pw = response.getWriter();
        pw.println(w);


    }
*/


}
