/**
 * Created by Ben on 4/12/16.
 */


$(document).ready(function () {

    var chapterID = $("#comic-name-reader-page").text();
    alert(chapterID);

    $.get("/read/get/"+chapterID, function(data){
        //alert("test");
        //$(".result").html(data);
        //alert(data);
    });
});
