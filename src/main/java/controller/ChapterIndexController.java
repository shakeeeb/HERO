package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class ChapterIndexController {
    @RequestMapping(value="chapter-index", method = RequestMethod.GET)
    public String chapterIndexPage(ModelMap model) {
        System.out.println("Going to chapter-index ");
        return "chapter-index";
    }
}
