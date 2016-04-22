package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class SeriesOverviewController {
    @RequestMapping(value="series-overview", method = RequestMethod.GET)
    public String seriesOverviewController(ModelMap model) {
        System.out.println("Going to series-overview ");
        return "series-overview";
    }

    @RequestMapping(value="series-overview/{seriesID}", method = RequestMethod.GET)
    public String seriesFromSearch(@PathVariable(value="seriesID")String seriesID, ModelMap model) {
        System.out.println("Going to series-overview ");
        return "series-overview";
    }

}
