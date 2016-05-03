package data.repository;

import data.model.Series;
import data.model.SeriesComment;
import data.model.UserData;

import java.util.List;
import static com.googlecode.objectify.ObjectifyService.ofy;


public class SeriesCommentRepository {
    //getAll
    public List<SeriesComment> getAll(){
     return ofy().load().type(SeriesComment.class).list();
    }

    //getByID
    public SeriesComment getById(Long Id){
        return ofy().load().type(SeriesComment.class).id(Id).now();
    }

    //getByOthers
    public List<SeriesComment> getByPoster(UserData user){
        return ofy().load().type(SeriesComment.class).filter("posterEmail", user.getEmail()).list();
    }

    public List<SeriesComment> getBySeries(Series series){
        return ofy().load().type(SeriesComment.class).filter("seriesName", series.getName()).list();
    }

    //exists
    public boolean exists(Long Id){
        SeriesComment sc = getById(Id);
        if(sc == null){
            return false;
        } else {
            return true;
        }
    }

    //create
    public SeriesComment create(Series series, UserData poster, String text){
        SeriesComment sc = new SeriesComment(poster, series, text);
        ofy().save().entity(sc).now();
        return sc;
    }

    //update
    public void update(SeriesComment sc){
        ofy().save().entity(sc).now();
    }

    //delete
    public void delete(SeriesComment sc){
        ofy().delete().entity(sc).now();
    }
}
