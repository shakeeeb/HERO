<%--
  Created by IntelliJ IDEA.
  User: Ben
  Date: 4/8/16
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
    <title>Hero - Last Page In Chapter</title>
    <link rel="stylesheet" href="/resources/css/ben.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <%@ include file="/resources/layouts/styles.jsp" %>
    <%@ include file="/resources/layouts/scripts.jsp" %>

    <script src="/resources/scripts/jquery/last-page-handler.js"></script>
<body id="admin-page-body">
    <div class="container">
        <%@ include file="/resources/layouts/navbar.jsp" %>
    </div>
        </br></br></br>
    <h1 class="greeting text-left">${chapterName}</h1>
    <div class="row text-center">
        <div id="page-reader-button-container">
            <i id="last-read-page-chapter-index"class="fa fa-list fa-th-large"></i>
            <i id="last-read-page-replay" class="fa fa-rotate-left fa-th-large"></i>
            <i id="last-read-page-next-chapter" class="fa fa-arrow-circle-o-right fa-th-large"></i>
        </div>
    </div>
    <div id="last-read-page-main"class="row">
        <div>
                <%--Comments Go here--%>
        </div>
    </div>
    <div style="display: none;">
        <p id="hidden-nextChapterID">${nextChapterID}</p>
        <p id="hidden-nextPageID">${nextPageID}</p>
        <p id="hidden-rootID">${rootID}</p>
        <p id="hidden-seriesID">${seriesID}</p>
        <p id="hidden-chapterID">${chapterID}</p>
    </div>

</body>
</html>