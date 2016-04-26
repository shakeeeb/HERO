package data.model;

import com.google.appengine.repackaged.com.google.protos.gdata.proto2api.Core;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;
import com.googlecode.objectify.condition.IfFalse;
import com.googlecode.objectify.condition.IfTrue;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
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
    private String seriesId;
    private Date dateCreated; // generate
    @Index private int chapterNumber;
    @Index(IfFalse.class) private Boolean approved = false;
    @Index(IfTrue.class)private boolean pendingApproval = false;
    private ArrayList<Ref<Page>> orphans;
    private int max = 1;
    private boolean reported = false;
    private String summary = null;

    public Chapter(){
        // each chapter must have a single page
        //Page p = new Page(); // should be ID in there
        //root = Ref.create(p);
        dateCreated = new Date();
    }

    /**
     * overloaded Constructors
     */
    public Chapter(String Id){
        //Page p = new Page();
        //root = Ref.create(p);
        this.chapterId = Id;
        this.orphans = new ArrayList<Ref<Page>>();
    }

    public Chapter(String Id, String theName, UserData theAuthor, Series theSeries, int chapterNo){
        this.chapterId = Id;
        this.name = theName;
        this.author = Ref.create(theAuthor);
        this.series = Ref.create(theSeries);
        this.seriesId = theSeries.getName();
        this.chapterNumber = chapterNo;
        this.dateCreated = new Date();
        this.orphans = new ArrayList<Ref<Page>>();
        //Page p = new Page();
        //root = Ref.create(p);
    }

    //without id
    public Chapter(String theName, UserData theAuthor, Series theSeries, int chapterNo){
        this.name = theName;
        this.author = Ref.create(theAuthor);
        this.series = Ref.create(theSeries);
        this.seriesId = theSeries.getName();
        this.chapterNumber = chapterNo;
        this.dateCreated = new Date();
        this.chapterId = theSeries.getName() +"~"+ theName;
        this.orphans = new ArrayList<Ref<Page>>();
        //Page p = new Page();
        //root = Ref.create(p);
    }

    //major constructor
    public Chapter(String theName, UserData theAuthor, Series theSeries, int chapterNo, Page theRoot){
        this.name = theName;
        this.author = Ref.create(theAuthor);
        this.series = Ref.create(theSeries);
        this.seriesId = theSeries.getName();
        this.chapterNumber = chapterNo;
        this.dateCreated = new Date();
        //this.root = Ref.create(theRoot);
        this.chapterId = theSeries.getName() + "~" +  theName;
        this.orphans = new ArrayList<Ref<Page>>();

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

    public Page getRoot() {
        return root.get();
    }

    public void setRoot(Page root) {

        this.root = Ref.create(root);
        ofy().load().entity(root);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserData getAuthor() {
        return author.get();
    }

    public void setAuthor(UserData a) {
        this.author = Ref.create(a);
    }

    public Series getSeries() {
        return series.get();
    }

    public void setSeries(Series s) {
        this.series = Ref.create(s);
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
    public Boolean isReported() {return reported;}

    public void setReported(Boolean reported) {
        this.reported = reported;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public void setOrphans(ArrayList<Page> unlinked){
        for(Page p : unlinked){
            orphans.add(Ref.create(p));
        }
    }

    public ArrayList<Page> getOrphans(){
        ArrayList<Page> returner = new ArrayList<Page>();
        for(Ref<Page> ref : orphans){
            returner.add(ref.get());
        }
        return returner;
    }

    public void addOrphan(Page orphan){
        orphans.add(Ref.create(orphan));
    }

    public void removeOrphan(Page orphan){
        orphans.remove(Ref.create(orphan));
    }

    // extended methods

    public void deletePage(Page toDelete){
        // find the page in the tree
        // find any pages that link to that page
        // and any pages that that page links to
        ArrayList<Page> parents = getParentsOf(toDelete);
        ArrayList<Page> orphans = getChildrenOf(toDelete);
        for (Page parent : parents){
            parent.removeOption(toDelete);
        }

        for(Page p: orphans){
            this.orphans.add(Ref.create(p));
        }

        //ready fro deletion

    }

    public ArrayList<Page> getParentsOf(Page p){
        ArrayList<Page> returner = new ArrayList<Page>();
        this.root.get().getParentsOf(p, returner);
        return returner;
    }

    public ArrayList<Page> getChildrenOf(Page p){
        ArrayList<Page> returner = new ArrayList<Page>();
        this.root.get().getChildrenOf(p, returner);
        return returner;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Chapter){
            Chapter c = (Chapter)o;
            if (c.getChapterId().equals(this.chapterId)){
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    @Override
    public String toString(){
        return this.chapterId;
    }

    public ArrayList<Page> getAllPages(){
        ArrayList<Page> returner = new ArrayList<Page>();
        root.get().getAllPages(returner);
        System.out.println("before adding on the orphans" + returner);
        for(Ref<Page> r: orphans){
            Page p = r.get();
            returner.add(p);
        }
        System.out.println(returner);
        return returner;
        
    }

    public int getMax(){
        return this.max++;
    }

    public boolean isOrphan(Page p){
        if(this.orphans.contains(Ref.create(p))){
            return true;
        } else {
            return false;
        }
    }

    public void setSummary(String newSummary){
        this.summary = newSummary;
    }

    public String getSummary(){
        return this.summary;
    }

    public void submitForApproval(){
        this.pendingApproval = true;
    }

    public void rejectSubmission(){
        this.pendingApproval = false;
    }






}
