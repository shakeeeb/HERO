$(document).ready(function () {

    var pendingApproval;
    var template;
    var author;
    var chapterName;
    var srcImage;
    var reportedComics;

    getSubmittedandReportedComics();

    function getSubmittedandReportedComics() {
        $.getJSON("/admin/get/comicInformation", function(data) {
        }).done(function (data) {
            pendingApproval = data.members.pendingApproval.elements;
            console.log(pendingApproval);
            reportedComics = data.members.reportedComics.elements;
            console.log(reportedComics);


            template = $('#hidden-template').html();
            template2 = $('#hidden-template2').html();

            for (var i = 0; i < pendingApproval.length; i++)
            {
                var item = $(template).clone();
                author = pendingApproval[i].members.author.members.key.members.raw.members.name.value;
                chapterName = pendingApproval[i].members.name.value;

                $(item).find("#author").html(author);
                $(item).find("#title").html(chapterName);

                $("#left").append(item);
            }

            for (var i = 0; i < reportedComics.length; i++)
            {
                var item = $(template2).clone();
                author = reportedComics[i].members.author.members.key.members.raw.members.name.value;
                chapterName = reportedComics[i].members.name.value;

                $(item).find("#author2").html(author);
                $(item).find("#title2").html(chapterName);

                $("#right").append(item);
            }

        })
    }
});