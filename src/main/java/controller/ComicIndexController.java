package controller;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import data.DbContext;
import data.model.Series;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;


@Controller
public class ComicIndexController {

    protected DbContext db = new DbContext();

    @RequestMapping(value="comic-index", method = RequestMethod.GET)
    public String comicIndexController(ModelMap model) {
        System.out.println("Going to comic-index ");
        return "comic-index";
    }

    @RequestMapping(value="comic-index/get/{genre}", method = RequestMethod.GET)
    public @ResponseBody JsonObject getComicsByGenre(@PathVariable(value="genre") String genre) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();
        ArrayList<Series> genreBySeries = null;

        if (genre.equals("all")) {
            genreBySeries = new ArrayList<Series>(db.seriesRepo.getAllSeries());
        }
        else {
            genreBySeries = new ArrayList<Series>(db.seriesRepo.listSeriesByGenre(genre));
        }

        json.add("comics", gson.toJsonTree(genreBySeries));
        return json;
    }
}
