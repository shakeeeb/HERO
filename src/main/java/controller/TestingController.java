package controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import data.DbContext;
import data.model.Chapter;
import data.model.Series;
import data.model.UserData;
import data.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * Created by shakeeb on 4/5/16.
 */
@Controller
public class TestingController {
    @RequestMapping(value="testing", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        System.out.println("Going to testing zone ");
        // check if the user's logged in lol
        UserService service = UserServiceFactory.getUserService();
        User u = service.getCurrentUser();
        DbContext db = new DbContext();
        u.getNickname(); // email
        db.userRepo.create(u.getNickname());
        UserData m = db.userRepo.getUserById(u.getNickname());
        m.setAboutMe("im a little teapot, short and stout. this is my handle. this is my spout.");
        db.userRepo.update(m);
        Series s = db.seriesRepo.create("Catbug", "Comedy", m, "REBECCAAAAAAA");
        db.seriesRepo.create("Computer Science", "Life", m, "it is i, computer. feed me a cat.");
        Chapter c = db.chapterRepo.create("the secret wedding", m, s, 1); // there should be a better way to set numbers
        Page p = db.pageRepo.create(s, c, 2); // there has to be a better way to do these numbers
        Page p2 = db.pageRepo.create(s, c, 3);
        // okay, so you do end up having to save pages individually
        ArrayList<Page> pages = new ArrayList<Page>();
        pages.add(p);
        pages.add(p2);
        db.pageRepo.saveMulitple(pages);
        // i could find  a way to save all pages in a chapter through a function in the chapter
        db.chapterRepo.update(c);
        return "testing";
    }
}
