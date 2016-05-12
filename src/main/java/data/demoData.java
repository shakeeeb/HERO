package data;

import data.model.*;
import data.repository.*;

import java.util.ArrayList;
import java.util.List;

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
        Series Tseries = db.seriesRepo.getById("My Best Friend Gleb");
        if(Tseries == null){
            Tseries = db.seriesRepo.create("My Best Friend Gleb", "Comedy", T, "Daily life with my co-worker, Gleb");
            System.out.println("created Series My Best Friend Gleb!");
        } else {
            System.out.println("series My Best Friend Gleb exists.");
        }
        Series MKSeries = db.seriesRepo.getById("Puppycat Savage");
        if(MKSeries == null){
            MKSeries = db.seriesRepo.create("Puppycat Savage", "Slice-of-Life", Miuki, "shitting on people since '94");
            System.out.println("created Series Puppycat Savage!");
        } else {
            System.out.println("series Puppycat Savage exists.");
        }
        Series BenSeries = db.seriesRepo.getById("One Piece: Two");
        if(BenSeries == null){
            BenSeries = db.seriesRepo.create("One Piece: Two", "Adventure", Ben, "Pirates! with 20% more Tex-Mex");
            System.out.println("created Series One Piece: Two!");
        } else {
            System.out.println("series One Piece: Two exists.");

        }
        Series MySeries = db.seriesRepo.getById("Devon! I'm Sorry!");
        if(MySeries == null){
            MySeries = db.seriesRepo.create("Devon! I'm Sorry!", "Mystery", Me, "I am my own man!");
            System.out.println("created Series Devon! I'm Sorry!");
        } else {
            System.out.println("series Devon! I'm Sorry! exists.");

        }
        Series JasonSeries = db.seriesRepo.getById("Jesus Will Find You");
        if(JasonSeries == null){
            JasonSeries = db.seriesRepo.create("Jesus Will Find You", "Thriller", Jason, "Jesus Will save you. no matter what.");
            System.out.println("created Series Jesus Will Find You!");
        } else {
            System.out.println("series Jesus Will Find You exists.");

        }
        Series TaraSeries = db.seriesRepo.getById("We Must Find Joe");
        if(TaraSeries == null){
            TaraSeries = db.seriesRepo.create("We Must Find Joe", "Mystery", TayTay, "Where is Joe? only Tara can find him.");
            System.out.println("created Series We Must Find Joe!");
        } else {
            System.out.println("series We Must Find Joe exists.");

        }
        Series JamezSeries = db.seriesRepo.getById("Green Ham & Cheese");
        if(JamezSeries == null){
            JamezSeries = db.seriesRepo.create("Green Ham & Cheese", "Food", James, "He goes around, making sandwiches for the hungry.");
            System.out.println("created Series Green Ham & Cheese!");
        } else {
            System.out.println("series Green Ham & Cheese exists.");

        }
        Series onePiece = db.seriesRepo.getById("One_Piece");
        if(onePiece == null){
            onePiece = db.seriesRepo.create("One_Piece", "Fiction", Ben, "Best MangaEver");
            System.out.println("created Series One_Piece!");
        } else {
            System.out.println("series One_Piece exists.");

        }

        //Adding chapters to series
        // Chapters
        //goddamn it! a chapter ID is a series + chapter name
        System.out.println(Tseries.getName() +"~" + "Day One: The Dream of Gleb");
        Chapter c1 = db.chapterRepo.getById(Tseries.getName() + "~" + "Day One: The Dream of Gleb");
        if(c1 == null){
            c1 = db.chapterRepo.create("Day One: The Dream of Gleb", T, Tseries, 1);
            System.out.println("created Day One: The Dream of Gleb!");
        } else {
            System.out.println("chapter Day One: The Dream of Gleb exists.");

        }
        Chapter c2 = db.chapterRepo.getById(Tseries.getName() + "~" + "Day Two: Gleb's Revenge");
        if(c2 == null){
            c2 = db.chapterRepo.create("Day Two: Gleb's Revenge", T, Tseries, 2);
            System.out.println("created Day Two: Gleb's Revenge!");
        } else {
            System.out.println("chapter Day Two: Gleb's Revenge exists.");

        }
        Chapter c3 = db.chapterRepo.getById(BenSeries.getName() +"~"+"Romance Dawn: The Trip to Moes!");
        if(c3 == null){
            c3 = db.chapterRepo.create("Romance Dawn: The Trip to Moes!", Ben, BenSeries, 1);
            System.out.println("created Romance Dawn: The Trip to Moes!");
        } else {
            System.out.println("chapter Romance Dawn: The Trip to Moes! exists.");

        }
        Chapter c4 = db.chapterRepo.getById(BenSeries.getName() + "~" +"The Great Guacamole Riot");
        if(c4 == null){
            c4 = db.chapterRepo.create("The Great Guacamole Riot", Ben, BenSeries, 2);
            System.out.println("created The Great Guacamole Riot!");
        } else {
            System.out.println("chapter The Great Guacamole Riot exists.");

        }
        Chapter c5 = db.chapterRepo.getById(MKSeries.getName() +"~"+"The Streets of New York");
        if(c5 == null){
            c5 = db.chapterRepo.create("The Streets of New York", Miuki, MKSeries, 1);
            System.out.println("created The Streets of New York!");
        } else {
            System.out.println("chapter The Streets of New York exists.");

        }
        Chapter c6 = db.chapterRepo.getById(MKSeries.getName()+"~"+"It Feels Good To Be A Gangsta");
        if(c6 == null){
            c6 = db.chapterRepo.create("It Feels Good To Be A Gangsta", Miuki, MKSeries, 2);
            c6.setDescription("It feels good to be a gangsta summary");
            System.out.println("created It feels good to be a gangsta");
        } else {
            System.out.println("chapter It Feels Good To be a Gangsta exists.");

        }
        Chapter c7 = db.chapterRepo.getById(onePiece.getName()+"~"+"Luffy_meets_Boa");
        if(c7 == null){
            c7 = db.chapterRepo.create("Luffy_meets_Boa", Ben, onePiece, 1);
            System.out.println("created Luffy_meets_Boa");
        } else {
            System.out.println("chapter Luffy_meets_Boa exists.");

        }
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        // Pages per chapter
        int spages = c6.getAllPages().size();
        //puppycat savage it feels good to be a gangsta
        if(spages == 1){
            // TODO: make this safer
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
        int gpages = c1.getAllPages().size();
        //my best friend gleb the dream of gleb
        if(gpages == 1){
            //TODO: make this safer
            Page p1 = c1.getRoot();
            Page p2 = db.pageRepo.create(Tseries, c1, 1);
            Page p3 = db.pageRepo.create(Tseries, c1, 2);
            Page p4 = db.pageRepo.create(Tseries, c1, 2);
            p1.setNext(p2, c1);
            p2.setNext(p3, c1);
            p2.setNext(p4, c1);
        }

        //one piece luffy meets boa
        int lpages = c7.getAllPages().size();

        if(lpages == 1){
            //TODO: make this safer
            Page pg1 = c7.getRoot();  // level 0
            Page pg2 = db.pageRepo.create(onePiece, c7, 1); //level 1
            Page pg3 = db.pageRepo.create(onePiece, c7, 2); // level 2
            Page pg4 = db.pageRepo.create(onePiece, c7, 2); // level 3
            Page pg5 = db.pageRepo.create(onePiece, c7, 3); //purposeful orphan level 3
            pg1.setImagePath("/resources/images/test/test1.png");
            pg2.setImagePath("/resources/images/test/test2.png");
            pg3.setImagePath("/resources/images/test/test3.png");
            pg4.setImagePath("/resources/images/test/test4.png");
            pg1.setNext(pg2,"Go to Page 2", c7); // 1->2
            pg2.setNext(pg3,"Go to Page 3", c7); //2->3
            pg2.setNext(pg4,"Go to Page 4", c7); //2->4
            db.chapterRepo.update(c7);
        }

        if(db.seriesRepo.exists("One_Piece")){
            //TODO: make this safer
            Chapter onePieceChapter2 = db.chapterRepo.create("Luffy Meets Kim Possible", Ben, onePiece, 2);
            // roots are auto generated
            Chapter onePieceChapter3 = db.chapterRepo.create("Luffy Meets Ron Stoppable", Ben, onePiece, 3);
            Chapter onePieceChapter4 = db.chapterRepo.create("Luffy Meets Evil Luffy", Ben, onePiece, 4);
            Chapter onePieceChapter5 = db.chapterRepo.create("Luffy, I love you", Ben, onePiece, 5);
            onePieceChapter5.setReported(true);
            db.chapterRepo.update(onePieceChapter5);
        }

        List<Series> mList = db.seriesRepo.listSeriesByUpdateTime();

        System.out.println("result of query: " + mList);

//        System.out.println("Page One's options are: " + pageOne.getOptions().get(0));
//
//        System.out.println("Page One next goes to: " + pageOne.getNext().toString());

        //at this point you wanna update
        // what if all the stuff's already in the datastore though? that wouldn't be good.
        // i gotta protect against that somehow
    }
}
