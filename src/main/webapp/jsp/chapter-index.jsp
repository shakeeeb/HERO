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
    <title>Chapter Index</title>
    <%@ include file="/resources/layouts/styles.jsp" %>
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <link rel="stylesheet" href="/resources/css/terrell.css">
</head>
<!-- Navigation -->
<%@ include file="/resources/layouts/navbar.jsp" %>
<br>
<br>
<br>
<!-- Image Background Page Header -->
<!-- Note: The background image is set within the business-casual.css file. -->
<header class="business-header">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
            </div>
        </div>
    </div>
</header>


<!-- Page Content -->
<div class="container">

    <div class="row">
        <div class="col-sm-8 chapter-list-container">
            <div class = "chapter-list-header">
                <h3>{Series}</h3>
                <h4>{Author}</h4>
            </div>
            <div class="chapter-list-item">
                test1
            </div>
            <div class="chapter-list-item">
                test2
            </div>
            <div class="chapter-list-item">
                test3
            </div>
            <div class="chapter-list-item">
                test4
            </div>
        </div>

        <div class="col-sm-4 series-info-container">
        </div>
    </div>
    <!-- /.row -->

    <hr>


</div>
<!-- /.container -->

<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

</body>

</html>
