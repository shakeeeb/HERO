<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 3/31/16
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Hero - Dashboard</title>
    <%@ include file="/resources/layouts/styles.jsp" %>
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <link rel="stylesheet" href="/resources/css/ben.css">
    <script src="/resources/scripts/jquery/dashboard-handler.js"></script>
    <script id="hidden-template" type="text/x-custom-template">
        <div class="new-dash-picture">
            <br>
            <div id="dashboard-paragraph" class="container">
                <h1 class="dashboard-img"></h1>
            </div>
        </div>
    </script>
</head>
<body>
<div class="container till_font">
    <%@ include file="/resources/layouts/navbar.jsp" %>
    <br><br><br><br><br><br>
    <div id="dashboard-greeting">Hello, ${nickname}!</div>
    <button type="button" id="dashboard-my-series" class="btn till_font">My Series!</button>

    <div id="dashboard-subscription" class="orange-span">
        <div id="dashboard-subscription-text" class="orange-span dashboard-text">
            <div class="dashboard-text till_font">Subscription</div>
            <br>
        </div>
        <div id="subscription-container" class="dashboard-holders">
        </div>

    </div>
    <br><br>
    <div id="dashboard-recent" class="orange-span">
        <div id="dashboard-recent-text" class="orange-span dashboard-text">
            <div class="dashboard-text till_font">Recent</div>
            <br>
        </div>
        <div id="recent-container" class="dashboard-holders">
        </div>
    </div>
    <br><br>
    <div id="dashboard-suggestion" class="orange-span">
        <div id="dashboard-suggestion-text" class="orange-span dashboard-text">
            <div class="dashboard-text till_font">Suggestion</div>
            <br>
        </div>
        <div id="suggestion-container"class="dashboard-holders">

        </div>
    </div>
</div>

<div style="display: none;">
    <p id="hidden-user-email">${user-email}</p>
</div>
</body>
</html>
