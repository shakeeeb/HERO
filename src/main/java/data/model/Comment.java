package data.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

/**
 * Comments are for Comments at the end of chapters!
 */
@Entity
@Cache
public class Comment {
    @Id private Long commentId;
    private String text;
    @Load private Ref<UserData> poster;
    @Load private Ref<Chapter> chapter;
    private int score;

    public Comment(){

    }

    /**
     * Overloaded constructors
     */
    public Comment(Long Id){
        this.commentId = Id;
    }
    // Id could be generated...
    public Comment(Long Id, UserData theUser, Chapter theChapter, String theText){
        this.poster = Ref.create(theUser);
        this.chapter = Ref.create(theChapter);
        this.commentId = Id;
        this.text = theText;
    }

    //no ID
    public Comment(UserData theUser, Chapter theChapter, String theText){
        this.poster = Ref.create(theUser);
        this.chapter = Ref.create(theChapter);
        this.text = theText;
    }

    //GETTER SETTER
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
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

    public Ref<Chapter> getSeries() {
        return chapter;
    }

    public void setSeries(Ref<Chapter> chapter) {
        this.chapter = chapter;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
