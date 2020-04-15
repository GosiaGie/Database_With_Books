import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/post")
public class Post extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String imie = req.getParameter("imie");
        String nazwisko = req.getParameter("nazwisko");

        PrintWriter pw = resp.getWriter();

        pw.println(imie);
        pw.println(nazwisko);


    }
}
