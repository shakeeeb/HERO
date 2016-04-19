$(document).ready(function () {
    var chapterName ="Luffy_meets_Boa"
    var pageNumber = 0;
    var seriesName = "One_Piece";
    var currentChapter = null;
    console.log("testing before call");
    loadPage(seriesName, chapterName, pageNumber);

    $("#pageImage").click(function() { // Use this handler for button 1 as well.
       $("#nextPage").submit();
    });


    function loadPage(seriesName, chapterName, pageNumber) {
        alert("hey");
        $.getJSON("/read/" + seriesName + "~" + chapterName + "/" + pageNumber, function(data) {
        }).done(function (data) {
            currentChapter = data.Chapter;
            console.log(currentChapter);
            alert("what");
        });
    }

    $("#nextPage").submit(function(event) { // Just a function declaration.
        console.log("#Next Page clicked.");
    });
});