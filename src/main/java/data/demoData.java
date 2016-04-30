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
        UserData T = null;
        if(!db.userRepo.exists("tangobearindustries@gmail.com")){
            T = db.userRepo.create("tangobearindustries@gmail.com");
            T.setAboutMe("Hey fam.");
            System.out.println("made UserData T!");
        } else {
            System.out.println("userData T exists.");
            T = db.userRepo.getUserById("tangobearindustries@gmail.com");
        }
        UserData Miuki = null;
        if(!db.userRepo.exists("MKYip@ColdSpringHarbor.lab")){
            Miuki = db.userRepo.create("MKYip@ColdSpringHarbor.lab");
            Miuki.setAboutMe(":)");
            System.out.println("made UserData Miu Ki!");
        } else {
            System.out.println("userData Miu Ki exists.");
            Miuki = db.userRepo.getUserById("MKYip@ColdSpringHarbor.lab");
        }
        UserData Ben = null;
        if(!db.userRepo.exists("Benjamin.Strumeyer@Paws.for.a.Cause")){
            Ben = db.userRepo.create("Benjamin.Strumeyer@Paws.for.a.Cause");
            Ben.setAboutMe("Lets go to Moes");
            System.out.println("made UserData Ben!");
        } else {
            System.out.println("userData Ben exists.");
            Ben = db.userRepo.getUserById("Benjamin.Strumeyer@Paws.for.a.Cause");
        }
        UserData Me = null;
        if(!db.userRepo.exists("ShakiraShakira@QueensLibrary.book")){
            Me = db.userRepo.create("ShakiraShakira@QueensLibrary.book");
            Me.setAboutMe("Let me make you food.");
            System.out.println("made UserData Shakeeb!");
        } else {
            System.out.println("userData Shakeeb exists.");
            Me = db.userRepo.getUserById("ShakiraShakira@QueensLibrary.book");
        }
        UserData TayTay = null;
        if(!db.userRepo.exists("TayTay@Math.calculus")){
            TayTay = db.userRepo.create("TayTay@Math.calculus");
            TayTay.setAboutMe("D:<");
            System.out.println("made UserData Tara!");
        } else {
            System.out.println("userData Tara exists.");
            TayTay = db.userRepo.getUserById("TayTay@Math.calculus");
        }
        UserData Jason = null;
        if(!db.userRepo.exists("Pineapple.Jason@InterVarsity.jesus")){
            Jason = db.userRepo.create("Pineapple.Jason@InterVarsity.jesus");
            Jason.setAboutMe("Hey, how's it going? Do you want some tea?");
            System.out.println("made UserData Jason!");
        } else {
            System.out.println("userData Jason exists.");
            Jason = db.userRepo.getUserById("Pineapple.Jason@InterVarsity.jesus");
        }
        UserData James = null;
        if(!db.userRepo.exists("Jamez@citi.sandwich.sleep.nap")){
            James = db.userRepo.create("Jamez@citi.sandwich.sleep.nap");
            James.setAboutMe("Zzzzzz");
            System.out.println("made UserData James!");
        } else {
            System.out.println("userData James exists.");
            James = db.userRepo.getUserById("Jamez@citi.sandwich.sleep.nap");
        }

        //Add Series to UserData
        Series Tseries = null;
        if(!db.seriesRepo.exists("My Best Friend Gleb")){
            Tseries = db.seriesRepo.create("My Best Friend Gleb", "Comedy", T, "Daily life with my co-worker, Gleb");
            System.out.println("created Series My Best Friend Gleb!");
        } else {
            System.out.println(" My Best Friend Gleb exists.");
            Tseries = db.seriesRepo.getById("My Best Friend Gleb");
        }
        Series MKSeries = null;
        if(!db.chapterRepo.exists("Puppycat Savage")){
            MKSeries = db.seriesRepo.create("Puppycat Savage", "Slice-of-Life", Miuki, "shitting on people since '94");
            System.out.println("created Series Puppycat Savage!");
        } else {
            System.out.println("series Puppycat Savage exists.");
            MKSeries = db.seriesRepo.getById("Puppycat Savage");
        }
        Series BenSeries = null;
        if(!db.seriesRepo.exists("One Piece: Two")){
            BenSeries = db.seriesRepo.create("One Piece: Two", "Adventure", Ben, "Pirates! with 20% more Tex-Mex");
            System.out.println("created Series One Piece: Two!");
        } else {
            System.out.println("series One Piece: Two exists.");
            BenSeries = db.seriesRepo.getById("One Piece: Two");
        }
        Series MySeries = null;
        if(!db.seriesRepo.exists("Devon! I'm Sorry!")){
            MySeries = db.seriesRepo.create("Devon! I'm Sorry!", "Mystery", Me, "I am my own man!");
            System.out.println("created Series Devon! I'm Sorry!");
        } else {
            System.out.println("series Devon! I'm Sorry! exists.");
            MySeries = db.seriesRepo.getById("Devon! I'm Sorry!");
        }
        Series JasonSeries = null;
        if(!db.seriesRepo.exists("Jesus Will Find You")){
            JasonSeries = db.seriesRepo.create("Jesus Will Find You", "Thriller", Jason, "Jesus Will save you. no matter what.");
            System.out.println("created Series Puppycat Savage!");
        } else {
            System.out.println("series Puppycat Savage exists.");
            JasonSeries = db.seriesRepo.getById("Jesus Will Find You");
        }
        Series TaraSeries = null;
        if(!db.seriesRepo.exists("We Must Find Joe")){
            TaraSeries = db.seriesRepo.create("We Must Find Joe", "Mystery", TayTay, "Where is Joe? only Tara can find him.");
            System.out.println("created Series We Must Find Joe!");
        } else {
            System.out.println("series We Must Find Joe exists.");
            TaraSeries = db.seriesRepo.getById("We Must Find Joe");
        }
        Series JamezSeries = null;
        if(!db.seriesRepo.exists("Green Ham & Cheese")){
            JamezSeries = db.seriesRepo.create("Green Ham & Cheese", "Food", James, "He goes around, making sandwiches for the hungry.");
            System.out.println("created Series Green Ham & Cheese!");
        } else {
            System.out.println("series Green Ham & Cheese exists.");
            JamezSeries = db.seriesRepo.getById("We Must Find Joe");
        }
        Series onePiece = null;
        if(!db.seriesRepo.exists("One_Piece")){
            onePiece = db.seriesRepo.create("One_Piece", "Fiction", Ben, "Best MangaEver");
            System.out.println("created Series One_Piece!");
        } else {
            System.out.println("series One_Piece exists.");
            onePiece = db.seriesRepo.getById("One_Piece");
        }

        //Adding chapters to series
        // Chapters
        Chapter c1 = null;
        if(!db.chapterRepo.exists("Day One: The Dream of Gleb")){
            c1 = db.chapterRepo.create("Day One: The Dream of Gleb", T, Tseries, 1);
            System.out.println("created Day One: The Dream of Gleb!");
        } else {
            System.out.println("chapter Day One: The Dream of Gleb exists.");
            c1 = db.chapterRepo.getById("Day One: The Dream of Gleb");
        }
        Chapter c2 = null;
        if(!db.chapterRepo.exists("Day Two: Gleb's Revenge")){
            c2 = db.chapterRepo.create("Day Two: Gleb's Revenge", T, Tseries, 2);
            System.out.println("created Day Two: Gleb's Revenge!");
        } else {
            System.out.println("chapter Day Two: Gleb's Revenge exists.");
            c2 = db.chapterRepo.getById("Day Two: Gleb's Revenge");
        }
        Chapter c3 = null;
        if(!db.chapterRepo.exists("Romance Dawn: The Trip to Moes!")){
            c3 = db.chapterRepo.create("Romance Dawn: The Trip to Moes!", Ben, BenSeries, 1);
            System.out.println("created Romance Dawn: The Trip to Moes!");
        } else {
            System.out.println("chapter Romance Dawn: The Trip to Moes! exists.");
            c3 = db.chapterRepo.getById("Romance Dawn: The Trip to Moes!");
        }
        Chapter c4 = null;
        if(!db.chapterRepo.exists("The Great Guacamole Riot")){
            c4 = db.chapterRepo.create("The Great Guacamole Riot", Ben, BenSeries, 2);
            System.out.println("created The Great Guacamole Riot!");
        } else {
            System.out.println("chapter The Great Guacamole Riot exists.");
            c4 = db.chapterRepo.getById("The Great Guacamole Riot");
        }
        Chapter c5 = null;
        if(!db.chapterRepo.exists("The Streets of New York")){
            c5 = db.chapterRepo.create("The Streets of New York", Miuki, MKSeries, 1);
            System.out.println("created The Streets of New York!");
        } else {
            System.out.println("chapter The Streets of New York exists.");
            c5 = db.chapterRepo.getById("The Streets of New York");
        }
        Chapter c6 = null;
        if(!db.chapterRepo.exists("It Feels Good To Be A Gangsta")){
            c6 = db.chapterRepo.create("It Feels Good To Be A Gangsta", Miuki, MKSeries, 2);
            c6.setSummary("It feels good to be a gangsta summary");
            System.out.println("created It feels good to be a gangsta");
        } else {
            System.out.println("chapter It Feels Good To be a Gangsta exists.");
            c6 = db.chapterRepo.getById("It Feels Good To Be A Gangsta");
        }
        Chapter c7 = null;
        if(!db.chapterRepo.exists("Luffy_meets_Boa")){
            c7 = db.chapterRepo.create("Luffy_meets_Boa", Ben, onePiece, 1);
            System.out.println("created Luffy_meets_Boa");
        } else {
            System.out.println("chapter Luffy_meets_Boa exists.");
            c7 = db.chapterRepo.getById("It Feels Good To Be A Gangsta");
        }
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        // Pages per chapter

        if(c6 != null){
            Page pp1 = c6.getRoot(); //root is level 0
            Page pp2 = db.pageRepo.create(MKSeries, c6, 1); //hardcoded levels for now
            Page pp3 = db.pageRepo.create(MKSeries, c6, 2);
            Page pp4 = db.pageRepo.create(MKSeries, c6, 2);
            pp1.setNext(pp2, c6);
            pp2.setNext(pp3, c6);
            pp2.setNext(pp4, c6);
            db.chapterRepo.update(c6);
        }
        //changed the repo create-- it takes priors.
        // db.pageRepo.create(series, chapter, priors)
        if(c1 != null){
            Page p1 = c1.getRoot();
            Page p2 = db.pageRepo.create(Tseries, c1, 1);
            Page p3 = db.pageRepo.create(Tseries, c1, 2);
            Page p4 = db.pageRepo.create(Tseries, c1, 2);
            p1.setNext(p2, c1);
            p2.setNext(p3, c1);
            p2.setNext(p4, c1);
        }

        if(c7 != null){
            Page pg1 = c7.getRoot();
            Page pg2 = db.pageRepo.create(onePiece, c7, 1);
            Page pg3 = db.pageRepo.create(onePiece, c7, 2); // page three comes from page two
            Page pg4 = db.pageRepo.create(onePiece, c7, 3);
            Page pg5 = db.pageRepo.create(onePiece, c7, 3); //purposeful orphan
            pg1.setImagePath("/resources/images/test/test1.png");
            pg2.setImagePath("/resources/images/test/test2.png");
            pg3.setImagePath("/resources/images/test/test3.png");
            pg4.setImagePath("/resources/images/test/test4.png");
            pg1.addOption(pg2, "Go to Page 2"); // 1->2
            pg2.addOption(pg3, "Go to Page 3"); //2->3
            pg2.addOption(pg4, "Go to Page 4"); //2->4
            db.chapterRepo.update(c7);
        }

        if(db.seriesRepo.exists("One_Piece")){
            Chapter onePieceChapter2 = db.chapterRepo.create("Luffy Meets Kim Possible", Ben, onePiece, 2);
            // roots are auto generated
            Chapter onePieceChapter3 = db.chapterRepo.create("Luffy Meets Ron Stoppable", Ben, onePiece, 3);
            Chapter onePieceChapter4 = db.chapterRepo.create("Luffy Meets Evil Luffy", Ben, onePiece, 4);
            Chapter onePieceChapter5 = db.chapterRepo.create("Luffy, I love you", Ben, onePiece, 5);
            onePieceChapter5.setReported(true);
            db.chapterRepo.update(onePieceChapter5);
        }





//        System.out.println("Page One's options are: " + pageOne.getOptions().get(0));
//
//        System.out.println("Page One next goes to: " + pageOne.getNext().toString());

        //at this point you wanna update
        // what if all the stuff's already in the datastore though? that wouldn't be good.
        // i gotta protect against that somehow
    }
}
