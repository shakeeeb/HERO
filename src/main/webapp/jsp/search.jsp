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
    <script src="/resources/scripts/jquery/example-jquery.js"></script>
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
            <form action="#">


            <div class="refine-search-item">
              <label>Genre</label>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="genre-radios" id="all-genre-button" value="all-genre" checked>
                        All
                    </p>
                </div>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="genre-radios" id="adventure-genre-button" value="adventure-genre">
                        Adventure
                    </p>
                </div>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="genre-radios" id="comedy-genre-button" value="commedy-genre">
                        Comedy
                    </p>
                </div>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="genre-radios" id="action-genre-button" value="action-genre">
                        Action
                    </p>
                </div>
                <div class="search-radio">
                    <p>
                        <input type="radio" name="genre-radios" id="thriller-genre-button" value="thriller-genre">
                        Thriller
                    </p>
                </div>
                <input type="text" class="form-control" id="tag-input" placeholder="Other">
            </div>


                <div class="refine-search-item">
                <label>Author</label>
                <input type="text" class="form-control" id="author-input" placeholder="Ben">
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
                        <input type="radio" name="rating-radio" id="one-stars-button" value="pme-stars">
                        1 Stars or up
                    </p>
                </div>
    </div>
                <button type="submit" class="btn btn-default">Okay</button>


            </form>

        </div>
    </div>
    <!-- Right side results-->
    <div id="result-container">
        <!-- List of results -->


        <div id="result-list-container">
            <label id="search-label"> Search Results</label>
            <div id="result-list" >
                <!-- the first search result -->
                <div class="search-result center-block-" id="result-1">
                    <div class="result-image-container">
                        <img class="result-image" src="https://placehold.it/125/ffa500/ffffff">
                    </div>
                    <div class="left-result-container">
                        <div class="result-title">Title: Epic Hero</div>
                        <div class="result-author"> Author: Shakeeb</div>
                        <div class="result-rating"> </div>
                    </div>
                    <div class="result-description-container">
                        <p class="result-description"> An epic hero goes on adventures</p>
                    </div>
                </div>

                <!-- the second search result -->
                <div class="search-result center-block-" id="result-2">
                    <div class="result-image-container">
                        <img class="result-image" src="https://placehold.it/125/ffa500/ffffff/text=Secret+Scientist">
                    </div>
                    <div class="left-result-container">
                        <div class="result-title">Title: Science Shorts</div>
                        <div class="result-author"> Author: Miuki</div>
                        <div class="result-rating"> </div>
                    </div>
                    <div class="result-description-container">
                        <p class="result-description">A secret scientist agent goes into the witness protection program as a college student</p>
                    </div>
                </div>



            </div>
        </div>
        <!-- Menu to choose result page -->
        <div id="result-page-menu" class="center-block-">

        </div>
    </div>
</div>
</body>
</html>