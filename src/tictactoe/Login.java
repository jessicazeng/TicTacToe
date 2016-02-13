package tictactoe;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.utils.SystemProperty;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Jessica on 2/4/2016.
 */
//@javax.servlet.annotation.WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getSession().getServletContext();
        String url = null;
        String driver = null;
        String password = null;
        try {
            if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                // Load the class that provides the new "jdbc:google:mysql://" prefix.
                driver = "com.mysql.jdbc.GoogleDriver";
                url = "jdbc:google:mysql://jeszeng-tic-tac-toe:tictactoe/tictactoe";
                password = "";
            } else {
                // Local MySQL instance to use during development.
                driver = "com.mysql.jdbc.Driver";
                url = "jdbc:mysql://173.194.239.47:3306/tictactoe";
                password = "password";
            }

            context.setAttribute("dbdriver", driver);
            context.setAttribute("dburl", url);
            context.setAttribute("dbpw", password);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if(user == null) {
            Boolean login = Boolean.valueOf(request.getParameter("login"));
            if(login == true)
                response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
            else
                response.sendRedirect("index.jsp");
        } else{
            try {
                Connection conn = DriverManager.getConnection(url, "root", password);

                try {
                    ResultSet rs = null;
                    String statement = "SELECT * FROM Players WHERE Nickname = '" + user.getNickname() + "'";
                    PreparedStatement stmt = conn.prepareStatement(statement);
                    rs = stmt.executeQuery();

                    if(!rs.next()){
                        int result = 0;
                        statement = "INSERT INTO Players VALUES ('" + user.getNickname() + "', 0)";
                        stmt = conn.prepareStatement(statement);
                        result = stmt.executeUpdate();
                    }
                } finally {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            context.setAttribute("Nickname", user.getNickname());
            response.sendRedirect("leaderboard.jsp");
        }
    }
}
