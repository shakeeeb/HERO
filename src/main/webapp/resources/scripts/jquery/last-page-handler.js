$(document).ready(function () {
    var nextChapterID = $("#hidden-nextChapterID").html();
    var rootID = $("#hidden-rootID").html();
    var nextPageID = $("#hidden-nextPageID").html();
    var seriesID = $("#hidden-seriesID").html();
    var chapterID = $("#hidden-chapterID").html();

    $("#last-read-page-chapter-index").click(function(){
        window.location.replace("/chapter-index/" + seriesID); // Replace with returning string
    });

    $("#last-read-page-replay").click(function(){
        window.location.replace("/read/" + chapterID + "/" + rootID);
    });

    $("#last-read-page-next-chapter").click(function(){
        window.location.replace("/read/" + nextChapterID + "/" + nextPageID);
    });

});
