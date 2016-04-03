package data.model;

/**
 * Created by Terrell Mack on 3/30/16.
 */
public class ExampleModel {
    /**
     * Right now
     * models are super basic POJOs
     * with generated setters and getters
     * and no other methods
     * right now relationships are handled using Ref<{object name}>
     * but, this may be changed to making each object serializable
     * however doing so we must implement the methods
     * readObject(), writeObject(), and the field serialVersionUID, which may be auto-generated
     * if these are implemented, we can replace the Ref<{object name}> to actually reference objects
     * however doing so would make them no longer POJOs, (would they still be beans? probably not)
     *
     * right now, we can keep component scanning on because its super useful in other areas of spring
     * (it does the annotation stuff)
     * but if things get too slow, we might need to
     * manually add things to the spring configuration file (dispatcher-servlet.xml)
     *
     * what we initally were going to have controllers, all that extra functionality
     * it's going to go into the repos
     * the repos will also do basic CRUD stuff, (even if i have to code it manually, its cool)
     * they will act as a DAO (data access object)
     *
     * DbContext will be the database access point
     */

    //TODO finish up basic queries for Page, Chapter, Series, UserData and equivalent Repos
    //TODO finish up advanced queries for Page, Chapter, Series, UserData Repositories
    //TODO finish up relation management in the Respective Repositories
    //TODO change the Getters and Setters for Ref<Object> so they return Object not Ref
    // arraylists too
}
