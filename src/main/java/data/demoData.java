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
        UserData T = db.userRepo.create("tangobearindustries@gmail.com");
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
        Page p1 = c1.getRoot();
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
        Page pageOne = newChapter.getRoot();//db.pageRepo.create(newSeries, newChapter, null); // the root page
        // this is placed as an orphan
        ArrayList<Page> priorHolder2 = new ArrayList<Page>();
        priorHolder2.add(pageOne);
        Page pageTwo = db.pageRepo.create(newSeries, newChapter, priorHolder2);// pagetwo links from page1
        priorHolder2.remove(pageOne);
        priorHolder2.add(pageTwo);
        Page pageThree = db.pageRepo.create(newSeries, newChapter, priorHolder2); // page three links from page 2
        Page wowWhataShortChapter = db.pageRepo.create(newSeries, newChapter, priorHolder2); // age four links from page 2
        pageOne.setImagePath("/resources/images/test/test1.png"); // root 0
        pageTwo.setImagePath("/resources/images/test/test2.png"); // second page 1
        pageThree.setImagePath("/resources/images/test-images/test3.png"); // 2
        wowWhataShortChapter.setImagePath("/resources/images/test-images/test4.png"); // 3

        //newChapter.setRoot(pageOne);
        //pageOne.setNext(pageTwo);
        //pageOne.setOptionDescriptors(pageOne.getOptionDescriptors());

        //pageTwo.addOption(pageThree, "Go to Page 3");
        //pageTwo.setOptionDescriptors(pageTwo.getOptionDescriptors());

        //pageTwo.addOption(wowWhataShortChapter, "Go to Page 4");
        //pageTwo.setOptionDescriptors(pageThree.getOptionDescriptors());

//        pageOne.setPageNumber(1);
//        pageTwo.setPageNumber(2);
//        pageThree.setPageNumber(3);
//        wowWhataShortChapter.setPageNumber(4);
        pageOne.printTraversal(0);


        db.chapterRepo.update(newChapter);
//        System.out.println("Page One's options are: " + pageOne.getOptions().get(0));

        System.out.println("Page One next goes to: " + pageOne.getNext().toString());

        //at this point you wanna update
        System.out.println("Ayyy");
        // what if all the stuff's already in the datastore though? that wouldn't be good.
        // i gotta protect against that somehow
    }
}
