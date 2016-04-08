package controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import data.DbContext;
import data.model.Series;
import data.model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by shakeeb on 4/5/16.
 */
@Controller
public class TestingController {
    @RequestMapping(value="testing", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        System.out.println("Going to testing zone ");
        System.out.println("Going to dashboard ");
        // check if the user's logged in lol
        UserService service = UserServiceFactory.getUserService();
        User u = service.getCurrentUser();
        DbContext db = new DbContext();
        u.getNickname(); // email
        db.userRepo.create(u.getNickname());
        UserData m = db.userRepo.getUserById(u.getNickname());
        m.setAboutMe("im a little teapot, short and stout. this is my handle. this is my spout.");
        db.userRepo.update(m);

        db.seriesRepo.create("Secret Scientist", "Sci-fi", m, "A series about a scientist doing science");
        db.seriesRepo.create("Epic Pirate", "Action", m, "CHAD?!");
        db.seriesRepo.create("Fashion Diva", "Drama", m, "Fashion Diva goes shopping for bags");
        db.seriesRepo.create("The Life Of Pablo", "Horror", m, "Kanye west loves his mother");
        Series s = db.seriesRepo.create("Catbug", "Comedy", m, "REBECCAAAAAAA");
        db.seriesRepo.create("Computer Science", "Life", m, "it is i, computer. feed me a cat.");
        db.chapterRepo.create("the secret wedding", m, s, 1); // there should be a better way to set numbers
        return "testing";
    }
}