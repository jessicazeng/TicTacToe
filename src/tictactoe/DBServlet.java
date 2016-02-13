package tictactoe;

import javax.servlet.ServletException;
import java.io.*;
import java.sql.*;
import java.util.Enumeration;
import javax.servlet.http.*;
import com.google.appengine.api.utils.SystemProperty;

/**
 * Created by sicaz on 2/10/2016.
 */
public class DBServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        String password = null;
        try {
            if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                // Load the class that provides the new "jdbc:google:mysql://" prefix.
                Class.forName("com.mysql.jdbc.GoogleDriver");
                url = "jdbc:google:mysql://jeszeng-tic-tac-toe:tictactoe/tictactoe";
                password = "";
            } else {
                // Local MySQL instance to use during development.
                Class.forName("com.mysql.jdbc.Driver");
                url = "jdbc:mysql://173.194.239.47:3306/tictactoe";
                password = "password";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        int win = Integer.parseInt(request.getParameter("win"));
        System.out.println(win);

        try {
            Connection conn = DriverManager.getConnection(url, "root", password);

            int retValue = 0;
            try {
                if(win == 1){
                    String statement = "UPDATE Players SET Wins = Wins + 1 WHERE Nickname = '" + request.getSession().getServletContext().getAttribute("Nickname") + "'";
                    PreparedStatement stmt = conn.prepareStatement(statement);
                    retValue = stmt.executeUpdate();
                    System.out.println(retValue);
                }
            } finally {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
