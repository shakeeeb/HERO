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
        T.setAboutMe("Hey fam.");
        UserData Miuki = db.userRepo.create("MKYip@ColdSpringHarbor.lab");
        Miuki.setAboutMe(":)");
        UserData Ben = db.userRepo.create("Benjamin.Strumeyer@Paws.for.a.Cause");
        Ben.setAboutMe("Lets go to Moes");
        UserData Me = db.userRepo.create("ShakiraShakira@QueensLibrary.book");
        Me.setAboutMe("Let me make you food.");
        UserData TayTay = db.userRepo.create("TayTay@Math.calculus");
        TayTay.setAboutMe("D:<");
        UserData Jason = db.userRepo.create("Pineapple.Jason@InterVarsity.jesus");
        Jason.setAboutMe("Hey, how's it going? Do you want some tea?");
        UserData James = db.userRepo.create("Jamez@citi.sandwich.sleep.nap");
        James.setAboutMe("Zzzzzz");
        //Add Series to UserData
        Series Tseries = db.seriesRepo.create("My Best Friend Gleb", "Comedy", T, "Daily life with my co-worker, Gleb");
        Series MKSeries = db.seriesRepo.create("Puppycat Savage", "Slice-of-Life", Miuki, "shitting on people since '94");
        Series BenSeries = db.seriesRepo.create("One Piece: Two", "Adventure", Ben, "Pirates! with 20% more Tex-Mex");
        Series MySeries = db.seriesRepo.create("Devon! I'm Sorry!", "Mystery", Me, "I am my own man!");
        Series JasonSeries = db.seriesRepo.create("Jesus Will Find You", "Thriller", Jason, "Jesus Will save you. no matter what.");
        Series TaraSeries = db.seriesRepo.create("We Must Find Joe", "Mystery", TayTay, "Where is Joe? only Tara can find him.");
        Series JamezSeries = db.seriesRepo.create("Green Ham & Cheese", "Food", James, "He goes around, making sandwiches for the hungry.");
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
        //goddamn it. my demodata got erased
        Page pp1 = c6.getRoot();
        Page pp2 = db.pageRepo.create(MKSeries, c6);
        Page pp3 = db.pageRepo.create(MKSeries, c6);
        Page pp4 = db.pageRepo.create(MKSeries, c6);
        pp1.setNext(pp2, c6);
        pp2.setNext(pp3, c6);
        pp2.setNext(pp4, c6);
        db.chapterRepo.update(c6);
        //changed the repo create-- it takes priors.
        // db.pageRepo.create(series, chapter, priors)
        Page p1 = c1.getRoot();
        Page p2 = db.pageRepo.create(Tseries, c1);
        Page p3 = db.pageRepo.create(Tseries, c1);
        Page p4 = db.pageRepo.create(Tseries, c1);
        p1.setNext(p2, c1);
        p2.setNext(p3, c1);
        p2.setNext(p4, c1);


        Series newSeries = db.seriesRepo.create("One_Piece", "Fiction", Ben, "Best MangaEver");
        Chapter newChapter = db.chapterRepo.create("Luffy_meets_Boa", Ben, newSeries, 1);
        Page pageOne = newChapter.getRoot();
        Page pageTwo = db.pageRepo.create(newSeries, newChapter);
        Page pageThree = db.pageRepo.create(newSeries, newChapter); // page three comes from page two
        Page wowWhataShortChapter = db.pageRepo.create(newSeries, newChapter);
        pageOne.setImagePath("/resources/images/test/test1.png");
        pageTwo.setImagePath("/resources/images/test/test2.png");
        pageThree.setImagePath("/resources/images/test/test3.png");
        wowWhataShortChapter.setImagePath("/resources/images/test/test4.png");


        pageOne.addOption(pageTwo, "Go to Page 2");
        //pageOne.setOptionDescriptors(pageOne.getOptionDescriptors());

        pageTwo.addOption(pageThree, "Go to Page 3");
        //pageTwo.setOptionDescriptors(pageTwo.getOptionDescriptors());

        pageTwo.addOption(wowWhataShortChapter, "Go to Page 4");
        //pageTwo.setOptionDescriptors(pageThree.getOptionDescriptors());
        
        db.chapterRepo.update(newChapter);

        Chapter onePieceChapter2 = db.chapterRepo.create("Luffy Meets Kim Possible", Ben, newSeries, 2);
        Page rootOne = db.pageRepo.create(newSeries, onePieceChapter2, null);
        Chapter onePieceChapter3 = db.chapterRepo.create("Luffy Meets Ron Stoppable", Ben, newSeries, 3);
        Page rootTwo = db.pageRepo.create(newSeries, onePieceChapter3, null);
        Chapter onePieceChapter4 = db.chapterRepo.create("Luffy Meets Evil Luffy", Ben, newSeries, 4);
        Page rootThree = db.pageRepo.create(newSeries, onePieceChapter4, null);
        Chapter onePieceChapter5 = db.chapterRepo.create("Luffy, I love you", Ben, newSeries, 5);
        Page rootFour = db.pageRepo.create(newSeries, onePieceChapter5, null);

        onePieceChapter5.setReported(true);
        db.chapterRepo.update(onePieceChapter5);


//        System.out.println("Page One's options are: " + pageOne.getOptions().get(0));
//
//        System.out.println("Page One next goes to: " + pageOne.getNext().toString());

        //at this point you wanna update
        // what if all the stuff's already in the datastore though? that wouldn't be good.
        // i gotta protect against that somehow
    }
}
