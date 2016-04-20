$(document).ready(function(){
    var parsedURL = urlParseGetID(); // after grabbing the ID
    var query = $("#series-overview-name").text();
    alert("parsedURL=" +parsedURL);
    alert("query=" + query);
    // AJAX for Datastore Information
    $.get("/series-overview/grab/" + query)
        .done(function(data){
            alert(parsedURL);
            // append the contents
            $("#result-container").html(data);
            // after , do the thing where you turn it into an href
    })
        .fail(function(){
            alert("fuck.");
        })

    function urlParseGetID() {
        var urlString = window.location.href;
        var lastIndexOfSlash = urlString.lastIndexOf("/");
        var afterSlash = urlString.slice(lastIndexOfSlash+1, urlString.length);
        return decodeURI(afterSlash); // returns the ID
    }
})
