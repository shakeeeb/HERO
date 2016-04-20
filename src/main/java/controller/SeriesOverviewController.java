package controller;

import data.DbContext;
import data.model.Chapter;
import data.model.Series;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class SeriesOverviewController {
    DbContext db = new DbContext();

    @RequestMapping(value="series-overview", method = RequestMethod.GET)
    public String seriesOverviewController(ModelMap model) {
        System.out.println("Going to series-overview ");
        return "series-overview";
    }

    @RequestMapping(value="series-overview/{seriesID}", method = RequestMethod.GET)
    public String seriesFromSearch(@PathVariable(value="seriesID")String seriesID, ModelMap model) {
        System.out.println("Going to series-overview ");
        // okay, an ID comes here-- so
        // query the datastore using the ID of the Series, so you can get Series info
        Series s = db.seriesRepo.getById(seriesID);
        String author = s.getAuthorName();
        //series name is ID
        model.addAttribute("AuthorName" , author);
        model.addAttribute("SeriesName", seriesID);
        //System.out.println("");

        // quick! learn how to use ModelAndView
        // or just use an AJAX call right ater, just like the search page
        return "series-overview";
    }

    @RequestMapping(value="series-overview/grab/{seriesID}", method = RequestMethod.GET)
    public void populateChapters(@PathVariable(value="seriesID")String seriesID, ModelMap model, HttpServletRequest request, HttpServletResponse response){

        System.out.println("seriesID-" + seriesID);
        Series s = db.seriesRepo.getById(seriesID); // returns the series
        // if series is null, dwai
        if(s == null){
            System.out.println("ITS NULL NOOOOOOOOOOO");
            return;
        } else { // it's not null! it exists!
            // it might have a few chapters in it
            // if it doesn't have any chapters in it, the last one will be a 'make chapter'
            System.out.println("it does exist");
            ArrayList<Chapter> chapters = s.getChapters();
            System.out.println(chapters);
            System.out.println("getting chapters");
            generateHTML(chapters, response);
        }
        //i will return generated HTML just like last time.
        return;
    }
    /*
        <div id="series-authored-story-1" class="orange-span one-story">
                <span id="authored-story-1-image" class="author-story-wrap">
                    <img src="https://placehold.it/125/ffa500/ffffff" height="122px" width="122px">
                </span>
                <span id="series-overview-story-1-information" class="author-story-wrap">
                    <span id="series-overview-chap-title" class="blue-box series-num-chapters">
                        Series Overview Chapter Title
                    </span>
                    <span>
                    <button id="edit-draft-button" type="submit" class="btn btn-default">Edit Draft</button>
                    </span>
                </span>
    </div>
     */

    public void generateHTML(ArrayList<Chapter> chapters, HttpServletResponse response){
        // will use printwriter to write to the response body, and then recieve that
        // the last 'thing' printed will be 'make next chapter'
        int count=0;
        try {
            PrintWriter p = response.getWriter();
            if(chapters.isEmpty()){
               // just write the base shit
                p.print("<div id=\"series-authored-story-" + count +"\" class=\"orange-span one-story\">");
                p.print("<span id=\"authored-story-"+count+"-image\" class=\"author-story-wrap\">");
                p.print("<img src=\"https://placehold.it/125/ffa500/ffffff\" height=\"122px\" width=\"122px\">");
                p.print("</span>");
                p.print("<span id=\"series-overview-story-" + count + "-information\" class=\"author-story-wrap\">");
                p.print("<span id=\"series-overview-chap-title\" class=\"blue-box series-num-chapters\">");
                p.print("Make a New Chapter!");
                p.print("</span>");
                p.print("<span>\n" +
                        "                    <button id=\"new-chapter-button\" type=\"button\" class=\"btn btn-default\">Create!</button>\n" +
                        "                    </span>");
                p.print("</span>");
                p.print("</div>");
                return;
            } else {
                // write all the shit
                for(Chapter c : chapters){
                    p.print("<div id=\"series-authored-story-" + count +"\" class=\"orange-span one-story\">");
                    p.print("<span id=\"authored-story-" +count+ "-image\" class=\"author-story-wrap\">\n" +
                            "                    <img src=\"https://placehold.it/125/ffa500/ffffff\" height=\"122px\" width=\"122px\">\n" +
                            "                </span>");
                    p.print("<span id=\"series-overview-story-" + count + "-information\" class=\"author-story-wrap\">");
                    p.print("<span id=\"series-overview-"+ c.getName() +  "\" class=\"blue-box series-num-chapters\">\n" +
                            "                        "+ c.getName()+"\n" +
                            "                    </span>");
                    // edit draft is only on the button if it's not a published chapter yet
                    if(!c.isPublished()){
                        p.print("<span>\n" +
                                "                    <button id=\"edit-draft-button-" + count + "\" type=\"submit\" class=\"btn btn-default\">Edit Draft</button>\n" +
                                "                    </span>");
                    }
                    p.print("</span>");
                    p.print("</div>");
                    count++;
                } // after all the chapters of the series are done being printed out
                // print out the 'make new chapter!' shit
                p.print("<div id=\"series-authored-story-" + count +"\" class=\"orange-span one-story\">");
                p.print("<span id=\"authored-story-"+count+"-image\" class=\"author-story-wrap\">");
                p.print("<img src=\"https://placehold.it/125/ffa500/ffffff\" height=\"122px\" width=\"122px\">");
                p.print("</span>");
                p.print("<span id=\"series-overview-story-" + count + "-information\" class=\"author-story-wrap\">");
                p.print("<span id=\"series-overview-chap-title\" class=\"blue-box series-num-chapters\">");
                p.print("Make a New Chapter!");
                p.print("</span>");
                p.print("<span>\n" +
                        "                    <button id=\"new-chapter-button\" type=\"button\" class=\"btn btn-default\">Create!</button>\n" +
                        "                    </span>");
                p.print("</span>");
                p.print("</div>");
                return;
            }
            //

        } catch(IOException e){
            e.printStackTrace();
        }

    }

}
