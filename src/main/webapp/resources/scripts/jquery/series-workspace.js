/**
 * Created by mk on 4/21/16.
 */

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
            var genre = null;

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
                    $(item).find(".overview-button").attr("id", seriesID);
                    $(item).find(".overview-button").text(seriesID);
                    $(item).find(".delete-series-button").attr("id", seriesID);
                    var numChapters = allSeries[i].members.numChapters.value;
                    $(item).find("#series-authored-chap-nums").text("Number of Chapters: " + numChapters);
                    //$(item).find("#authored-story-1-tags").text("Drafts/Completed: " + numDrafts + "/" + complete);

                    $(".series-workspace-main").append(item);
                }
            }

            $(".genre-dropdown .btn").click(function() {
                genre = $(this).html();
            });

            $(".series-authored-story").find(".overview-button").click(function() {
                var seriesID = this.id;
                loadSeriesOverview(seriesID);
            });

            $(".series-authored-story").find(".delete-series-button").click(function() {
                var seriesID = this.id;
                deleteSeries(seriesID);
            });

            $(".series-workspace-create-chapter").click(function() {
                var seriesTitle = $("#seriesTitle").val();
                var seriesDescription = $("#seriesDescription").val();
                //var cgenre = genre;
                if(genre == null){
                    console.log("BOOO ");
                    return;
                }

                if (!seriesTitle || !seriesDescription)
                {
                    alert("Title and Description Required");
                }
                else
                {
                    alert("SeriesTitle: " + seriesTitle + "\n" +
                            "Series Description: " + seriesDescription + "\n" +
                            "Genre: " + genre);
                    createSeries(genre, seriesTitle, seriesDescription);
                }
            });


        }).fail(function() {
                console.log("Could not get data");
        });
    }

    function loadSeriesOverview(seriesID) {
        window.location.replace("/series-overview/" + seriesID);
    }

    function createSeries(genre, seriesTitle, seriesDescription) {
            $.getJSON("/series-workspace/createSeries/" + seriesTitle + "/" + genre + "/" + seriesDescription, function(data) {
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

