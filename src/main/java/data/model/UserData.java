package data.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.condition.IfTrue;
import data.DbContext;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;


@Entity
public class UserData {
    @Id private String email;
    private String nickname;
    @Load private ArrayList<Ref<Series>> mySeries;
    @Load private ArrayList<Ref<Page>> bookmarks;
    @Load private ArrayList<Ref<Rating>> ratings;
    @Load private ArrayList<Ref<Series>> recentlyViewed;
    @Load private ArrayList<Ref<Series>> subscriptions;
    @Load private ArrayList<Ref<Series>> suggestions;
    int totalSeriesViewed = 0;
    @Index(IfTrue.class) private Boolean isAdmin = false;
    private String aboutMe;
    private String profilePic;


    public UserData(){
        this.mySeries = new ArrayList<Ref<Series>>();
        this.bookmarks = new ArrayList<Ref<Page>>();
        this.ratings = new ArrayList<Ref<Rating>>();
        this.recentlyViewed = new ArrayList<Ref<Series>>();
        this.subscriptions = new ArrayList<Ref<Series>>();
        this.suggestions = new ArrayList<Ref<Series>>();
    }

    public UserData(String email){
        this.email = email;
        this.nickname = email.split("@")[0]; // splits off the email portion to create a nickname
        this.mySeries = new ArrayList<Ref<Series>>();
        this.bookmarks = new ArrayList<Ref<Page>>();
        this.ratings = new ArrayList<Ref<Rating>>();
        this.recentlyViewed = new ArrayList<Ref<Series>>();
        this.subscriptions = new ArrayList<Ref<Series>>();
        this.suggestions = new ArrayList<Ref<Series>>();
    }

    public UserData(String email, String name, String about){
        this.email = email;
        this.nickname = name;
        this.aboutMe = about;
        this.mySeries = new ArrayList<Ref<Series>>();
        this.bookmarks = new ArrayList<Ref<Page>>();
        this.ratings = new ArrayList<Ref<Rating>>();
        this.recentlyViewed = new ArrayList<Ref<Series>>();
        this.subscriptions = new ArrayList<Ref<Series>>();
        this.suggestions = new ArrayList<Ref<Series>>();
    }

    public UserData(String email, String name, String about, String pic){
        this.email = email;
        this.nickname = name;
        this.aboutMe = about;
        this.profilePic = pic;
        this.mySeries = new ArrayList<Ref<Series>>();
        this.bookmarks = new ArrayList<Ref<Page>>();
        this.ratings = new ArrayList<Ref<Rating>>();
        this.recentlyViewed = new ArrayList<Ref<Series>>();
        this.subscriptions = new ArrayList<Ref<Series>>();
        this.suggestions = new ArrayList<Ref<Series>>();
    }


    //GETTER SETTER
    public String getEmail(){return email;}

    public void setEmail(String email){ this.email = email; }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ArrayList<Series> getSubscriptions() {
        ArrayList<Series> returner = new ArrayList<Series>();
        for(Ref<Series> r: this.subscriptions){
            returner.add(r.get());
        }
        return returner;
    }

    public void addSuggestions(Series s){
        // Determine suggestions here.
        this.suggestions.clear();
        String genre = s.getMainGenre();
        List<Series> allSeriesInGenreList = DbContext.seriesRepo.listSeriesByGenre(genre);
        ArrayList<Series> allSeriesInGenre = new ArrayList<Series>(allSeriesInGenreList);
        for(Series currentSeries: allSeriesInGenre){
            this.suggestions.add(Ref.create(currentSeries));
        }
        ofy().save().entity(this).now();
    }

    public ArrayList<Series> getSuggestions() {
        ArrayList<Series> returner = new ArrayList<Series>();
        for(Ref<Series> r: this.suggestions){
            returner.add(r.get());
        }
        return returner;
    }
    public void addSubscription(Series s){
        this.subscriptions.add(Ref.create(s));
        //add a series add subscriber.
        ofy().save().entity(this).now();
    }

    public boolean isSubscribed(Series s) {
//        System.out.println("I am subscribed to: " + mySeries);
//        System.out.println("Check if the series is: " + s);
        if (this.subscriptions.contains(Ref.create(s))) {
            return true;
        }
        return false;
    }

    public void removeSubscription(Series s){
        if(this.subscriptions.contains(Ref.create(s))){
            this.subscriptions.remove(Ref.create(s));
            System.out.println("successfully removed");
        } else {
            System.out.println("uh oh");
        }
    }

    public ArrayList<Series> getMySeries() {
        ArrayList<Series> returner = new ArrayList<Series>();
        for(Ref<Series> s: mySeries){
            returner.add(s.get());
        }
        return returner;
    }

