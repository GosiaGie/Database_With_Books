import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.bookstore.database.Database;
import pl.bookstore.database.DatabaseDAO;
import pl.bookstore.database.ServicesDatabase;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class LoginTest {
//TO DO
/*
    Login servlet;

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ServicesDatabase databaseDAO;
    @Mock
    private HttpSession session;


    @Before
    public void setUp() {
      //  MockitoAnnotations.initMocks(this);

        MockitoAnnotations.initMocks(this);


    //    when(session.getAttribute("shoppingCart")).thenReturn(shoppingCart);

    }



    @Test
    public void LoginServletTest() throws Exception {//logowanie admina
        servlet = new Login();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        databaseDAO = mock(ServicesDatabase.class);
        session = mock(HttpSession.class);
        System.out.println("before");

        Login tc = spy(new Login());
        DatabaseDAO database = mock(ServicesDatabase.class);
        when(tc.nethenReturn(database);
        Gosia g = mock(Gosia.class);
        when(new Gosia()).thenReturn(g);
        when(g.abc()).thenReturn("gosiaaaaaaaaaaaaaaaaa");
     //   request.addParameter("username", "scott");
     //   request.addParameter("password", "tiger");
       // request.setAttribute("mail", "mail");
       // request.setAttribute("pass", "pass");

        when(request.getParameter("mail")).thenReturn("me");
        when(request.getParameter("pass")).thenReturn("secret");
        when(request.getSession()).thenReturn(session);
       // String mail = request.getParameter("mail");

       // String pass = request.getParameter("pass");

      //  when(databaseDAO.checkLogin(request.getParameter("mail"), request.getParameter("pass"))).thenReturn(true);
          when(databaseDAO.checkLogin("me", "secret")).thenReturn(true);
          when(databaseDAO.checkAdminRole(request.getParameter("mail"))).thenReturn(true);


       // request.addParameter("username", "scott");
       // request.addParameter("password", "tiger");

        servlet.doPost(request, response);
        //doPost(request, response);

      //  new Login().doPost(request, response);

      //  verify(databaseDAO).checkLogin(mail, pass);


        assertEquals("adminPanel.jsp", response.getContentType());

    }
*/
}
