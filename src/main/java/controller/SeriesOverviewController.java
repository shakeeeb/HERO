package controller;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import data.DbContext;
import data.model.Chapter;
import data.model.Series;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class SeriesOverviewController {

    protected DbContext db = new DbContext();

    @RequestMapping(value="series-overview", method = RequestMethod.GET)
    public String seriesOverviewController(ModelMap model) {
        System.out.println("Going to series-overview ");
        return "series-overview";
    }

    @RequestMapping(value="series-overview/{seriesID}", method = RequestMethod.GET)
    public String seriesFromSearch(@PathVariable(value="seriesID")String seriesID, ModelMap model) {
        System.out.println("Going to series-overview ");
        Series s = db.seriesRepo.getById(seriesID);

        String seriesName = s.getName();
        String seriesAuthor = s.getAuthorName();

        model.addAttribute("seriesName", seriesName);
        model.addAttribute("seriesAuthor", seriesAuthor);
        return "series-overview";
    }

    @RequestMapping(value = "admin/get/{series-ID}/allChapters", method = RequestMethod.GET)
    public @ResponseBody JsonObject getPage(@PathVariable(value = "series-ID") String seriesID, ModelMap model, HttpSession session, HttpServletRequest req) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();

        Series s = db.seriesRepo.getById(seriesID);
        ArrayList<Chapter> allChapters = s.getChapters();

        json.add("allChapters", gson.toJsonTree(allChapters));



        return json;
    }

}
