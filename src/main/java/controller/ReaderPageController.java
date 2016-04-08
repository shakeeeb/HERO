package controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class ReaderPageController {
    @RequestMapping(value="read", method = RequestMethod.GET)
    public String readerPageController(ModelMap model) {
        System.out.println("Going to reader-page ");
        return "read";
    }
}
// QUERY FOR THE PAGE
// Load a model with the page info.
// Go to view.
// Use the information that was used for the page, which should be the page URL.
