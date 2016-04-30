package controller;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import data.DbContext;
import data.model.Chapter;
import data.model.Series;
import data.model.UserData;
import data.repository.ChapterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "series-overview/get/{series-ID}/allChapters", method = RequestMethod.GET)
    public @ResponseBody JsonObject getPage(@PathVariable(value = "series-ID") String seriesID, ModelMap model, HttpSession session, HttpServletRequest req) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();

        Series s = db.seriesRepo.getById(seriesID);
        ArrayList<Chapter> allChapters = null;
        allChapters = s.getChapters();

        json.add("allChapters", gson.toJsonTree(allChapters));

        return json;
    }

    @RequestMapping(value = "series-overview/getChapter/{chapter-ID}", method = RequestMethod.GET)
    public @ResponseBody JsonObject getChapters(@PathVariable(value = "chapter-ID") String chapterID, ModelMap model, HttpSession session, HttpServletRequest req) {
        System.out.println("In series overview");
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();

        Chapter c = db.chapterRepo.getById(chapterID);
        Series s = c.getSeries();
        db.chapterRepo.delete(c,s);
        db.seriesRepo.update(s);


        return json;
    }

    @RequestMapping(value = "series-overview/createChapter/{series-ID}/{chapterName}/{chapterDescription}", method = RequestMethod.GET)
    public @ResponseBody JsonObject createNewChapter(@PathVariable(value = "series-ID") String seriesID, @PathVariable(value = "chapterName") String chapterName, @PathVariable(value = "chapterDescription") String chapterDescription, ModelMap model, HttpSession session, HttpServletRequest req) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();

        Series s = db.seriesRepo.getById(seriesID);
        ArrayList<Chapter> chapterList = s.getChapters();
        int nextChapterNumber = chapterList.size() + 1;
        UserData author = s.getAuthor();

        Chapter newChapter = db.chapterRepo.create(chapterName, author, s, nextChapterNumber);

        newChapter.setDescription(chapterDescription);

        System.out.println(newChapter.getDescription());



        return json;
    }



}
