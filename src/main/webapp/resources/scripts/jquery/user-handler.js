/**
 * Created by mk on 4/21/16.
 */

$(document).ready(function() {
    var currentUserEmail = null;
    var about_me;
    var nickname;
    var series_list;
    currentUserEmail = $("#userEmail").html();
    console.log("Loading the page!");
    //loadPage(currentUserEmail);
    //http://localhost:8080/user/

    var webpage_url;
    webpage_url = window.location.href;
    webpage_url = webpage_url.slice(webpage_url.indexOf("?")+1, webpage_url.length);
    decoded_web = decodeURI(webpage_url).replace("%40", "@").toString();
    decoded_web = decoded_web.trim();



    $(document).on("click",".user-story-title", function () {
        //seriesID

        var saving_series = $(this).text();
        console.log(saving_series);
        window.location.replace("/chapter-index/"+saving_series);
    });



    loadPage(decoded_web);
    function loadPage(userToLookUpEmail) {
        $.getJSON("/user/get/",{"userToLookUpEmail" : userToLookUpEmail}, function(data) {
        }).done(function(data) {

                console.log(data);
                nickname = data.members.nickname.value;
                about_me = data.members.about_me.value;

                //for loop to get all the user series into the arraylist
                length_of_array = data.members.series_list.elements.length;

                for (i = 0; i <length_of_array; i++) {
                    series_list = data.members.series_list.elements[i];
                }

                console.log("CAT ARE COOL.");
                console.log(series_list);


                $("#user-information").html(nickname +"<br><br><br>"+ about_me);

                console.log(data.members.series_list.elements[0].members.name.value);
                console.log(data.members.series_list.elements[0].members.description.value);

                //create the rest of the series to show in the div with the list of series
                for (var l = 0; l < length_of_array; l++)
                {
                    var seriesHTML = "<span class=\"one-story\" id=\"authored-story-"+i +"\">" +
                        "<span class=\"author-story-wrap\" id=\"authored-story-"+i +"-image\">"+
                        "<span class=\"author-story-wrap\" id=\"authored-story-"+i+"-information\">"    +
                        "<span class=\"black-span white-boxes user-story-title\" id=\"authored-story-"+i+"-title\">"
                           + data.members.series_list.elements[l].members.description.value +
                        "</span>"  +
                        "<span class=\"black-span white-boxes user-story-tags\" id=\"authored-story-"+i+"-tags\" >"   +

                    data.members.series_list.elements[l].members.name.value+
                        "</span></span></span><br>";
                    $("#all_the_series").append(seriesHTML);
                }

            })
            .fail(function() {
                console.log(userToLookUpEmail);
                console.log("Could not get data");
            });
    }

});

