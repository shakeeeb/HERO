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
    var tree = [];
    var pageIds = [];
    var orphans = [];

    // these are used for linking pages
    var fromPageID = null;
    var toPageID = null;
    var linkingMode = false;
    var pageToLink= null;


    var chapterID = $("#hidden-chapterID").html();
    // test chapter being used for development, will be replaced with grabbing the id from the backend
    loadChapter(chapterID);
    //loadChapter("One_Piece~Luffy_meets_Boa");

    /**
     * Loads a chapter from the datastore into the story tree
     * @param chapterID: the ID of the chapter from the datastore
     */
    function loadChapter(chapterID) {

        $.getJSON("/workspace/load/" + chapterID , function(data) {
        }).done(function (data) {
            cID = chapterID;
            chapter = data.Chapter;
            pages = data.Pages;
            $("#title-input").val(chapter.name);
            $("#summary-input").val(chapter.description);

            console.log(chapter);
            console.log(pages);

            initPageIds(pages);


            //var i;
            //for(i = 0;pages.length;i++){
            //    var newId = pages[i].pageId;
            //    pageIds.push(newId);
            //}


            loadTree(chapter.root, pages, 0);
            //alert("Loaded");
            loadOrphans();


            //after this is done
            //>>console.log("printing the tree: " + tree);
            // after loading tree from root, load orphans
            validateBottomRow();
            //>>placeOrphans();


        });
    }


    function clearChapter() {

        $('#page-table tr:not(:first)').remove();
        tree = [];
        $('#page-1').addClass('hidden-page');

    }
    function addLevel() {
        $.get("/resources/layouts/chapterlevel.html", function(data) {
            $("#page-table").append(data);
            //>>console.log($("#page-table"));
            $('#chapter-level-X').attr("id", "chapter-level-" + (getDepth()-1));

        });
    }


    function getMaxOrphanLevel() {
        var maxLevel = 0;
        for(var i = 0; i < chapter.orphans.length; i++) {
            var pageLevel = getPage(chapter.orphans[i].key.raw.name, pages).pageLevel;
            if(pageLevel > maxLevel) {
                maxLevel = pageLevel;
            }

        }
        return maxLevel;

    }

    function addLevels(level) {
        for(var i = 0; i < level; i++) {
            if(getLevel(i) == null) {
                addRow();
            }
        }
    }

    function loadOrphans() {

        addLevels(getMaxOrphanLevel()+1);

        for(var i = 0; i < chapter.orphans.length; i++) {
            var pageID = chapter.orphans[i].key.raw.name;
            var pageLevel = getPage(chapter.orphans[i].key.raw.name, pages).pageLevel;
            addPage(pageID, pageLevel);
        }
    }
