package data;

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

        // what if all the stuff's already in the datastore though? that wouldn't be good.
        // i gotta protect against that somehow
    }
}
