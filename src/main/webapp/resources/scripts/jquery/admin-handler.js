$(document).ready(function () {

    var pendingApproval;
    var template;
    var author;
    var chapterName;
    var srcImage;
    var reportedComics;

    getSubmittedandReportedComics();

    function getSubmittedandReportedComics() {
        $.getJSON("/admin/get/comicInformation", function(data) {
        }).done(function (data) {
            pendingApproval = data.members.pendingApproval.elements;
            console.log(pendingApproval);
            reportedComics = data.members.reportedComics.elements;
            console.log(reportedComics);

            $("#left").empty();
            $("#right").empty();


            template = $('#hidden-template').html();
            template2 = $('#hidden-template2').html();

            for (var i = 0; i < pendingApproval.length; i++)
            {
                var item = $(template).clone();
                author = pendingApproval[i].members.author.members.key.members.raw.members.name.value;
                chapterName = pendingApproval[i].members.name.value;

                $(item).find("#author").html(author);
                $(item).find("#title").html(chapterName);

                var currentChapterID = pendingApproval[i].members.chapterId.value;
                $(item).find(".approve-comic-button").attr("id", currentChapterID);

                $("#left").append(item);
            }

            for (var i = 0; i < reportedComics.length; i++)
            {
                var item = $(template2).clone();
                author = reportedComics[i].members.author.members.key.members.raw.members.name.value;
                chapterName = reportedComics[i].members.name.value;

                $(item).find("#author2").html(author);
                $(item).find("#title2").html(chapterName);

                var reportedComicChapterID = reportedComics[i].members.chapterId.value;

                $(item).find(".approve-comic-button").attr("id", reportedComicChapterID);
                //$(item).find("#hidden-reportedComicChapterID").html(reportedComicChapterID);
                $("#right").append(item);
            }
            //done

            $(".item-approval").find(".approve-comic-button").click(function() {
                // Take the container and empty() it.
                // Not sure how to do this without reloading the page, or getting an entirely new json object by recursively calling getSubmittedandReportedComics
                var chapterID = this.id;
                approveComic(chapterID);
            });

            $(".reported-approval").find(".approve-comic-button").click(function() {
               var chapterID = this.id;
                falselyReported(chapterID);
            });

        })
    }

    function approveComic(chapterID) {
        $.getJSON("/admin/get/approveComic/" + chapterID, function(data) {
        }).done(function (data) {
            getSubmittedandReportedComics();
        });
    }

    function falselyReported(chapterID) {
        $.getJSON("/admin/get/falselyReported/" + chapterID, function(data) {
        }).done(function (data) {
            getSubmittedandReportedComics();
        });
    }
});