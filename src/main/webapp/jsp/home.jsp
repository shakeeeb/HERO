<%--
  Created By Terrell2 Mack
  Date: 3/31/16
  Time: 2:55 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <%@ include file="/resources/layouts/styles.jsp" %>
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <link rel="stylesheet" href="/resources/css/terrell.css">
<body>
<div class="container">
<%@ include file="/resources/layouts/navbartest.jsp" %>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <h1 id="logo-placeholder">LOGO HERE</h1>

    <form id="home-search" class="navbar-form navbar-left navbar-input-group searchForm" role="search">
        <div class="input-group col-xs-12">
            <input id="search-input" type="text" class="form-control"  placeholder="Start your adventure...">
                            <span class="input-group-btn">
                                <button id="SearchButton" class="btn btn-default" type="button">Search</button>
                            </span>
        </div>
    </form>
    <br><br><br><br>

    <h3 id="home-more-options"><a id="anchor-home" href="#featured-comic-title">Don't want to search? Click for more!</a></h3>
    <h3 id="home-more-options-down"><a id="anchor-home" href="#featured-comic-title">V</a></h3>

    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

    <h3 id="featured-comic-title">Featured Comics</h3>
    <div id="featured-carousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#featured-carousel" data-slide-to="0" class="active"></li>
            <li data-target="#featured-carousel" data-slide-to="1"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div id="item-active" class="item active">
                <img src="https://placehold.it/1200x400?text=Featured+1" width="1200" height="380">
            </div>

            <div id="" class="item">
                <img src="https://placehold.it/1200x400?text=Featured+2" width="1200" height="380">
            </div>
        </div>

        <!-- Controls -->
        <a class="carousel-control left" href="#featured-carousel" role="button" data-slide="prev">
            <div class="carousel-arrows">
                <
            </div>
        </a>
        <a class="right carousel-control" href="#featured-carousel" role="button" data-slide="next">
            <div class="carousel-arrows">
                >
            </div>
        </a>
    </div>


<h3>Recently Updated</h3>

    <div class="text-center">
        <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="10000" id="recentcarousel">

            <div class="carousel-inner">
                <div class="item active">
                    <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="https://placehold.it/190x190?text=Latest+1" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="https://placehold.it/190x190?text=Latest+2" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="https://placehold.it/190x190?text=Latest+3" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="https://placehold.it/190x190?text=Latest+4" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="https://placehold.it/190x190?text=Latest+5" class="img-responsive"></a></div>
                </div>
                <div class="item">
                    <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="https://placehold.it/190x190?text=Latest+6" class="img-responsive"></a></div>
                </div>
            </div>

            <!-- Controls -->
            <a class="carousel-control left bottom" href="#recentcarousel" role="button" data-slide="prev">
                <div class="carousel-arrows-bottom">
                    <
                </div>
            </a>
            <a class="right carousel-control bottom" href="#recentcarousel" role="button" data-slide="next">
                <div class="carousel-arrows-bottom">
                    >
                </div>
            </a>
        </div>
    </div>


</div>

</body>
</html>