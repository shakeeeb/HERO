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
    <title>Hero - Chapter Workspace</title>
    <%@ include file="/resources/layouts/styles.jsp" %>
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <link rel="stylesheet" href="/resources/css/terrell.css">
</head>
<body>
    <div id="chapter-workspace-container">
        <div id="chapter-form">
            <form action="#" class="chapter-form">
                <div class="form-group">
                    <input id="title-input" type="text" name="title" value="Title">
                </div>
                <br>
                <div class="form-group">
                    <input id="summary-input" type="text" name="summary" value="Summary of Chapter">
                </div>
                <div class="form-group">
                    <label for="cover-upload-input">Cover Page Upload</label>
                    <br>
                    <input id="cover-upload-input" type="file" name="coverupload" value="upload">
                </div>
                <br>

                <button id="save-chapter-button" type="submit" class="btn btn-default">save</button>
                <br>
                <br>
                <button id="cancel-chapter-button" type="button" class="btn btn-default btn-sm">cancel</button>
            </form>
        </div>
        <div id="chapter-page-flow">
            <h3>Chapter Workspace</h3>
            <div id="page-flow">

            </div>

        </div>
    </div>
</body>
</html>
