/**
 * Created by mk on 4/21/16.
 */

$(document).ready(function() {

    loadPage("tangobearindustries@gmail.com");

    //load the user and grab all the stuff from the datastore
    function loadPage(userToLookUpEmail) {

        $.getJSON("/dashboard/",{"userToLookUpEmail" : userToLookUpEmail}, function(data) {
        }).done(function(data) {

            console.log(data);
            nickname = data.members.nickname.value;

        })
            .fail(function() {

                console.log(userToLookUpEmail);
                console.log("Could not get data");

            });

    }

});

