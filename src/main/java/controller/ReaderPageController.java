package controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.appengine.repackaged.com.google.gson.JsonArray;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.googlecode.objectify.cmd.Query;
import data.DbContext;
import data.model.Chapter;
import data.model.Page;
import data.model.Series;
import data.model.UserData;
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
//@RequestMapping("read")
public class ReaderPageController {

    protected DbContext db = new DbContext();
//
//    @RequestMapping(value="search/refine/{query}", method = RequestMethod.GET)
//    public @ResponseBody String searchResultController(@PathVariable(value="query") String query, ModelMap model, HttpSession session, HttpServletRequest req) {
//        System.out.println("refining the search");

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String readController(String chapterID, ModelMap model, HttpSession session, HttpServletRequest req) {
        System.out.println("Going to reader-page");
//        // get the chapter ID
//
//        System.out.println("Chapter ID: " + chapterID);
//
//        // load the chapter using the chapter ID
//
//        // load the model with the chapter ID
//        model.addAttribute("chapterID", chapterID);

//        String currentComic = req.getParameter("read-form");//.toLowerCase(); // CHANGE SEARCH INPUT
//        System.out.println(currentComic);

        // grabbing the series we need to read from url
//        System.out.println("Series Requested: " + name);

        // grab the actual series from the DB
        return "read";
    }

    @RequestMapping(value = "/read/{chapter-ID}/{page-Number}", method = RequestMethod.GET)
    public @ResponseBody JsonObject getPage(@PathVariable(value = "chapter-ID") String chapterID, @PathVariable(value = "page-Number") int pageNumber, ModelMap model, HttpSession session, HttpServletRequest req) {
        JsonObject json = new JsonObject();
        System.out.println("fetching chapter...");
        System.out.println("The Chapter ID from the URL is: " + chapterID);
        Chapter c = db.chapterRepo.getById(chapterID);
        System.out.println("grabbed chapter ID: " + c.getChapterId());
        Page currentPage = c.getRoot();
        System.out.println("Image URL: " + currentPage.getImagePath());

        System.out.println("The number of optinos this page has is: " + currentPage.getNumOptions());
        int numOptions = currentPage.getNumOptions();

        String chapterTitle = c.getName();

        String seriesID = c.getSeries().getName();
        System.out.println("The chapter name is: " + c.getName());
        String chapterName = c.getName();


        model.addAttribute("imagePath", currentPage.getImagePath());
        model.addAttribute("chapterName", chapterName);
        model.addAttribute("numOptions", numOptions);
        model.addAttribute("chapterTitle", chapterTitle);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("seriesID", seriesID);

        Gson gson = new GsonBuilder().create();
        json.add("Chapter", gson.toJsonTree(c));

        System.out.println("Chapter: " + json.toString());
        System.out.println("Returned the json string");

//        System.out.println("The object chapter ID is: " +db.chapterRepo.getById(chapterID).toString());
//        System.out.println("The root Page is: " + db.chapterRepo.getById(chapterID).getRoot().toString());
//        System.out.println("The next Page is: " + db.chapterRepo.getById(chapterID).getRoot().getNext().toString());
//
//        System.out.println(db.seriesRepo.getById("One_Piece").getChapters().get(0).getRoot().getPageId());
//        System.out.println("The Chapter number is: " + currentChapter.getChapterNumber());
//        System.out.println(db.seriesRepo.getById("One_Piece").getChapters().get(0).toString());

//        System.out.println(currentChapter.getChapterId());
//        Series chap = null;
//        Query<Series> q = null;
//        if(chapterID == null){
//            // new search
//            System.out.println("Search was null");
//            //q = newSearch(searchInput);
//        } else {
//            // refine a prior search
//            // grab the query... if it's null, just grab everything
//            // okay so i can't store queries in the session object
//            // ughhhhhhhh
//            //q = (Query<Series>)session.getAttribute("priorQuery");
//            System.out.println("aahhh "+ db.seriesRepo.getById("One_Piece").getChapters().size());
//
//            //chap = db.chapterRepo.getById("the secret weddingCatbug");
//            //q = refineSearch(q, genre, tag, author, rating, date);
//
//        }
//        System.out.println("query returned: "+ chap.toString() );
//        List<> s = q.list();
//        for(Series s3:s){
//            System.out.println(s3.toString());
//        }
//        System.out.println("Test" + s.toString());
//        // return things as JSON
//        model.addAttribute("seriesList", s.toString());


//         transform the series into a JSON
//       gson.toJson(newChapter, System.out);

        // return the JSON string to wherever calls this controller
//        String jsonString = gson.toJson(currentChapter);
//        System.out.println(jsonString);
        //return jsonString; //the json String


        return json;
    }

//    @RequestMapping(value = "/read/{chapter-ID}/{page-Number}", method = RequestMethod.GET)
//    public String getPage(@PathVariable(value = "chapter-ID") String chapterID, @PathVariable(value = "page-Number") int pageNumber, ModelMap model, HttpSession session, HttpServletRequest req) {
//        System.out.println("fetching chapter...");
//        System.out.println("The Chapter ID from the URL is: " + chapterID);
//        Chapter c = db.chapterRepo.getById(chapterID);
//        System.out.println("grabbed chapter ID: " + c.getChapterId());
//        Page currentPage = c.getAllPages().get(pageNumber);
//        System.out.println("Image URL: " + currentPage.getImagePath());
//
//        System.out.println("The number of optinos this page has is: " + currentPage.getNumOptions());
//        int numOptions = currentPage.getNumOptions();
//
//        String chapterName = c.getName();
//        String seriesID = c.getSeries().getName();
//
//
//        model.addAttribute("imagePath", currentPage.getImagePath());
//        model.addAttribute("chapterID", chapterID);
//        model.addAttribute("numOptions", numOptions);
//        model.addAttribute("pageNumber", pageNumber);
//        model.addAttribute("seriesID", seriesID);
//        model.addAttribute("chapterName", chapterName);
//        return "read";
//    }
}