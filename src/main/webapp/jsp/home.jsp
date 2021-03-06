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
    <script src="/resources/scripts/jquery/home-handler.js"></script>
    <link rel="stylesheet" href="/resources/css/terrell.css">
<body>
<div class="container">
<%@ include file="/resources/layouts/navbar.jsp" %>

<br><br><br><br><br><br><br><br><br>
    <h1 id="logo-placeholder"><img src="/resources/images/hero.png" height="200px"></h1>

    <form action="/search" method="get" id="home-search" class="navbar-form navbar-left navbar-input-group searchForm" role="search">
        <div class="input-group col-xs-12">
            <input id="search-input" name="search-input" type="text" class="form-control"  placeholder="Start your adventure...">
                            <span class="input-group-btn">
                                <button id="SearchButton" class="btn btn-default" type="submit">Search</button>
                            </span>
        </div>
    </form>
    <br><br><br><br>

    <h3 id="home-more-options"><a id="anchor-home" href="#featured-comic-title"><img src="/resources/images/clickformore.png" height="200px"></a></h3>

    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

    <h3 id="featured-comic-title" class="till_font">Featured Comics</h3>
    <div id="featured-carousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#featured-carousel" data-slide-to="0" class="active"></li>
            <li data-target="#featured-carousel" data-slide-to="1"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div id="item-active" class="item active">
               <a href="/chapter-index/Science%20Shorts"><img src="/resources/images/banner/roofusseedybanner.jpg" width="1200" height="380"> </a>
            </div>

            <div id="" class="item">
              <a href=/chapter-index/Roofus%20and%20Seedy" ><img  src="/resources/images/banner/scienceshortsbanner.jpg" width="1200" height="380"> </a>
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


<h3 class="till_font">Recently Updated</h3>

    <div class="text-center">
        <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="10000" id="recentcarousel">

            <div class="carousel-inner">
                <div class="item active">
                    <div class="col-md-3 col-sm-4 col-xs-12 item-1 home-item"><a href="#">></a></div>
                </div>
                <div class="item">
                    <div class="col-md-3 col-sm-4 col-xs-12 item-2 home-item"></div>
                </div>
                <div class="item">
                    <div class="col-md-3 col-sm-4 col-xs-12 item-3 home-item"></div>
                </div>
                <div class="item">
                    <div class="col-md-3 col-sm-4 col-xs-12 item-4 home-item"></div>
                </div>
                <div class="item">
                    <div class="col-md-3 col-sm-4 col-xs-12 item-5 home-item"></div>
                </div>
                <div class="item">
                    <div class="col-md-3 col-sm-4 col-xs-12 item-6 home-item"></div>
                </div>
                <div class="item">
                    <div class="col-md-3 col-sm-4 col-xs-12 item-7 home-item"></div>
                </div>
                <div class="item">
                    <div class="col-md-3 col-sm-4 col-xs-12 item-8 home-item"></div>
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