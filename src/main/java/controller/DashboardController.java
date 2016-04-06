package controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import data.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import data.*;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class DashboardController {
    @RequestMapping(value="dashboard", method = RequestMethod.GET)
    public String dashboardController(ModelMap model) {
        System.out.println("Going to dashboard ");
        // check if the user's logged in lol
        UserService service = UserServiceFactory.getUserService();
        User u = service.getCurrentUser();
        DbContext db = new DbContext();
        u.getNickname(); // email
        db.userRepo.createUser(u.getNickname());
        //db.userRepo.getUserById();
        UserData m = db.userRepo.getUserById(u.getNickname());
        m.setAboutMe("im a little teapot, short and stout. this is my handle. this is my spout.");
        db.userRepo.updateUser(m);

        return "dashboard";
    }
}
