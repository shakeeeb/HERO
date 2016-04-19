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

    <script src="/resources/scripts/widgets/drawing.js"></script>

</head>
<body>
<div>
    <%@ include file="/resources/layouts/navbar.jsp" %>
    <br><br><br>
    <div>
        <!--Drawing Application here-->
        <div id="drawing-top-toolbar">

            <%--<div id="undo-redo-buttons" class="toolbar-item">--%>
            <%--<button id="undo-button">UNDO</button>--%>
            <%--<button id="redo-button">REDO</button>--%>
            <%--</div>--%>

            <form>
                <div id="top-tools-container" class="toolbar-item">
                    <div id="brush-width-slider">
                        <div class="brush-width-item" > brush width:</div>
                        <div class="brush-width-item">
                        <input type="range" title="" id="brush-width" name="width-slider" min="1" max="150">
                        </div>
                    </div>


                <div id="font-dropdown" class="dropdown toolbar-item">
                    <select title="" id="fonts">
                        <option value="1">Serif</option>
                        <option value="2">San-serif</option>
                    </select>
                    <input id="font-button" type="button" value="Change!"/>
                </div>

                <div id="color-picker" class="dropdown toolbar-item">
                    Color Selection:
                    <input id="color-button" type="color" name="favcolor" value="#ff0000">
                </div>


                    <div class="toolbar-item label-item-combo">
                        <%--<form action="#" class="drawing-upload-form">--%>
                            <%--<div id="drawing-upload-container" class="form-group">--%>
                                <label class="label-item" for="drawing-upload-input">Upload Image</label>
                                <%--<br>--%>
                                <input class="label-item" id="drawing-upload-input" type="file" name="coverupload" value="upload">
                            <%--</div>--%>
                            <%--<br>--%>
                        <%--</form>--%>
                    </div>
                </div>

                <div class="toolbar-item">
                    <input id="save-button" type="button" value="Save my drawing!"/>
                </div>

            </form>



        </div>
        <!-- /.drawing-top-toolbar -->
        <div id="drawing-container">
            <!-- Drawing -->

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

                <div id="eraser-tool" class="drawing-tool">
                    {ERASER}
                </div>

                <div id="text-tool" class="drawing-tool">
                    {TEXT}
                </div>

                <div id="shape-tool" class="drawing-tool">
                    {RECTANGLE}
                </div>

            </div>
            <!-- /.drawing-side-toolbar -->

            <!-- Drawing-canvas -->
            <div id="drawing-canvas-container">
                <%--<div id="shape-modal">--%>


                <%--</div>--%>
                <canvas id="imageCanvas" width="800" height="1307">
                    <!-- /.drawing-canvas -->

                </canvas>

            </div>

        </div>

        <!--Shape Modal-->

        <!-- ./shape-modal -->
        <!-- /.drawing-container -->
    </div>



</div>
</body>
</html>
