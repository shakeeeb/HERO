/**
 * Created by shakeeb on 4/12/16.
 */
$(document).ready(function () {

   var query = $("#test2").text();
    $.get( "/search/refine/"+query, function(data){
        //  on return
        // parse through json
        $(".result").html(data);
       alert(data)
    });
});
