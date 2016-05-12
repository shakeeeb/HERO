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
    <script src="/resources/scripts/jquery/dashboard-handler.js"></script>
    <script id="hidden-template" type="text/x-custom-template">
        <div class="new-dash-picture">Picture1</div>
    </script>
</head>
<body>
<div class="container">
    <%@ include file="/resources/layouts/navbar.jsp" %>
    <br><br><br><br><br><br>
    <div id="dashboard-greeting">Hello, ${nickname}!</div>
    <button type="button" id="dashboard-my-series" class="btn till_font">My Series!</button>

    <div id="dashboard-subscription" class="orange-span">
        <div id="dashboard-subscription-text" class="orange-span">
            <div class="dashboard-text till_font">Subscription</div>
            <div id="close-subscription" class="dashboard-close">X</div>
            <br>
        </div>
        <div id="subscription-container" class="dashboard-holders">
        </div>

    </div>
    <br><br>
    <div id="dashboard-recent" class="orange-span">
        <div id="dashboard-recent-text" class="orange-span">
            <div class="dashboard-text till_font">Recent</div>
            <div id="close-recent" class="dashboard-close">X</div>
            <br>
        </div>
        <div id="recent-container" class="dashboard-holders">
        </div>
    </div>
    <br><br>
    <div id="dashboard-suggestion" class="orange-span">
        <div id="dashboard-suggestion-text" class="orange-span">
            <div class="dashboard-text till_font">Suggestion</div>
            <div id="close-suggestion" class="dashboard-close">X</div>
            <br>
        </div>
        <div id="suggestion-container"class="dashboard-holders">
            <div class="new-dash-picture">Picture1</div>
            <div class="new-dash-picture">Picture2</div>
            <div class="new-dash-picture">Picture1</div>
            <div class="new-dash-picture">Picture2</div>
            <div class="new-dash-picture">Picture2</div>

        </div>
    </div>
</div>

<div style="display: none;">
    <p id="hidden-user-email">${user-email}</p>
</div>
</body>
</html>
