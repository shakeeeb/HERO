package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Terrell Mack on 3/30/16.
 */
@Controller
public class HomeController {

    @RequestMapping(value={"/", "home"}, method = RequestMethod.GET)
    public String home(ModelMap model) {
        System.out.println("Redirecting to homepage. ");
        return "home";
    }
}
