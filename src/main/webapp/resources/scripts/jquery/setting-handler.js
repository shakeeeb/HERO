/**
 * Created by mk on 4/21/16.
 */

$(document).ready(function() {
    var currentUserEmail = null;

    currentUserEmail = $("#userEmail").html();

    loadPage(currentUserEmail);
    //you want to load information from the datastore
    //function loadPage(userID) {
    //    console.log("I'm loading the user information.");
    //    console.log(userID);
    //    $.get("/settingsUser/" + userID)
    //        .done(function(data){
    //            // assume that the data that has been returned is html
    //            // just add it to the page using the selector
    //            //console.log(data.nickname);
    //            //$("#nickname").html(data.nickname);
    //            //$("#about-me").html(data.aboutMe);
    //            console.log(data);
    //        })
    //        .fail(function(){
    //            console.log("Cannot get the user back from the datastore");
    //        });
    //
    //}
    var nickname;
    function loadPage(userEmail) {
        $.getJSON("/settingsUser/get/" + userEmail, function(data) {
        }).done(function(data) {

            console.log(data);
            nickname = data.members.nickname.value;
            var modifiedNickname;


        $("#nickname").attr("placeholder", nickname);

            //var aboutMe = data.members.aboutMe;

                alert("Nickname: " + nickname);

            $("#edit-settings-save").click(function(){
                nickname = "T Mack";
                saveSettingsPage(nickname);

                //alert("Going to save this page");
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

    function saveSettingsPage(nickname) {
        $.getJSON("/settingsUserSave/" + nickname, function(data) {
        }).done(function(data) {

                console.log(data);


                //$("#nickname").attr("placeholder", nickname);
                //var aboutMe = data.members.aboutMe;
                alert("Nickname: " + nickname);
                //    alert("aboutMe: " + aboutMe);

            })
            .fail(function() {
                console.log("Could not get data");
            });
    }


});

