/**
 * Created by shakeeb on 4/12/16.
 */
$(document).ready(function () {

   var query = $("#test2").text();

    $.get("/search/" + query)
        .done(function(data){
            // assume that the data that has been returned is html
            // just add it to the page using the selector
            $("#result-list").html(data);

            // this is just a testing function
            $(".search-result").click(function() {
                console.log("hello there");
                this.getAttribute("id");
                console.log(this.getAttribute("id"));
                $(location).attr('href', "/series-overview/"+this.getAttribute("id"));

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

});
