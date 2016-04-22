package controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import data.DbContext;
import data.model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class EditSettingsController {

    protected DbContext db = new DbContext();
    @RequestMapping(value="settings", method = RequestMethod.GET)
    public String editSettingsController(ModelMap model) {
        System.out.println("Going to edit-settings ");
        return "settings";
    }

    @RequestMapping(value="settings/user", method = RequestMethod.GET)
    public String templateUserSettingsPage(ModelMap model, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
        UserService userService = UserServiceFactory.getUserService();
        String userEmail = userService.getCurrentUser().getEmail();
        System.out.println("The user email is: " + userEmail);
        model.addAttribute("userEmail", userEmail);
        return "settings";
    }

    @RequestMapping(value="settingsUser/get/{userQuery}", method = RequestMethod.GET)
    public @ResponseBody
    JsonObject getUser(@PathVariable(value="userQuery") String userQuery, ModelMap model, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();
        System.out.println("Got here");
        // check if user is logged in
        UserService userService = UserServiceFactory.getUserService();
        System.out.println(UserServiceFactory.getUserService());
        // load user from the datastore
        UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());
        System.out.println(user.getEmail());
        System.out.println(user.getNickname());
        System.out.println(user.getAdmin());

        String nickname = user.getNickname();
        String aboutMe = user.getAboutMe();
        String userEmail = user.getEmail();
        System.out.println("userEmail: " + userEmail);
        json.add("nickname", gson.toJsonTree(nickname));
        json.add("aboutMe", gson.toJsonTree(aboutMe));

        System.out.println(json);

        model.addAttribute("userEmail", userEmail);
        //Load this users settings

        return json;
    }

    boolean userLoggedIn;
    User currentPerson;

    @RequestMapping(value="settingsUserSave/{nickname}", method = RequestMethod.GET)
    public @ResponseBody
    JsonObject saveUserInformation(@PathVariable(value="nickname") String nickname , ModelMap model, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
        System.out.println("I'm in the saveUserInformation function.");
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();
        System.out.println("We passed in this nickname: "+ nickname);
        UserService userService = UserServiceFactory.getUserService();
        userLoggedIn = userService.isUserLoggedIn();

        if(userLoggedIn){
            currentPerson = userService.getCurrentUser();
            UserData user = db.userRepo.getUserById(currentPerson.getEmail());
            user.setNickname(nickname);
            System.out.println("This is what the nickname was set to: "+ user.getNickname());
            //TODO: SAVE THE USER INTO THE DATASTORE, BRUH.
        }

        return json;
    }


}
