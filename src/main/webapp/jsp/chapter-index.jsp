<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 3/31/16
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chapter Index</title>
    <%@ include file="/resources/layouts/styles.jsp" %>
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <link rel="stylesheet" href="/resources/css/terrell.css">
    <link rel="stylesheet" href="/resources/css/ben.css">
    <script src="/resources/scripts/jquery/chapter-index-handler.js"></script>
</head>
<!-- Navigation -->
<%@ include file="/resources/layouts/navbar.jsp" %>
<br>
<br>
<br>
<!-- Image Background Page Header -->
<header class="series-banner">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
            </div>
        </div>
    </div>
</header>

<!-- Page Content -->
<div class="container">

    <div class="row">
        <div class="col-sm-8 chapter-list-container">
            <div class="chapter-list-header till_font">
                <h3 id="chapter-index-series-name"></h3>
                <h4 id="chapter-index-author"></h4>
            </div>
            <div id="chapter-index-chapter-list" class="chapter-list till_font">

            </div>
        </div>

        <div class="col-sm-4 series-info-container">
            <div class="series-description till_font">
                <p id="chapter-index-series-description">An epic hero goes on adventures</p>
            </div>

            <div class="text-center">
                <span><button id="subscribe-button"class="btn btn-default"></button></span>
            </div>
            <div class="text-center">
                <span><button id="chapter-index-start-from-beginning" class="btn btn-default start-beginning-button">Start From Beginning</button></span>
            </div>
        </div>
    </div>
    <!-- /.row -->

    <hr>

</div>

<div style="display: none;">
    <p id="hidden-seriesID">${seriesID}</p>
    <p id="hidden-rootID">${rootID}</p>
    <p id="hidden-chapterID">${chapterID}</p>
</div>
<!-- /.container -->


</body>

</html>
