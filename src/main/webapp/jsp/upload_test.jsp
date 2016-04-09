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

    <!-- Get version 1.1.0 of Fabric.js from CDN -->
    <script src="/resources/scripts/fabric/fabric.js"></script>

    <script src="/resources/scripts/widgets/upload.js"></script>

</head>
<body>
<div>
    <%@ include file="/resources/layouts/navbar.jsp" %>
    <br><br><br>
    <div>
        <!--Drawing Application here-->
        <div id="drawing-container">
            <!-- Drawing -->
            <div id="drawing-top-toolbar">

                <div id="undo-redo-buttons" class="toolbar-item">
                    <button id="undo-button">UNDO</button>
                    <button id="redo-button">REDO</button>
                </div>

                <div id="top-tools-container" class="toolbar-item">


                    <div id="brush-width-slider">
                        <input type="range" name="width-slider" min="1" max="150">
                    </div>

                </div>

                <div id="font-dropdown" class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" onchange="fontChange()">Fonts
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li value="ariel"><a href="#">Ariel</a></li>
                        <li value="courier"><a href="#">Courier</a></li>
                        <li value="timess"><a href="#">Times</a></li>
                    </ul>
                </div>



            </div>
            <!-- /.drawing-top-toolbar -->

            <!-- Drawing side toolbar -->
            <div id="drawing-side-toolbar">
                <div id="selection-tool" class="drawing-tool">
                    {SELECTION}
                </div>

                <div id="pencil-tool" class="drawing-tool">
                    {PENCIL}
                </div>

                <div class="drawing-tool">
                    {BRUSH}
                </div>

                <div class="drawing-tool">
                    {ERASER*}
                </div>

                <div class="drawing-tool">
                    {LINE}
                </div>

                <div id="text-tool" class="drawing-tool">
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
                <canvas id="imageCanvas" width="800" height="1307"></canvas>

            </div>
            <!-- /.drawing-canvas -->
        </div>
        <!-- /.drawing-container -->
    </div>

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

</div>
</body>
</html>
