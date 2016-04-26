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
<script src="/resources/scripts/jquery/user-handler.js"></script>
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

    <span class="orange-span col-lg-offset-1 col-lg-6" id="all_the_series">
    <br>



        <br>
        </span>
</div>

<div style="display:none;">
    <p id="userEmail">${userEmail}</p>
</div>

</body>
</html>
