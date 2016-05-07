$(document).ready(function() {
    var subscriptions;
    var recentlyViewed;
    var template;
    loadDashboard();
    function loadDashboard() {

        $.getJSON("/dashboard/get/", function(data) {
        }).done(function(data) {
            subscriptions = data.members.subscriptions.elements;
            recentlyViewed = data.members.recentlyViewed.elements;

            console.log(subscriptions);
            console.log(recentlyViewed);
            loadEverything(subscriptions);

        }).fail(function() {
            console.log("Could not get data");
        });
    }

    function loadEverything(subscriptions) {
        loadSubscriptions(subscriptions);
        loadRecentlyViewed(recentlyViewed);
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
        if (recentlyViewed.length > 0) {
            template = $('#hidden-template').html();
            for (var i = 0; i < numRecentlyViewed; i++) {
                // Template that shit
                var item = $(template).clone();
                $("#recent-container").append(item);
            }
        }
    }

});

