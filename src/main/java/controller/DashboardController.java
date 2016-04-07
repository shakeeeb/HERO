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
        return "dashboard";
    }
}
