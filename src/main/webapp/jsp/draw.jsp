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
    <link rel="stylesheet" href="/resources/css/terrell.css">
</head>
<body>
<div>
    <%@ include file="/resources/layouts/navbar.jsp" %>
    <br><br><br>
    <div>
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
    <div>

        <!--Drawing Application here-->
        <div id="drawing-container">
            <!-- Drawing -->
            <div id="drawing-top-toolbar">
                {TOP TOOLBAR}
            </div>
            <!-- /.drawing-top-toolbar -->

            <!-- Drawing side toolbar -->
            <div id="drawing-side-toolbar">
                <div class="drawing-tool">
                    {SELECTION}
                </div>

                <div class="drawing-tool">
                    {PENCIL}
                </div>

                <div class="drawing-tool">
                    {BRUSH}
                </div>

                <div class="drawing-tool">
                    {FILL}
                </div>

                <div class="drawing-tool">
                    {ERASER*}
                </div>

                <div class="drawing-tool">
                    {LINE}
                </div>

                <div class="drawing-tool">
                    {TEXT}
                </div>

                <div class="drawing-tool">
                    {SHAPES}
                </div>

                <div class="color-wheel">
                    {COLOR SELECTOR}
                </div>



            </div>
            <!-- /.drawing-side-toolbar -->

            <!-- Drawing-canvas -->
            <div id="drawing-canvas-container">
                <canvas id="drawing-canvas">
                </canvas>

            </div>
            <!-- /.drawing-canvas -->
        </div>
        <!-- /.drawing-container -->
    </div>
    <br><br><br><br><br><br>
    <br><br><br><br><br><br>
    <br><br><br><br><br><br>

    <div>
        <form action="#">
            <button id="save-drawing" type="submit" class="btn btn-default">Save</button>
        </form>
    </div>

</div>
</body>
</html>
