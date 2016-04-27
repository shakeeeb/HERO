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
                        <button type="submit" class="btn btn-default series-overview-edit-draft-button">Edit Draft</button>
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

    <br><br><br>
    <div>
        <row>
            <div id="series-overview-name" class="greeting">${seriesName}</div>
            <div id="series-overview-author" class="greeting">${seriesAuthor}</div>
        </row>
    </div>
    <div id="result-container">
        <%--<div id="series-authored-story-1" class="orange-span one-story">--%>
                <%--<span id="authored-story-1-image" class="author-story-wrap">--%>
                    <%--<img src="/resources/images/logo.png" height="122px" width="122px">--%>
                <%--</span>--%>
                <%--<span id="series-overview-story-1-information" class="author-story-wrap">--%>
                    <%--<span id="series-overview-chap-title" class="blue-box series-num-chapters">--%>
                        <%--Series Overview Chapter Title--%>
                    <%--</span>--%>
                    <%--<span>--%>
                    <%--<button id="edit-draft-button" type="submit" class="btn btn-default">Edit Draft</button>--%>
                    <%--</span>--%>
                <%--</span>--%>
        <%--</div>--%>
    </div>
</div>
</body>
</html>