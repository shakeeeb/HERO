$(document).ready(function() {
    var subscriptions;
    var recentlyViewed;
    var suggestions;

    var template;
    loadDashboard();
    function loadDashboard() {

        $.getJSON("/dashboard/get/", function(data) {
        }).done(function(data) {
            subscriptions = data.members.subscriptions.elements;
            recentlyViewed = data.members.recentlyViewed.elements;
            suggestions = data.members.suggestions.elements;

            console.log(subscriptions);
            console.log(recentlyViewed);
            console.log(suggestions);
            loadSubscriptionsAndRecentlyViewed(subscriptions, recentlyViewed);

            $("#dashboard-my-series").click(function() {
                goToSeriesWorkspace();
            });

            $(".subscription").click(function() {
                var seriesID = $(this).attr('id');
                goToChapterIndex(seriesID);
            });

            $(".recent").click(function() {
                var seriesID = $(this).attr('id');
                goToChapterIndex(seriesID);
            });

            $(".suggestions").click(function() {
                var seriesID = $(this).attr('id');
                goToChapterIndex(seriesID);
            });


        }).fail(function() {
            console.log("Could not get data");
        });
    }

    function goToSeriesWorkspace() {
        window.location.replace("../series-workspace");
    }

    function loadSubscriptionsAndRecentlyViewed(subscriptions, recentlyViewed) {
        loadSubscriptions(subscriptions);
        loadRecentlyViewed(recentlyViewed);
        loadSuggestions(suggestions);
    }

    function loadSubscriptions(allSubscriptions) {
        var numSubscriptions = 5;

        if (subscriptions.length < 5)
        {
            numSubscriptions = subscriptions.length;
        }

        template = $('#hidden-template').html();
        if (allSubscriptions.length > 0) {
            for (var i = 0; i < numSubscriptions; i++) {
                // Template that shit.
                var item = $(template).clone();
                $(item).find(".dashboard-img").attr('class', "img-responsive dashboard-img subscription");
                $(item).find(".dashboard-img").attr('id', allSubscriptions[i].members.name.value);
                var id = $(item).find(".dashboard-img").attr('id');
                $(item).find(".dashboard-img").html(id);
                $("#subscription-container").append(item);
            }
        }
    }

    function loadRecentlyViewed(recentlyViewed) {
        // Passed in array is already loaded. Just do it.
        var numRecentlyViewed = recentlyViewed.length;
        template = $('#hidden-template').html();
        if (recentlyViewed.length > 0) {
            for (var i = 0; i < numRecentlyViewed; i++) {
                // Template that shit
                var item = $(template).clone();
                $(item).find(".dashboard-img").attr('class', "img-responsive dashboard-img recent");
                $(item).find(".dashboard-img").attr('id', recentlyViewed[i].members.name.value);
                var id = $(item).find(".dashboard-img").attr('id');
                $(item).find(".dashboard-img").html(id);
                $("#recent-container").append(item);
            }
        }
    }

    function loadSuggestions(suggestions) {
        template = $('#hidden-template').html();
        var arraylen = 5;

        if (suggestions.length < 5) {
            arraylen = suggestions.length;
        }

        if (suggestions.length > 0) {
            shuffle(suggestions);
            for (var i = 0; i < arraylen; i++) {
                var item = $(template).clone();
                $(item).find(".dashboard-img").attr('class', "img-responsive dashboard-img suggestions");
                $(item).find(".dashboard-img").attr('id', suggestions[i].members.name.value);
                var id = $(item).find(".dashboard-img").attr('id');
                $(item).find(".dashboard-img").html(id);
                $("#suggestion-container").append(item);
            }
        }
    }

    function contains(viewSuggestions, obj) {
        var flag = false;
        for (var i = 0; i < viewSuggestions.length; i++)
        {
            if (viewSuggestions[i] === obj)
            {
                flag = true;
            }
        }
        return flag;
    }

    function shuffle(suggestions) {
        var j, x, i;
        for (i = suggestions.length; i; i -= 1) {
            j = Math.floor(Math.random() * i);
            x = suggestions[i - 1];
            suggestions[i - 1] = suggestions[j];
            suggestions[j] = x;
        }
    }

    function goToChapterIndex(seriesID) {
        window.location.replace("../chapter-index/" + seriesID);

    }
});

