package controller;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Terrell Mack on 3/30/16.
 */
@Controller
public class LoginLogoutController {

    @RequestMapping(value="login", method = RequestMethod.GET)
    protected void login(HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();
        response.sendRedirect(userService.createLoginURL("dashboard"));
    }

    @RequestMapping(value="logout", method = RequestMethod.GET)
    protected void logout(HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();

        if(userService.getCurrentUser() != null) {
            response.sendRedirect(userService.createLogoutURL("home"));
        } else {
            response.sendRedirect("home");
        }

    }
}

