package controller;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import data.DbContext;
import data.model.Series;
import data.model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class SeriesWorkspaceController {

    protected DbContext db = new DbContext();

    @RequestMapping(value="series-workspace", method = RequestMethod.GET)
    public String seriesWorkspaceController(ModelMap model) {
        System.out.println("Going to series-workspace ");
        return "series-workspace";
    }


    @RequestMapping(value="user/lookup/{userEmail}", method = RequestMethod.GET)
    public String loadSeriesName(@PathVariable(value="userEmail") String userEmail, ModelMap model) {
        System.out.println("Going to user-page");
        return "user";
    }

    //get the series from the previous page
    @RequestMapping(value="series-workspace/{series_to_load}/{userEmail}", method = RequestMethod.GET)
    public @ResponseBody JsonObject getSeries(@PathVariable(value="series_to_load") String series_to_load, @PathVariable(value="userEmail") String userEmail, ModelMap model) {
        System.out.println("Hey, how's it going?");

        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();

        System.out.println(series_to_load);

        // load user from the datastore
        UserData user = db.userRepo.getUserById(userEmail);
        System.out.println(user);
        Series specific_series = user.getSpecificSeries(series_to_load);

        json.add("series", gson.toJsonTree(specific_series));

        return json;
    }
}
