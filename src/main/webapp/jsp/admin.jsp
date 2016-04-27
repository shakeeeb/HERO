<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 3/31/16
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hero - Admin Page</title>
    <link rel="stylesheet" href="/resources/css/ben.css">
    <%@ include file="/resources/layouts/styles.jsp" %>
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <script src="/resources/scripts/jquery/admin-handler.js"></script>

    <script id="hidden-template" type="text/x-custom-template">
        <div class="row item-approval">
            <div class="col-md-11 col-md-push-1 row orange-span submitted-comics comic-span">
                <div class="col-md-1">
                <span class="pull-left comic_authorization_image">
                <img id="img" src="https://placehold.it/170x170?text=Latest+2">
                </span>
                </div>
                <div class="col-md-2 col-md-offset-4">
                    <div class="row submitted-comic-info">
                        <h4 id="title" class="submitted-comics-title"></h4>
                    </div>
                    <div class="submitted-comics-author" class="row">
                        <h4 id="author"></h4>
                    </div>
                    <div class="row">
                        <div class="col-md-6 approve-comic-button-div">
                            <div class="approve-comic">
                                <button type="button" class="btn btn-md button-ok pull-right approve-comic-button">
                                    Approve Comic
                                </button>
                            </div>
                        </div>
                        <div class="col-md-6 delete-comic-button-div">
                            <button type="button"
                                    class="btn btn-md button-negative pull-right delete-comic delete-comic-button">
                                Delete Comic
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <br>
    </script>

    <script id="hidden-template2" type="text/x-custom-template">
        <div class="row reported-approval">
            <div class="col-md-11 col-md-push-1 row orange-span submitted-comics">
                <div class="col-md-1">
                <span class="pull-left comic_authorization_image">
                <img src="https://placehold.it/170x170?text=Latest+2">
                </span>
                </div>
                <div class="col-md-2 col-md-offset-4">
                    <div class="row submitted-comic-info">
                        <h4 id="title2" class="submitted-comics-title"></h4>
                    </div>
                    <div class="submitted-comics-author" class="row">
                        <h4 id="author2"></h4>
                    </div>
                    <div class="row">
                        <div class="col-md-6 approve-comic-button-div">
                            <div class="approve-comic">
                                <button type="button" class="btn btn-md button-ok pull-right approve-comic-button">Approve Comic</button>
                            </div>
                        </div>
                        <div class="col-md-6 delete-comic-button-div">
                            <button type="button" class="btn btn-md button-negative pull-right delete-comic delete-comic-button">Delete
                                Comic
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <br>
    </script>

</head>
<body id="admin-page-body">
<div class="container">
    <%@ include file="/resources/layouts/navbar.jsp" %>
</div>
</br></br></br>
<h1 class="greeting text-left">Hello, Go D. ${nickname}</h1>

<div class="row">
    <%--<div class="col-md-5 text-center">--%>
    <%--<h2 class="submitted-comics-header">Submitted fsdfdComics </h2>--%>
    <%--</div>--%>
    <div class="col-md-5 col-md-push-1 text-center">
        <h2 class="submitted-comics-header">Submitted Comics </h2>
    </div>
    <div class="col-md-5 text-center">
        <h2 id="reported-comics-header">Reported Comics</h2>
    </div>
</div>

<div class="col-md-5 col-md-offset-1 orange-span" id="left">


    <%--<div class="row">--%>
    <%--<div class="col-md-11 col-md-push-1 row orange-span submitted-comics comic-span">--%>
    <%--<div class="col-md-1">--%>
    <%--<span class="pull-left comic_authorization_image">--%>
    <%--<img id="img" src="https://placehold.it/170x170?text=Latest+2">--%>
    <%--</span>--%>
    <%--</div>--%>
    <%--<div class="col-md-2 col-md-offset-4">--%>
    <%--<div class="row submitted-comic-info">--%>
    <%--<h4 id="title" class="submitted-comics-title"></h4>--%>
    <%--</div>--%>
    <%--<div class="submitted-comics-author" class="row">--%>
    <%--<h4 id="author"></h4>--%>
    <%--</div>--%>
    <%--<div class="row">--%>
    <%--<div class="col-md-6 approve-comic-button">--%>
    <%--<div class="approve-comic">--%>
    <%--<button type="button" class="btn btn-md button-ok pull-right">Approve Comic</button>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="col-md-6 delete-comic-button">--%>
    <%--<button type="button" class="btn btn-md button-negative pull-right delete-comic">Delete Comic</button>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>

    <%--</div>--%>
    <%--</div>--%>
    <%--<br>--%>


