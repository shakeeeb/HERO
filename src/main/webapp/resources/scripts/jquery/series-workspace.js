$(document).ready(function() {

    var allSeries;
    var template = null;

    loadAllSeries();

    function loadAllSeries() {
        $.getJSON("/series-workspace/lookup/get/", function(data) {
        }).done(function(data) {
            console.log(data);
            allSeries = data.members.allSeries.elements;
            console.log(allSeries);

            $(".series-workspace-main").empty();

            var numDrafts = 0;
            var complete = 0;
            var newSeriesGenre;

            for (var j = 0; j < allSeries.length; j++) {
                if (allSeries[j].members.isApproved.value) {
                    complete++;
                }
                else {
                    numDrafts++;
                }
            }
            if (allSeries.length > 0) {
                template = $('#hidden-template').html();
                for (var i = 0; i < allSeries.length; i++) {
                    // Template that shit
                    var item = $(template).clone();
                    var seriesID = allSeries[i].members.name.value;
                    var currentSeriesGenre = allSeries[i].members.mainGenre.value;

                    $(item).find(".overview-button").attr("id", seriesID);
                    $(item).find(".overview-button").text(seriesID);
                    $(item).find(".delete-series-button").attr("id", seriesID);
                    var numChapters = allSeries[i].members.numChapters.value;
                    $(item).find("#series-authored-chap-nums").text("Number of Chapters: " + numChapters);
                    $(item).find("#authored-story-1-tags").text("Genre: " + currentSeriesGenre);

                    $(".series-workspace-main").append(item);
                }
            }

            $(".series-authored-story").find(".overview-button").click(function() {
                var seriesID = this.id;
                loadSeriesOverview(seriesID);
            });

            $(".series-authored-story").find(".delete-series-button").click(function() {
                var seriesID = this.id;
                deleteSeries(seriesID);
            });

            $(".genre-dropdown .btn").click(function() {
                newSeriesGenre = $(this).html();
            });

            $(".series-workspace-create-chapter").click(function() {
                var seriesTitle = $("#seriesTitle").val();
                var seriesDescription = $("#seriesDescription").val();

                if(newSeriesGenre == null){
                    console.log("boo");
                    return;
                }

                if (!seriesTitle || !seriesDescription)
                {
                    alert("Title and Description Required");
                }
                else
                {
                    //alert("SeriesTitle: " + seriesTitle + "\n" +
                    //        "Series Description: " + seriesDescription + "\n" +
                    //        "Genre: " + newSeriesGenre);
                    createSeries(newSeriesGenre, seriesTitle, seriesDescription);
                }
            });


        }).fail(function() {
                console.log("Could not get data");
        });
    }

    function loadSeriesOverview(seriesID) {
        window.location.replace("/series-overview/" + seriesID);
    }

    function createSeries(newSeriesGenre, seriesTitle, seriesDescription) {
            $.getJSON("/series-workspace/createSeries/" + seriesTitle + "/" + newSeriesGenre + "/" + seriesDescription, function(data) {
            }).done(function(data) {
                console.log(data);
                loadAllSeries();

            }).fail(function() {
                console.log("Could not get data");
            });
    }

    function deleteSeries(seriesID) {
        $.getJSON("/series-workspace/deleteSeries/" + seriesID, function(data) {
        }).done(function(data) {
            console.log(data);
            loadAllSeries();

        }).fail(function() {
            console.log("Could not get data");
        });
    }

});

