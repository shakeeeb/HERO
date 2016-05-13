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
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
    <div id="chapter-workspace-container" class="till_font">
        <div id="chapter-form">
            <form onsubmit="updateChapter()" class="chapter-form">
                <div class="form-group">
                    <input id="title-input" class="center-block" type="text" name="title" placeholder="Title">
                </div>
                <div class="form-group">
                    <textarea id="summary-input" form="chapter-form" placeholder="Summary of Chapter" class="center-block"></textarea>
                </div>
                <%--<div id="cover-upload-container" class="form-group">--%>
                    <%--<label for="cover-upload-input">Cover Page Upload</label>--%>
                    <%--<br>--%>
                    <%--<input id="cover-upload-input" type="file" name="coverupload" value="upload">--%>
                <%--</div>--%>
                <br>

                <button id="save-chapter-button" type="submit" class="center-block btn btn-default">save</button>

                <button id="cancel-chapter-button" type="button" class="center-block btn btn-default btn-sm">cancel</button>
            </form>
        </div>

        <div id="svgContainer" style="margin: 50px 50px;">
            <%--<svg id="svg1" width="0" height="0" >--%>
                <%--<path id="0-1-connector"--%>
                        <%--id="path1"--%>
                        <%--d="M0 0"--%>
                        <%--stroke="#000"--%>
                        <%--fill="none"--%>
                        <%--stroke-width="6px";/>--%>

                <%--<path--%>
                        <%--id="1-2-connector"--%>
                        <%--d="M0 0"--%>
                        <%--stroke="#000"--%>
                        <%--fill="none"--%>
                        <%--stroke-width="6px";/>--%>

                <%--<path--%>
                        <%--id="1-3-connector"--%>
                        <%--d="M0 0"--%>
                        <%--stroke="#000"--%>
                        <%--fill="none"--%>
                        <%--stroke-width="6px";/>--%>
            <%--</svg>--%>
        </div>

        <div id="chapter-page-flow">
            <h3>Chapter Workspace</h3>
            <div id="page-flow">


                <table id="page-table" class="center-block-">
                    <tr id="chapter-level-0" class="chapter-level">
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                        <td><button class="chapter-page hidden-page" type="button"></button></td>
                    </tr>


                </table>



            </div>

            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header no-line">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Add Page</h4>
                        </div>
                        <div class="modal-body light-grey-boxes">

                        </div>
                        <div class="modal-footer light-grey-boxes">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button id="add-modal-save-button" type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="display: none;">
        <p id="hidden-chapterID">${chapterID}</p>
    </div>
</body>
</html>
