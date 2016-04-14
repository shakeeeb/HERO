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

        Series newSeries = db.seriesRepo.create("One_Piece", "Fiction", Oda, "Best MangaEver");
        Chapter newChapter = db.chapterRepo.create("Luffy_meets_Boa", Oda, newSeries, 1);
        //newSeries.addChapter(newChapter);
        Page pageOne = db.pageRepo.create(newSeries, newChapter, 1);
        Page pageTwo = db.pageRepo.create(newSeries, newChapter, 2);
        Page pageThree = db.pageRepo.create(newSeries, newChapter, 3);
        Page wowWhataShortChapter = db.pageRepo.create(newSeries, newChapter, 4);
        pageOne.setImagePath("/resources/images/test-pages/rebecca.png");
        pageTwo.setImagePath("/resources/images/test-pages/bee_and_puppycat_by_project_gammaray-d6d5n3l.png");
        pageThree.setImagePath("/resources/images/test-pages/laser_beam.png");
        wowWhataShortChapter.setImagePath("/resources/images/test-pages/so_round.png");

        newChapter.setRoot(pageOne);
        pageOne.addOption(pageTwo, "Go to Page 2");
        //pageOne.setOptionDescriptors(pageOne.getOptionDescriptors());

        pageTwo.addOption(pageThree, "Go to Page 3");
        //pageTwo.setOptionDescriptors(pageTwo.getOptionDescriptors());

        pageTwo.addOption(wowWhataShortChapter, "Go to Page 4");
        //pageTwo.setOptionDescriptors(pageThree.getOptionDescriptors());


        db.chapterRepo.update(newChapter);
//        System.out.println("Page One's options are: " + pageOne.getOptions().get(0));

        System.out.println("Page One next goes to: " + pageOne.getNext().getNext().toString());

        //at this point you wanna update
        model.addAttribute("chapter", newChapter);

        System.out.println("Ayyy");

        return "chapter-index";
    }
}
