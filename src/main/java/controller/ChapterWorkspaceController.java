package controller;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


/**
 * Created by mk on 3/31/16.
 */

@Controller
public class ChapterWorkspaceController {

    protected DbContext db = new DbContext();

    @RequestMapping(value="chapter-workspace", method = RequestMethod.GET)
    public String chapterWorkspacePage(ModelMap model) {
        System.out.println("Going to chapter-workspace ");
        return "chapter-workspace";
    }

    @RequestMapping(value="workspace/loadChapter/{chapterID}", method = RequestMethod.GET)
    public String loadThisChapter(@PathVariable(value="chapterID") String chapterID, ModelMap model) {
        model.addAttribute("chapterID", chapterID);
        return "chapter-workspace";
    }

    @RequestMapping(value="workspace/load/{chapterID}", method = RequestMethod.GET)
    public @ResponseBody JsonObject loadChapter(@PathVariable(value="chapterID") String chapterID) {
        JsonObject json = new JsonObject();
        System.out.println("Looking for: " + chapterID);

        // check if user is logged in
        UserService userService = UserServiceFactory.getUserService();
        if(userService.isUserLoggedIn() == false) {
            System.out.println("Error: User not logged in");
            return json;
        }

        // load user from the datastore
        UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());
        if(user == null) {
            System.out.println("Error: Unable to load user from datastore");
            return json;
        }

        // load the chapter
        Chapter chapterToLoad = db.chapterRepo.getById(chapterID);
        if(chapterToLoad == null) {
            System.out.println("Error: Unable to load chapter from datastore");
            return json;
        }

        // check if user owns chapter being requested
//        if(chapterToLoad.getAuthor().getEmail() != user.getEmail()) {
//            System.out.println("Error: User does not have access to chapter");
//            return json;
//        }

        // TODO check if the chapter being requested is editable (still in draft mode)
//        if(chapterToLoad.isPublished() == false) {
//            return "Error: Chapter is no longer editable";
//        }

        // load chapter as a String/JSon
        Gson gson = new GsonBuilder().create();
        json.add("Chapter", gson.toJsonTree(chapterToLoad));

        Page rootPage = chapterToLoad.getRoot();
        if(rootPage == null) {
            System.out.println("Unable to load root");
            return json;
        }

        ArrayList<Page> allPages = new ArrayList<Page>();
        rootPage.getAllPages(allPages);
        if (allPages == null) {
            System.out.println("unable to load pages");
            return json;
        }

        json.add("Pages", gson.toJsonTree(allPages));
        System.out.println("Chapter, Pages: " + json.toString());

        return json;

    }

    // load the pages for a chapter
    @RequestMapping(value="workspace/load/pages/{chapterID}", method = RequestMethod.GET)
    public @ResponseBody JsonObject loadChapterPages(@PathVariable(value="chapterID") String chapterID) {
        JsonObject json = new JsonObject();

        System.out.println("Loading pages for: " + chapterID);
        // check if user is logged in
        UserService userService = UserServiceFactory.getUserService();
        if(userService.isUserLoggedIn() == false) {
            return json;//"Error: User not logged in";
        }

        // load user from the datastore
        UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());
        if(user == null) {
            return json;//"Error: Unable to load user from datastore";
        }

        // load the chapter
        Chapter chapterToLoad = db.chapterRepo.getById(chapterID);
        if(chapterToLoad == null) {
            return json;//"Error: Unable to load chapter from datastore";
        }

        // check if user owns chapter being requested
        if(chapterToLoad.getAuthor().getEmail() != user.getEmail()) {
            return json;//"Error: User does not have access to chapter";
        }

        Page rootPage = chapterToLoad.getRoot();
        if(rootPage == null) {
            return json; // Unable to load root
        }

        ArrayList<Page> allPages = new ArrayList<Page>();
        rootPage.getAllPages(allPages);
        if (allPages == null) {
            return json; // unable to load pages
        }

        // load up the pages into a json
        Gson gson = new GsonBuilder().create();
        json.add("Pages", gson.toJsonTree(allPages));
        return json;
    }

    /**
     *
     * @param request
     * @param response
     */
    @RequestMapping(value="get-chapter-page", method = RequestMethod.POST)
    protected void getChapterPage(HttpServletRequest request, HttpServletResponse response){

        String boxText = request.getParameter("data");
        System.out.println(boxText);

        // load the page
        Page pageToLoad = db.pageRepo.getById("My Best Friend Gleb~Day One: The Dream of Gleb^0");
        if(pageToLoad == null) {
            System.out.println("Error in getting the page to save a test json");
        }
        else
        {

        }

    }

    @RequestMapping(value="make-chapter-page/{chapterID}", method = RequestMethod.GET)
    protected @ResponseBody JsonObject makeChapterPage(@PathVariable(value="chapterID") String chapterID, HttpServletRequest request, HttpServletResponse response){
        // i need the chapter ID and the series, which i can get from the chapter
        // so really, just chapter id-- also level, if possible
        System.out.println("arrived at the setPage controller");
        int level = Integer.parseInt(request.getParameter("level"));
        Chapter chapter = db.chapterRepo.getById(chapterID);
        Series series = chapter.getSeries();
        Page newBaby = db.pageRepo.create(series,  chapter, level);
        System.out.println("created a page for the chapter" + chapterID+ " called" + newBaby.toString());

        // now return the series as a JSON object, using Gson
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();
        json.add("Page", gson.toJsonTree(newBaby));
        return json;

    }

}
