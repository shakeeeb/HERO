<%--<nav class="navbar navbar-default navbar-fixed-top" role="navigation">--%>
    <%--<div class="container">--%>
        <%--<div class="row">--%>
            <%--<div class="col-lg-offset-1 col-lg-1">--%>
                <%--<div class="navbar-header">--%>
                    <%--<a href="/home"><img src="https://placehold.it/50x50?text=hero" height="50px" width="50px">  </a>--%>
                <%--</div> <!-- navbar-header-->--%>
            <%--</div><!--col-sm-4-->--%>
                <%--<form class="navbar-form navbar-left navbar-input-group searchForm" role="search">--%>
                        <%--<div class="input-group col-xs-12">--%>
                            <%--<input id="search-input" type="text" class="form-control"  placeholder="Start your adventure...">--%>
                            <%--<span class="input-group-btn">--%>
                                <%--<button id="SearchButton" class="btn btn-default" type="button">Search</button>--%>
                            <%--</span>--%>
                        <%--</div>--%>
                <%--</form>--%>
                    <%--<div class="hamburger navbar-right">--%>
                        <%--<a href="/home"><img src="https://placehold.it/50x50?text=menu" height="50px" width="50px">  </a>--%>
                    <%--</div> <!-- hamburger-->--%>
        <%--</div>--%>
    <%--</div><!--container-->--%>
<%--</nav>--%>


<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-offset-1 col-lg-1">
                <div class="navbar-header">
                    <a href="/home"><img src="/resources/images/hero.png" height="50px" width="50px">  </a>
                </div> <!-- navbar-header-->
            </div><!--col-sm-4-->
            <form action = "/search" method ="get" class="navbar-form navbar-left navbar-input-group searchForm" role="search">
                <div class="input-group col-xs-12">
                    <input id="search-input" name="search-input" type="text" class="form-control"  placeholder="Start your adventure...">
                            <span class="input-group-btn">
                                <button id="SearchButton" class="btn btn-default" type="submit">Search</button>
                            </span>
                </div>
            </form>

            <div id="hamburger-menu-icon" class="dropdown hamburger navbar-right">
                <!--<button class="dropdown-toggle" data-toggle="dropdown">
                    <a href="/home"><i class="fa fa-bars fa-3x"></i></a></button>-->
                    <i class="fa fa-bars fa-3x"data-toggle="dropdown"></i>
                <ul id="nav-dropdown-top" class="dropdown-menu">
                    <li><a href="/login">Sign In</a></li>
                    <li><a href="/logout">Sign Out</a></li>
                    <li><a href="/settings/user">Settings</a></li>
                    <li><a href="/dashboard">Dashboard</a></li>
                    <li><a href="/series-workspace">Make A Comic</a></li>
                    <li><a href="/comic-index">Comic Index</a></li>
                </ul>
            </div> <!-- hamburger-->

        </div>
    </div><!--container-->
</nav>