</div>

<div class="col-md-5 col-md-offset-1 orange-span" id="right">
    <%--<div class="row">--%>
    <%--<div class="col-md-11 col-md-push-1 row orange-span submitted-comics">--%>
    <%--<div class="col-md-1">--%>
    <%--<span class="pull-left comic_authorization_image">--%>
    <%--<img src="https://placehold.it/170x170?text=Latest+2">--%>
    <%--</span>--%>
    <%--</div>--%>
    <%--<div class="col-md-2 col-md-offset-4">--%>
    <%--<div class="row submitted-comic-info">--%>
    <%--<h4 class="submitted-comics-title">Title: {Name}</h4>--%>
    <%--</div>--%>
    <%--<div class="submitted-comics-author" class="row">--%>
    <%--<h4>Author: {Name}</h4>--%>
    <%--</div>--%>
    <%--<div class="row">--%>
    <%--<div class="col-md-6 approve-comic-button">--%>
    <%--<div class="approve-comic">--%>
    <%--<button type="button" class="btn btn-md button-ok pull-right">Approve Comic</button>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="col-md-6 delete-comic-button">--%>
    <%--<button type="button" class="btn btn-md button-negative pull-right delete-comic">Delete Comic</button>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>

    <%--</div>--%>
    <%--</div>--%>
    <%--<br>--%>


    <div style="display: none;">
        <p id="username">${username}</p>
    </div>
</div>


<%--<div>--%>
<%--<div class="row">--%>
<%--<div class="col-md-5 col-md-offset-1 orange-span submitted-comics">--%>
<%--<span>--%>
<%--<div class="col-md-1">--%>
<%--<span class="pull-left comic_authorization_image">--%>
<%--<img src="https://placehold.it/170x170?text=Latest+2">--%>
<%--</span>--%>
<%--</div>--%>
<%--<div class="col-md-6 col-md-offset-5">--%>
<%--<div class="row submitted-comic-info">--%>
<%--<h4>Title: {Name}</h4>--%>
<%--</div>--%>
<%--<div class="row">--%>
<%--<h4>Author: {Name}</h4>--%>
<%--</div>--%>
<%--<div class="row">--%>
<%--<div class="col-md-6">--%>
<%--<div class="approve-comic">--%>
<%--<button type="button" class="btn btn-sm button-ok pull-right">Approve Comic</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="col-md-6">--%>
<%--<button type="button" class="btn btn-sm button-negative pull-right delete-comic">Delete Comic</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</span>--%>
<%--</div>--%>

<%--<div class="col-md-5 col-md-offset-1 col-md-pull-1 orange-span reported-comics">--%>
<%--<div class="col-md-1">--%>
<%--<span class="pull-left comic_authorization_image">--%>
<%--<img src="https://placehold.it/170x170?text=Latest+2">--%>
<%--</span>--%>
<%--</div>--%>
<%--<div class="col-md-6 col-md-offset-5">--%>
<%--<div class="row submitted-comic-info">--%>
<%--<h4>Title: {Name}</h4>--%>
<%--</div>--%>
<%--<div class="row">--%>
<%--<h4>Author: {Name}</h4>--%>
<%--</div>--%>
<%--<div class="row">--%>
<%--<div class="col-md-6">--%>
<%--<div class="approve-comic">--%>
<%--<button type="button" class="btn btn-sm button-ok pull-right">Approve Comic</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="col-md-6">--%>
<%--<button type="button" class="btn btn-sm button-negative pull-right delete-comic">Delete Comic</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>


</body>
</html>
