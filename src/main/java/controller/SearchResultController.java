package controller;

import com.googlecode.objectify.cmd.Query;
import data.DbContext;
import data.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class SearchResultController {
    protected DbContext db = new DbContext();

    @RequestMapping(value="search", method = RequestMethod.GET)
    public String searchView(ModelMap model, HttpSession session, HttpServletRequest req){
        System.out.println("Going to search page ");
        String searchInput = req.getParameter("search-input");//.toLowerCase();
        if(searchInput != null){
            searchInput = searchInput.toLowerCase();
        }
        System.out.println("Searching for: " + searchInput);
//        newSearch(searchInput);
        model.addAttribute("query", searchInput);
        return "search";
    }

   @RequestMapping(value="search/refine/{query}", method = RequestMethod.GET)
    public @ResponseBody String querySearch(@PathVariable(value="query") String query) {
        System.out.println(query);
        return query;
    }


    @RequestMapping(value="search/{query}", method = RequestMethod.GET)
    public void searchResultController(@PathVariable(value="query") String query, ModelMap model, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
        System.out.println("refining the search");
        String searchInput = req.getParameter("search-input");//.toLowerCase();
        if(searchInput != null){
            searchInput = searchInput.toLowerCase();
        }
        System.out.println("Search Input:" + searchInput);
        String genre = req.getParameter("search-radio");
        System.out.println("Genre: " + genre);
        String tag = req.getParameter("tag");
        System.out.println("Tag:" + tag);
        String author = req.getParameter("author-input");
        System.out.println("Author: " + author);
        String d = req.getParameter("date-radio");
        System.out.println("update order: " + d);
        String r = req.getParameter("rating-radio");
        System.out.println("rating order: " + r);
        int rating = getRating(r);
        System.out.println("rating: " + rating);
        int date = getDate(d);
        System.out.println("date: " + date);

        //String
        Query<Series> q;
        if(searchInput != null){
            // new search
            q = newSearch(searchInput);
        } else {
            // refine a prior search
            // grab the query... if it's null, just grab everything
            // okay so i can't store queries in the session object
            // ughhhhhhhh
            //q = (Query<Series>)session.getAttribute("priorQuery");
            System.out.println("Prior Query: "+ query);
            q = db.seriesRepo.grabQueryByName(query);
            if(q == null){
                q = db.seriesRepo.getAllSeriesAsAQuery();
            }
            q = refineSearch(q, genre, tag, author, rating, date);

        }
        System.out.println("query returned");
        List<Series> seriesList = q.list();
        System.out.println(seriesList.toString());
        // return things as JSON

        //model.addAttribute("seriesList", s.toString());
        System.out.println("returning Json");
        generateHTML(seriesList, res);
        return; //seriesList.toString();

    }

    public Query<Series> refineSearch(Query<Series> q, String genre, String tag, String author, int rating, int date){
        System.out.println("refining shit");
        System.out.println(q.list().toString());
        if(genre != null){
            if(!genre.equals("all")){
                q = db.seriesRepo.refineQueryByMainGenre(q, genre);
            }
        }
        System.out.println(q.list().toString());
        if(tag != null){
            q = db.seriesRepo.refineQueryByTag(q, tag);
        }
        System.out.println(q.list().toString());
        if(author != null){
            if(!author.equals("")){
                q = db.seriesRepo.refineQueryByAuthorName(q, author);
            }
        }
        System.out.println(q.list().toString());
        if(date != 0){
            if(date == 1){
                // most
                q = db.seriesRepo.refineQueryByLatestUpdate(q);
            } else {
                // least
                q = db.seriesRepo.refineQueryByLeastUpdate(q);
            }
        }
        System.out.println(q.list().toString());
        if(rating != 0){
            q = db.seriesRepo.refineByStarCount(q, rating);
        }
        return q;
    }

    public Query<Series> newSearch(String searchInput){
        // search input is going to be series title
        //ArrayList<Series> returner = new ArrayList<Series>();
        Query<Series> q = null;
        q = db.seriesRepo.grabQueryByName(searchInput);
        if(q == null){
            // if that series doesn't exist, just get all series as a query
            q = db.seriesRepo.getAllSeriesAsAQuery();
        }
        return q;
    }

    public int getRating(String rating){
        if(rating == null){
            return 0;
        } else if(rating.equals("one-stars")){
            return 1;
        } else if(rating.equals("two-stars")){
            return 2;
        } else if(rating.equals("three-stars")){
            return 3;
        } else if(rating.equals("four-stars")){
            return 4;
        } else if(rating.equals("five-stars")){
            return 5;
        } else {
            return 0;
        }
    }

    public int getDate(String date){
        if(date == null){
            return 0;
        } else if(date.equals("most-recent")){
            return 1;
        } else if(date.equals("least recent")){
            return 2;
        } else {
            return 0;
        }
    }
    /*
    <div class="search-result center-block-" id="result-1">
                    <div class="result-image-container">
                        <img class="result-image" src="https://placehold.it/125/ffa500/ffffff">
                    </div>
                    <div class="left-result-container">
                        <div class="result-title">Title: Epic Hero</div>
                        <div class="result-author"> Author: Shakeeb</div>
                        <div class="result-rating"> </div>
                    </div>
                    <div class="result-description-container">
                        <p class="result-description"> An epic hero goes on adventures</p>
                    </div>
                </div>
     */

    //generates the HTML as a string, given a List of Series
    public void generateHTML(List<Series> seriesList, HttpServletResponse response){
        try {
            PrintWriter p = response.getWriter();
            for (Series s : seriesList) {
                p.print("<div class=\"search-result center-block-\" id=\"" + s.getName().replaceAll(" ", "%20") + "\">");

                p.print("<div class=\"result-image-container\">\n" +
                        "                        <img class=\"result-image\" src=\"https://placehold.it/125/ffa500/ffffff\">\n" +
                        "                    </div>");
                p.print(" <div class=\"left-result-container\">\n" +
                        "                        <div class=\"result-title\">");
                p.print("Title: "+s.getName()+ "</div>");
                p.print("<div class=\"result-author\"> Author: " + s.getAuthorName() + "</div>");
                p.print("<div class=\"result-rating\"> </div> </div>");
                p.print("<div class=\"result-description-container\">\n" +
                        "                        <p class=\"result-description\">"+ s.getDescription() +"</p>\n" +
                        "                    </div>");
                p.print("</div>");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
