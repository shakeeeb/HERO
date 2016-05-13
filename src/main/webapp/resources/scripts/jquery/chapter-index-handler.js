$(document).ready(function () {
    var seriesID = "One_Piece";
    var chapterList = null;
    var numChapters = null;
    var seriesName = null;
    var author = null;
    var seriesDescription = null;
    var chapterID = null;
    var rootID = null;

    var tempSeriesID = $("#hidden-seriesID").html();

    if (!!tempSeriesID)
    {
        seriesID = $("#hidden-seriesID").html();
    }

    chapterID = $("#hidden-chapterID").html();
    rootID = $("#hidden-rootID").html();

    getChaptersAndSubscriptionInfo(seriesID);

    function getChaptersAndSubscriptionInfo(seriesID) {
        $.getJSON("/chapter-index/" + "get/" +seriesID, function(data) {
        }).done(function (data) {
            chapterList = data.members.chapterList.elements;
            console.log(chapterList);
            series = data.members.series.members;
            console.log(series);
            var isSubscribed = data.members.isSubscribed.value;
            console.log(isSubscribed);

            numChapters = chapterList.length;
            seriesName = series.name.value;
            author = series.authorName.value;
            seriesDescription = series.description.value;



            $("#chapter-index-series-name").text(seriesName);
            $("#chapter-index-author").text(author);
            $("#chapter-index-series-description").text(seriesDescription);

            if (isSubscribed) {
                $("#subscribe-button").html("Unsubscribe");
            }
            else {
                $("#subscribe-button").html("Subscribe");
            }
            var currentChapter = null;
            if (numChapters > 0) {
                for (var o = 1; o < numChapters + 1; o++)
                {
                    currentChapter = chapterList[o - 1]
                    var itemHTML = "<div class=\"chapter-list-item center-block-\" id=\"" + currentChapter.members.chapterId.value + "\">" +
                        "<div class=\"inline\">" +
                        "<img src=\"https://placehold.it/125/ffa500/ffffff\">" +
                        "</div>" +
                        "<div class=\"inline\">" +
                        "<div id=\"" + currentChapter.members.chapterId.value + currentChapter.members.chapterNumber.value + "\">Chapter #: " + currentChapter.members.chapterNumber.value + "</div>" +
                        "<div id=\"" + currentChapter.members.name.value + "\" class=\"inline\">" + currentChapter.members.name.value + "</div>" +
                        "<div>" +
                        "<p id=\"" + "" + "\"></p>" + // Put chapter description here.
                        "</div>" +
                        "<div id=\"" + currentChapter.members.dateCreated.value +"\" class=\"pull-right chapter-date\">" +
                        "</div>" +
                        "</div>";

                    $("#chapter-index-chapter-list").append(itemHTML);
                }
            }

            $(".chapter-list-item").click(function() {
                var currentChapterID = this.id;
                var currentPageID = null;

                for(var p = 0; p < chapterList.length; p++)
                {
                    if(currentChapterID == chapterList[p].members.chapterId.value)
                    {
                        currentPageID = chapterList[p].members.root.members.key.members.raw.members.name.value;
                    }
                }
                //loadPage(currentChapterID, currentPageID);
                updateRecentlyViewed(currentChapterID, "/read/" + currentChapterID + "/" + currentPageID);

                //Don't load the page. Switch to the page and use the controller to get the information we need.

            });

            $("#chapter-index-start-from-beginning").click(function() {
                updateRecentlyViewed(chapterID, "/read/" + chapterID + "/" + rootID);
                window.location.replace("/read/" + chapterID + "/" + rootID);
            });

            $("#subscribe-button").click(function(){
                toggleSubscription(seriesID);
            });


            // Let's add the subscribe feature here.
            // Make a function that does the get request for
            // /chapter-index/subscribe/{chapter-ID}
            //// Should change the text to subscribed if the user is already subscribed.
        })
    }

    function toggleSubscription(seriesID) { // Change this to /read/ + /chapterID + /pageNumber
        $.getJSON("/chapter-index/subscribe/" + seriesID).done(function (data) {
            var isSubscribed = data.members.subscriptionToggled.value;
            console.log(isSubscribed);

            if (isSubscribed) {
                $("#subscribe-button").text("Unsubscribe");
            }
            else
            {
                $("#subscribe-button").text("Subscribe");
            }
        });
    }

    function updateRecentlyViewed(chapterID, urlMapping) {
        $.getJSON("/chapter-index/updateRecentlyViewed/" + chapterID)
            .done(function (data) {
                window.location.replace(urlMapping);
        });
    }

    //function loadPage(chapterID, pageID) { // Change this to /read/ + /chapterID + /pageNumber
    //    $.getJSON("/read/" + chapterID + "/" + pageID, function(data) {
    //    }).done(function (data) {
    //        console.log(data.members.Chapter.members);
    //        console.log(data.members.page.members);
    //        console.log(data.members.pageOptions);
    //        console.log(data.members.pageList);
    //
    //        chapter = data.members.Chapter.members;
    //        currentPage = data.members.page.members;
    //        pageOptions = data.members.pageOptions;
    //        pageList = data.members.pageList;
    //
    //        $("#chapter-name-reader-page").text(currentPage.pageId.value);
    //        $("#page-number-reader-page").text(currentPage.pageNumber.value);
    //
    //
    //        var numOptions = currentPage.numOptions.value;
    //        var optionText = currentPage.optionDescriptors.elements;
    //        var pageSrc = currentPage.imagePath.value;
    //
    //        $("#page-reader-example-img").attr("src", pageSrc);
    //
    //        for (var i = 1; i < numOptions + 1; i++)
    //        {
    //            var buttonHTML = "<div class=\"row pageOptions\">" +
    //                "<button id=\"" + pageOptions.elements[i-1].members.pageId.value + "\" type=\"button\" class =\"optionButtons\">" +
    //                optionText[i - 1].value + "</button> </div>";
    //            $("#option-container").append(buttonHTML);
    //        }
    //
    //        $(".optionButtons").click(function() {
    //            $("#option-container").empty();
    //
    //            var nextPageID = this.id;
    //            loadPage(chapterID, nextPageID);
    //        });
    //    });
    //}
});