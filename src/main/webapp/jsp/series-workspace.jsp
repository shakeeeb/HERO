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
    <title>Hero - Series Workspace</title>
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <%@ include file="/resources/layouts/styles.jsp" %>
    <link rel="stylesheet" href="/resources/css/ben.css">
    <script id="hidden-template" type="text/x-custom-template">
        <br>
        <span id="series-authored-story-1" class="light-grey-boxes black-span one-story series-authored-story">
                <span id="authored-story-1-image" class="author-story-wrap">
                    <img src="/resources/images/logo.png" height="100px" width="100px">
                </span>
                <span id="authored-story-1-information" class="author-story-wrap">
                    <span id="series-authored-chap-nums" class="white-boxes blue-box series-num-chapters">
                        Number of Chapters
                    </span>

                    <span id="authored-story-1-tags" class="white-boxes blue-box completeness">
                        Ongoing / Complete
                    </span>
                </span>

                    <br>
            <div class="container-fluid">
                <button type="button" class="btn overview-button">Overview</button>
                 <button type="button" class="btn delete-series-button">Delete</button>
             </div>


        </span>
    </script>

</head>
<body>
<%@ include file="/resources/layouts/navbar.jsp" %>
<script src="/resources/scripts/jquery/series-workspace.js"></script>
<br><br><br><br>
<div class="container series-container">
    <div class="col-lg-offset-1 col-lg-11 series-header">
        Series Workspace
    </div>
    <div class="black-span series-workspace-main col-lg-offset-1 col-lg-11">
    </div>

    <div class="new-series-button">
        <br>
        <button type="button" id="new-series" class="btn" data-toggle="modal" data-target="#myModal" >+ New Series</button>

        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header no-line">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title text-center text-bolding">Add Series</h4>
                    </div>
                    <div class="modal-body light-grey-boxes">
                                <div>
                                    <div class="form-group">
                                        <input type="text" class="blue-box form-control input-lg" id="seriesTitle" placeholder="Series Title">
                                    </div>
                                    <div class="form-group">
                                        <textarea class="blue-box form-control" rows="7" id="seriesDescription" placeholder="Series Description"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer light-grey-boxes no-line">
                        <div class="btn-group">
                            <button type="button" id="genre-drop" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                Genre
                            </button>
                            <div class="dropdown-menu genre-dropdown drop-down-modal">
                                <button class="btn btn-default dropdown-item dropdown-button">Action</button>
                                <br>
                                <button class="btn btn-default dropdown-item dropdown-button">Comedy</button>
                                <br>
                                <button class="btn btn-default ropdown-item dropdown-button">Fantasy</button>
                                <br>
                                <button class="btn btn-default dropdown-item dropdown-button">Horror</button>
                                <br>
                                <button class="btn btn-default dropdown-item dropdown-button">Drama</button>
                                <br>
                                <button class="btn btn-default dropdown-item dropdown-button">Romance</button>
                                <br>
                                <button class="btn btn-default dropdown-item dropdown-button">Sci-Fi</button>
                                <br>
                                <button class="btn btn-default wdropdown-item dropdown-button">Adventure</button>
                            </div>
                        </div>
                        <button type="button" id="create-series-workspace" role="menu" class="btn btn-default series-workspace-create-chapter" data-dismiss="modal">Create Series</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
<div style="display: none;">
    <p id="hidden-author-email">${authorEmail}</p>
</div>
</body>
</html>
