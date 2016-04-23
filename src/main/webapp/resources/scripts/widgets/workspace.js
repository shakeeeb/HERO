/*
 * chapter-workspace widget template
 * represented using an HTML table
 * svg arrows for page options
 * Created by Terrell
 */

$(document).ready(function() {

    var MAX_WIDTH = 5;
    var chapter = null;
    var pages = null;

    // test chapter being used for development, will be replaced with grabbing the id from the backend
    loadChapter("One_Piece~Luffy_meets_Boa");

    /**
     * Loads a chaoter from the datastore into the story tree
     * @param chapterID: the ID of the chapter from the datastore
     */
    function loadChapter(chapterID) {

        $.getJSON("/workspace/load/" + chapterID , function(data) {
        }).done(function (data) {
            chapter = data.Chapter;
            pages = data.Pages;

            console.log(chapter);
            console.log(pages);

           // console.log(factorial(6));
            loadTree(chapter.root, pages, 1);
            //insertPages(pages);

        });
    }

/***********************************************************************************************************************
 * A) HANDLERS: Things that handle user interaction
 **********************************************************************************************************************/

    //when clicked, get the page to be edited and send it to the backend
    $(".chapter-page").click(function() {

        var pageID = this.getTextContent;

        $.post( "/get-chapter-page", {"data":pageID})
            .done(function() {
                console.log("Sending the data back to the servlet");
            })
            .fail(function() {
                console.log("Cannot send the data back to the servlet");
            });
    });

    /**
     * Adds a new drawing page to the level of a story
     */
    $(".add-page").click(function(){
        var chapterRow = $(this).parent().parent()[0].getAttribute("id");
        var rowID =chapterRow.split('-')[2];
        console.log(getPageCount(rowID));

        if(getPageCount(rowID) > 5) {

        }
        else if(getPageCount(rowID) == 5) {
            $(this).removeClass('add-page').addClass('chapter-page').addClass('hidden-page').text('');
            addPage('PAGE-ID', rowID);
        } else {
            addPage('PAGE-ID', rowID);
        }

        // add page to chapter in Datstore (ajax request)

        // add new page element to level
        //addPage('PAGEID', rowNumber);

        // validate tree

    });


/***********************************************************************************************************************
* B) ESSENTIALS: Things used by Handlers
**********************************************************************************************************************/

    /**
     * Adds pages of a chapter to the story tree
     * @param pages
     */
    function insertPages(pages) {
        pagesToAdd = pages;
        addPage(pagesToAdd[0].pageId,1);
        //console.log(pagesToAdd[1].options.length);
        insertOptions(pagesToAdd[0], pages, 2);
        //addPage(pagesToAdd[1].pageId, 2);
        //addPage(pagesToAdd[2].pageId, 2);
        addPage(pagesToAdd[3].pageId, 3);

    }

    /**
     * Recursivly adds chapter to the story tree
     * @param root: the root of the story tree
     * @param pages: all pages of the chapter
     * @param level: the level to add pages to
     */
    function insertOptions(root, pages, level) {
        //console.log(root.options[0].key.raw.name);
        //console.log(root.options);
        console.log(root.options.length);
        if(root.options.length < 1) {
            return;
        }
        //
        //for(var i = 0; i < root.options.length; i++) {
        //    //console.log(root.options[i]);
        //    addPage(getPage(root.options[i].key.raw.name,pages).pageId, level);
        //    //insertOptions(getPage(root.options[i].key.raw.name,pages), pages, level+1);
        //    console.log(getPage(root.options[i].key.raw.name,pages).pageId);
        //}
        console.log(root.options[1]);
        console.log("Root option length " + root.options.length);


    return ;
      //  return insertOptions(getPage(root.options[1].key.raw.name,pages), pages, level+1);



    }



    // ADDS A PAGE TO A ROW, PLEASE THE ROW INDEX STARTS FROM 0, PLEASE EXCUSE THE MESS
    /**
     * Adds a page to a the story tree
     * @param pageID: The page being added
     * @param level: the level to add the page to
     * note: the page gets added to the level in a order, from the center outward
     */
    function addPage(pageID, level) {
        var levelToEdit = getLevel(level);
        var pageToEdit = null;
        console.log("adding page to Row: "+ levelToEdit);
        if(levelToEdit == null) {
            console.log("Row doesn't exist");
        } else {
            // check if center page taken
            if(isUnused(levelToEdit[2]) == true) {
                pageToEdit = levelToEdit[2];
                // check if second page is taken
            } else if(isUnused(levelToEdit[1]) == true) {
                pageToEdit =levelToEdit[1];
                // check if fourth page is taken
            } else if(isUnused(levelToEdit[3]) == true) {
                pageToEdit =levelToEdit[3];
                // check if first page is taken
            } else if(isUnused(levelToEdit[0]) == true) {
                pageToEdit =levelToEdit[0];
                // check if fifth page is taken
            } else if(isUnused(levelToEdit[4]) == true) {
                pageToEdit =levelToEdit[4];
            } else {
                console.log("Level is full");
            }

            if(pageToEdit == null ) {
                console.log("pageToEdit is null");
            } else
                setPage(pageToEdit.getElementsByClassName("chapter-page")[0], pageID)

        }
    }


    /**
     * Sets a page from the datastore to a page on the story tree
     * @param page: the page on the story tree being set
     * @param pageID: the id of the datastore page
     */
    function setPage(page, pageID) {
        if(page == null) {
            return;
        }
        var formatedPageID = pageID.replace(/ /g, "%20");

        page.setAttribute("id", formatedPageID);
        // TODO: do this using jquery, selector wasnt working
        page.className = "";
        page.className = "chapter-page";

    }

    /**
     * Gets the number of pages being used in a story tree level
     * @param levelNumber: the level of the story tree being checked
     * @returns
     * -1: if the level doesn't exist
     * The number of pages on success
     */
    function getPageCount(levelNumber) {
        var pageCount = 0;
        var level = getLevel(levelNumber);
        if(level == null) {
            console.log("Level doesn't exist");
            return -1;
        } else {

            for(var i = 0; i < level.length; i++) {
                if(isUnused(level[i])) {
                pageCount++;
                    }
            }
            console.log("Page count: " + pageCount);

            return pageCount;
            //// check if center page taken
            //if(isUnused(level[2]) == false) {
            //    pageCount++;
            //    // check if second page is taken
            //}
            //if(isUnused(level[1]) == false) {
            //    pageCount++;
            //    // check if fourth page is taken
            //}
            //if(isUnused(level[3]) == false) {
            //    pageCount++;
            //    // check if first page is taken
            //}
            //if(isUnused(level[0]) == false) {
            //    pageCount++;
            //    // check if fifth page is taken
            //}
            //if(isUnused(level[4]) == false) {
            //    pageCount++;
            //}
            //return pageCount;
        }
    }

    /**
     * Checks if a drawing page is currently by a level
     * @param page: the page being checked for
     * @returns
     *  true: if the page is not being used
     *  false: if the page is being used
     */
    function isUnused(page) {
        return page.getElementsByClassName("hidden-page").length > 0;
    }

    /**
     * Validates the bottom of the story tree
     * note: If the bottom level has pages, this appends a new empty level allowing the user to
     * continually add to their story
     */
    validateBottomRow();
    function validateBottomRow() {
        var pageCountForBottomLevel = getPageCount($(".chapter-level").length);
        if(pageCountForBottomLevel > 0) {

        }

    }

    /**
     * Adds a new level to the story tree
     * TODO: figure out why onpage add click doesn't work for rows added
     */
    function addRow() {
        $('#page-table > tbody:last-child').append('<tr id=\"chapter-row-'+ ($(".chapter-level").length+1) + '\" class=\"chapter-level\">' +
            '<td><button class=\"chapter-page hidden-page\" type=\"button\"></button></td>' +
            '<td><button class=\"chapter-page hidden-page\" type=\"button\"></button></td>' +
            '<td><button class=\"chapter-page hidden-page\" type=\"button\"></button></td>' +
            '<td><button class=\"chapter-page hidden-page\" type=\"button\"></button></td>' +
            '<td><button class=\"add-page\" type=\"button\">new</button></td>' +
            '</tr>');


       //  $('#page-table > tbody:last-child').append('<tr>test</tr>');     // this ANOTHER POSSIBLE WAY TO GRAB A ROW
        //  $('#page-table tr:last').after
    }


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


/***********************************************************************************************************************
 * HELPER FUNCTIONS
 **********************************************************************************************************************/

    // gets a page from a list of pages
    function getPage(pageId, pages) {

        for(var i = 0; i < pages.length; i++) {
            if(pages[i].pageId === pageId) {

                return pages[i];
            }
        }
        return null;
    }

    /**
     * Gets a level of the story tree
     * @param level: the level of the story tree being checked for, starting at 1
     * @returns
     *  On Success: the story level
     *  On Failure: null
     */
    function getLevel(level) {
        // checks if the level exists
        if($("#chapter-row-"+level).length < 1) {
            return null;
        } else {
            // TODO check if row size is max width
            return $("#chapter-row-"+ level).children();
        }
    }



    // recursively  loads story tree
    function loadTree(rootID, allPages, level) {
        // check if root is null
        if(rootID == null) {
            //console.log("Root is null, Level: " + level);
            return;
        } else {

            // add root to tree
            var root = getPage(getPageID(rootID), allPages);
            addPage(root.pageId, level);
            // add each option to the level before
            for (var i = 0; i < root.options.length; i++) {
                 //console.log(i);
                // option = getPage(getPageID(root.options[i]), allPages);

                //console.log("The options are: " + root.options[i]);
                //console.log(root.options[i]);


                    //insertOptions(getPage(root.options[i].key.raw.name,pages), pages, level+1);
                loadTree(root.options[i], allPages,level+1);
            }


        return;
        }
    }

    var factorial = function(number) {
        if (number <= 0) { // terminal case
            return 1;
        } else { // block to execute
            return (number * factorial(number - 1));
        }
    };

    function getPageID(page) {
       return page.key.raw.name;
    }

});