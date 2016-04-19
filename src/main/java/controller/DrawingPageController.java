package controller;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import data.DbContext;
import data.model.Chapter;
import data.model.Page;
import data.model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;

import static com.googlecode.objectify.ObjectifyService.ofy;


/**
 * Created by mk on 3/31/16.
 */

@Controller
public class DrawingPageController {

    protected DbContext db = new DbContext();

    @RequestMapping(value="draw", method = RequestMethod.GET)
    public String drawingPageController(ModelMap model) {
        System.out.println("Going to drawing-page ");
        return "draw";
    }

    @RequestMapping(value="get-json", method = RequestMethod.POST)
    protected void getJson(HttpServletRequest request, HttpServletResponse response){

            String buttonText = request.getParameter("data");
            System.out.println(buttonText);

    }

    @RequestMapping(value="load-page/{pageID}", method = RequestMethod.GET)
    public @ResponseBody JsonObject loadPage(@PathVariable(value="pageID") String pageID) {
        System.out.println("Loading Image");

        JsonObject pageJson = new JsonObject();

        // check if user is logged in
        UserService userService = UserServiceFactory.getUserService();
        if(userService.isUserLoggedIn() == false) {
            return pageJson;//"Error: User not logged in";
        }

        // load user from the datastore
        UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());
        if(user == null) {
            return pageJson;//"Error: Unable to load user from datastore";
        }

        // load the page
        Page pageToLoad = db.pageRepo.getById(pageID);
        if(pageToLoad == null) {
            return pageJson;//"Error: Unable to load chapter from datastore";
        }


        // load chapter as a String/JSon
        Gson gson = new GsonBuilder().create();
        pageJson.add("gottenJsonImage", gson.toJsonTree(pageToLoad));
        System.out.println("before return");
        System.out.println(pageJson.toString());
        System.out.println("hello");
        return pageJson;

    }


}