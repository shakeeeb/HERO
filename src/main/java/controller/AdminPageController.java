package controller;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import data.DbContext;
import data.model.Chapter;
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
public class AdminPageController {

    protected DbContext db = new DbContext();

    @RequestMapping(value="admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        System.out.println("Going to admin-page ");
        return "admin";
    }

    @RequestMapping(value="admin/control", method = RequestMethod.GET)
    public String templatePage(ModelMap model) {
        boolean userLoggedIn;
        UserService userService = UserServiceFactory.getUserService();
        userLoggedIn = userService.isUserLoggedIn();
        UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());

        if(userLoggedIn == false)
        {
            System.out.println("User is not logged in");
        }

        String nickname = user.getEmail();
        System.out.println("The current user is: " + nickname);

        model.addAttribute("nickname", nickname);

        return "admin";
    }

    @RequestMapping(value = "admin/get/comicInformation", method = RequestMethod.GET)
    public @ResponseBody JsonObject getPageOptions(ModelMap model, HttpSession session, HttpServletRequest req) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();

        // Loop over these variables
        int submittedComicSize;
        int reportedComicSize;

        // Why is it returning a list -_-
        List<Chapter> allChaptersList = db.chapterRepo.getAll();
        ArrayList<Chapter> allChapters = new ArrayList<Chapter>(allChaptersList);

        ArrayList<Chapter> pendingApproval = new ArrayList<Chapter>();

        for (Chapter c : allChapters)
        {
            if (!c.isPublished())
            {
                pendingApproval.add(c);
            }
        }

        json.add("pendingApproval", gson.toJsonTree(pendingApproval));
        ArrayList<Chapter> reportedComics = new ArrayList<Chapter>();

        for (Chapter c : allChapters)
        {
            if (c.isReported()) {
                reportedComics.add(c);
            }
        }

        json.add("reportedComics", gson.toJsonTree(reportedComics));

        return json;
    }

    @RequestMapping(value = "admin/get/approveComic/{chapter-ID}", method = RequestMethod.GET)
    public @ResponseBody JsonObject approveComic(@PathVariable(value = "chapter-ID") String chapterID, ModelMap model, HttpSession session, HttpServletRequest req) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();
        System.out.println(chapterID);

        Chapter c = db.chapterRepo.getById(chapterID);
        c.setPublished(true);
        db.chapterRepo.update(c);

        List<Chapter> allChaptersList = db.chapterRepo.getAll();
        ArrayList<Chapter> allChapters = new ArrayList<Chapter>(allChaptersList);

        ArrayList<Chapter> pendingApproval = new ArrayList<Chapter>();

        for (Chapter currentChapter : allChapters)
        {
            if (!currentChapter.isPublished())
            {
                pendingApproval.add(currentChapter);
            }
        }

        json.add("pendingApproval", gson.toJsonTree(pendingApproval));

        return json;
    }

    @RequestMapping(value = "admin/get/falselyReported/{chapter-ID}", method = RequestMethod.GET)
    public @ResponseBody JsonObject falselyReported(@PathVariable(value = "chapter-ID") String chapterID, ModelMap model, HttpSession session, HttpServletRequest req) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();

        Chapter c = db.chapterRepo.getById(chapterID);
        c.setPublished(true);
        c.setReported(false);
        db.chapterRepo.update(c);

        List<Chapter> allChaptersList = db.chapterRepo.getAll();
        ArrayList<Chapter> allChapters = new ArrayList<Chapter>(allChaptersList);

        ArrayList<Chapter> reportedComics = new ArrayList<Chapter>();

        for (Chapter chap : allChapters)
        {
            if (!chap.isReported())
            {
                reportedComics.add(chap);
            }
        }

        json.add("reportedComics", gson.toJsonTree(reportedComics));

        return json;
    }


}
