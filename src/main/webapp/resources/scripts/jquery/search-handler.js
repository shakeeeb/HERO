/**
 * Created by shakeeb on 4/12/16.
 */
$(document).ready(function () {

   var query = $("#test2").text();
   // $.getJSON( "/search/refine/"+query, function(data){
        //  on return
        // parse through json
      //  $(".result").html(data);
        //alert(JSON.stringify(data));
        // for each piece of data in the Json array
        // i need the title, author, and the description.
        // and a link to the series page--> meaning onClick
      //  $.each(data, function(key,value) {
            // okay cool,
        //    alert(value.authorName);
      //  });
        //alert(data)
   // });

    alert(query);
    $.get("/search/" + query)
        .done(function(data){
            // assume that the data that has been returned is html
            // just add it to the page using the selector
            $("#result-list").html(data);
        })
        .fail( function(){
            alert("oops");
        });

    /*
    $.getJSON("/search/refine/" + query)
        .done( function(data){
            $.each(data, function(key,value) {
                // okay cool,
                // i guess if it doesnt find anything, it just wont do anything
                alert(value.authorName);
            });
        })
        .fail(function(data){
            alert("boop");
        });
        */
    $("#submit-button").click(function() {
        $.get("/search/" + query,{"search-radio": $("[name='genre-radio']:checked").attr("value"),
        "author-input":$("#author-input").attr("value"),
        "date-radio":$("[name='date-radio']:checked").attr("value"),
        "rating-radio":$("[name='rating-radio']:checked").attr("value"),
        "tag":$("#tag-input").attr("value")
        })
            .done(function(data) {
                alert(data);
                $("#result-list").html(data);
            })
            .fail(function() {
                alert("it didn't work");
            });
    });
    //$("#refine").ajaxSubmit({url: '/search/refine/' + query, type: 'get'})
    //    .done(function(data){
    //
    //    })
    //    .fail( function(data){
    //        alert("oops");
    //    });



});
