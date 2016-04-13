package data.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

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
    private int pageNumber;
    private int pageLevel;
    private int numOptions = 0;
    private int numPriors = 0;
    private String imagePath;
    @Load private Ref<Page> Next = null;
    @Load private Ref<Page> Prev = null;
    @Load private ArrayList<Ref<Page>> options = null;
    @Load private ArrayList<Ref<Page>> priors = null;
    private ArrayList<String> optionDescriptors;

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
        this.pageId = mySeries.getName() + theChapter.getName() + pgNo; // id
        this.series = Ref.create(mySeries);
        this.chapter = Ref.create(theChapter);
        this.chapterNameSeriesName = theChapter.getChapterId();
        this.pageLevel = pgLvl;
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
        this.pageId = mySeries.getName() + theChapter.getName() + pgNo;
        this.series = Ref.create(mySeries);
        this.chapter = Ref.create(theChapter);
        this.chapterNameSeriesName = theChapter.getChapterId();
    }

    //GETTER SETTER
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

    public void setNumOptions(int numOptions) {
        this.numOptions = numOptions;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Page getNext() {
        return Next.get();
    }

    public void setNext(Page newNext) {
        if(numOptions == 0){
            numOptions = 1;
            this.Next = Ref.create(newNext);
        } else if (numOptions == 1){ // theres a next but no other options
            // these both become options
            Page oldNext = this.Next.get(); //FUCK this hasnt been stored in the datastore yet!
            this.Next = null;
            options.add(Ref.create(oldNext));
            options.add(Ref.create(newNext));
            numOptions = options.size();
        } else { // there are options, and we're just adding on another option
            options.add(Ref.create(newNext));
            numOptions = options.size();
        }
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
        for(Page p :newPriors){
            setOpts.add(Ref.create(p));
        }
        this.priors = setOpts;
    }

    public Page getPrev() {
        Page previous = Prev.get();
        return previous;
    }

    /**
     * DEPRECATED DONT USE
     * @param newPrev
     */
    public void setPrev(Page newPrev) {
        //this.Prev = Ref.create(previous);
        if(numPriors == 0){
            numPriors = 1;
            this.Prev = Ref.create(newPrev);
        } else if (numPriors == 1){ // theres a prev but no other priors
            // these both become priors
            Page oldPrev = this.Prev.get();
            this.Prev = null;
            priors.add(Ref.create(oldPrev));
            priors.add(Ref.create(newPrev));
            numPriors = priors.size();
        } else { // there are options, and we're just adding on another option
            priors.add(Ref.create(newPrev));
            numOptions = priors.size();
        }
    }

    public ArrayList<Page> getOptions() {
        ArrayList<Page> returner = new ArrayList<Page>();
        for(Ref<Page> p : options){
            returner.add(p.get());
        }
        return returner;
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
        if(numOptions == 0){ // totally new, no next. shouldnt be used this way, but... gotta be exhaustive
            Next = Ref.create(option);
            numOptions = 1;
        } else {
            options.add(Ref.create(option));
            optionDescriptors.add(optionDescriptor);
            numOptions = options.size();
        }
    }

    public boolean removeOption(int optionIndex){
        // gotta check all cases
        if(numOptions == 0){
            // there are no options to remove
            return false;
        } else if (numOptions == 2){
            // two options, just turn one into next
            options.remove(optionIndex);
            optionDescriptors.remove(optionIndex);
            this.Next = options.get(0);
            options = null;
            numOptions = 1;
            return true;
        } else if (numOptions == 1){
            // a single option, meaning it's a next. delete the next
            this.Next = null;
            numOptions = 0;
            return true;
        } else {
            // many options
            options.remove(optionIndex);
            optionDescriptors.remove(optionIndex);
            numOptions = options.size();
            return true;
        }
    }

    public boolean removeOption(Page removeMe){
        Ref<Page> toRemove = Ref.create(removeMe);
        if(numOptions == 0){
            // no pages to remove
            return false;
        } else if(numOptions == 2){
            int index = options.indexOf(toRemove);
            if(options.remove(toRemove)){
                options.remove(toRemove);
                optionDescriptors.remove(index);
                this.Next = options.get(0);
                options = null;
                numOptions = 1;
                return true;
            } else {
                //failure to remove
                return false;
            }
        } else if(numOptions == 1){
            // a single option, meaning it's next
            this.Next = null;
            numOptions = 0;
            return true;
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
        if(numPriors == 0){ // totally new, no prev. shouldnt be used this way, but... gotta be exhaustive
            Prev = Ref.create(prior);
            numPriors = 1;
        } else {
            priors.add(Ref.create(prior));
            numPriors = priors.size();
        }
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
        } else if (numPriors == 2){
            // two options, just turn one into next
            priors.remove(priorIndex);
            this.Prev = priors.get(0);
            priors = null;
            numPriors = 1;
            return true;
        } else if (numPriors == 1){
            // a single option, meaning it's a next. delete the next
            this.Prev = null;
            numPriors = 0;
            return true;
        } else {
            // many priors
            priors.remove(priorIndex);
            numPriors = priors.size();
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
        } else if(numPriors == 2){
            if(priors.remove(toRemove)){
                this.Prev = priors.get(0);
                priors = null;
                numPriors = 1;
                return true;
            } else {
                return false;
            }
        } else if(numPriors == 1){
            if(priors.remove(toRemove)){
                // a single option, meaning it's a next. delete the next
                this.Prev = null;
                numPriors = 0;
                return true;
            } else {
                //failure to remove
                return false;
            }
        } else {
            // many priors
            priors.remove(toRemove);
            numPriors = priors.size();
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
        if(this.options.isEmpty()){
            if(!returner.contains(this)){
                returner.add(this);
            }
            return;
        } else if(returner.contains(this)){
            // this has already been visited, meaning the children were also already visited
            return;
        } else {
            // options are not empty, call this on options after placing itself in
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

}





