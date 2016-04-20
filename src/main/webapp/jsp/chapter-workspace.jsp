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
    <script src="/resources/scripts/widgets/workspace.js"></script>

</head>
<body>
    <div id="chapter-workspace-container">
        <div id="chapter-form">
            <form action="#" class="chapter-form">
                <div class="form-group">
                    <input id="title-input" class="center-block" type="text" name="title" placeholder="Title">
                </div>
                <div class="form-group">
                    <textarea id="summary-input" form="chapter-form" placeholder="Summary of Chapter" class="center-block"></textarea>
                </div>
                <div id="cover-upload-container" class="form-group">
                    <label for="cover-upload-input">Cover Page Upload</label>
                    <br>
                    <input id="cover-upload-input" type="file" name="coverupload" value="upload">
                </div>
                <br>

                <button id="save-chapter-button" type="submit" class="center-block btn btn-default">save</button>

                <button id="cancel-chapter-button" type="button" class="center-block btn btn-default btn-sm">cancel</button>
            </form>
        </div>
        <div id="chapter-page-flow">
            <h3>Chapter Workspace</h3>
            <div id="page-flow">
                <table id="page-table" class="center-block-">
                    <tr id="chapter-row-1" class="chapter-level">
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                    </tr>

                    <tr id="chapter-row-2" class="chapter-level">
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="add-page" type="button">new</button></td>
                    </tr>

                    <tr id="chapter-row-3" class="chapter-level chapter-level-bottom">
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="add-page" type="button">new</button></td>
                    </tr>


                </table>
            </div>
        </div>
    </div>
</body>
</html>
