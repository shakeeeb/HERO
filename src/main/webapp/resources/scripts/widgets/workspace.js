/*
 * chapter-workspace widget template
 * represented using an HTML table
 * svg arrows for page options
 * Created by Terrell
 */

$(document).ready(function() {
    var MAX_DEPTH = 5;
    var chapter = null;
    var pages = null;

    loadChapter("My Best Friend Gleb~Day One: The Dream of Gleb");

    // loads a chapter from the datastore
    function loadChapter(chapterID) {

        $.getJSON("/workspace/load/" + chapterID , function(data) {
        }).done(function (data) {
          chapter = data.Chapter;
          pages = data.Pages;

          console.log(chapter);
            $("#title-input").val(chapter.name);
            console.log(pages);


            // load root
            $("#page-1").text(pages[0].pageId);
            $("#page-2").text(pages[1].pageId);
            $("#page-3").text(pages[2].pageId);

            // recursivly get pages for a level
            // load level into table row
            // validate tree
        });



    }

    // save

    // add page
        // get level (row)
        // create page in Datastore (ajax request)
        // add page to chapter in Datstore (ajax request)
        // add new page element to level
        // validate tree

    // delete page
        // get page ID
        // remove page from Datastore (ajax request)
        // remove page from chapter (ajax request)
        // remove page element from level
        // validate tree

    // validate tree
        // validate each level
        // loop -> vaildateLevel
        // validate bottom

    // validate bottom
        // if bottom level has an element
        // add new row to bottom (this allows users to always create a longer story)

    // check if add button is valid
        //validateLevel(level l)
        // if five rows in level remove add button
        // else if add button is hidden show it

    // page
        // on hover
        // display delete option
        // display edit option

        // on click?
        // load draw page

    // draw option (using svg)
    // TODO: check if svg draws arrow staticlly or dynamicaly
    // if static, must redraw after adding and deleting pages
        // get from page
        // get to page
        // draw arrow

});

