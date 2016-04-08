package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class UploadTestController {
    @RequestMapping(value="upload_test", method = RequestMethod.GET)
    public String uploadTestController(ModelMap model) {
        System.out.println("Going to upload test page");
        return "upload_test";
    }
}
