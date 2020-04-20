import javax.servlet.http.HttpServlet;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    public static ArrayList<String> search(String name) throws ClassNotFoundException, SQLException, FileNotFoundException {


        ArrayList <String> wyniki = new ArrayList<String>();

        Boolean found = false;

        String url = "jdbc:mysql://localhost:3306/sakila?useSSL=false";
        String username = "root";
        String password = "kochamJava";

        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();


        name = "'" + name + "'";
        ResultSet resultSet = statement.executeQuery("select * from books where last_name=" + name);

        connection = null;
        if(connection==null)
        {
            wyniki.add("nie udalo sie polaczyc");
            return wyniki;
        }



        while (resultSet.next()){

          String result = resultSet.getString("title");
          wyniki.add(result);

        }


return wyniki;





}


}
