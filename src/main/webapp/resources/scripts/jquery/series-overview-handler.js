$(document).ready(function(){
    var seriesID = $("#series-overview-name").html();

    var allChapters;
    var template;
    var thisManyChapters;
    var chapterTitle;
    var chapterID;

    getAllChapters(seriesID);

    function getAllChapters(seriesID) {
        $.getJSON("/series-overview/get/" + seriesID + "/allChapters", function(data) {
        }).done(function (data) {
            allChapters = data.members.allChapters.elements;
            console.log(allChapters);

            $("#result-container").empty();

            thisManyChapters = allChapters.length;

            template = $('#hidden-template').html();
            if (thisManyChapters > 0) {
                for (var o = 0; o < thisManyChapters; o++) {
                    var item = $(template).clone();

                    chapterTitle = allChapters[o].members.name.value;
                    chapterID = allChapters[o].members.chapterId.value;
                    $(item).find("#series-overview-chap-title").html(chapterTitle);
                    $(item).find(".series-overview-edit-draft-button").attr('id', chapterID);
                    $(item).find(".series-overview-delete-chapter-button").attr('id', chapterID);

                    $("#result-container").append(item);
                }
            }

            $(".one-story").find(".series-overview-edit-draft-button").click(function() {
                var currentChapterID = this.id;
                window.location.replace("../workspace/loadChapter/" + currentChapterID);
            });

            $(".one-story").find(".series-overview-delete-chapter-button").click(function() {
                // Get json. Recursively call getAllChapters
                var currentChapterID = this.id;
                deleteChapter(currentChapterID);
                //Almost worked
            });

            $(".series-overview-create-chapter").click(function() {
                var chapterTitle = $("#chapterTitle").val();
                var chapterDescription = $("#chapterDescription").val();

                if (!chapterTitle || !chapterDescription)
                {
                    alert("Title and Description Required");
                }
                else
                {
                    createChapter(seriesID, chapterTitle, chapterDescription);
                }
            });
        })
    }

    function deleteChapter(chapterID) {
        $.getJSON("/series-overview/getChapter/" + chapterID, function(data) {
        }).done(function (data) {
            getAllChapters(seriesID);
        });
    }

    function createChapter(seriesID, chapterTitle, chapterDescription) {
        $.getJSON("/series-overview/createChapter/" + seriesID+ "/" + chapterTitle + "/" + chapterDescription, function(data) {
        }).done(function (data) {
            getAllChapters(seriesID);
        });
    }
});
