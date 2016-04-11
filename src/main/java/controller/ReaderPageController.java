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
public class ReaderPageController {
    @RequestMapping(value="read", method = RequestMethod.GET)
    public String readerPageController(ModelMap model) {
        System.out.println("Going to reader-page ");

        DbContext db = new DbContext();


        UserService service = UserServiceFactory.getUserService();
        User u = service.getCurrentUser();
        db.userRepo.create(u.getNickname());
        UserData Oda = db.userRepo.getUserById(u.getNickname());

        Series newSeries = db.seriesRepo.create("One Piece", "Fiction", Oda, "Best MangaEver");
        Chapter newChapter = db.chapterRepo.create("Luffy meets Boa", Oda, newSeries, 1);
        Page pageOne = db.pageRepo.create(newSeries, newChapter, 1);
        Page pageTwo = db.pageRepo.create(newSeries, newChapter, 2);
        Page pageThree = db.pageRepo.create(newSeries, newChapter, 3);
        Page wowWhataShortChapter = db.pageRepo.create(newSeries, newChapter, 4);


        return "read";
    }
}
// QUERY FOR THE PAGE
// Load a model with the page info.
// Go to view.
// Use the information that was used for the page, which should be the page URL.
