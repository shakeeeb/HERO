/**
 * Created by mk on 4/21/16.
 */

$(document).ready(function() {
    console.log("I'm the JS of the series workspace page!");
    var currentUserEmail;
    currentUserEmail = $("#userEmail").html();
    console.log("User email that I'm grabbing from the page: " + currentUserEmail);
    var hardcoded_series_name = "My Best Friend Gleb";

    loadPage(hardcoded_series_name, currentUserEmail);
    function loadPage(series_to_load, userEmail) {
        $.getJSON("/series-workspace/",{"series_to_load" : series_to_load, "userEmail": userEmail}, function(data) {
        }).done(function(data) {

                console.log(data);

            })
            .fail(function() {
                console.log(series_to_load);
                console.log("Could not get data");
            });
    }

});

