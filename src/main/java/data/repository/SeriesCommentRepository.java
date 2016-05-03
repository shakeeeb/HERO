package data.repository;

import data.model.SeriesComment;

import java.util.List;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by shakeeb on 4/30/16.
 */
public class SeriesCommentRepository {
    //getAll
    public List<SeriesComment> getAll(){
     return ofy().load().type(SeriesComment.class).list();
    }

    //getByID
    public SeriesComment getById(Long Id){
        return null;
    }

    //getByOthers

    //exists

    //create

    //update

    //delete
}
