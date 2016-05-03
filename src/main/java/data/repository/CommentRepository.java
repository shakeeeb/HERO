package data.repository;
import data.model.*;
import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Objectify; // turn this into a static import

import java.util.ArrayList;
import java.util.List;



public class CommentRepository {
    // each repo fucks with Objectify

    //getAll()
    public List<Comment> getAll(){
        return ofy().load().type(Comment.class).list();
    }

    //getById(Id)
    public Comment getById(Long id){
        return ofy().load().type(Comment.class).id(id).now();
    }

    //getByOtherThingsIfNeedBe
    //actually, i don't think i can do that...
    public List<Comment> getCommentsByUser(UserData user){
        return ofy().load().type(Comment.class).filter("posterEmail", user.getEmail()).list();
    }

    public List<Comment> getCommentsByChapter(Chapter chapter){
        return ofy().load().type(Comment.class).filter("chapterId", chapter.getChapterId()).list();
    }
    //exists(Id)
    public boolean exists(Long Id){
        Comment c = getById(Id);
        if(c == null){
            return false;
        } else {
            return true;
        }
    }

    //create(Id, stuff)
    public Comment create(UserData poster, Chapter chapter, String text){
        Comment c = new Comment(poster, chapter, text);
        ofy().save().entity(c).now();
        return c;
    }

    //overload create with a whole bunch of stuff

    //update(Id, stuff to update...)
    public void update(Comment c){
        ofy().save().entity(c).now();
    }

    //delete(Id)
    public void delete(Comment c){
        ofy().delete().entity(c).now();
    }
}
