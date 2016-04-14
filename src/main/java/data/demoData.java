package data;

import data.model.*;
import data.repository.*;

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
        Chapter c2 = db.chapterRepo.create("Day Two: Gleb's Revenge", T, Tseries, 2);
        Chapter c3 = db.chapterRepo.create("Romance Dawn: The Trip to Moes!", Ben, BenSeries, 1);
        Chapter c4 = db.chapterRepo.create("The Great Guacamole Riot", Ben, BenSeries, 2);
        Chapter c5 = db.chapterRepo.create("The Streets of New York", Miuki, MKSeries, 1);
        Chapter c6 = db.chapterRepo.create("It Feels Good To Be A Gangsta", Miuki, MKSeries, 2);
        // Pages per chapter
        Page p1 = db.pageRepo.create(Tseries, c1, 1);
        Page p2 = db.pageRepo.create(Tseries, c1, 2);
        Page p3 = db.pageRepo.create(Tseries, c1, 3);
        Page p4 = db.pageRepo.create(Tseries, c1, 4);
        p1.setNext(p2);
        p1.setNext(p3);
        p2.setNext(p4);
        p3.setNext(p4);
        // what if all the stuff's already in the datastore though? that wouldn't be good.
        // i gotta protect against that somehow
    }
}
