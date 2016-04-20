$(document).ready(function () {
    var chapterID = "One_Piece~Luffy_meets_Boa"
    var hardCodedPageID = "One_Piece~Luffy_meets_Boa^0";
    var chapter = null;
    var currentPage = null;
    var pageOptions = null;
    console.log("testing before call");
    loadPage(chapterID, hardCodedPageID);
    var numOptions = null;
    var optionArraySize = null;
    var buttonTextIncrementor;
    var nextPageID = null;
    var pageList = null;
    var nextPage = null;
    var nextPageSrc = null;
    var nextPageNumOptions = null;
    var nextNextPageID = null;
    var nextPageOptions = null;

    $("#pageImage").click(function() { // Use this handler for button 1 as well.
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

            ///read/" + chapterID + "/" + pageOptions.elements[i - 1].members.pageId.value +"

            for (var i = 1; i < numOptions + 1; i++)
            { // 1 based loop so we can use i as our incrememtor and Id setter.
                // Create the number of buttons and set the text.
                var buttonHTML = "<div class=\"row pageOptions\">" +
                                    "<button id=\"" + pageOptions.elements[i-1].members.pageId.value + "\" type=\"button\" class =\"optionButtons\">" +
                    optionText[i - 1].value + "</button> </div>";
                $("#option-container").append(buttonHTML);
            }

            $(".optionButtons").click(function() {
                //alert("handler for option buttons clicked");
                //Remove everything here.
                //$("#page-reader-main").empty();
                // Don't have to change the image because you change the image path.
                $("#option-container").empty();

                //Put back the elements
                // - Add specific buttons.
                // - Add back the image pertaining to the new page source.
                nextPageID = this.id;
                //alert(nextPageID);

                loadPage(chapterID, nextPageID);
                //for(var k = 1; k < pageList.elements.length; k++)
                //{
                //
                //    if (nextPageID == pageList.elements[k-1].members.pageId.value)
                //    {
                //        alert("here;");
                //        nextPage = pageList.elements[k-1].members;
                //        break;
                //    }
                //}
                //nextPageSrc = nextPage.imagePath.value;

                //$("#page-reader-example-img").attr("src", nextPageSrc);

                //Now add back the divs with the buttons.
                //alert("This next page has  this many options: " + nextPage.optionDescriptors.elements.length);



                //getPageOptions(nextPageID);
            });

            //function getPageOptions(pageid) { // Change this to /read/ + /chapterID + /pageNumber
            //    $.getJSON("/read/" + "/pageOptions/" + pageID, function(data) {
            //    }).done(function (data) {
            //        console.log(data);
            //        nextPageOption = data.members.nextOptions.elements[0].members;
            //
            //
            //
            //
            //        nextNumOptions = nextPageOption.numOptions.value;
            //
            //        for (var v = 1; v < nextNumOptions + 1; v++)
            //        {
            //            var nextButtonHTML = "<div class=\"row pageOptions\">" +
            //                "<button id=\"" + nextPageOption.options.elements[v - 1].members.key.members.raw.members.name.value + "\" type=\"button\" class =\"optionButtons\">" +
            //                nextPageOption.optionDescriptors.elements[v-1].value + "</button> </div>";
            //            $("#option-container").append(nextButtonHTML);
            //        }
            //
            //
            //        $(".optionButtons").click(function() {
            //            //alert("handler for option buttons clicked");
            //            //Remove everything here.
            //            //$("#page-reader-main").empty();
            //            // Don't have to change the image because you change the image path.
            //            $("#option-container").empty();
            //
            //            //Put back the elements
            //            // - Add specific buttons.
            //            // - Add back the image pertaining to the new page source.
            //            nextPageID = this.id;
            //            //alert(nextPageID);
            //            for(var k = 1; k < pageList.elements.length; k++)
            //            {
            //
            //                if (nextPageID == pageList.elements[k-1].members.pageId.value)
            //                {
            //                    alert("here;");
            //                    nextPage = pageList.elements[k-1].members;
            //                    break;
            //                }
            //            }
            //            nextPageSrc = nextPage.imagePath.value;
            //
            //            $("#page-reader-example-img").attr("src", nextPageSrc);
            //
            //            //Now add back the divs with the buttons.
            //            //alert("This next page has  this many options: " + nextPage.optionDescriptors.elements.length);
            //
            //            getPageOptions(nextPageID);
            //        });
            //
            //
            //
            //    });
            //}



            // Remove everything from the image div. Then remove everything from the button div. Then get the option.

            //Sets other things.
            //nextPageID =




        });
    }





    //$("#nextPage").submit(function(event) { // Just a function declaration.
    //    console.log("#Next Page clicked.");
    //});
});