/***********************************************************************************************************************
 * A) HANDLERS: Things that handle user interaction
 **********************************************************************************************************************/


    $(document).on("click", ".edit-option", function() {
       if($(this).parent().hasClass("add-page")) {
           return;
       }
        if($(this).parent().find("")){
            // get the page ID
            var pageID = cID + '^' +  $(this).parent().attr('id').split('-')[1];

            // load drawing page
            window.location.replace("/draw/"+pageID);
        }
    });
    /**
     * if page options have been clicked on
     */
    $(document).on("click", ".page-options", function() {

        if(linkingMode == false) {
        // grabbing the from page
        // set clicked page to from page
        if($(this).parent().find("")){
            // get the page ID
            var pageID = cID + '^' +  $(this).parent().attr('id').split('-')[1];
            var p = $(this).parent();///.removeClass('page-glow');
            p.removeClass('page-glow');
            //$(this).parent.find("").addClass('page-glow');
            p.addClass('page-glow');
            pageToLink = p;
            fromPageID = pageID;

        }
        linkingMode = true;
        }
        else { //grabbing the To page
                var pageID = cID + '^' + $(this).parent().attr('id').split('-')[1];
                toPageID = pageID;
                //need to have a handle to the glowing object, to make it un-glow
                pageToLink = null;
                //alert("Linking " + fromPageID + " to " + toPageID);
            $.post("/workspace/add/page-option", {"fromPage": fromPageID, "toPage": toPageID})
                .done(function(data){
                    console.log(data);

                    clearChapter();
                    // gotta make this synchronous.
                    $.getJSON("/workspace/load/" + cID , function(data) {
                    }).done(function (data) {
                        chapter = data.Chapter;
                        pages = data.Pages;
                        $("#title-input").val(chapter.name);
                        $("#summary-input").val(chapter.description);

                        console.log(chapter);
                        console.log(pages);

                        initPageIds(pages);
                        loadTree(chapter.root, pages, 0);
                        loadOrphans();
                        validateBottomRow();

                        var optionCount = getPage(fromPageID, pages).options.length;

                        // if more than one option, must provide the different options the user can choose
                        if(optionCount == 0) {
                            // add new option
                            $.post("/workspace/update/page-options", {"page": fromPageID, "options":[toPageID], "optionPrompts":[""]})
                                .done(function(data){
                                    console.log(data);
                                });

                        } else if(optionCount > 0){

                            // load modal with options
                            $('#options-form').empty();
                            var p = getPage(fromPageID,pages);


                            for(var i = 0; i < optionCount; i++) {
                                $('#options-form').append('<div class="form-group">'+
                                    '<label for='+getPage(fromPageID,pages).options[i].key.raw.name+'>Option: '+ getPage(fromPageID,pages).options[i].key.raw.name +'</label>' +
                                    '<input type="text" class="form-control" id="'+getPage(fromPageID,pages).options[i].key.raw.name+'" value="' +getPage(fromPageID, pages).optionDescriptors[i]+ '">' +
                                    '</div>');
                            }

                            $('#options-modal').modal('show');
                        }
                        linkingMode = false;
                    });
                    //loadChapter(cID); -- i literally just copied the code over
                    // im gonna put everything in done so it's synchronous.
            // enable linking mode
            linkingMode = false;
                });
        }
    });


    $("#save-options-button").click(function() {

        var pageIDList = [];
        var optionsList = [];

       //console.log($("#options-form :input"));
        var inputs = $("#options-form :input");
        for(var i = 0; i < inputs.length; i++) {
            pageIDList.push(inputs[i].id);
            optionsList.push(document.getElementById(inputs[i].id).value);
        }

        //alert(pageIDList + "," + optionsList);

        $.post("/workspace/update/page-options", {"page": fromPageID, "options":pageIDList, "optionPrompts":optionsList})
            .done(function(data){
                console.log(data);
            });

        $("#options-modal").modal('hide');


    });


    /**
     * HOVER--
     * FOR LINKING
     *
     * hover- options are presented.
     * if the current page has children, those children glow
     *  two options are presented-- link, and unlink
     *  you can add even more links, OR you can delete links.
     *  clicking an option will put you into that mode.
     *
     * if it does not have children, then only the link option will be present
     */
    $(document).on("mouseover", ".chapter-page", function() {

        if($(this).hasClass("add-page")) {
            return;
        }
        var pagediv = $(this);
        $(this).context.firstElementChild.style.display = 'block';
        if(linkingMode == true){
            $(this).removeClass('page-glow');
            $(this).addClass('page-glow');
        } // on mouseover, if this is not in linking mode, make all the children of this node glow
        else {
            //alert(numberIDtoPageID($(this).attr("id"))); // turn this from a page id into a real page id
            var node = getPage(numberIDtoPageID($(this).attr("id")) , pages);
            $(this).addClass('page-glow');
            for(var i = 0; i< node.options.length;i++){
                // grab the div that cooresponds to the id
                // turn this into a number id
                var pid = "page-" +pageIDtoNumberID(node.options[i].key.raw.name);
                var cnode = $('#' + pid) // woot
                cnode.addClass('page-glow');
            }

            //var node = getPage($(this).attr("id") , );
        }
    });
    /**
     * UNHOVER
     * UNLINKING
     */
    $(document).on("mouseleave", ".chapter-page", function() {

        if($(this).hasClass("add-page")) {
            return;
        }
        if(linkingMode == true){
            // if it's linking, then take off the glow
            $(this).removeClass('page-glow');
        }
        // get the page ID
        $(this).context.firstElementChild.style.display = 'none';
        if(!pageToLink){
            // its null
            // this is a page that you're hovering on
            // otherwise, its not linking, its just glowing, make it stop glowing. and it's children too.
            $(this).removeClass('page-glow');
            var node = getPage(numberIDtoPageID($(this).attr("id")) , pages);

            for(var i = 0; i<node.options.length;i++){
                // grab the div that cooresponds to the id
                // turn this into a number id
                var pid = "page-" +pageIDtoNumberID(node.options[i].key.raw.name);
                var cnode = $('#' + pid) // woot
                cnode.removeClass('page-glow');
            }
                return;
        }
        if((pageToLink.attr('id') == $(this).attr('id')) && (linkingMode == true)){
            // if this is the parent, it remains glowy
            return;
        }
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

        //console.log(getPageCount(rowID));

        if(getPageCount(rowID) > 5) {

        }
        else if(getPageCount(rowID) == 5) {
            $(this).removeClass('add-page').addClass('chapter-page').addClass('hidden-page').text('');
            // we have to check if we need to create a new page for this
            addPage("", rowID);
        } else {
            addPage("", rowID);
        }
        validateBottomRow();

    });

    /**
     * assuming that we'll have little x's in the corners of each page
     */
    $(document).on("click", ".delete-option", function(){
        //alert("ayyy");
        var chapterRow = $(this).parent().parent().parent()[0].getAttribute("id");
        var rowID = chapterRow.split('-')[2];
        var target = $(this).parent();
        var pageID = target[0].getAttribute("id");
        pageID = numberIDtoPageID(pageID);
        // get page id

        //alert("Page Count for row"+ rowID +" "+ getPageCount(rowID));
        if((getPageCount(rowID) - 2) == 0){
            // call a special handler

            //except when row ID is like, 2 or something
           // console.log("delete row");
            $.post("/delete-row" ,{"level": rowID, "chapterID":cID, "pageID":pageID}, function(){
            })
                .done(function(data){
                    console.log(data); // a post will refresh automatically
                    location.reload();
            })
                .fail(function(data){
                    console.log(data);
                });
            //refresh the page
        } else {
            //console.log("remove page");
            removePage(target , pageID, rowID);
            //$.post("delete-chapter-page", )
        }
        // check afterwards to see if we should remove this row or not
        // what if a row in the middle is deleted? what happens to levels?
        // the answer is we refresh the page
    })


