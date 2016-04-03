package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class EditSettingsController {
    @RequestMapping(value="edit-settings", method = RequestMethod.GET)
    public String editSettingsController(ModelMap model) {
        System.out.println("Going to edit-settings ");
        return "edit-settings";
    }
}
