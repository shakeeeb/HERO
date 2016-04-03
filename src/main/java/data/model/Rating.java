package data.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by shakeeb on 3/30/16.
 */
@Entity
@Cache
public class Rating {
    @Id private String ratingId;
    private Ref<UserData> user;
    private int rating;

    public Rating() {

    }

    public Rating(UserData user, int rating){
        this.user = Ref.create(user);
        this.rating = rating;
    }


    //GETTER SETTER
    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    public Ref<UserData> getUser() {
        return user;
    }

    public void setUser(Ref<UserData> user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
