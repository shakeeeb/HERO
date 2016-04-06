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
</head>
<body>

<div class="genre-container center-block">
    <%@ include file="/resources/layouts/navbar.jsp" %>
    <br>
    <br>
    <br>
    <ul class="genre-list">
        <li>All</li>
        <li>Action</li>
        <li>Comedy</li>
        <li>Fantasy</li>
        <li>Horror</li>
        <li>Drama</li>
        <li>Romance</li>
        <li>Sci-Fi</li>
    </ul>
</div>
<br>
  <div class="container">



      <!-- Projects Row -->
      <div class="row">
          <div class="col-md-3 series-item">
              <a href="#">
                  <img class="img-responsive series-image" src="http://placehold.it/750x450" alt="">
              </a>
          </div>
          <div class="col-md-3 series-item">
              <a href="#">
                  <img class="img-responsive series-image" src="http://placehold.it/750x450" alt="">
              </a>
          </div>
          <div class="col-md-3 series-item">
              <a href="#">
                  <img class="img-responsive  series-image" src="http://placehold.it/750x450" alt="">
              </a>
          </div>
          <div class="col-md-3 series-item">
              <a href="#">
                  <img class="img-responsive  series-image" src="http://placehold.it/750x450" alt="">
              </a>
          </div>
      </div>
      <!-- /.row -->

      <!-- Projects Row -->
      <div class="row">
          <div class="col-md-3 series-item">
              <a href="#">
                  <img class="img-responsive series-image" src="http://placehold.it/750x450" alt="">
              </a>
          </div>
          <div class="col-md-3 series-item">
              <a href="#">
                  <img class="img-responsive series-image" src="http://placehold.it/750x450" alt="">
              </a>
          </div>
          <div class="col-md-3 series-item">
              <a href="#">
                  <img class="img-responsive series-image" src="http://placehold.it/750x450" alt="">
              </a>
          </div>
          <div class="col-md-3 series-item">
              <a href="#">
                  <img class="img-responsive series-image" src="http://placehold.it/750x450" alt="">
              </a>
          </div>
      </div>
      <!-- /.row -->

</div>
</body>
</html>
