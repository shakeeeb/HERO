<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 3/31/16
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comic Index</title>
    <%@ include file="/resources/layouts/styles.jsp" %>
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <link rel="stylesheet" href="/resources/css/terrell.css">
    <script src="/resources/scripts/jquery/comic-index-handler.js"></script>

    <script id="hidden-template" type="text/x-custom-template">
        <div class="col-md-3 series-item">
            <a href="#">
                <%--<img class="img-responsive series-image" src="http://placehold.it/750x450" alt="">--%>
                <h1 class="series-image"></h1>
            </a>
        </div>
    </script>
</head>
<body>

<div class="genre-container center-block">
    <%@ include file="/resources/layouts/navbar.jsp" %>
    <br>
    <br>
    <br>
    <ul class="genre-list till_font">
        <li id="all">All</li>
        <li id="action">Action</li>
        <li id="comedy">Comedy</li>
        <li id="fantasy">Fantasy</li>
        <li id="horror">Horror</li>
        <li id="drama">Drama</li>
        <li id="romance">Romance</li>
        <li id="sci-fi">Sci-Fi</li>
        <li id="kids">Kids</li>
        <li id="adventure">Adventure</li>
    </ul>
</div>
<br>
  <div class="container">
      <div id="comic-container" class="container-fluid">
          <%--<div class="col-md-3 series-item">--%>
              <%--<a href="#">--%>
                  <%--<img class="img-responsive series-image" src="http://placehold.it/750x450" alt="">--%>
              <%--</a>--%>
          <%--</div>--%>
      </div>
      <%--</div>--%>
      <!-- /.row -->

</div>
</body>
</html>
