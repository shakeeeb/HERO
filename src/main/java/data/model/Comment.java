package data.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

/**
 * Created by shakeeb on 3/30/16.
 */
@Entity
@Cache
public class Comment {
    @Id private String commentId;
    private String text;
    @Load private Ref<UserData> poster;
    @Load private Ref<Series> series;
    private int score;



    public Comment(){

    }

    /**
     * Overloaded constructors
     */
    public Comment(String Id){
        this.commentId = Id;
    }
    // Id could be generated...
    public Comment(String Id, UserData theUser, Series theSeries, String theText){
        this.poster = Ref.create(theUser);
        this.series = Ref.create(theSeries);
        this.commentId = Id;
        this.text = theText;
    }

    //no ID
    public Comment(UserData theUser, Series theSeries, String theText){
        this.poster = Ref.create(theUser);
        this.series = Ref.create(theSeries);
        this.text = theText;
    }

    //GETTER SETTER
    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Ref<UserData> getPoster() {
        return poster;
    }

    public void setPoster(Ref<UserData> poster) {
        this.poster = poster;
    }

    public Ref<Series> getSeries() {
        return series;
    }

    public void setSeries(Ref<Series> series) {
        this.series = series;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
