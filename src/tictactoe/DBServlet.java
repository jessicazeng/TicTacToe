package tictactoe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.google.appengine.api.utils.SystemProperty;

/**
 * Created by sicaz on 2/10/2016.
 */
public class DBServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String url = null;
//        String driver = null;
//        try {
//            if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
//                // Load the class that provides the new "jdbc:google:mysql://" prefix.
//                driver = "com.mysql.jdbc.GoogleDriver";
//                url = "jdbc:google:mysql://jeszeng-tic-tac-toe:tictactoe/tictactoe";
//            } else {
//                // Local MySQL instance to use during development.
//                driver = "com.mysql.jdbc.Driver";
//                url = "jdbc:mysql://173.194.239.47:3306/tictactoe";
//            }
//
//            ServletContext context = request.getSession().getServletContext();
//            context.setAttribute("dbdriver", driver);
//            context.setAttribute("dburl", url);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }

//        response.setHeader("Refresh", "3; url=/leaderboard.jsp");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/leaderboard.jsp");
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
