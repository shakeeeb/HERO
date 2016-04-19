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
 * Created by shakeeb on 4/5/16.
 */
@Controller
public class TestingController {
    @RequestMapping(value="testing", method = RequestMethod.GET)
    public String testingController(ModelMap model) {
        System.out.println("Going to testing zone ");
        // check if the user's logged in lol
        UserService service = UserServiceFactory.getUserService();
        User u = service.getCurrentUser();
        DbContext db = new DbContext();
        Series s = db.seriesRepo.getById("My Best Friend Gleb");
        if(s == null){
            System.out.println("JESUS FUCK");
        } else {
            System.out.println("series name: "+  s.getName());
        }
        ArrayList<Chapter> opchaps = s.getChapters();
        Page p = db.pageRepo.getById("My Best Friend Gleb~Day One: The Dream of Gleb^2");
        if(p == null){
            System.out.println("page load failed");
        }else{
            System.out.println("ayyyy");
        }
        Chapter c = db.chapterRepo.getById("My Best Friend Gleb~Day One: The Dream of Gleb");
        Chapter c2 = db.chapterRepo.getByKey(s, "My Best Friend Gleb~Day One: The Dream of Gleb");
        //Page p2 = db.pageRepo.getByKey(c2, "Day One: The Dream of Gleb~My Best Friend Gleb^0");
        if(c == null){
            System.out.println("oh no!");
        } else {
            System.out.println("the chapter is: " + c.getChapterId());
        }
        if(c2 == null){
            System.out.println("god fucking damn it");
        } else {
            System.out.println("the get by key :" + c2.getChapterId());
        }
        if(opchaps != null){
            System.out.println("the arraylist" + opchaps.toString());
        }


//        u.getNickname(); // email
//        db.userRepo.create(u.getNickname());
//        UserData m = db.userRepo.getUserById(u.getNickname());
//        m.setAboutMe("im a little teapot, short and stout. this is my handle. this is my spout.");
//        db.userRepo.update(m);
//        Series s = db.seriesRepo.create("Catbug", "Comedy", m, "REBECCAAAAAAA");
//        db.seriesRepo.create("Computer Science", "Life", m, "it is i, computer. feed me a cat.");
//        Chapter c = db.chapterRepo.create("the secret wedding", m, s, 1); // there should be a better way to set numbers
//        //We don't know page numbers until the chapter is created. Make it equivalent with the level.
//        db.pageRepo.create(s, c, 2); // there has to be a better way to do these numbers
        return "testing";
    }
}
