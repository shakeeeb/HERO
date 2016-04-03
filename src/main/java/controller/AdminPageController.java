package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class AdminPageController {
    @RequestMapping(value="admin-page", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        System.out.println("Going to admin-page ");
        return "admin-page";
    }
}
