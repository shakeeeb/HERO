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
</head>
<body>
<%@ include file="/resources/layouts/navbar.jsp" %>
<script src="/resources/scripts/jquery/setting-handler.js"></script>
<br><br><br>

<div class="row till_font">
    <div class="col-md-4 fixed-settings-page-column">
    </div>
    <div class="col-md-8 scrollit">
        <div class="row" id="settings-form">
            <form action="#" class="settings-form">
                <div class="row">
                    <div class="form-group">
                        <input id="nickname" class="center-block" type="text" name="nickname" placeholder="Enter Your Nickname: ">
                    </div>
                </div>
                <div class="form-group">
                    <textarea id="about-me" form="settings-form" placeholder="About Me:" class="center-block"></textarea>
                </div>
            </form>
        </div>
        <div id="entire-div-to-the-left"class="row">
            <button type="button" class="btn btn-md button-negative" id="edit-settings-cancel">Cancel</button>
            <button type="button" class="btn btn-lg button-ok" id="edit-settings-save">Save</button>
        </div>
    </div>
</div>

<div style="display:none;">
    <p id="userEmail">${userEmail}</p>
</div>

</div>

</body>
</html>