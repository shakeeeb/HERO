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
    }

    function loadSubscriptions(allSubscriptions) {
        var numSubscriptions = 5;

        if (subscriptions.length < 5)
        {
            numSubscriptions = subscriptions.length;
        }

        template = $('#hidden-template').html();
        for (var i = 0; i < numSubscriptions; i++)
        {
            // Template that shit.
            if (allSubscriptions.length > 0)
            {
                var item = $(template).clone();

                $("#subscription-container").append(item);

            }
        }


    }

});

