package data.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;


/**
 * goddamnit ben
 */
@Entity
@Cache
public class Page {
    @Id private String pageId; // id is a concat of seriesname,chaptername,pagenumber
    @Load private Ref<Series> series;
    @Parent @Load private Ref<Chapter> chapter;
    @Index private String chapterNameSeriesName;
    private String chapterId;
    private String seriesId;
    private int pageNumber;
    private int pageLevel;
    private int numOptions = 0;
    private int numPriors = 0;
    private String imagePath;
    //@Load private Ref<Page> Next = null;
    //@Load private Ref<Page> Prev = null;
    @Load private ArrayList<Ref<Page>> options = null;
    @Load private ArrayList<Ref<Page>> priors = null;
    private ArrayList<String> optionDescriptors;
    private String jsonString = null;
    private String SVGString = null;

    //NOTE - DO NOT USE PRIORS OR PREV -> SINGLE LINK
    // IT SHOULD BE REMOVED AT SOME POINT



    /**
     * OverLoaded Constructors
     */
    // need to manage the tree at this level
    public Page(){
        optionDescriptors = new ArrayList<String>();
        options = new ArrayList<Ref<Page>>();
        priors = new ArrayList<Ref<Page>>();
    }

    public Page(String Id){
        optionDescriptors = new ArrayList<String>();
        options = new ArrayList<Ref<Page>>();
        priors = new ArrayList<Ref<Page>>();
        this.pageId = Id;
    }

    // add imagepaths later
    public Page(Series mySeries, Chapter theChapter, int pgNo, int pgLvl){
        optionDescriptors = new ArrayList<String>();
        options = new ArrayList<Ref<Page>>();
        priors = new ArrayList<Ref<Page>>();
        this.pageId = mySeries.getName() +"~"+ theChapter.getName() +"^"+ pgNo; // id
        this.chapterId = theChapter.getChapterId();
        this.seriesId = mySeries.getName();
        this.series = Ref.create(mySeries);
        this.chapter = Ref.create(theChapter);
        this.chapterNameSeriesName = theChapter.getChapterId();
        this.pageLevel = pgLvl;
        this.pageNumber = pgNo;
    }

    /**
     * major constructor
     * @param mySeries
     * @param theChapter
     * @param pgNo
     */
    public Page(Series mySeries, Chapter theChapter, int pgNo){
        optionDescriptors = new ArrayList<String>();
        options = new ArrayList<Ref<Page>>();
        priors = new ArrayList<Ref<Page>>();
        this.pageId = theChapter.getChapterId() +"^"+ pgNo;
        this.series = Ref.create(mySeries);
        this.chapter = Ref.create(theChapter);
        this.chapterId = theChapter.getChapterId();
        this.seriesId = mySeries.getName();
        this.chapterNameSeriesName = theChapter.getChapterId();
        this.pageNumber = pgNo;
    }

    public Page(Series mySeries, Chapter theChapter, int pgNo, ArrayList<Page> predecessors){
        optionDescriptors = new ArrayList<String>();
        options = new ArrayList<Ref<Page>>();
        priors = new ArrayList<Ref<Page>>();
        this.pageId = theChapter.getChapterId() +"^"+  pgNo;
        this.series = Ref.create(mySeries);
        this.chapter = Ref.create(theChapter);
        this.chapterId = theChapter.getChapterId();
        this.seriesId = mySeries.getName();
        this.chapterNameSeriesName = theChapter.getChapterId();
        this.pageNumber = pgNo;
        setPriors(predecessors);
    }

    //GETTER SETTER
    public String getSVGString(){
      return SVGString;
    }

    public void setSVGString(String SVG){
        this.SVGString = SVG;
    }
    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Series getSeries() {
        return series.get();
    }

    public void setSeries(Series s) {
        this.series = Ref.create(s);
    }

    public Chapter getChapter() {
        return chapter.get();
    }

