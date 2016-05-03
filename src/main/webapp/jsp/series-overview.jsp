<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 3/31/16
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hero - Series Overview</title>
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <%@ include file="/resources/layouts/styles.jsp" %>
    <link rel="stylesheet" href="/resources/css/ben.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="/resources/scripts/jquery/series-overview-handler.js"></script>

    <script id="hidden-template" type="text/x-custom-template">
        <div id="series-authored-story-1" class="orange-span one-story">
                <span id="authored-story-1-image" class="author-story-wrap">
                    <img src="/resources/images/logo.png" height="122px" width="122px">
                </span>
                <span id="series-overview-story-1-information" class="author-story-wrap">
                    <span id="series-overview-chap-title" class="blue-box series-num-chapters">
                        Series Overview Chapter Title
                    </span>
                    <span>
                        <button type="submit" class="btn btn-default series-overview-delete-chapter-button">X</button>
                        <button type="submit" class="btn btn-default series-overview-edit-draft-button">Edit Draft
                        </button>
                    </span>
                </span>
        </div>
        <br>
    </script>
</head>
<body>
<%@ include file="/resources/layouts/navbar.jsp" %>
<br><br><br><br>
<div class="container">
        <div id="series-overview-name" class="greeting">${seriesName}</div>
        <button type="button" class="btn btn-default series-overview-add-chapter-button pull-right" data-toggle="modal"
                data-target="#myModal">Add Chapter
        </button>
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Add Chapter</h4>
                    </div>
                    <div class="modal-body">
                        <div>
                            <div class="form-group">
                                <label for="chapterTitle">Chapter Title </label>
                                <input type="text" class="form-control input-lg" id="chapterTitle">
                            </div>
                            <div class="form-group">
                                <label for="chapterDescription">Chapter Description</label>
                                <textarea class="form-control" rows="7" id="chapterDescription"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default series-overview-create-chapter" data-dismiss="modal">Create Chapter</button>
                    </div>
                </div>
            </div>
        </div>
</div>
<div id="result-container">

</div>
</div>
</body>
</html>