$(document).ready(function() {
    var template;
    var seriesTitle;

    getRecentlyUpdated();

    //$(".")

    function getRecentlyUpdated() {

        $.getJSON("/home/getRecentlyUpdated", function(data) {
        }).done(function(data) {
            var recentlyUpdated = data.members.recentlyUpdated.elements;
            console.log(recentlyUpdated);

            if (recentlyUpdated.length === 8)
            {
                $(".item-1").attr('id', recentlyUpdated[0].members.name.value);
                var item1id =$(".item-1").attr('id');
                $(".item-1").text(item1id);

                $(".item-2").attr('id', recentlyUpdated[1].members.name.value);
                var item2id =$(".item-2").attr('id');
                $(".item-2").text(item2id);

                $(".item-3").attr('id', recentlyUpdated[2].members.name.value);
                var item3id =$(".item-3").attr('id');
                $(".item-3").text(item3id);

                $(".item-4").attr('id', recentlyUpdated[3].members.name.value);
                var item4id =$(".item-4").attr('id');
                $(".item-4").text(item4id);

                $(".item-5").attr('id', recentlyUpdated[4].members.name.value);
                var item5id =$(".item-5").attr('id');
                $(".item-5").text(item5id);

                $(".item-6").attr('id', recentlyUpdated[5].members.name.value);
                var item6id =$(".item-6").attr('id');
                $(".item-6").text(item6id);

                $(".item-7").attr('id', recentlyUpdated[6].members.name.value);
                var item7id =$(".item-7").attr('id');
                $(".item-7").text(item7id);

                $(".item-8").attr('id', recentlyUpdated[7].members.name.value);
                var item8id =$(".item-8").attr('id');
                $(".item-8").text(item8id);
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
                window.location.replace("../chapter-index/" + seriesID);
            }

            function loadItem2SeriesOverview() {
                var seriesID = $(".item-2").attr('id');
                window.location.replace("../chapter-index/" + seriesID);
            }

            function loadItem3SeriesOverview() {
                var seriesID = $(".item-3").attr('id');
                window.location.replace("../chapter-index/" + seriesID);
            }

            function loadItem4SeriesOverview() {
                var seriesID = $(".item-4").attr('id');
                window.location.replace("../chapter-index/" + seriesID);
            }

            function loadItem5SeriesOverview() {
                var seriesID = $(".item-5").attr('id');
                window.location.replace("../chapter-index/" + seriesID);
            }

            function loadItem6SeriesOverview() {
                var seriesID = $(".item-6").attr('id');
                window.location.replace("../chapter-index/" + seriesID);
            }

            function loadItem7SeriesOverview() {
                var seriesID = $(".item-7").attr('id');
                window.location.replace("../chapter-index/" + seriesID);
            }

            function loadItem8SeriesOverview() {
                var seriesID = $(".item-8").attr('id');
                window.location.replace("../chapter-index/" + seriesID);
            }




        }).fail(function() {
            console.log("Could not get data");
        });
    }

});

