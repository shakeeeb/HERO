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
    var cID = null;
    var loadThisChapterID = $("#hidden-chapterID").html();

    // test chapter being used for development, will be replaced with grabbing the id from the backend
    loadChapter(loadThisChapterID);

    /**
     * Loads a chaoter from the datastore into the story tree
     * @param chapterID: the ID of the chapter from the datastore
     */
    function loadChapter(chapterID) {

        $.getJSON("/workspace/load/" + chapterID , function(data) {
        }).done(function (data) {
            cID = chapterID;
            chapter = data.Chapter;
            pages = data.Pages;
            $("#title-input").val(chapter.name);
            $("#summary-input").val(chapter.summary);

            console.log(chapter);
            console.log(pages);

            loadTree(chapter.root, pages, 0);
            validateBottomRow();


        });
    }

    function addLevel() {
        $.get("/resources/layouts/chapterlevel.html", function(data) {
            $("#page-table").append(data);
            console.log($("#page-table"));
            $('#chapter-level-X').attr("id", "chapter-level-" + (getDepth()-1));

        });
    }

/***********************************************************************************************************************
 * A) HANDLERS: Things that handle user interaction
 **********************************************************************************************************************/

    //when clicked, get the page to be edited and send it to the backend
    $(".chapter-page").click(function() {

        var pageID = this.getTextContent;

        //$.post( "/get-chapter-page", {"data":pageID})
        //    .done(function() {
        //        console.log("Sending the data back to the servlet");
        //    })
        //    .fail(function() {
        //        console.log("Cannot send the data back to the servlet");
        //    });
    });

    $(document).on("click", ".chapter-page", function() {
       if($(this).hasClass("add-page")) {
           return;
       }
        // get the page ID
       var pageID = chapter.chapterId + '^' +  $(this).attr('id').split('-')[1];

        // load drawing page
        window.location.replace("/draw/"+pageID);

    });


    $(document).on("click", "#add-modal-save-button", function() {
        $('#myModal').modal('hide');
    });
    /**
     * Adds a new drawing page to the level of a story
     * Using .on instead of .click because of problem with generating levels dynamically
     */
    $(document).on("click", ".add-page", function() {

       // $('#myModal').modal('show');

        var chapterRow = $(this).parent().parent()[0].getAttribute("id");
        var rowID = chapterRow.split('-')[2];

        console.log(getPageCount(rowID));

        if(getPageCount(rowID) > 5) {

        }
        else if(getPageCount(rowID) == 5) {
            $(this).removeClass('add-page').addClass('chapter-page').addClass('hidden-page').text('');
            addPage('PAGE-ID', rowID);
        } else {
            addPage('PAGE-ID', rowID);
        }
        validateBottomRow();

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


    /**
     * Adds a page to a the story tree
     * @param pageID: The page being added
     * @param level: the level to add the page to
     * note: the page gets added to the level in a order, from the center outward
     */
    function addPage(pageID, level) {
        var levelToEdit = getLevel(level);
        var pageToEdit = null;
        console.log("adding page to Level: "+ levelToEdit);
        if(levelToEdit == null) {
            console.log("Level doesn't exist");
        } else {
            // check if center page taken
            if(isUnused(levelToEdit[2]) == true) {
                pageToEdit = levelToEdit[2];
                // check if second page is taken

            } else if(isUnused(levelToEdit[1]) == true) {
                pageToEdit = levelToEdit[1];
                // check if fourth page is taken
            } else if(isUnused(levelToEdit[3]) == true) {
                pageToEdit = levelToEdit[3];
                // check if first page is taken
            } else if(isUnused(levelToEdit[0]) == true) {
                pageToEdit = levelToEdit[0];
                // check if fifth page is taken
            } else if((isUnused(levelToEdit[4]) == true)||(isAddPage(levelToEdit[4]) == true)) {
                pageToEdit = levelToEdit[4];
            } else {
                console.log("Level is full");
            }

            if(pageToEdit == null) {
                console.log("pageToEdit is null");
            } else
                setPage(pageToEdit.getElementsByClassName("chapter-page")[0], pageID, level)


        }
    }


    /**
     * Sets a page from the datastore to a page on the story tree
     * @param page: the page on the story tree being set
     * @param pageID: the id of the datastore page
     */
    function setPage(page, pageID, level) {
        if(page == null) {
            return;
        }
        //var formatedPageID = pageID.replace(/ /g, "%20");
        //var formatedPageID = encodeURI(pageID);
        //var formatedPageID = pageID;
        //var idNumber = pageID.split('^')[1];
        page.setAttribute("id", 'page-' + pageIDtoNumberID(pageID));
        var datastorePage = getPage(pageID,pages);
        var formatedPageID = encodeURI(pageID);
        //page.setAttribute("id", formatedPageID);
        var datastorePage = $.getJSON("make-chapter-page/" + cID ,{level: level} ,function(data) {
        })
            .done(function(data){
                console.log(data);
                console.log("success");
            }).fail(function(data){
                console.log("failure");
            });
        /*getPage(pageID,pages)*/
        //console.log(datastorePage);

        // TODO: do this using jquery, selector wasnt working
        page.className = "";
        page.className = "chapter-page";
        if(datastorePage != null) {
            page.style.backgroundImage = "url(\'" + datastorePage.imagePath +"\')";
            page.style.backgroundSize = "cover";
            page.style.backgroundRepeat = "no-repeat";

        }

    }

    function pageIDtoNumberID(pageID) {
        return  idNumber = pageID.split('^')[1];
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
        //if(level == null) {
        //    console.log("Level doesn't exist");
        //    return -1;
        //} else {
        //    console.log(level);
        //    for(var i = 0; i < level.length; i++) {
        //        if(isUnused(level[i])) {
        //        pageCount++;
        //            }
        //    }
        //    console.log("Page count: " + pageCount);
        //
        //    return pageCount;
            // check if center page taken
        if(level == null) {
         return;
        }
            if(isUnused(level[2]) == false) {
                pageCount++;
                // check if second page is taken
            }
            if(isUnused(level[1]) == false) {
                pageCount++;
                // check if fourth page is taken
            }
            if(isUnused(level[3]) == false) {
                pageCount++;
                // check if first page is taken
            }
            if(isUnused(level[0]) == false) {
                pageCount++;
                // check if fifth page is taken
            }
            if(isUnused(level[4]) == false) {
                pageCount++;
            }
            return pageCount;
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
    function isAddPage(page) {
        return page.getElementsByClassName("add-page").length > 0;

    }

    /**
     * Validates the bottom of the story tree
     * note: If the bottom level has pages, this appends a new empty level allowing the user to
     * continually add to their story
     */
    validateBottomRow();
    function validateBottomRow() {
        var pageCountForBottomLevel = getPageCount($(".chapter-level").length -1 );
        if(pageCountForBottomLevel > 1) {
            addRow();

        }

    }

    /**
     * Adds a new level to the story tree
     * TODO: figure out why onpage add click doesn't work for rows added
     */
    function addRow() {
        $('#page-table > tbody:last-child').append('<tr id=\"chapter-level-'+ ($(".chapter-level").length) + '\" class=\"chapter-level\">' +
            '<td><button class=\"chapter-page hidden-page\" type=\"button\"></button></td>' +
            '<td><button class=\"chapter-page hidden-page\" type=\"button\"></button></td>' +
            '<td><button class=\"chapter-page hidden-page\" type=\"button\"></button></td>' +
            '<td><button class=\"chapter-page hidden-page\" type=\"button\"></button></td>' +
            '<td><button class=\"chapter-page add-page\" type=\"button\">new</button></td>' +
            '</tr>');


       //  $('#page-table > tbody:last-child').append('<tr>test</tr>');     // this ANOTHER POSSIBLE WAY TO GRAB A ROW
        //  $('#page-table tr:last').after
    }
// adds path to connect two pages
    function addConnector(fromPageID, toPageID) {
        //connectElements($("#svg1"), $("#path1"), $('#page-0'),  $("#page-1"));
        $('#svg1').append('<path id=\"'+ fromPageID +'-'+ toPageID + '-connector\"' +
        'd=\"M0 0\"' +
        'stroke=\"#000\"' +
        'fill=\"none\"' +
        'stroke-width=\"6px \" ;>' );

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
       console.log("chapter-level-"+(level-1));
        console.log($("#chapter-level-"+(level-1)));
        if($("#chapter-level-"+(level)).length < 1) {
            return null;
        } else {
            // TODO check if row size is max width
            return $("#chapter-level-"+ level).children();
        }
    }

    function getDepth() {
       return  $('#page-table tr').length;

    }



    // recursively  loads story tree
    function loadTree(rootID, allPages, level) {
        // check if root is null
        if(rootID == null) {
            console.log("Root is null, Level: " + level);
            return;
        } else {

            // add root to tree
            var root = getPage(getPageID(rootID), allPages);
            if(getLevel(level) == null) {
                addRow();
            }
            addPage(root.pageId, level);
            // add each option to the level before
            for (var i = 0; i < root.options.length; i++) {
                // console.log(i);
                // option = getPage(getPageID(root.options[i]), allPages);

                console.log(root.options[i]);


                    //insertOptions(getPage(root.options[i].key.raw.name,pages), pages, level+1);
                loadTree(root.options[i], allPages,level+1);
                var rootPageNumber = pageIDtoNumberID(root.pageId);
                 var childPageNumber = pageIDtoNumberID(getPage(getPageID(root.options[i]), allPages).pageId);

                //addConnector(pageIDtoNumberID(root.pageId),pageIDtoNumberID(getPage(getPageID(root.options[i]), allPages).pageId));
                //connectElements($("#svg1"), $(pageIDtoNumberID(root.pageId) + '-' + pageIDtoNumberID(getPage(getPageID(root.options[i]), allPages).pageId) + '-connector'),  $('#page-' + (pageIDtoNumberID(root.pageId))),  $("#page-" +  pageIDtoNumberID(getPage(getPageID(root.options[i]), allPages).pageId)));
                //console.log(rootPageNumber);


               //ƒ addConnector(rootPageNumber, childPageNumber);
                connectElements($("#svg1"), $('#'+ rootPageNumber+'-'+ childPageNumber + '-' + 'connector'), $('#page-'+rootPageNumber),  $("#page-"+ childPageNumber));
                //
                //    //insertOptions(getPage(root.options[i].key.raw.name,pages), pages, level+1);
            }


        return;
        }
    }


    function getPageID(page) {
       return page.key.raw.name;
    }


    $(document).on("click", "#save-chapter-button", function() {
        $.post( "/workspace/update", {chapterID: chapter.chapterId, summary: $('#summary-input').val() } );
    });


/***********************************************************************************************************************
 * LINE DRAWING TOOL LIBRARY
 **********************************************************************************************************************/
    //helper functions, it turned out chrome doesn't support Math.sgn()
    function signum(x) {
        return (x < 0) ? -1 : 1;
    }
    function absolute(x) {
        return (x < 0) ? -x : x;
    }

    function drawPath(svg, path, startX, startY, endX, endY) {
        // get the path's stroke width (if one wanted to be  really precize, one could use half the stroke size)
        var stroke =  parseFloat(path.attr("stroke-width"));
        // check if the svg is big enough to draw the path, if not, set heigh/width
        if (svg.attr("height") <  endY)                 svg.attr("height", endY);
        if (svg.attr("width" ) < (startX + stroke) )    svg.attr("width", (startX + stroke));
        if (svg.attr("width" ) < (endX   + stroke) )    svg.attr("width", (endX   + stroke));

        var deltaX = (endX - startX) * 0.15;
        var deltaY = (endY - startY) * 0.15;
        // for further calculations which ever is the shortest distance
        var delta  =  deltaY < absolute(deltaX) ? deltaY : absolute(deltaX);

        // set sweep-flag (counter/clock-wise)
        // if start element is closer to the left edge,
        // draw the first arc counter-clockwise, and the second one clock-wise
        var arc1 = 0; var arc2 = 1;
        if (startX > endX) {
            arc1 = 1;
            arc2 = 0;
        }
        // draw tha pipe-like path
        // 1. move a bit down, 2. arch,  3. move a bit to the right, 4.arch, 5. move down to the end
        path.attr("d",  "M"  + startX + " " + startY +
            " V" + (startY + delta) +
            " A" + delta + " " +  delta + " 0 0 " + arc1 + " " + (startX + delta*signum(deltaX)) + " " + (startY + 2*delta) +
            " H" + (endX - delta*signum(deltaX)) +
            " A" + delta + " " +  delta + " 0 0 " + arc2 + " " + endX + " " + (startY + 3*delta) +
            " V" + endY );
    }

    function connectElements(svg, path, startElem, endElem) {
        var svgContainer= $("#svgContainer");

        // if first element is lower than the second, swap!
        if(startElem.offset().top > endElem.offset().top){
            var temp = startElem;
            startElem = endElem;
            endElem = temp;
        }

        // get (top, left) corner coordinates of the svg container
        var svgTop  = svgContainer.offset().top;
        var svgLeft = svgContainer.offset().left;

        // get (top, left) coordinates for the two elements
        var startCoord = startElem.offset();
        var endCoord   = endElem.offset();

        // calculate path's start (x,y)  coords
        // we want the x coordinate to visually result in the element's mid point
        var startX = startCoord.left + 0.5*startElem.outerWidth() - svgLeft;    // x = left offset + 0.5*width - svg's left offset
        var startY = startCoord.top  + startElem.outerHeight() - svgTop;        // y = top offset + height - svg's top offset

        // calculate path's end (x,y) coords
        var endX = endCoord.left + 0.5*endElem.outerWidth() - svgLeft;
        var endY = endCoord.top  - svgTop;

        // call function for drawing the path
        drawPath(svg, path, startX, startY, endX, endY);

    }



    function connectAll() {
        // connect all the paths you want!


    connectElements($("#svg1"), $("#0-1-connector"), $('#page-0'),  $("#page-1"));
        connectElements($("#svg1"), $("#1-2-connector"), $('#page-1'),  $("#page-2"));
        connectElements($("#svg1"), $("#1-3-connector"), $('#page-1'),  $("#page-3"));
        //connectElements($("#svg1"), $("#path2"), $("#red"),    $("#orange"));
        //connectElements($("#svg1"), $("#path3"), $("#teal"),   $("#aqua")  );
        //connectElements($("#svg1"), $("#path4"), $("#red"),    $("#aqua")  );
        //connectElements($("#svg1"), $("#path5"), $("#purple"), $("#teal")  );
        //connectElements($("#svg1"), $("#path6"), $("#orange"), $("#green") );

    }

    //$(document).ready(function() {
    //    // reset svg each time
    //    $("#svg1").attr("height", "0");
    //    $("#svg1").attr("width", "0");
    //    connectAll();
    //});
    //
    $(window).resize(function () {
        // reset svg each time
        $("#svg1").attr("height", "0");
        $("#svg1").attr("width", "0");
        connectAll();
    });

});