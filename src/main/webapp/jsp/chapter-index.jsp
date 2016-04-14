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
<header class="series-banner">
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
            <div class="chapter-list-header">
                <h3>{Series}</h3>
                <h4>{Author}</h4>
            </div>
            <div class="chapter-list">

                <form action="/read" method="get" role="button" name="read-form">
                    <div class="chapter-list-item center-block-" id="chapter-N" onclick="document.forms['read-form'].submit();">
                        <div class="inline">
                            <img src="https://placehold.it/125/ffa500/ffffff">
                        </div>
                        <div class="inline">
                            <div id="readChapter"class="inline">${chapter}</div>
                            <div class="inline">{Title}</div>

                            <div>
                                <p>An epic hero goes to the mountains</p>
                            </div>

                        </div>
                        <div class="pull-right chapter-date">
                            04/05/1993
                        </div>
                    </div>
                </form>


                <div class="chapter-list-item center-block-" id="chapter-N-1">
                    <div class="inline">
                        <img src="https://placehold.it/125/ffa500/ffffff">
                    </div>
                    <div class="inline">
                        <div class="inline">{Chapter #}</div>
                        <div class="inline">{Title}</div>

                        <div>
                            <p>An epic hero goes to the beach</p>
                        </div>

                    </div>
                    <div class="pull-right chapter-date">
                        05/04/1994
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-4 series-info-container">
            <div class="series-description">
                <p>An epic hero goes on adventures</p>
            </div>

            <div class="text-center">
                <span><button class="btn btn-default subscribe-button">Subscribe</button></span>
            </div>
            <div class="text-center">
                <span><button class="btn btn-default start-beginning-button">Start From Beginning</button></span>
            </div>
        </div>
    </div>
    <!-- /.row -->

    <hr>



</div>
<!-- /.container -->


</body>

</html>
