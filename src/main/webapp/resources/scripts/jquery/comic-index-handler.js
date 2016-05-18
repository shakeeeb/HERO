$(document).ready(function () {
    var genre = "all";
    var seriesID;
    var comics;
    var template;

    getComicsByGenre(genre);

    function getComicsByGenre(genre) {
        $.getJSON("/comic-index/get/" + genre, function(data) {
        }).done(function (data) {
            comics = data.comics;
            console.log(comics);

            $("#comic-container").empty();

            template = $('#hidden-template').html();

            if (comics.length > 0) {
                for (var o = 0; o < comics.length; o++) {
                    var item = $(template).clone();
                    seriesID = comics[o].name;

                    $(item).find(".series-image").attr('id',seriesID);
                    $("#comic-container").append(item);
                }
            }

            $(".series-image").click(function() {
                var chapterIndexID = $(this).attr('id');
                loadChapterIndex(chapterIndexID);
            });

            $("ul li").click(function() {
                genre = $(this).attr('id');
                getComicsByGenre(genre);
            });

        });
    }
    function loadChapterIndex(chapterIndexID) {
        window.location.replace("../chapter-index/" + chapterIndexID);
    }
});