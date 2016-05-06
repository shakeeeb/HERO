/**
 * Created by mk on 4/21/16.
 */

$(document).ready(function() {
    var currentUserEmail = $("#hidden-author-email").html();
    currentUserEmail = "Benjamin.Strumeyer@Paws.for.a.Cause"; // For testing purposes.
    loadAllSeries();

    function loadAllSeries() {
        $.getJSON("/series-workspace/lookup/get/", function(data) {
        }).done(function(data) {
            console.log(data);

        }).fail(function() {
                console.log("Could not get data");
        });
    }

});

