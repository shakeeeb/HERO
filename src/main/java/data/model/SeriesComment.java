package data.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * Created by shakeeb on 4/30/16.
 */
public class SeriesComment {
    @Id private Long commentId;
    private String text;
    @Load private Ref<UserData> poster;
    @Index private String posterEmail;
    @Load private Ref<Series> series;
    @Index private String seriesName;
    private int score;

    public SeriesComment(){

    }

    public SeriesComment(UserData poster, Series series, String text){
        this.poster = Ref.create(poster);
        this.series = Ref.create(series);
        this.seriesName = series.getName();
        this.posterEmail = poster.getEmail();
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

    public String getPosterEmail(){
        return this.posterEmail;
    }

    public String getSeriesName(){
        return this.seriesName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
