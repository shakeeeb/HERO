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
    <title>Search Results</title>
    <%@ include file="/resources/layouts/styles.jsp" %>
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <link rel="stylesheet" href="/resources/css/terrell.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="/resources/scripts/jquery/search-handler.js"></script>
</head>
<body>

<%@ include file="/resources/layouts/navbar.jsp" %>
<br>
<br>
<div id="search-container">
    <!-- Left side menu -->
    <div id="refine-menu-container">
        <div id="refine-menu" class="center-block-">
            <p id="refine-search-label" class="text-center">Refine Search</p>

            <form id= "refine">


            <div class="refine-search-item">
              <label>Genre</label>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="genre-radio" id="all-genre-button" value="all" checked>
                        All
                    </p>
                </div>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="genre-radio" id="adventure-genre-button" value="adventure">
                        Adventure
                    </p>
                </div>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="genre-radio" id="comedy-genre-button" value="comedy">
                        Comedy
                    </p>
                </div>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="genre-radio" id="action-genre-button" value="action">
                        Action
                    </p>
                </div>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="genre-radio" id="thriller-genre-button" value="thriller">
                        Thriller
                    </p>
                </div>
                <input type="text" name="tag-input" class="form-control" id="tag-input" placeholder="Other">
            </div>


                <div class="refine-search-item">
                <label>Author</label>
                <input type="text" name="author-input" class="form-control" id="author-input" placeholder="Ben">
                    </div>

                <div class="refine-search-item">
                <label>Date</label>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="date-radio" id="most-recent-button" value="most-recent"  class="first-radio-button">
                        Most Recently Updated
                    </p>
                </div>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="date-radio" id="least-recent-button" value="least-recent">
                        Least Recent
                    </p>
                </div>
                </div>

                <div class="refine-search-item">
               <label>Rating</label>
                <div class="search-radio">
                <p>
                    <input type="radio" name="rating-radio" id=five-stars-button" value="five-stars"  class="first-radio-button">
                    5 Stars
                </p>
            </div>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="rating-radio" id="four-stars-button" value="four-stars">
                        4 Stars or up
                    </p>
                </div>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="rating-radio" id="three-stars-button" value="three-stars">
                        3 Stars or up
                    </p>
                </div>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="rating-radio" id="two-stars-button" value="two-stars">
                        2 Stars or up
                    </p>
                </div>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="rating-radio" id="one-stars-button" value="one-stars">
                        1 Stars or up
                    </p>
                </div>
    </div>
                <button id="submit-button" type="button" class="btn btn-default">Okay</button>


            </form>

        </div>
    </div>
    <!-- Right side results-->
    <div id="result-container">
        <!-- List of results -->



        <div id="result-list-container">

            <label id="search-label">Search Results: <div id="test2">${query}</div> </label>
            <!-- the entire search list is here-->
            <!-- use JSTL to go through the model-->
            <!-- however, this means that we aren't returning JSON -->
            <!-- if we did return JSON we can parse through it in the jquery-->
            <!-- it could be built in the jquery, and then injected-->
            <div id="result-list" >


            </div>
        </div>
        <!-- Menu to choose result page -->
        <div id="result-page-menu" class="center-block-">

        </div>
    </div>
</div>
</body>
</html>