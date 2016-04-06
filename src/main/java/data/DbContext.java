package data;

import data.model.*;
import data.repository.*;

/**
 * Created by shakeeb on 3/31/16.
 */
public class DbContext {
    public static UserRepository userRepo;
    public static ChapterRepository chapterRepo;
    public static SeriesRepository seriesRepo;
    public static RatingRepository ratingRepo;
    public static PageRepository pageRepo;
    public static CommentRepository commentRepo;

    public DbContext(){
        this.init();
    }

    public void init(){
        userRepo = new UserRepository();
        chapterRepo = new ChapterRepository();
        seriesRepo = new SeriesRepository();
        ratingRepo = new RatingRepository();
        pageRepo = new PageRepository();
        commentRepo = new CommentRepository();
    }

}