    public void setChapter(Chapter c) {
        this.chapter = Ref.create(c);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageLevel() {
        return pageLevel;
    }

    public void setPageLevel(int pageLevel) {
        this.pageLevel = pageLevel;
    }

    public int getNumOptions() {
        return numOptions;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public void setNumOptions(int numOptions) {
        this.numOptions = numOptions;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        System.out.println("The Image path for this image is: " + this.imagePath);
    }

    public Page getNext() {
        return options.get(0).get();
    }

    public void setNext(Page newNext, String optionDescriptor, Chapter myChapter){
        options.add(Ref.create(newNext));
        optionDescriptors.add(optionDescriptor);
        newNext.addPrior(this);
        numOptions = options.size();
        if(myChapter.getOrphans().contains(newNext)){
            System.out.println("removing " + newNext.getPageId() + " from orphans");
            myChapter.removeOrphan(newNext);
            ofy().save().entity(myChapter).now();
            // if it's in an oprhan subtree, it should be okay
        }
        System.out.println("not an orphan");
        // also add the prior reference
        ofy().save().entity(this).now();
        ofy().save().entity(newNext).now();
        ofy().save().entity(myChapter).now();
    }

//    public void setNext(Page newNext) {
//        options.add(Ref.create(newNext));
//        optionDescriptors.add("go to Page:" + newNext.getPageId());
//        newNext.addPrior(this);
//        numOptions = options.size();
//        Chapter c = this.chapter.get();
//        if(c.getOrphans().contains(this)){
//            c.removeOrphan(this);
//            ofy().save().entity(c).now();
//        }
//        // also add the prior reference
//        ofy().save().entity(this).now();
//        ofy().save().entity(newNext).now();
//    }

    /**
     *
     * @param newNext
     * @param myChapter
     */
    public void setNext(Page newNext, Chapter myChapter){
            options.add(Ref.create(newNext));
            optionDescriptors.add("go to Page:" + newNext.getPageId());
            newNext.addPrior(this);
            numOptions = options.size();
            if(myChapter.getOrphans().contains(newNext)){
                System.out.println("removing " + newNext.getPageId() + " from orphans");
                myChapter.removeOrphan(newNext);
                ofy().save().entity(myChapter).now();
                // if it's in an oprhan subtree, it should be okay
            }
            System.out.println("not an orphan");
            // also add the prior reference
            ofy().save().entity(this).now();
            ofy().save().entity(newNext).now();
            ofy().save().entity(myChapter).now();
    }

    /**
     * DEPRECATED DO NOT USE
     * @return
     */
    public ArrayList<Page> getPriors(){
        ArrayList<Page> returner = new ArrayList<Page>();
        for(Ref<Page> p : priors){
            returner.add(p.get());
        }
        return returner;
    }

    /**
     * DEPRECATED DO NOT USE
     * @param newPriors
     */
    public void setPriors(ArrayList<Page> newPriors){
        ArrayList<Ref<Page>> setOpts = new ArrayList<Ref<Page>>();
        for(Page p : newPriors){
            setOpts.add(Ref.create(p));
            ofy().load().entity(p);
        }
        Chapter c = this.chapter.get();
        if(c.isOrphan(this)){
            // remove it as an orphan
            c.removeOrphan(this);
            ofy().save().entity(c).now();
        }
        this.priors = setOpts;
    }

    public Page getPrev() {
        return this.priors.get(0).get();
    }

    /**
     * DEPRECATED DONT USE
     * @param newPrev
     */
    public void setPrev(Page newPrev, Chapter c) {
        // change the reference to the chapter
        //grab it as an orphan,
        //remove it from the list of orphans, because there's a parent now
        if(c.isOrphan(this)){
            // remove it as an orphan
            c.removeOrphan(this);
            ofy().save().entity(c).now();
        }
        this.priors.add(Ref.create(newPrev));
        numPriors = priors.size();
        // set the next
        ofy().save().entity(c).now();
        ofy().save().entity(newPrev).now();
        ofy().save().entity(this).now();
    }

    public ArrayList<Page> getOptions() {
        ArrayList<Page> returner = new ArrayList<Page>();
        for(Ref<Page> p : options){
            returner.add(p.get());
        }
        return returner;
    }

    public void setOptions(ArrayList<Page> newOpts, ArrayList<String> descriptors, Chapter c){
        // have to handle priors for each option
        // have to handle descriptors

        //newopts and descriptors have to be the same length

        // remove  all prior options
        //set all nexts
        for(Ref<Page> option: options){
            // remove all options
            this.unlinkOption(option.get());
        }

        for(int i =0; i<newOpts.size();i++){
            this.setNext( newOpts.get(i), descriptors.get(i), c );
        }
    }

    public void setOptions(ArrayList<Page> newOptions) {
        ArrayList<Ref<Page>> setOpts = new ArrayList<Ref<Page>>();
        for(Page p :newOptions){
            setOpts.add(Ref.create(p));
        }
        this.options = setOpts;
    }

    public ArrayList<String> getOptionDescriptors() {
        return optionDescriptors;
    }

    public void setOptionDescriptors(ArrayList<String> optionDescriptors) {
        this.optionDescriptors = optionDescriptors;
    }

    /**
     * this adds an option and a descriptor
     */
    public void addOption(Page option, String optionDescriptor){
        // gotta check all cases of next
            options.add(Ref.create(option));
            optionDescriptors.add(optionDescriptor);
            numOptions = options.size();
    }

    /**
     * adds an option and a descriptor in the proper way
     * @param option
     * @param myChapter
     * @param optionDescriptor
     */
    public void addOption(Page option, Chapter myChapter, String optionDescriptor){
        this.setNext(option, myChapter);
        optionDescriptors.add(optionDescriptor);
        numOptions = options.size();
    }

    public boolean removeOption(int optionIndex){
        // gotta check all cases
        if(numOptions == 0){
            // there are no options to remove
            return false;
        } else {
            // many options
            options.remove(optionIndex);
            optionDescriptors.remove(optionIndex);
            numOptions = options.size();
            return true;
        }
    }

    public boolean unlinkOption(Page removeMe){
        // this handles links
        Ref<Page> toRemove = Ref.create(removeMe);
        if(numOptions == 0){
            // no pages to remove
            return false;
        } else {
            // many options
            int index = options.indexOf(toRemove);
            optionDescriptors.remove(index);
            if(options.remove(toRemove)){
                removeMe.removePrior(this);
                numOptions = options.size();
                ofy().save().entity(this).now();
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean unlinkPrior(){
        // this handles links
        return false;
    }

    public boolean removeOption(Page removeMe){
        // this does not handle links
        Ref<Page> toRemove = Ref.create(removeMe);
        if(numOptions == 0){
            // no pages to remove
            return false;
        } else {
            // many options
            int index = options.indexOf(toRemove);
            optionDescriptors.remove(index);
            if(options.remove(toRemove)){
                numOptions = options.size();
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * DEPRECATED DO NOT USE
     * @param prior
     */
    public void addPrior(Page prior){
        // gotta check all cases of next
        Chapter c = chapter.get();
        if(c.isOrphan(this)){
            // remove it as an orphan
            c.removeOrphan(this);
            ofy().save().entity(c).now();
        }
            priors.add(Ref.create(prior));
            numPriors = priors.size();
        ofy().save().entity(this).now();
    }

    /**
     * DEPRECATED DO NOT USE
     * @param priorIndex
     * @return
     */
    public boolean removePrior(int priorIndex){
        // gotta check all cases
        if(numPriors == 0){
            // there are no options to remove
            return false;
        } else {
            // many priors
            priors.remove(priorIndex);
            numPriors = priors.size();
            if(numPriors == 0){ //orphaned
                Chapter c = chapter.get();
                c.addOrphan(this);
                ofy().save().entity(c).now();
            }
            return true;
        }
    }

    /**
     * DEPRECATED DO NOT USE
     * @param RemoveMe
     * @return
     */
    public boolean removePrior(Page RemoveMe){
        // check all cases
        Ref<Page> toRemove = Ref.create(RemoveMe);
        if(numPriors == 0){
            return false;
        } else {
            // many priors
            priors.remove(toRemove);
            numPriors = priors.size();
            if(numPriors == 0){
                Chapter c = chapter.get();
                c.addOrphan(this);
                ofy().save().entity(c).now();
            }
            return true;
        }
    }

    public boolean removePrior(Page RemoveMe, Chapter c){
        Ref<Page> toRemove = Ref.create(RemoveMe);
        if(numPriors == 0){
            return false;
        } else {
            // many priors
            priors.remove(toRemove);
            numPriors = priors.size();
            if(numPriors == 0){
                c.addOrphan(this);
                ofy().save().entity(c).now();
            }
            return true;
        }
    }

    /**
     * this is if i wasnt able to give it all 3 major things upon cosntruction
     */
    public void generateId(){
        // based on the chapter, the series, pgno
        Chapter c = chapter.get();
        Series s = series.get();
        this.pageId = c.getName() + s.getName() + pageNumber;
        this.chapterNameSeriesName = c.getName() + s.getName();
    }

    public String getChapterNameSeriesName(){
        return this.chapterNameSeriesName;
    }

    public void setChapterNameSeriesName(String chapterNameSeriesName){
        this.chapterNameSeriesName = chapterNameSeriesName;
    }

    public boolean hasOption(Page p){
        if(options.isEmpty()){
            return false;
        }
        for (Ref<Page> ref: options){
            if(options.contains(p));
            return true;
        }
        return false;
    }

    public boolean contains(Page p){
        // to see if this subtree contains the given page
        if(this.hasOption(p)){
            return true;
        } else {
            for(Ref<Page> ref: options){
                return ref.get().contains(p);
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Page){
            Page p = (Page)o;
            if (p.getPageId().equals(this.pageId)){
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * getParents of
     * @param p
     * @return
     */
    public void getParentsOf(Page p, ArrayList<Page> returner){
        if(this.options.isEmpty()){
            // end of list
            return;
        }
        else if(this.isParentOf(p)){
            if(returner.contains(this)){
                // already visited this node
                return;
            } else {
                // havent visited this node yet
                returner.add(this);
            }
        } else {
            //its not a parent
            // transform the Ref<Pages> to <Pages>
            // search that
            ArrayList<Page> searcher = new ArrayList<Page>();
            for(Ref<Page> ref : options){
                searcher.add(ref.get());
            }
            for(Page q : searcher){
                q.getParentsOf(p, returner);
            }
            return;
        }
    }

    /**
     *
     * @param p
     * @return
     */
    public boolean isParentOf(Page p){
        ArrayList<Page> searcher = new ArrayList<Page>();
        for(Ref<Page> ref : options){
            searcher.add(ref.get());
        }
        if(searcher.contains(p)){
            return true;
        }
        return false;
    }

    /**
     *
     * @param p
     * @param returner
     * @return
     */
    public void getChildrenOf(Page p, ArrayList<Page> returner){
        if(this.options.isEmpty()){
            return;
        }
        else if(this.equals(p)){
           //ArrayList<Page> returner = new ArrayList<Page>();
            for(Ref<Page> ref: options){
                returner.add(ref.get());
            }
            return;
        } else {
            // THIS SHIT AGAIN
            ArrayList<Page> searcher = new ArrayList<Page>();
            for(Ref<Page> ref : options){
                searcher.add(ref.get());
            }
            for(Page q : searcher){
                q.getChildrenOf(p, returner);
            }
        }
        return;
    }

    public void getAllPages(ArrayList<Page> returner){
        //gets all pages in the whatever
        if(this.options.isEmpty()){ // either options or next
            if(!returner.contains(this)) {
                returner.add(this);
            }
            return;
        } else if(returner.contains(this)){
            // this has already been visited, meaning the children were also already visited
            return;
        } else {
            // options are not empty, call this on options after placing itself in
            returner.add(this);
            // THIS SHIT AGAIN
            ArrayList<Page> searcher = new ArrayList<Page>();
            for(Ref<Page> ref : options){
                searcher.add(ref.get());
            }
            for(Page p : searcher){
                p.getAllPages(returner);
            }
            return;
        }
    }
    @Override
    public String toString(){
        return this.pageId;
    }

    public boolean hasChildren(){
        if(this.options.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    public Page find(Page findMe){
        //find is a generic tree search
        if(this.equals(findMe)){
            //this is the node we're looking for!
            return this;
        } else if(this.hasChildren()){
            // the node we're lookign for may be in the children of this node
            // search each subtree
            ArrayList<Page> searcher = this.getOptions();
            for(Page p: searcher){
                Page p2 = p.find(findMe);
                if(p2 != null){
                    return p2;
                }
            }
            return null;
        } else {
            //not found
            return null;
        }
    }

}





