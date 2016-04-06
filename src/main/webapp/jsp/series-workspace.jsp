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
    <title>Hero - Series Workspace</title>
    <%@ include file="/resources/layouts/scripts.jsp" %>
    <%@ include file="/resources/layouts/styles.jsp" %>
</head>
<body>
<%@ include file="/resources/layouts/navbar.jsp" %>
<br><br><br><br>
<div class="container">
    <div class="col-lg-offset-1 col-lg-11">
        Series Workspace
    </div>

    <div class="orange-span series-workspace-main col-lg-offset-1 col-lg-11">

        <br><br>
            <span id="series-authored-story-1" class="orange-span one-story">
                <span id="authored-story-1-image" class="author-story-wrap">
                    <img src="/resources/images/logo.png" height="100px" width="100px">
                </span>
                <span id="authored-story-1-information" class="author-story-wrap">
                    <span id="series-authored-chap-nums" class="blue-box series-num-chapters">
                        Number of Chapters
                    </span>

                    <span id="authored-story-1-tags" class="blue-box completeness">
                        Ongoing / Complete
                    </span>
                </span>

                    <br>
                    <button type="button" class="btn overview-button">Overview</button>

                    <br>
                    <button type="button" class="btn series-new-chapter-button">+ New Chapter</button>

            </span>

        <br><br><br><br>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc diam velit, commodo ut interdum sed, ultrices at
        lorem. In dignissim molestie leo sit amet volutpat. Donec eu suscipit nulla, ac lobortis neque. Phasellus sed
        bibendum dui. Nullam et libero vitae nunc congue blandit. Integer efficitur bibendum viverra. Nunc sodales
        tincidunt dui, nec venenatis augue suscipit eget. Nunc interdum luctus dolor ut rutrum. Aliquam sodales neque
        quis quam accumsan interdum. In in massa eu sapien finibus lobortis. Fusce at aliquet mauris. Pellentesque felis
        leo, tempus vel dapibus in, pellentesque nec dolor.
        <br><br><br><br>
        Praesent at mi non velit blandit sollicitudin. In posuere eget felis condimentum ornare. Sed non pellentesque
        lacus, nec porttitor tellus. Sed libero odio, sollicitudin id tempor id, commodo et arcu. Aenean vel ante nibh.
        Donec dapibus eros non ex blandit, vel malesuada sem facilisis. Nam quis nisl nec ipsum dignissim convallis.
        Aliquam ac nisi blandit, euismod dui ac, mollis nunc. Fusce fermentum, massa sit amet pellentesque auctor, nisl
        tortor sagittis metus, id mollis enim mi nec sem. Sed pharetra elit et dapibus dapibus. Pellentesque consectetur
        felis leo, sit amet luctus ex varius sed.
        <br><br><br><br>
        Fusce scelerisque lectus arcu, at egestas arcu convallis ac. Morbi condimentum quam mauris, quis iaculis massa
        dapibus vitae. Aenean ligula ante, accumsan ut lectus sit amet, porta placerat diam. Morbi sagittis ex a metus
        lobortis, non dictum nibh vulputate. Proin sodales sit amet neque vel malesuada. Aliquam non elit sit amet
        lectus semper feugiat vitae eget sem. Integer luctus varius lobortis. Donec nec tellus dui. Proin metus eros,
        venenatis et tellus quis, blandit iaculis nisi. Nam pulvinar diam id vehicula sagittis. In eget lectus commodo
        ante accumsan ultricies eu venenatis velit. Donec sollicitudin fermentum ex, et convallis dolor finibus et.
        Proin sed aliquam dui. Integer quis tortor sed dui malesuada vehicula. Vivamus ullamcorper arcu nec tincidunt
        viverra.
        <br><br><br><br>
        Pellentesque libero felis, mattis ut ex ac, sagittis gravida quam. Mauris at sapien dolor. Curabitur eu
        malesuada erat. Sed consectetur ac nisl et tristique. In placerat ullamcorper leo, vel condimentum ex tempor at.
        Ut id lorem ante. Proin vel suscipit lectus. Donec sit amet euismod risus. In ultrices velit semper libero
        pharetra volutpat. Quisque et tellus vel ipsum dignissim mattis quis eu metus. Nam ornare rhoncus velit eget
        pharetra. Quisque ullamcorper, velit eu ullamcorper hendrerit, nisi nisl ornare metus, eu ultricies diam arcu
        imperdiet eros. Duis maximus est sit amet dui facilisis, quis hendrerit lorem posuere.
        <br><br><br><br>
        Donec ultricies finibus urna nec viverra. Curabitur vehicula ut quam non porttitor. Nulla accumsan tortor sem,
        vel aliquet orci ornare et. Mauris dignissim dictum justo, nec consectetur magna malesuada a. Vestibulum ante
        ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Maecenas non commodo enim, quis faucibus
        ipsum. Phasellus tincidunt lobortis sem dignissim tincidunt. Donec id ipsum tincidunt, luctus eros id, efficitur
        ante. Praesent aliquet ut tellus quis porttitor. Mauris sit amet porttitor dolor.
    </div>

    <div class="new-series-button">
        <br>
        <button type="button" class="btn">+ New Series</button>
    </div>
</div>
</body>
</html>
