$(document).ready(function () {
    //var chapterID = "One_Piece~Luffy_meets_Boa"
    //var hardCodedPageID = "One_Piece~Luffy_meets_Boa^0";

    var chapterID;
    var pageID;
    var chapter = null;
    var currentPage = null;
    var pageOptions = null;
    var nextPageID = null;
    var pageList = null;
    var nextPage = null;
    var seriesID = null;
    var svgObject = null;


    //Glitch if a chapter only contains one page.

    //Find out where you're coming from.
    // Coming from the chapter index. Case when you find other routes to the chapter index here.
    chapterID = $("#hidden-chapterID").html();
    pageID = $("#hidden-pageID").html();

    loadPage(chapterID, pageID);

    $("#pageImage").click(function() {
       $("#nextPage").submit();
    });

    function loadPage(chapterID, pageID) { // Change this to /read/ + /chapterID + /pageNumber
        $.getJSON("/read/get/" + chapterID + "/" + pageID, function(data) {
        }).done(function (data) {
            chapter = data.members.Chapter.members;
            currentPage = data.members.page.members;
            pageOptions = data.members.pageOptions;
            pageList = data.members.pageList;
            seriesID = data.members.seriesID.value;
            svgObject = data.members.page.members.SVGString.value;

            console.log(chapter);
            console.log(currentPage);
            console.log(pageOptions);
            console.log(pageList);
            console.log(seriesID);
            console.log(svgObject);


            if (pageList.elements.length === 1)
            {
                // This should work. It doesn't. Kill me.
                // It goes to read/chapter-index/One_Piece instead of /chapter-Index/One_Piece
                //window.location.replace("../chapter-index/" + seriesID);
            }

            $("#chapter-name-reader-page").text(currentPage.pageId.value);
            $("#page-number-reader-page").text(currentPage.pageNumber.value);


            var numOptions = currentPage.numOptions.value;
            var optionText = currentPage.optionDescriptors.elements;
            //var pageSrc = currentPage.imagePath.value;

            //$("#page-reader-example-img").attr("src", pageSrc);
            console.log("placing svg object");
            $("#page-reader-main").html(svgObject);


            if(numOptions == 0) // It's the last page.
            {
                //Append one button and it has to go to the next page.
                var buttonHTML = "<div class=\"row pageOptions\">" +
                    "<button id=\"lastPage\" type=\"button\" class =\"btn-block\">" +
                    "End Of Chapter" + "</button> </div>";
                $("#option-container").append(buttonHTML);
            }

            $("#lastPage").click(function() {
                window.location.replace("/last-page/" + chapterID + "/" + pageID);
            });


            for (var i = 1; i < numOptions + 1; i++)
            {
                var buttonHTML = "<div class=\"row pageOptions\">" +
            "<button id=\"" + pageOptions.elements[i-1].members.pageId.value + "\" type=\"button\" class =\"optionButtons btn-block\">" +
            optionText[i - 1].value + "</button> </div>";
                $("#option-container").append(buttonHTML);
            }

            $(".optionButtons").click(function() {
                $("#option-container").empty();

                nextPageID = this.id;
                loadPage(chapterID, nextPageID);
            });
        });
    }
});