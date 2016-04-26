/**
 * Created by mk on 4/21/16.
 */

$(document).ready(function() {
    var currentUserEmail = null;

    currentUserEmail = $("#userEmail").html();

    loadPage(currentUserEmail);
    var about_me;
    var nickname;
    function loadPage(userEmail) {
        $.getJSON("/settingsUser/get/" + userEmail, function(data) {
        }).done(function(data) {

            console.log(data);
            nickname = data.members.nickname.value;
            about_me = data.members.about_me.value;

            $("#nickname").attr("placeholder", nickname);
            $("#about-me").attr("placeholder", about_me);

                //alert("aboutMe: " + about_me);

            $("#edit-settings-save").click(function(){
                var nickname = $('#nickname').val();
                var about_me = $("#about-me").val();

                saveSettingsPage(nickname, about_me);

            });

        })
            .fail(function() {
                console.log("Could not get data");
            });
    }
    //save user information
    //if the save button is clicked,
    //take the information
    //send it over to the datastore

    function saveSettingsPage(nickname, about_me) {
        $.getJSON("/settingsUserSave/" + nickname +"/"+about_me, function(data) {
        }).done(function(data) {
                console.log(data);
            })
            .fail(function() {
                console.log("Could not get data");
            });
    }

});

