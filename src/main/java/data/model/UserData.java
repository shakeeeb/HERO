package data.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import com.googlecode.objectify.annotation.Load;

import java.util.ArrayList;

/**
 * Created by shakeeb on 3/30/16.
 */
@Entity
public class UserData {
    @Id private String email;
    private String nickname;
    @Load private ArrayList<Ref<Series>> mySeries;
    @Load private ArrayList<Ref<Page>> bookmarks;
    @Load private ArrayList<Ref<Rating>> ratings;
    @Load private ArrayList<Ref<Series>> viewed;
    int totalSeriesViewed = 0;
    private Boolean isAdmin = false;
    private String aboutMe;
    private String profilePic;


    public UserData(){
        this.mySeries = new ArrayList<Ref<Series>>();
        this.bookmarks = new ArrayList<Ref<Page>>();
        this.ratings = new ArrayList<Ref<Rating>>();
        this.viewed = new ArrayList<Ref<Series>>();
    }

    public UserData(String email){
        this.email = email;
        this.nickname = email.split("@")[0]; // splits off the email portion to create a nickname
        this.mySeries = new ArrayList<Ref<Series>>();
        this.bookmarks = new ArrayList<Ref<Page>>();
        this.ratings = new ArrayList<Ref<Rating>>();
        this.viewed = new ArrayList<Ref<Series>>();
    }

    public UserData(String email, String name, String about){
        this.email = email;
        this.nickname = name;
        this.aboutMe = about;
        this.mySeries = new ArrayList<Ref<Series>>();
        this.bookmarks = new ArrayList<Ref<Page>>();
        this.ratings = new ArrayList<Ref<Rating>>();
        this.viewed = new ArrayList<Ref<Series>>();
    }

    public UserData(String email, String name, String about, String pic){
        this.email = email;
        this.nickname = name;
        this.aboutMe = about;
        this.profilePic = pic;
        this.mySeries = new ArrayList<Ref<Series>>();
        this.bookmarks = new ArrayList<Ref<Page>>();
        this.ratings = new ArrayList<Ref<Rating>>();
        this.viewed = new ArrayList<Ref<Series>>();
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

    public ArrayList<Series> getViewed() {
        ArrayList<Series> returner = new ArrayList<Series>();
        for(Ref<Series> s: viewed){
            returner.add(s.get());
        }
        return returner;
    }

    public void setViewed(ArrayList<Series> viewed) {
        ArrayList<Ref<Series>> intermediary = new ArrayList<Ref<Series>>();
        for(Series r: viewed){
            intermediary.add(Ref.create(r));
        }
        this.viewed = intermediary;
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

    public void removeBookmark(int index){
        bookmarks.remove(index);
    }

    public void removeBookmark(Page removeMe){
        Ref<Page> toRemove = Ref.create(removeMe);
        for(Ref<Page>bookmark : bookmarks){
            bookmarks.remove(toRemove);
        }
    }

    public void addSeries(Series newSeries){
        mySeries.add(Ref.create(newSeries));
    }

    public void removeSeries(Series removeMe){
        Ref<Series> toRemove = Ref.create(removeMe);
        for(Ref<Series> series : mySeries){
            mySeries.remove(toRemove);
        }
    }

    public void addRating(Rating newRating){
        ratings.add(Ref.create(newRating));
    }

    public void removeRating(Rating removeMe){
        Ref<Rating> toRemove = Ref.create(removeMe);
        for(Ref<Rating> rating: ratings){
            ratings.remove(rating);
        }
    }

    public void addViewed(Series series){
        totalSeriesViewed++;
        if(viewed.size() >= 10){
            // replace a thing already in the arraylist
            viewed.remove(totalSeriesViewed % 10);
        } else {
            // just add it to an arraylist
            viewed.add(Ref.create(series));
        }
    }
}
