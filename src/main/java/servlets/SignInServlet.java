package servlets;

import dbService.DBException;
import dbService.accounts.AccountService;
import dbService.dataSet.UsersDataSet;
import sun.plugin.util.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Bugarchev A.A. on 2016.
 */
public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        UsersDataSet profile = null;
        try {
            profile = accountService.getUserByLogin(login);
        } catch (DBException e) {
            e.printStackTrace();
        }
        if (profile == null || !profile.getPassword().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }




        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Authorized: "+login);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
