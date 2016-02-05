package tictactoe;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

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
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if(user == null)
            response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
        else{
            ServletContext context = request.getSession().getServletContext();
            context.setAttribute("Nickname", user.getNickname());
            response.sendRedirect("leaderboard.jsp");
        }

//        String nextJSP = "leaderboard.jsp";
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
//        dispatcher.forward(request,response);
    }
}
