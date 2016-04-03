package data.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shakeeb on 3/30/16.
 */
@Entity
@Cache
public class Series {
    @Id private String name;
    private ArrayList<String> tags;
    @Load private Ref<UserData> author;
    private int rating;
    private int totalStars;
    private int totalPossibleStars;
    @Load private ArrayList<Ref<UserData>> subscribers;
    private int numChapters = 0;
    @Load private ArrayList<Ref<Chapter>> chapters;
    private String description;
    @Load private ArrayList<Ref<Comment>> comments;
    private Boolean isApproved = false;
    private String bannerURL;
    private Date updateTime;

    public Series(){
        this.updateTime = new Date();
    }

    public Series(String theName, UserData theAuthor){
        this.updateTime = new Date();
        this.name = theName;
        this.author = Ref.create(theAuthor);
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

    public Ref<UserData> getAuthor() {
        return author;
    }

    public void setAuthor(Ref<UserData> author) {
        this.author = author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
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

    public ArrayList<Ref<UserData>> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(ArrayList<Ref<UserData>> subscribers) {
        this.subscribers = subscribers;
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

    public ArrayList<Ref<Chapter>> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Ref<Chapter>> chapters) {
        this.chapters = chapters;
    }

    public ArrayList<Ref<Comment>> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Ref<Comment>> comments) {
        this.comments = comments;
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
}
