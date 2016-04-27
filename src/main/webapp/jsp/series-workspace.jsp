<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 3/31/16
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hero - Series Workspace</title>
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <%@ include file="/resources/layouts/styles.jsp" %>
</head>
<body>
<%@ include file="/resources/layouts/navbar.jsp" %>
<script src="/resources/scripts/jquery/series-workspace.js"></script>
<br><br><br><br>
<div class="container">
    <div class="col-lg-offset-1 col-lg-11">
        Series Workspace
    </div>

    <div class="orange-span series-workspace-main col-lg-offset-1 col-lg-11">

        <br><br>
            <span id="series-authored-story-1" class="orange-span one-story">
                <span id="authored-story-1-image" class="author-story-wrap">
                    <img src="/resources/images/logo.png" height="100px" width="100px">
                </span>
                <span id="authored-story-1-information" class="author-story-wrap">
                    <span id="series-authored-chap-nums" class="blue-box series-num-chapters">
                        Number of Chapters
                    </span>

                    <span id="authored-story-1-tags" class="blue-box completeness">
                        Ongoing / Complete
                    </span>
                </span>

                    <br>
                    <button type="button" class="btn overview-button">Overview</button>

            </span>

        <br><br><br><br>

    </div>

    <div class="new-series-button">
        <br>
        <button type="button" class="btn">+ New Series</button>
    </div>
</div>
</body>
</html>
