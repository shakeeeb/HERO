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
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

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
        System.out.println("Chapter ID: " + chapterID);
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

        ArrayList<Page> allPages = chapterToLoad.getAllPages();
        if (allPages == null) {
            System.out.println("unable to load pages");
            return json;
        }

        json.add("Pages", gson.toJsonTree(allPages));
        System.out.println("Chapter, Pages: " + json.toString());

        return json;

    }



    @RequestMapping(value="workspace/update", method=RequestMethod.POST)
    public @ResponseBody String updateChapter(@RequestParam("chapterID") String chapterID, @RequestParam("summary") String summary) {
        System.out.println("\n\n\n\n\n\n\n\n\nASDASDASDASDAS");
        Chapter chapterToUpdate = db.chapterRepo.getById(chapterID);
        chapterToUpdate.setDescription(summary);
        db.chapterRepo.update(chapterToUpdate);

        return "Success";
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
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nNumber of orphans" + chapterToLoad.getOrphans().isEmpty());
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

        ArrayList<Page> allPages = chapterToLoad.getAllPages();
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
    protected void getChapterPage( HttpServletRequest request, HttpServletResponse response){
        //okay so here
        //get  a page from the db
        //if the page doesn't exist
        // create it
        System.out.println("grabbing page from the database");


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

    @RequestMapping(value="make-chapter-page", method = RequestMethod.GET)
    protected @ResponseBody JsonObject makeChapterPage( HttpServletRequest request, HttpServletResponse response){
        // i need the chapter ID and the series, which i can get from the chapter
        // so really, just chapter id-- also level, if possible
        // if the page exists, grab it
        // elsewise, create it
        //assume cID and pID are arguments in request
        System.out.println("arrived at the setPage controller");
        JsonObject json = new JsonObject();

        String chapterID = request.getParameter("chapterID");
        String pageID = request.getParameter("pageID");
        int level = Integer.parseInt(request.getParameter("level"));

        Chapter chapter = db.chapterRepo.getById(chapterID);

        if(chapter == null){
            System.out.println("chapter is null");
            return json;
        }

        Series series = chapter.getSeries();
        System.out.println(pageID);
        Page pp1 = db.pageRepo.getById(pageID);
        if(pp1 == null){
            //make a new page
            pp1 = db.pageRepo.create(series,  chapter, level);
            System.out.println("currently: "+ chapter.getAllPages());
            System.out.println("created a page for the chapter" + chapterID+ " called" + pp1.toString());
        } else {
            System.out.println("currently: "+ chapter.getAllPages());
            System.out.println("successfully retireieved a page from the database");
        }

        Gson gson = new GsonBuilder().create();
        json.add("Page", gson.toJsonTree(pp1));


        // now return the series as a JSON object, using Gson


        return json;

    }

    @RequestMapping(value="delete-chapter-page", method = RequestMethod.POST)
    protected @ResponseBody String deleteChapterPage( HttpServletRequest request, HttpServletResponse response){
        // i need the chapter ID and the Id of the page i want to delete
        // and then, i just delete it
        System.out.println("arrived at the delete page controller");
        String chapterID  = request.getParameter("chapterID");
        String pageID = request.getParameter("pageID");

        System.out.println("Chapter ID:"+ chapterID);
        System.out.println("Page ID:"+pageID);

        Chapter c = db.chapterRepo.getById(chapterID);
        if(c == null){
            System.out.println("the chapter does not exist");
            return "failure";
        }
        Page p = db.pageRepo.getById(pageID);
        if(p == null){
            System.out.println("the page does not exist");
            return "failure";
        }
        db.pageRepo.delete(c, p);
        db.chapterRepo.update(c);
        return "success";
    }

    @RequestMapping(value="delete-row", method = RequestMethod.POST)
    protected @ResponseBody String refactorChapterPage( HttpServletRequest request, HttpServletResponse response){
        int level = Integer.parseInt(request.getParameter("level"));
        String chapterID = request.getParameter("chapterID");
        String pageID = request.getParameter("pageID");
        Chapter chapter = db.chapterRepo.getById(chapterID);
        System.out.println("deleting a page, then deleting a row");

        System.out.println("deleting a row");
        if(chapter == null){
            System.out.println("chapter does not exist");
            return "failure";
        }
        Page pg = db.pageRepo.getById(pageID);

        if(pg == null){
            System.out.println("page does not exist");
            return "failure";
        }
        db.pageRepo.delete(chapter, pg);
        // take this chapter, change all the levels of the selected level to selected level-1
        // the page will be reloaded after this

        ArrayList<Page> pages = chapter.getAllPages();
        if(pages == null){
            System.out.println("could not retrieve pages");
            return "failure";
        }

        for(Page p : pages){
            if(p.getPageLevel() >= level){
                int cLevel = p.getPageLevel() -1;
                p.setPageLevel(cLevel);
            }
        }
        db.pageRepo.saveMulitple(pages);
        return "success";
        // refresh page
    }


    /**
     *
     * @param request
     * @return
     * note: uses two arrays one for options and one for option prompts because sending nested arrays in the request scope is weird
     */
    @RequestMapping(value="workspace/update/page-options", method = RequestMethod.POST)
    protected @ResponseBody String updatePageOptions(HttpServletRequest request) {

        // grab the page to edit and it its options from the requests parameters
        if(request.getParameter("page") == null) {
            return "error: page parameter is null";
        }

        String[] options = request.getParameterValues("options[]");
        String[] optionPrompts = request.getParameterValues("optionPrompts[]");
        if(options.length != optionPrompts.length) {
            return "error: the number of options don't match the number of paths";
        }

        if(options == null) {
            return "error: options parameter is null";
        }

        // grab the page to edit and its options from the datastore
       Page pageToEdit = db.pageRepo.getById(request.getParameter("page"));
        if(pageToEdit == null) {
            return "error: page does not exist in datastore";
        }

        // clear previous options
        pageToEdit.setOptions(new ArrayList<Page>()); // TODO: check if this is okay

        // add each option to the page
        for(int i = 0; i < options.length; i++) {

            if(options[i] == null) {
                return "error: null option";
            }

            Page option = db.pageRepo.getById(options[i]);
            if(option == null) {
                return "error: option doesn't exist inside datastore";
            }

            pageToEdit.setNext(option, optionPrompts[i], option.getChapter());
            db.pageRepo.save(pageToEdit);
        }

        return "success";
    }




    /**
     *
     * @param request
     * @return
     * note: uses two arrays one for options and one for option prompts because sending nested arrays in the request scope is weird
     */
    @RequestMapping(value="workspace/add/page-option", method = RequestMethod.POST)
    protected @ResponseBody String addPageOption(HttpServletRequest request) {

        // grab pages being linked
        if(request.getParameter("fromPage") == null) {
            return "error: from page is null";
        }

        if(request.getParameter("toPage") == null) {
            return "error: to page is null";
        }

        // grab the page to edit and its options from the datastore
        Page fromPage = db.pageRepo.getById(request.getParameter("fromPage"));
        if(fromPage == null) {
            return "error: from page does not exist in datastore";
        }

        Page toPage = db.pageRepo.getById(request.getParameter("toPage"));
        if(toPage == null) {
            return "error: to page does not exist in the datastore";
        }

        if(fromPage.getPageId().contentEquals(toPage.getPageId())) {
            return "error: attempting to link a page to itself";
        }
        // check if to page is already an option
        for(int i = 0; i < fromPage.getOptions().size(); i++) {
            if(toPage == fromPage.getOptions().get(i)) {
                return "page already an option";
            }
        }

        // add the option
        fromPage.setNext(toPage,"",fromPage.getChapter());
        db.pageRepo.save(fromPage);

        return "success";
    }

}
