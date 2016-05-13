package controller;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import data.model.Series;
import data.model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import data.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Terrell Mack on 3/30/16.
 */
@Controller
public class HomeController {

    protected DbContext db = new DbContext();
    demoData dm = new demoData();

    @RequestMapping(value={"/", "home"}, method = RequestMethod.GET)
    public String home(ModelMap model) {
        System.out.println("Redirecting to homepage. ");
        dm.init(); // injecting demodata
        return "home";
    }
    @RequestMapping(value="home/getRecentlyUpdated", method = RequestMethod.GET)
    public @ResponseBody
    JsonObject getSubscriptionsRecentSuggestions(ModelMap model) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();

        System.out.println("Getting RecentlyUpdated");
        List<Series> seriesByUpdateTimeList= db.seriesRepo.listSeriesByUpdateTime();
        Collections.reverse(seriesByUpdateTimeList);
        ArrayList<Series> intermediateArrayList = new ArrayList<Series>(seriesByUpdateTimeList);
        List <Series> recentlyUpdatedList = intermediateArrayList.subList(0, 8);

        ArrayList<Series> recentlyUpdated = new ArrayList<Series>(recentlyUpdatedList);

        json.add("recentlyUpdated", gson.toJsonTree(recentlyUpdated));

        return json;
    }
}
