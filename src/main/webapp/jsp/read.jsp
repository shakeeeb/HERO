<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 3/31/16
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="myFirstModule">
<head>
    <title>Hero - Reader-Page</title>
    <link rel="stylesheet" href="/resources/css/ben.css">
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <%@ include file="/resources/layouts/styles.jsp" %>
    <script src="/resources/scripts/jquery/readHandler.js"></script>
</head>
<body id="reader-page-body">
<%@ include file="/resources/layouts/navbar.jsp" %>
<br><br>

<div class="container">

    <div class="col-md-8 col-md-offset-2 page-header orange-span" id="page-read-header">
        <h2 id="comic-name-reader-page"> ${chapterID} </h2>
        <h2 id="chapter-name-reader-page">Chapter Name</h2>
        <h2 id="page-number-reader-page">Page Number</h2>
    </div>

    <div class="col-md-8 col-md-offset-2" id="page-reader-main">
        <form action="/read" method="get" role="button" name="read-form">
            <div id="page-image" onclick="document.forms['read-form'].submit();">
                <img
                     src="${imagePath}">
            </div>
        </form>
    </div>

    <div>
    </div>


</div>
</body>
</html>