package controller;

import com.google.appengine.repackaged.com.google.gson.JsonObject;
import data.DbContext;
import data.model.Chapter;
import data.model.Series;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Ben on 3/31/16.
 */

@Controller
public class LastReadController {

    protected DbContext db = new DbContext();

    @RequestMapping(value="last-page", method = RequestMethod.GET)
    public String testLastReadController(ModelMap model) {
        System.out.println("Going to last-page");
        return "last-page";
    }

    @RequestMapping(value="last-page/{chapter-ID}/{page-ID}", method = RequestMethod.GET)
    public String getLastPage(@PathVariable(value = "chapter-ID") String chapterID, @PathVariable(value = "page-ID") String pageID, ModelMap model, HttpSession session, HttpServletRequest req) {
        System.out.println("Going to last-page");


        Chapter c = db.chapterRepo.getById(chapterID);
        String rootID = c.getRoot().getPageId();
        Series s = c.getSeries();
        String seriesID = s.getName();
        ArrayList<Chapter> chapterList = s.getChapters();
        int chapterNumber = -1;
        int nextChapterNumber = -1;

        for (int i = 0; i < chapterList.size() - 1; i++) {
            if (c == chapterList.get(i)) {
                chapterNumber = i;
            }
        }

        nextChapterNumber = chapterNumber + 1;
        String nextChapterID = chapterList.get(nextChapterNumber).getChapterId();
        String nextPageID = chapterList.get(nextChapterNumber).getRoot().getPageId();

        String chapterName = c.getName();
        model.addAttribute("chapterName", chapterName);
        model.addAttribute("nextChapterID", nextChapterID);
        model.addAttribute("nextPageID", nextPageID);
        model.addAttribute("rootID", rootID);
        model.addAttribute("seriesID", seriesID);
        model.addAttribute("chapterID", chapterID);

        return "last-page";
    }
}