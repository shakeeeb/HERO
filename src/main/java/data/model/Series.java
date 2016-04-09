package data.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shakeeb on 3/30/16.
 */
@Entity
@Cache
public class Series {
    @Id private String name;
    @Index private String mainGenre;
    @Index private ArrayList<String> tags = null;
    @Load private Ref<UserData> author;
    @Index private double rating = 0;
    private int totalStars = 0;
    private int totalPossibleStars = 0;
    @Load private ArrayList<Ref<UserData>> subscribers;
    private int numChapters = 0;
    @Load private ArrayList<Ref<Chapter>> chapters;
    private String description = null;
    @Load private ArrayList<Ref<Comment>> comments;
    @Index private Boolean isApproved = false;
    private String bannerURL = null;
    @Index private Date updateTime;

    public Series(){
        this.updateTime = new Date();
    }

    public Series(String theName, UserData theAuthor){
        this.updateTime = new Date();
        this.name = theName;
        this.author = Ref.create(theAuthor);
        this.subscribers = new ArrayList<Ref<UserData>>();
        this.chapters = new ArrayList<Ref<Chapter>>();
        this.comments = new ArrayList<Ref<Comment>>();
    }

    public Series(String theName, UserData theAuthor, String description, String genre){
        this.updateTime = new Date();
        this.name = theName;
        this.author = Ref.create(theAuthor);
        this.description = description;
        this.mainGenre = genre;
        this.subscribers = new ArrayList<Ref<UserData>>();
        this.chapters = new ArrayList<Ref<Chapter>>();
        this.comments = new ArrayList<Ref<Comment>>();
    }

    //GETTER SETTER
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public UserData getAuthor() {
        return author.get();
    }

    public void setAuthor(UserData author) {
        this.author = Ref.create(author);
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getTotalStars() {
        return totalStars;
    }

    public void setTotalStars(int totalStars) {
        this.totalStars = totalStars;
    }

    public int getTotalPossibleStars() {
        return totalPossibleStars;
    }

    public void setTotalPossibleStars(int totalPossibleStars) {
        this.totalPossibleStars = totalPossibleStars;
    }

    public ArrayList<UserData> getSubscribers() {
        ArrayList<UserData> returner = new ArrayList<UserData>();
        for(Ref<UserData> r: subscribers){
            returner.add(r.get());
        }
        return returner;
    }

    public void setSubscribers(ArrayList<UserData> subs) {
        ArrayList<Ref<UserData>> intermediary = new ArrayList<Ref<UserData>>();
        for(UserData u :subs){
            intermediary.add(Ref.create(u));
        }
        this.subscribers = intermediary;
    }

    public int getNumChapters() {
        return numChapters;
    }

    public void setNumChapters(int numChapters) {
        this.numChapters = numChapters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Chapter> getChapters() {
        ArrayList<Chapter> returner = new ArrayList<Chapter>();
        for(Ref<Chapter> ref: chapters){
            returner.add(ref.get());
        }
        return returner;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        ArrayList<Ref<Chapter>> intermediary = new ArrayList<Ref<Chapter>>();
        for(Chapter c: chapters){
            intermediary.add(Ref.create(c));
        }
        this.chapters = intermediary;
    }
    //TODO return ArrayList<comments> instead of ArrayList<Ref<comments>>
    public ArrayList<Ref<Comment>> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        ArrayList<Ref<Comment>> intermediary = new ArrayList<Ref<Comment>>();
        for(Comment c :comments){
            intermediary.add(Ref.create(c));
        }
        this.comments = intermediary;
    }

    public String getBannerURL() {
        return bannerURL;
    }

    public void setBannerURL(String bannerURL) {
        this.bannerURL = bannerURL;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setMainGenre(String genre){
        this.mainGenre = genre;
    }

    public String getMainGenre(String genre){
        return this.mainGenre;
    }

    //extended methods

    public boolean addChapter(Chapter c){
        this.chapters.add(Ref.create(c));
        numChapters++;
        this.updateTime = new Date();
        return true;
    }

    public boolean removeChapter(Chapter c){
        if(numChapters == 0){
            // no chapters to remove
            System.out.println("no chapters to remove");
            return false;
        }
        else {
            if(this.chapters.remove(Ref.create(c))){
                numChapters--;
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean removeChapter(int chapterNumber){
        if(numChapters == 0){
            System.out.println("no Chapters to remove");
            return false;
        }
        for(Ref<Chapter> ref: chapters){
            Chapter r = ref.get();
            if(r.getChapterNumber() == chapterNumber){
                chapters.remove(ref);
                numChapters = chapters.size();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Page){
            Series s = (Series)o;
            if (s.getName().equals(this.name)){
                return true;
            }
        } else {
            return false;
        }
        return false;
    }


}
