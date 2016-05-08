package controller;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.googlecode.objectify.cmd.Query;
import data.DbContext;
import data.model.Chapter;
import data.model.Series;
import data.model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping(value="series-workspace/lookup/", method = RequestMethod.GET)
    public String loadSeriesName(String authorEmail, ModelMap model) {
        System.out.println("Going to series-workspace ");
//        model.addAttribute("authorEmail", authorEmail);
        return "series-workspace";
    }

    @RequestMapping(value="series-workspace/lookup/get/", method = RequestMethod.GET)
    public @ResponseBody JsonObject getSeries(String authorEmail, ModelMap model) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();

        UserService userService = UserServiceFactory.getUserService();
        UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());
        String userEmail = user.getEmail();
        List<Series> allSeries = db.seriesRepo.listSeriesByAuthor(userEmail);

        //Query<Series> authRefinedQuery = db.seriesRepo.refineQueryByAuthorName(allSeriesQuery, /*author email*/);

//        allSeriesQuery = refineSearch(allSeriesQuery, null, null, authorEmail, 0, 0);
//        System.out.println("Query Series: " + allSeriesQuery);
//
//        List<Series> allSeriesList = allSeriesQuery.list();
//        System.out.println("List Series: " + allSeriesList);
//
//        ArrayList<Series> allSeries = new ArrayList<Series>(allSeriesList.size());
//        allSeries.addAll(allSeriesList);
//
        System.out.println("allSeries: " + allSeries);

        json.add("allSeries", gson.toJsonTree(allSeries));

        return json;
    }

    @RequestMapping(value="series-workspace/createSeries/{seriesTitle}/{genre}/{seriesDescription}", method = RequestMethod.GET)
    public @ResponseBody JsonObject createSeries(@PathVariable(value = "seriesTitle") String seriesTitle, @PathVariable(value = "genre") String genre, @PathVariable(value = "seriesDescription") String seriesDescription, ModelMap model) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();

        UserService userService = UserServiceFactory.getUserService();
        UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());

        System.out.println("About to Create a series" + seriesTitle + genre + seriesDescription);

        Series s = db.seriesRepo.create(seriesTitle, genre, user, seriesDescription);

        db.seriesRepo.update(s);

        return json;
    }

    @RequestMapping(value="series-workspace/deleteSeries/{seriesID}", method = RequestMethod.GET)
    public @ResponseBody JsonObject deleteSeries(@PathVariable(value = "seriesID") String seriesID, ModelMap model) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();

        UserService userService = UserServiceFactory.getUserService();
        UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());

        Series s = db.seriesRepo.getById(seriesID);
        db.seriesRepo.delete(s, user);

        db.userRepo.update(user);



        return json;
    }

    //get the series from the previous page
//    @RequestMapping(value="series-workspace/{series_to_load}/{userEmail}", method = RequestMethod.GET)
//    public @ResponseBody JsonObject getSeries(@PathVariable(value="series_to_load") String series_to_load, @PathVariable(value="userEmail") String userEmail, ModelMap model) {
//        System.out.println("Hey, how's it going?");
//
//        JsonObject json = new JsonObject();
//        Gson gson = new GsonBuilder().create();
//
//        System.out.println(series_to_load);
//
//        // load user from the datastore
//        UserData user = db.userRepo.getUserById(userEmail);
//        System.out.println(user);
//        Series specific_series = user.getSpecificSeries(series_to_load);
//
//        json.add("series", gson.toJsonTree(specific_series));
//
//        return json;
//    }
}