/***********************************************************************************************************************
* B) ESSENTIALS: Things used by Handlers
**********************************************************************************************************************/

    /**
     * Recursivly adds chapter to the story tree
     * @param root: the root of the story tree
     * @param pages: all pages of the chapter
     * @param level: the level to add pages to
     */
    function insertOptions(root, pages, level) {
        //console.log(root.options[0].key.raw.name);
        //console.log(root.options);
        //>>console.log(root.options.length);
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
        //>>console.log(root.options[1]);
        //>>console.log("Root option length " + root.options.length);


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
        console.log("adding page of ID" + pageID);
        //>>console.log("adding page to Level: "+ levelToEdit);
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
            } else {
                if(pageID == ""){
                    // page Id doesnt exist, meaning, the page itself hasnt been made just yet
                    createPage(pageToEdit.getElementsByClassName("chapter-page")[0], pageID, level);
                } else {
                    //otherwise we're setting the page for a page that exists
                    setPage(pageToEdit.getElementsByClassName("chapter-page")[0], pageID, level);
                }
                validateBottomRow();
            }



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
        page.setAttribute("id",'page-' + pageIDtoNumberID(pageID));
        // TODO: do this using jquery, selector wasnt working
        page.className = "";
        page.className = "chapter-page";
        if(page != null) {
            //page.style.backgroundImage = "url(\'" + datastorePage.imagePath +"\')";
            //page.style.backgroundSize = "cover";
            //page.style.backgroundRepeat = "no-repeat";
        }
        validateBottomRow();


    }

    function createPage(page, pageID, level){
        if(page == null) {
            return;
        }
        console.log("creating a new page");
        $.getJSON("/make-chapter-page" ,{"level": level, "chapterID":cID, "pageID":pageID} ,function(data) {
            })
            .done(function(data){
                console.log(data);
                page.setAttribute("id",'page-' + pageIDtoNumberID(data.Page.pageId));
                page.className = "";
                page.className = "chapter-page";
                //add newly created pages to 'pages' object
                if(pageIds.indexOf(data.Page.pageId) >= 0){

                } else {
                    // doesnt exist, add it to pages
                    pages.push(data.Page);
                    pageIds.push(data.Page.pageId);
                }
                //  console.log("success");
                validateBottomRow();
            }).fail(function(data){
            console.log("failure");
        });

    }

    function pageIDtoNumberID(pageID) {
        return  idNumber = pageID.split('^')[1];
    }

    function numberIDtoPageID(pageID){
        idNumber = pageID.split("-")[1];
        idNumber = cID +"^"+ idNumber;
        return idNumber;
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

    function initPageIds(pages){
        var i;
        for(i = 0;i<pages.length ;i++){
            var newId = pages[i].pageId;
            pageIds.push(newId);
        }
    }

    /**
     * Validates the bottom of the story tree
     * note: If the bottom level has pages, this appends a new empty level allowing the user to
     * continually add to their story
     */
    validateBottomRow();
    function validateBottomRow() {
        var pageCountForBottomLevel = getPageCount($(".chapter-level").length -1 );
        //alert("page Count: " + pageCountForBottomLevel);
        console.log("page Count: " + pageCountForBottomLevel);
        if(pageCountForBottomLevel > 0) {
            addRow();

        }

    }

    /**
     * Adds a new level to the story tree
     * TODO: figure out why onpage add click doesn't work for rows added
     */
    function addRow() {
        $('#page-table > tbody:last-child').append('<tr id=\"chapter-level-'+ ($(".chapter-level").length) + '\" class=\"chapter-level\">' +
            '<td><div class=\"chapter-page hidden-page\" type=\"button\">' +
            '<div class=\"page-options\"><i class="fa fa-link" aria-hidden="true"></i></div>' +
            '<div class=\"delete-option\">' +
            '<i class=\"fa fa-times\" aria-hidden=\"true\"></i>' +
            '</div>' +
            '<div class=\"edit-option\">' +
            '<i class="fa fa-paint-brush" aria-hidden="true"></i>' +
            '</div>' +
            '</div></td>' +
            '' +
                '<td><div class=\"chapter-page hidden-page\" type=\"button\"><div class=\"page-options\"><i class="fa fa-link" aria-hidden="true"></i> </div><div class=\"delete-option\"><i class=\"fa fa-times\" aria-hidden=\"true\"></i></div><div class=\"edit-option\"><i class=\"fa fa-paint-brush\" aria-hidden=\"true\"></i></div></div></td>' +
            '<td><div class=\"chapter-page hidden-page\" type=\"button\"><div class=\"page-options\"><i class="fa fa-link" aria-hidden="true"></i> </div><div class=\"delete-option\"><i class=\"fa fa-times\" aria-hidden=\"true\"></i></div><div class=\"edit-option\"><i class=\"fa fa-paint-brush\" aria-hidden=\"true\"></i></div></div></td>' +
            '<td><div class=\"chapter-page hidden-page\" type=\"button\"><div class=\"page-options\"><i class="fa fa-link" aria-hidden="true"></i> </div><div class=\"delete-option\"><i class=\"fa fa-times\" aria-hidden=\"true\"></i></div><div class=\"edit-option\"><i class=\"fa fa-paint-brush\" aria-hidden=\"true\"></i></div></div></td>' +
            '<td><div class=\"chapter-page add-page\" type=\"button\">new</div></td>' +
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

    /**
     * PlaceOrphans uses the global tree variable to find any nodes that are not in the tree
     * then, it places these nodes in their cooresponding levels
     */
    function placeOrphans(){
        var orphans = [];
        var i;
        for(i =0;i<pages.length ;i++){
            var finder=pages[i].pageId;
            if(tree.indexOf(finder) >= 0){
                //its in the tree
               // console.log("not an orphan");
            }else{
                //its not in the tree
               // console.log("is orphan");
                orphans.push(pages[i]);
            }
        }
        for(i = 0; i<orphans.length;i++){
            addPage(orphans[i].pageId, orphans[i].level);
        }
    }

    /**
     * remove page-- finds the page due for removal,
     * then calls deletePage on that page
     * @param PageID
     * @param level
     */
    function removePage(target, pageID, level){
        // remove page element from level
        //alert("PageID " + pageID);
        var numberID = pageIDtoNumberID(pageID);
        numberID = "page-" + numberID;
        //alert("level " + level);
        var levelToEdit = getLevel(level);
        var pageToDelete = null;
        var isAddPage = 0;
        if(levelToEdit == null) {
            console.log("Level doesn't exist");
        } else {
            // don't need to do this check
            // get the fuckin, thing by id or wa'ever
            // i passed target down

            if(target[0] == null) {
                console.log("pageToDelete is null");
            } else {
                //delete page
                deletePage(target[0], pageID, level, isAddPage)
            }
        }

        }

    /**
     * deletes a page from the datastore
     */
    function deletePage(page, pageID, level, isAddPage){
        //alert("page: " + page);
        //alert("pageID: " + pageID);
        //alert("level: " + level);
        //alert("isAddPage: " + isAddPage);
        if(page == null) {
            return;
        }
        $.post("/delete-chapter-page" ,{"chapterID":cID, "pageID":pageID} , function(data) {
            })
            .done(function(data){
               // console.log(data);
                //unlink the page here
                if(pageIds.indexOf(pageId) >= 0){

                } else {
                    // doesnt exist, add it to pages
                    pageIds.remove(pageId);
                }
               // console.log("success");// get the page ID
                //window.reload();
            }).fail(function(data){
               // console.log("failure");
            });
        // TODO: do this using jquery, selector wasnt working
        if(isAddPage == 1){
            //it's the add page
            page.className = "";
            page.className = "chapter-page add-page";
        } else {
            page.className = "";
            page.className = "chapter-page hidden-page";
        }
        // return up

    }

    // delete page
    // get page ID
    // remove page from Datastore (ajax request)
    // remove page from chapter (ajax request)
    // remove page element from level
    // validate tree

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
       //>>console.log("chapter-level-"+(level-1));
        //>>console.log($("#chapter-level-"+(level-1)));
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



    // recursively loads story tree
    function loadTree(rootID, allPages, level) {
        // check if root is null
        if(rootID == null) {
            //>>console.log("Root is null, Level: " + level);
            return;
        } else {

            // add root to tree
            var root = getPage(getPageID(rootID), allPages);
            if(getLevel(level) == null) {
                addRow();
            }
            if(tree.indexOf(root.pageId) >= 0){ //already in the tree
                //meaning, this node has already been visited
                //>>console.log("this node has already been visited");
                return;
            }
            console.log("pushing " +root.pageId  +"into the tree");
            tree.push(root.pageId);
            addPage(root.pageId, level); // add to global tree var as well
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
                //connectElements($("#svg1"), $('#'+ rootPageNumber+'-'+ childPageNumber + '-' + 'connector'), $('#page-'+rootPageNumber),  $("#page-"+ childPageNumber));
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