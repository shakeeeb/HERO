/**
 * Created by mk on 4/21/16.
 */

$(document).ready(function() {

    var allSeries;
    var currentUserEmail = $("#hidden-author-email").html();
    var template = null;
    currentUserEmail = "Benjamin.Strumeyer@Paws.for.a.Cause"; // For testing purposes.
    loadAllSeries();

    function loadAllSeries() {
        $.getJSON("/series-workspace/lookup/get/", function(data) {
        }).done(function(data) {
            console.log(data);
            allSeries = data.members.allSeries.elements;
            console.log(allSeries);

            var numDrafts = 0;
            var complete = 0;

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
                    var numChapters = allSeries[i].members.numChapters.value;
                    $(item).find("#series-authored-chap-nums").text("Number of Chapters: " + allSeries.length);
                    $(item).find("#authored-story-1-tags").text("Drafts/Completed: " + numDrafts + "/" + complete);

                    $(".series-workspace-main").append(item);
                }
            }
            $(".series-authored-story").find(".overview-button").click(function() {
                var seriesID = this.id;
                loadSeriesOverview(seriesID);
            });

        }).fail(function() {
                console.log("Could not get data");
        });
    }

    function loadSeriesOverview(seriesID) {
        window.location.replace("/series-overview/" + seriesID);
    }

});

