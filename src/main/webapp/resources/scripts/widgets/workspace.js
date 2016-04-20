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
            //$("#page-1").text(pages[0].pageId);
            //$("#page-2").text(pages[1].pageId);
            //$("#page-3").text(pages[2].pageId);

            // recursivly get pages for a level
            // load level into table row
            // validate tree


            // swapping because root gets added to the end

            insertPages(pages);

        });
    }

    //when clicked, get the text and send it back to the controller
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


    // save

    function insertPages(pages) {
        pagesToAdd = pages;
        addPage(pagesToAdd[0].pageId,1);
        //console.log(pagesToAdd[1].options.length);
        insertOptions(pagesToAdd[0], pages, 2);
        //addPage(pagesToAdd[1].pageId, 2);
        //addPage(pagesToAdd[2].pageId, 2);
        addPage(pagesToAdd[3].pageId, 3);

    }

    function insertOptions(root, pages, level) {
        //console.log(root.options[0].key.raw.name);
       //console.log(root.options);
        console.log(root.options.length);
        if(root.options.length < 1) {
            return;
        }

        for(var i = 0; i < root.options.length; i++) {
            //console.log(root.options[i]);
            addPage(getPage(root.options[i].key.raw.name,pages).pageId, level);
            //insertOptions(getPage(root.options[i].key.raw.name,pages), pages, level+1);
             console.log(getPage(root.options[i].key.raw.name,pages).pageId);
        }

       // insertOptions(getPage(root.options[1].key.raw.name,pages), pages, level+1);

        return;


    }

    function getPage(pageId, pages) {

        for(var i = 0; i < pages.length; i++) {
            if(pages[0].pageId = pageId) {
                return pages[0];
            }
        }
        return null;
    }

    // ADDS A PAGE TO A ROW, PLEASE THE ROW INDEX STARTS FROM 0, PLEASE EXCUSE THE MESS
    function addPage(pageID, row) {
        var rowToEdit = getRow(row);
        var pageToEdit = null;
        if(rowToEdit == null) {
         console.log("Row doesn't exist");
        } else {
        // check if center page taken
        if(isUnused(rowToEdit[2]) == true) {
            pageToEdit = rowToEdit[2];
        // check if second page is taken
        } else if(isUnused(rowToEdit[1]) == true) {
            pageToEdit =rowToEdit[1];
        // check if fourth page is taken
        } else if(isUnused(rowToEdit[3]) == true) {
            pageToEdit =rowToEdit[3];
        // check if first page is taken
        } else if(isUnused(rowToEdit[0]) == true) {
            pageToEdit =rowToEdit[0];
        // check if fifth page is taken
        } else if(isUnused(rowToEdit[4]) == true) {
            pageToEdit =rowToEdit[4];
        } else {
            console.log("Row is full");
        }

        if(pageToEdit == null ) {

        } else
            setPage(pageToEdit.getElementsByClassName("chapter-page")[0], pageID)

        }
    }

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


    // checks if a page in a row is currently being used
    function isUnused(page) {
        if(page.getElementsByClassName("hidden-page").length > 0) {
            return true;
        } else {
            return false;
        }
    }

    // check if a row exists
    function getRow(row) {
        if($("#chapter-row-"+row).length < 1) {
            return null;
        } else {
            // TODO check if row size is max width
            return $("#chapter-row-"+ row).children();

        }


    }
        // get level (row)
        // create page in Datastore (ajax request)
        $(".add-page").click(function(){
            alert(console.log($(this)));
        });
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