    public void setMySeries(ArrayList<Series> mySeries) {
        ArrayList<Ref<Series>> intermediary = new ArrayList<Ref<Series>>();
        for(Series s: mySeries){
            intermediary.add(Ref.create(s));
        }
        this.mySeries = intermediary;
    }

    public ArrayList<Page> getBookmarks() {
        ArrayList<Page> returner = new ArrayList<Page>();
        for(Ref<Page> s: bookmarks){
            returner.add(s.get());
        }
        return returner;
    }

    public void setBookmarks(ArrayList<Page> bookmarks) {
        ArrayList<Ref<Page>> intermediary = new ArrayList<Ref<Page>>();
        for(Page p: bookmarks){
            intermediary.add(Ref.create(p));
        }
        this.bookmarks = intermediary;
    }

    public ArrayList<Rating> getRatings() {
        ArrayList<Rating> returner = new ArrayList<Rating>();
        for(Ref<Rating> s: ratings){
            returner.add(s.get());
        }
        return returner;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        ArrayList<Ref<Rating>> intermediary = new ArrayList<Ref<Rating>>();
        for(Rating r: ratings){
            intermediary.add(Ref.create(r));
        }
        this.ratings = intermediary;
    }

    public ArrayList<Series> getRecentlyViewed() {
        ArrayList<Series> returner = new ArrayList<Series>();
        for(Ref<Series> s: recentlyViewed){
            returner.add(s.get());
        }
        return returner;
    }

    public void setRecentlyViewed(ArrayList<Series> recentlyViewed) {
        ArrayList<Ref<Series>> intermediary = new ArrayList<Ref<Series>>();
        for(Series r: recentlyViewed){
            intermediary.add(Ref.create(r));
        }
        this.recentlyViewed = intermediary;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    // extended functionality
    public void addBookmark(Page newBookmark){
        bookmarks.add(Ref.create(newBookmark));
    }

    public boolean removeBookmark(Page removeMe){
        Ref<Page> toRemove = Ref.create(removeMe);
        if(bookmarks.remove(toRemove)){
            return true;
        } else {
            return false;
        }
    }

    public boolean addSeries(Series newSeries){
        mySeries.add(Ref.create(newSeries));

        return true;
    }

    public boolean removeSeries(Series removeMe){
        Ref<Series> toRemove = Ref.create(removeMe);
        if(mySeries.remove(toRemove)){
            return true;
        } else {
            return false;
        }
    }

    public boolean hasSeries(Series checkMe){
        ArrayList<Series> mySeries = this.getMySeries();
        if(mySeries.contains(checkMe)){
            return true;
        } else {
            return false;
        }
    }

    public void addRating(Rating newRating){
        ratings.add(Ref.create(newRating));
    }

    public boolean removeRating(Rating removeMe){
        Ref<Rating> toRemove = Ref.create(removeMe);
        if(ratings.remove(toRemove)){
            return true;
        } else {
            return false;
        }
    }

//    public void addViewed(Series series){
//        totalSeriesViewed++;
//        if(recentlyViewed.size() >= 10){
//            // replace a thing already in the arraylist
//            recentlyViewed.remove(totalSeriesViewed % 10);
//            recentlyViewed.add(Ref.create(series));
//        } else {
//            // just add it to an arraylist
//            recentlyViewed.add(Ref.create(series));
//        }
//    }

    public void addViewed(Series series){
        System.out.println("Adding blah");
        if (this.recentlyViewed.contains(Ref.create(series))) {
            System.out.println("It contains the series");
            for (int i = 0; i < this.recentlyViewed.size(); i++) {
                System.out.println("i: " + i);
                if (this.recentlyViewed.get(i).equals(Ref.create(series))) {
                    System.out.println("It equals at: " + i);
                    System.out.println(("Removing series: " + recentlyViewed.get(i)));
                    this.recentlyViewed.remove(i);
                    this.recentlyViewed.add(0, Ref.create(series));
                }
            }
        }
        else {
            System.out.println("Case: Does not contain series");
            if (this.recentlyViewed.size() >= 5){
                System.out.println("the size is 5 or greater.");
                this.recentlyViewed.remove(4);
                this.recentlyViewed.add(0, Ref.create(series));
            }
            else {
                System.out.println("The size is less than 5.");
                this.recentlyViewed.add(0, Ref.create(series));
            }
            System.out.println("Got passed If");
        }
    }

    public void clearViewed() {
        System.out.println("Clearing recentlyViewed");
        this.recentlyViewed.clear();
    }

    public Series getSpecificSeries(String SeriesName){
        Series returner = null;
        for(Ref<Series> s: mySeries){
            returner = s.get();
            if(returner.getName().equals(SeriesName)){
                return returner;
            }

        }
        return null;
    }
}
