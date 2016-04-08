package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ben on 3/31/16.
 */

@Controller
public class testLastReadController {
    @RequestMapping(value="test-last-read", method = RequestMethod.GET)
    public String testLastReadController(ModelMap model) {
        System.out.println("Going to test-Last-Read");
        return "test-last-read";
    }
}