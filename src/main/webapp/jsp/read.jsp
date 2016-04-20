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
    <title>Hero - Reader-Page</title>
    <link rel="stylesheet" href="/resources/css/ben.css">
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <%@ include file="/resources/layouts/styles.jsp" %>
    <script src="/resources/scripts/jquery/read-handler.js"></script>

</head>
<body id="reader-page-body">
<%@ include file="/resources/layouts/navbar.jsp" %>
<br><br>

<div class="container">

    <div class="col-md-8 col-md-offset-2 page-header orange-span" id="page-read-header">
        <h2 id="comic-name-reader-page"></h2>
        <h2 id="chapter-name-reader-page"></h2>
        <h2 id="page-number-reader-page"></h2>
    </div>
    <div id="pageImage" class="row">
            <div id="page-reader-main" class="col-md-8 col-md-offset-2">
                <img id="page-reader-example-img"
                     src="">
            </div>
    </div>

    <div id="option-container" class="container-fluid">
        <%--<div class="row">--%>
            <%--<button id="option1" type="button" class="btn">Insert Option Text Here.</button>--%>
        <%--</div>--%>
        <%--<div class="row">--%>
            <%--<button id="option2" type="button" class="btn">Insert Option Text Here.</button>--%>
        <%--</div>--%>
    </div>




</div>

</body>
</html>