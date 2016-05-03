package data.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

/**
 * Created by shakeeb on 4/30/16.
 */
public class SeriesComment {
    @Id private Long commentId;
    private String text;
    @Load private Ref<UserData> poster;
    @Load private Ref<Series> series;
    private int score;

    public SeriesComment(){

    }

    public SeriesComment(UserData poster, Series series, String text){
        this.poster = Ref.create(poster);
        this.series = Ref.create(series);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public UserData getPoster() {
        return poster.get();
    }

    public void setPoster(UserData poster) {
        this.poster = Ref.create(poster);
    }

    public Series getSeries() {
        return series.get();
    }

    public void setSeries(Series series) {
        this.series = Ref.create(series);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
