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

<%@ include file="/resources/layouts/navbar.jsp" %>
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

        </form>
    </div>

    <div id="svgContainer" style="margin: 50px 50px;">
        <svg id="svg1" width="0" height="0" >
            <%--<path id="1-2-connector"--%>
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
        </svg>
    </div>

    <div id="chapter-page-flow">
        <h3>Chapter Workspace</h3>
        <div id="page-flow">


            <table id="page-table" class="center-block-">
                <tr id="chapter-level-0" class="chapter-level">
                    <td><button class="chapter-page hidden-page" type="button"></button></td>
                    <td><button class="chapter-page hidden-page" type="button"></button></td>
                    <td><div class="chapter-page hidden-page" type="button"><div class="page-options"><i class="fa fa-link" aria-hidden="true"></i> </div><div class="edit-option"><i class="fa fa-paint-brush" aria-hidden="true"></i></div></div></td>
                    <%--<td><button class="chapter-page hidden-page" type="button"></button></td>--%>
                    <td><button class="chapter-page hidden-page" type="button"></button></td>
                    <td><button class="chapter-page hidden-page" type="button"></button></td>
                </tr>


            </table>



        </div>

        <!-- Modal -->
        <div class="modal fade" id="options-modal" tabindex="-1" role="dialog" aria-labelledby="options-label">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Edit Options</h4>
                    </div>

                    <div id="options-modal-body" class="modal-body">
                        <form id="options-form" class="modal-body">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputFile">File input</label>
                                <input type="file" id="exampleInputFile">
                                <p class="help-block">Example block-level help text here.</p>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox"> Check me out
                                </label>
                            </div>
                        </form>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button id="save-options-button" type="submit" class="btn btn-primary">Save changes</button>
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
