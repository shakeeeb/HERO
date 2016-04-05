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
    <title>Hero - Reader-Page</title>
    <link rel="stylesheet" href="/resources/css/ben.css">
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <%@ include file="/resources/layouts/styles.jsp" %>
</head>
<body id="reader-page-body">
<%@ include file="/resources/layouts/navbar.jsp" %>
<br><br><br><br>

<div class="container">

    <div class="col-md-8 col-md-offset-2 page-header orange-span ">
        <h2 id="comic-name-reader-page">Comic Name</h2>
        <h2 id="chapter-name-reader-page">Chapter Name</h2>
        <h2 id="page-number-reader-page">Page Number</h2>
    </div>

    <div class="col-md-8 col-md-offset-2" id="page-reader-main">
        <img id="page-reader-example-img"
             src="https://placeholdit.imgix.net/~text?txtsize=67&txt=800%C3%971307&w=800&h=1307">
    </div>


</div>

</body>
</html>