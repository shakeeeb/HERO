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
</head>
<body>
<%@ include file="/resources/layouts/navbar.jsp" %>
<br><br><br><br>
<div class="container">

    <br><br><br>
    <div>
        <row>
            <div>Series Name</div>
            <div id="series-overview-author">By Author</div>
        </row>
    </div>
                <div id="series-authored-story-1" class="orange-span one-story">
                <span id="authored-story-1-image" class="author-story-wrap">
                    <img src="/resources/images/logo.png" height="122px" width="122px">
                </span>
                <span id="series-overview-story-1-information" class="author-story-wrap">
                    <span id="series-overview-chap-title" class="blue-box series-num-chapters">
                        Series Overview Chapter Title
                    </span>
                    <span>
                    <button id="edit-draft-button" type="submit" class="btn btn-default">Edit Draft</button>
                    </span>
                </span>



                    
            </div>
</div>
</body>
</html>