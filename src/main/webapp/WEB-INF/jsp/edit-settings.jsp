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
<br><br><br>

<div class="row">
    <div class="col-md-4 fixed-settings-page-column">
        <div class="container">
            <div class="row">
                <img id="settings-profile-picture"src="https://placeholdit.imgix.net/~text?txtsize=23&txt=250×350&w=250&h=350">
            </div>
            <div class="row">
                <button type="button" class="btn btn-md" id="upload-profile-picture">Upload Profile Picture</button>
            </div>
            <div class="row">
                <button type="button" class="btn btn-md button-negative" id="edit-settings-cancel">Cancel</button>
            </div>
            <div class="row">
                <button type="button" class="btn btn-lg button-ok" id="edit-settings-save">Save</button>
            </div>
        </div>
    </div>
    <div class="col-md-8 scrollit">
        <div class="row" id="settings-form">
            <form action="#" class="settings-form">
                <div class="form-group">
                    <input id="nickname" class="center-block" type="text" name="nickname" placeholder="Enter Your Nickname: ">
                </div>
                <div class="form-group">
                    <textarea id="about-me" form="settings-form" placeholder="About Me:" class="center-block"></textarea>
                </div>
            </form>
        </div>
        <div class="row">

        </div>
    </div>
</div>

</body>
</html>