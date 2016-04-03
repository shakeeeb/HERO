<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 3/31/16
  Time: 8:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hero - User Page</title>
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <%@ include file="/resources/layouts/styles.jsp" %>
</head>
<body>
<%@ include file="/resources/layouts/navbar.jsp" %>
<br><br><br><br>
<div class="container">
    <span class="orange-span col-lg-offset-1 col-lg-4">
        <br>
        <div id="user-image" class="orange-span">
            <img src="/resources/images/logo.png" height="346px" width="346px">
        </div>

        <br>
        <div id="user-information" class="orange-span box-text-align">
            <br>USER INFORMATION HERE<br>
            <br>Etiam ut fringilla tortor. Fusce et odio magna. Ut pulvinar massa eu mi maximus sollicitudin. In hac
            habitasse platea dictumst. Vivamus sit amet nunc tincidunt, condimentum mauris id, cursus tellus. Fusce quam
            leo, lobortis sed tristique vel, finibus dictum velit. Curabitur sit amet magna vitae sapien fermentum
            iaculis ac ut urna.
        </div>

        <br>

    </span>

    <span class="orange-span col-lg-offset-1 col-lg-6">
    <br>
        <span id="authored-story-1" class="orange-span one-story">
            <span id="authored-story-1-image" class="author-story-wrap">
                <img src="/resources/images/logo.png" height="100px" width="100px">
            </span>
            <span id="authored-story-1-information" class="author-story-wrap">
                <span id="authored-story-1-title" class="orange-span user-story-title">
                    Comic Title
                </span>

                <span id="authored-story-1-tags" class="orange-span user-story-tags">
                    HELLO THERE HOW ARE YOU DOING
                </span>
            </span>
        </span>

        <br>
                <span id="authored-story-2" class="orange-span one-story">
            <span id="authored-story-2-image" class="author-story-wrap">
                <img src="/resources/images/logo.png" height="100px" width="100px">
            </span>
            <span id="authored-story-2-information" class="author-story-wrap">
                <span id="authored-story-2-title" class="orange-span user-story-title">
                    Comic Title
                </span>

                <span id="authored-story-2-tags" class="orange-span user-story-tags">
                    HELLO THERE HOW ARE YOU DOING
                </span>
            </span>
        </span>


        <br>
        </span>
</div>
</body>
</html>
