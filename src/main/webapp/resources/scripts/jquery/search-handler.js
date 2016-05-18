/**
 * Created by shakeeb on 4/12/16.
 */
$(document).ready(function () {

    //if someone pressed the username of the person on the page
    //go to the user page with the username in the url

    //$(document).on("click",".result-author", function () {
    //    console.log(this);
    //    var email = $("#series_author").text();
    //    var url = "/user?" + encodeURIComponent(email.trim());
    //    window.location.href = url;
    //});

   var query = $("#test2").text();

    $.get("/search/" + query)
        .done(function(data){
            // assume that the data that has been returned is html
            // just add it to the page using the selector
            $("#result-list").html(data);

            $(".go-to-chapter-index").click(function() {
                var seriesID = $(".go-to-chapter-index").attr("id");
                window.location.replace("../chapter-index/" + seriesID);

            });
        })
        .fail( function(){
            console.log("Search isn't working for the append");
        });

    $("#submit-button").click(function() {
        $.get("/search/" + query,{"search-radio": $("[name='genre-radio']:checked").attr("value"),
        "author-input":$("#author-input").attr("value"),
        "date-radio":$("[name='date-radio']:checked").attr("value"),
        "rating-radio":$("[name='rating-radio']:checked").attr("value"),
        "tag":$("#tag-input").attr("value")
        })
            .done(function(data) {
                console.log(data);
                $("#result-list").html(data);
            })
            .fail(function() {
                console.log("it didn't work");
            });
    });

    //$(".go-to-chapter-index").click(function() {
    //    alert("hey");
    //   var seriesID = $("this").attr('id');
    //   // alert(seriesID);
    //});
});
