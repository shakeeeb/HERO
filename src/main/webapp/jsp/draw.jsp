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
    <title>Hero - Drawing Page</title>
    <%@ include file="/resources/layouts/styles.jsp" %>
    <%@ include file="/resources/layouts/scripts.jsp" %>
</head>
<body>
<div class="container">
    <%@ include file="/resources/layouts/navbar.jsp" %>
    <br><br><br>
    <div id="chapter-form">
        <form action="#" class="drawing-upload-form">
            <div id="drawing-upload-container" class="form-group">
                <label for="drawing-upload-input">Select File to Upload</label>
                <br>
                <input id="drawing-upload-input" type="file" name="coverupload" value="upload">
            </div>
            <br>
        </form>
    </div>
    <br>

    <br><br><br><br><br><br>
    <br><br><br><br><br><br>
    <br><br><br><br><br><br>
    <div>
        <!--Drawing Application here-->
    </div>
    <br><br><br><br><br><br>
    <br><br><br><br><br><br>
    <br><br><br><br><br><br>

    <div id="chapter-form">
        <form action="#" class="chapter-form">
            <button id="save-drawing" type="submit" class="btn btn-default">Save</button>
        </form>
    </div>

</div>
</body>
</html>
