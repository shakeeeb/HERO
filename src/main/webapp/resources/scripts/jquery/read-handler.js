$(document).ready(function () {
    var chapterID = "One_Piece~Luffy_meets_Boa"
    var hardCodedPageID = "One_Piece~Luffy_meets_Boa^0";
    var chapter = null;
    var currentPage = null;
    var pageOptions = null;
    console.log("testing before call");
    loadPage(chapterID, hardCodedPageID);
    var nextPageID = null;
    var pageList = null;
    var nextPage = null;

    $("#pageImage").click(function() {
       $("#nextPage").submit();
    });

    function loadPage(chapterID, pageID) { // Change this to /read/ + /chapterID + /pageNumber
        $.getJSON("/read/" + chapterID + "/" + pageID, function(data) {
        }).done(function (data) {
            console.log(data.members.Chapter.members);
            console.log(data.members.page.members);
            console.log(data.members.pageOptions);
            console.log(data.members.pageList);

            chapter = data.members.Chapter.members;
            currentPage = data.members.page.members;
            pageOptions = data.members.pageOptions;
            pageList = data.members.pageList;

            $("#chapter-name-reader-page").text(currentPage.pageId.value);
            $("#page-number-reader-page").text(currentPage.pageNumber.value);


            var numOptions = currentPage.numOptions.value;
            var optionText = currentPage.optionDescriptors.elements;
            var pageSrc = currentPage.imagePath.value;

            $("#page-reader-example-img").attr("src", pageSrc);

            for (var i = 1; i < numOptions + 1; i++)
            {
                var buttonHTML = "<div class=\"row pageOptions\">" +
                                    "<button id=\"" + pageOptions.elements[i-1].members.pageId.value + "\" type=\"button\" class =\"optionButtons\">" +
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