package data.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;
import com.googlecode.objectify.condition.IfFalse;

import java.util.Date;

@Entity
@Cache
public class Chapter {
    @Id private String chapterId;
    private boolean published = false;
    @Load private Ref<Page> root;
    private String name;
    @Load private Ref<UserData> author;
    @Parent @Load private Ref<Series> series;
    private Date dateCreated; // generate
    @Index private int chapterNumber;
    @Index(IfFalse.class) private Boolean approved = false;

    public Chapter(){
        // each chapter must have a single page
        Page p = new Page(); // should be ID in there
        root = Ref.create(p);
        dateCreated = new Date();
    }

    /**
     * overloaded Constructors
     */
    public Chapter(String Id){
        //Page p = new Page();
        //root = Ref.create(p);
        this.chapterId = Id;
    }

    public Chapter(String Id, String theName, UserData theAuthor, Series theSeries, int chapterNo){
        this.chapterId = Id;
        this.name = theName;
        this.author = Ref.create(theAuthor);
        this.series = Ref.create(theSeries);
        this.chapterNumber = chapterNo;
        this.dateCreated = new Date();
        //Page p = new Page();
        //root = Ref.create(p);
    }

    //without id
    public Chapter(String theName, UserData theAuthor, Series theSeries, int chapterNo){
        this.name = theName;
        this.author = Ref.create(theAuthor);
        this.series = Ref.create(theSeries);
        this.chapterNumber = chapterNo;
        this.dateCreated = new Date();
        this.chapterId = theName + theSeries.getName();
        //Page p = new Page();
        //root = Ref.create(p);
    }

    //major constructor
    public Chapter(String theName, UserData theAuthor, Series theSeries, int chapterNo, Page theRoot){
        this.name = theName;
        this.author = Ref.create(theAuthor);
        this.series = Ref.create(theSeries);
        this.chapterNumber = chapterNo;
        this.dateCreated = new Date();
        //this.root = Ref.create(theRoot);
        this.chapterId = theName + theSeries.getName();
    }

    //  GETTER SETTER
    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Ref<Page> getRoot() {
        return root;
    }

    public void setRoot(Page root) {
        this.root = Ref.create(root);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ref<UserData> getAuthor() {
        return author;
    }

    public void setAuthor(Ref<UserData> author) {
        this.author = author;
    }

    public Ref<Series> getSeries() {
        return series;
    }

    public void setSeries(Ref<Series> series) {
        this.series = series;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    // extended methods


}
