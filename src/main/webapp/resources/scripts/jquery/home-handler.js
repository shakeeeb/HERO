$(document).ready(function() {
    var template;
    var seriesTitle;

    getRecentlyUpdated();

    function getRecentlyUpdated() {

        $.getJSON("/home/getRecentlyUpdated", function(data) {
        }).done(function(data) {
            var recentlyUpdated = data.members.recentlyUpdated.elements;
            console.log(recentlyUpdated);

            if (recentlyUpdated.length === 8)
            {
                $(".item-1").attr('id', recentlyUpdated[0].members.name.value);
                //Change the attr of the src image here.
                $(".item-2").attr('id', recentlyUpdated[1].members.name.value);
                //Change the attr of the src image here.
                $(".item-3").attr('id', recentlyUpdated[2].members.name.value);
                //Change the attr of the src image here.
                $(".item-4").attr('id', recentlyUpdated[3].members.name.value);
                //Change the attr of the src image here.
                $(".item-5").attr('id', recentlyUpdated[4].members.name.value);
                //Change the attr of the src image here
                $(".item-6").attr('id', recentlyUpdated[5].members.name.value);
                //Change the attr of the src image here.
                $(".item-7").attr('id', recentlyUpdated[6].members.name.value);
                //Change the attr of the src image here.
                $(".item-8").attr('id', recentlyUpdated[7].members.name.value);
                //Change the attr of the src image here.
            }

            $(".item-1").on('click', loadItem1SeriesOverview);
            $(".item-2").on('click', loadItem2SeriesOverview);
            $(".item-3").on('click', loadItem3SeriesOverview);
            $(".item-4").on('click', loadItem4SeriesOverview);
            $(".item-5").on('click', loadItem5SeriesOverview);
            $(".item-6").on('click', loadItem6SeriesOverview);
            $(".item-7").on('click', loadItem7SeriesOverview);
            $(".item-8").on('click', loadItem8SeriesOverview);

            function loadItem1SeriesOverview() {
                var seriesID = $(".item-1").attr('id');
                window.location.replace("../series-overview/" + seriesID);
            }

            function loadItem2SeriesOverview() {
                var seriesID = $(".item-2").attr('id');
                window.location.replace("../series-overview/" + seriesID);
            }

            function loadItem3SeriesOverview() {
                var seriesID = $(".item-3").attr('id');
                window.location.replace("../series-overview/" + seriesID);
            }

            function loadItem4SeriesOverview() {
                var seriesID = $(".item-4").attr('id');
                window.location.replace("../series-overview/" + seriesID);
            }

            function loadItem5SeriesOverview() {
                var seriesID = $(".item-5").attr('id');
                window.location.replace("../series-overview/" + seriesID);
            }

            function loadItem6SeriesOverview() {
                var seriesID = $(".item-6").attr('id');
                window.location.replace("../series-overview/" + seriesID);
            }

            function loadItem7SeriesOverview() {
                var seriesID = $(".item-7").attr('id');
                window.location.replace("../series-overview/" + seriesID);
            }

            function loadItem8SeriesOverview() {
                var seriesID = $(".item-8").attr('id');
                window.location.replace("../series-overview/" + seriesID);
            }


        }).fail(function() {
            console.log("Could not get data");
        });
    }

});

