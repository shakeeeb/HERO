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
            //suggestions = data.members.suggestions.elements;

            console.log(subscriptions);
            console.log(recentlyViewed);
            //console.log(suggestions);
            loadSubscriptionsAndRecentlyViewed(subscriptions, recentlyViewed);

            $("#dashboard-my-series").click(function() {
                goToSeriesWorkspace();
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
        //loadSuggestions(suggestions);
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
                $("#recent-container").append(item);
            }
        }
    }

    function loadSuggestions(suggestions) {
        var suggestions = suggestions.length;
        template = $('#hidden-template').html();
        if (suggestions.length > 0) {
            for (var i = 0; i < suggestions.length; i++) {
                // Template that shit
                var item = $(template).clone();
                $("#suggestion-container").append(item);
            }
        }
    }

});

