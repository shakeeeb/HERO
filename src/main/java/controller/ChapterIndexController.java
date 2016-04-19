package controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import data.DbContext;
import data.model.Chapter;
import data.model.Page;
import data.model.Series;
import data.model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class ChapterIndexController {

    protected DbContext db = new DbContext();

    @RequestMapping(value="chapter-index", method = RequestMethod.GET)
    public String chapterIndexPage(ModelMap model) {
        System.out.println("Going to chapter-index ");

        UserService service = UserServiceFactory.getUserService();
        User u = service.getCurrentUser();
        db.userRepo.create(u.getNickname());
        UserData Oda = db.userRepo.getUserById(u.getNickname());



        return "chapter-index";
    }
}
