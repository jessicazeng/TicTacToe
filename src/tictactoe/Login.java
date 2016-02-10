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
        try {
            if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                // Load the class that provides the new "jdbc:google:mysql://" prefix.
                driver = "com.mysql.jdbc.GoogleDriver";
                url = "jdbc:google:mysql://jeszeng-tic-tac-toe:tictactoe/tictactoe";
            } else {
                // Local MySQL instance to use during development.
                driver = "com.mysql.jdbc.Driver";
                url = "jdbc:mysql://173.194.239.47:3306/tictactoe";
            }

//            context.setAttribute("dbdriver", driver);
//            context.setAttribute("dburl", url);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if(user == null)
            response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
        else{
            context.setAttribute("Nickname", user.getNickname());
            response.sendRedirect("leaderboard.jsp");
        }

//        String nextJSP = "leaderboard.jsp";
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
//        dispatcher.forward(request,response);
    }
}
