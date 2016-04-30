package controller;

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


    @RequestMapping(value="series-workspace/lookup/{authorEmail}", method = RequestMethod.GET)
    public String loadSeriesName(@PathVariable(value="authorEmail") String authorEmail, ModelMap model) {
        System.out.println("Going to series-workspace ");
        model.addAttribute("authorEmail", authorEmail);
        return "series-workspace";
    }

    @RequestMapping(value="series-workspace/lookup/get/{authorEmail}", method = RequestMethod.GET)
    public @ResponseBody JsonObject getSeries(@PathVariable(value="authorEmail") String authorEmail, ModelMap model) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();

        Query<Series> allSeriesQuery = db.seriesRepo.getAllSeriesAsAQuery();

        allSeriesQuery = refineSearch(allSeriesQuery, null, null, authorEmail, 0, 0);
        System.out.println("Query Series: " + allSeriesQuery);

        List<Series> allSeriesList = allSeriesQuery.list();
        System.out.println("List Series: " + allSeriesList);

        ArrayList<Series> allSeries = new ArrayList<Series>(allSeriesList.size());
        allSeries.addAll(allSeriesList);

        System.out.println("allSeries: " + allSeries);


        json.add("allSeries", gson.toJsonTree(allSeries));

        return json;
    }

    public Query<Series> refineSearch(Query<Series> q, String genre, String tag, String author, int rating, int date){
        System.out.println("refining shit");
        System.out.println(q.list().toString());
        if(genre != null){
            if(!genre.equals("all")){
                q = db.seriesRepo.refineQueryByMainGenre(q, genre);
            }
        }
        System.out.println(q.list().toString());
        if(tag != null){
            q = db.seriesRepo.refineQueryByTag(q, tag);
        }
        System.out.println(q.list().toString());
        if(author != null){
            if(!author.equals("")){
                q = db.seriesRepo.refineQueryByAuthorName(q, author);
            }
        }
        System.out.println(q.list().toString());
        if(date != 0){
            if(date == 1){
                // most
                q = db.seriesRepo.refineQueryByLatestUpdate(q);
            } else {
                // least
                q = db.seriesRepo.refineQueryByLeastUpdate(q);
            }
        }
        System.out.println(q.list().toString());
        if(rating != 0){
            q = db.seriesRepo.refineByStarCount(q, rating);
        }
        return q;
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
