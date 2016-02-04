package tictactoe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jessica on 2/4/2016.
 */
//@javax.servlet.annotation.WebServlet(name = "Play")
public class Play extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextJSP = "game.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }
}
