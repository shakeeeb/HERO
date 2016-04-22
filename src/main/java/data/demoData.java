package data;

import data.model.*;
import data.repository.*;

import java.util.ArrayList;

/**
 * Created by shakeeb on 4/8/16.
 */
public class demoData {
    public static DbContext db;
    public demoData(){
        db = new DbContext();
    }

    public void init(){
        // all demodata should go here
        // i intend for this class to be called by the homepage controller or something
        //UserDatas
        UserData T = db.userRepo.create("tpmx@Essextec.WallStreet");
        UserData Miuki = db.userRepo.create("MKYip@ColdSpringHarbor.lab");
        UserData Ben = db.userRepo.create("Benjamin.Strumeyer@Paws.for.a.Cause");
        UserData Me = db.userRepo.create("ShakiraShakira@QueensLibrary.book");
        UserData TayTay = db.userRepo.create("TayTay@Math.calculus");
        UserData Jason = db.userRepo.create("Pineapple.Jason@InterVarsity.jesus");
        UserData james = db.userRepo.create("Jamez@citi.sandwich.sleep.nap");
        //Add Series to UserData
        Series Tseries = db.seriesRepo.create("My Best Friend Gleb", "Comedy", T, "Daily life with my co-worker, Gleb");
        Series MKSeries = db.seriesRepo.create("Puppycat Savage", "Slice-of-Life", Miuki, "shitting on people since '94");
        Series BenSeries = db.seriesRepo.create("One Piece: Two", "Adventure", Ben, "Pirates! with 20% more Tex-Mex");
        Series MySeries = db.seriesRepo.create("Devon! I'm Sorry!", "Mystery", Me, "I am my own man!");
        Series JasonSeries = db.seriesRepo.create("Jesus Will Find You", "Thriller", Jason, "Jesus Will save you. no matter what.");
        Series TaraSeries = db.seriesRepo.create("We Must Find Joe", "Mystery", TayTay, "Where is Joe? only Tara can find him.");
        Series JamezSeries = db.seriesRepo.create("Green Ham & Cheese", "Food", james, "He goes around, making sandwiches for the hungry.");
        // Chapters
        Chapter c1 = db.chapterRepo.create("Day One: The Dream of Gleb", T, Tseries, 1);
        System.out.println(c1);
        Chapter c2 = db.chapterRepo.create("Day Two: Gleb's Revenge", T, Tseries, 2);
        System.out.println(c2);
        Chapter c3 = db.chapterRepo.create("Romance Dawn: The Trip to Moes!", Ben, BenSeries, 1);
        System.out.println(c3);
        Chapter c4 = db.chapterRepo.create("The Great Guacamole Riot", Ben, BenSeries, 2);
        System.out.println(c4);
        Chapter c5 = db.chapterRepo.create("The Streets of New York", Miuki, MKSeries, 1);
        System.out.println(c5);
        Chapter c6 = db.chapterRepo.create("It Feels Good To Be A Gangsta", Miuki, MKSeries, 2);
        System.out.println(c6);
        // Pages per chapter
        //changed the repo create-- it takes priors.
        // db.pageRepo.create(series, chapter, priors)
        ArrayList<Page> priorHolder = new ArrayList<Page>();
        Page p1 = db.pageRepo.create(Tseries, c1, null);
        priorHolder.add(p1);
        Page p2 = db.pageRepo.create(Tseries, c1, priorHolder);
        Page p3 = db.pageRepo.create(Tseries, c1, priorHolder);
        priorHolder.remove(p1);
        priorHolder.add(p2);
        priorHolder.add(p3);
        Page p4 = db.pageRepo.create(Tseries, c1, priorHolder);

        Series newSeries = db.seriesRepo.create("One_Piece", "Fiction", Ben, "Best MangaEver");
        Chapter newChapter = db.chapterRepo.create("Luffy_meets_Boa", Ben, newSeries, 1);
        //newSeries.addChapter(newChapter);
        Page pageOne = db.pageRepo.create(newSeries, newChapter, null); // the root page
        ArrayList<Page> priorHolder2 = new ArrayList<Page>();
        priorHolder2.add(pageOne);
        Page pageTwo = db.pageRepo.create(newSeries, newChapter, priorHolder2);
        priorHolder2.remove(pageOne);
        priorHolder2.add(pageTwo);
        Page pageThree = db.pageRepo.create(newSeries, newChapter, priorHolder); // page three comes from page two
        Page wowWhataShortChapter = db.pageRepo.create(newSeries, newChapter, priorHolder2);
        pageOne.setImagePath("/resources/images/test/test1.png");
        pageTwo.setImagePath("/resources/images/test/test2.png");
        pageThree.setImagePath("/resources/images/test/test3.png");
        wowWhataShortChapter.setImagePath("/resources/images/test/test4.png");

        newChapter.setRoot(pageOne);
        pageOne.addOption(pageTwo, "Go to Page 2");
        //pageOne.setOptionDescriptors(pageOne.getOptionDescriptors());

        pageTwo.addOption(pageThree, "Go to Page 3");
        //pageTwo.setOptionDescriptors(pageTwo.getOptionDescriptors());

        pageTwo.addOption(wowWhataShortChapter, "Go to Page 4");
        //pageTwo.setOptionDescriptors(pageThree.getOptionDescriptors());


        db.chapterRepo.update(newChapter);

//        Chapter onePieceChapter2 = db.chapterRepo.create("Luffy Meets Kim Possible", Ben, newSeries, 2);
//        Page rootOne = db.pageRepo.create(newSeries, onePieceChapter2, null);
//        Chapter onePieceChapter3 = db.chapterRepo.create("Luffy Meets Ron Stoppable", Ben, newSeries, 3);
//        Page rootTwo = db.pageRepo.create(newSeries, onePieceChapter3, null);
//        Chapter onePieceChapter4 = db.chapterRepo.create("Luffy Meets Evil Luffy", Ben, newSeries, 4);
//        Page rootThree = db.pageRepo.create(newSeries, onePieceChapter4, null);
//        Chapter onePieceChapter5 = db.chapterRepo.create("Luffy, I love you", Ben, newSeries, 5);
//        Page rootFour = db.pageRepo.create(newSeries, onePieceChapter5, null);
//        System.out.println("Page One's options are: " + pageOne.getOptions().get(0));

        System.out.println("Page One next goes to: " + pageOne.getNext().toString());

        //at this point you wanna update
        // what if all the stuff's already in the datastore though? that wouldn't be good.
        // i gotta protect against that somehow
    }
}
