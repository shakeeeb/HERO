package controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.appengine.repackaged.com.google.gson.JsonArray;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.google.common.reflect.TypeToken;
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
import java.lang.reflect.Type;
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

    @RequestMapping(value = "/read/{chapter-ID}/{page-ID}", method = RequestMethod.GET)
    public @ResponseBody JsonObject getPage(@PathVariable(value = "chapter-ID") String chapterID, @PathVariable(value = "page-ID") String pageID, ModelMap model, HttpSession session, HttpServletRequest req) {

        Chapter c = db.chapterRepo.getById(chapterID);
//        Page currentPage = c.getRoot();
        Page currentPage = db.pageRepo.getById(pageID);
        ArrayList<Page> pageList = new ArrayList<Page>();
        ArrayList<Page> currentPageOptions = currentPage.getOptions();
        pageList = c.getAllPages();
//
//        currentPage.getAllPages(pageList);


//        System.out.println("The Page list contains: " + pageList);

//        String pageArray[]=pageList.toArray(new String[pageList.size()]);

//        JSONArray jsonAraay = new JSONArray(your_array_list);

        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();
        json.add("Chapter", gson.toJsonTree(c));
//      json.add("pageArray", gson.toJsonTree(pageArray));
        json.add("page", gson.toJsonTree(currentPage));
        json.add("pageOptions", gson.toJsonTree(currentPageOptions));
        json.add("pageList", gson.toJsonTree(pageList));

        System.out.println("fetching chapter...");
        System.out.println("The Chapter ID from the URL is: " + chapterID);
//        System.out.println("grabbed chapter ID: " + c.getChapterId());
        System.out.println("Image URL: " + currentPage.getImagePath());
        System.out.println("The number of options this page has is: " + currentPage.getNumOptions());
//        System.out.println("The chapter name is: " + c.getName());
        System.out.println("Chapter & pageList: " + json.toString());
        System.out.println("Returned the json string");


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