<%@ page import="java.util.List" %>
<%@ page import="com.in6k.twitter.model.User" %>
<%@ page import="com.in6k.twitter.model.Tweet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%User user = (User)request.getAttribute("currentuser");%>
<!DOCTYPE html>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<html>
<head>
    <title>Twitter Clone</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <!--<script src="../../assets/js/html5shiv.js"></script>
    <script src="../../assets/js/respond.min.js"></script>-->
    <![endif]-->

</head>
<body>

<nav class="navbar navbar-default col-md-6 col-md-offset-3" role="navigation" >
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <a href="/home-page.jsp">
            <img src="/images/logo.jpeg" width="50" height="50" alt="logo"/>
        </a>
    </div>
    <div>
        <ul class="nav navbar-nav navbar-right">
            <% if (request.getSession().getAttribute("authorized") != null) { %>
            <li><a href="#" style="color: blue"><%=request.getSession().getAttribute("login") %></a></li>
            <li><a href="/logout">Logout</a></li>
            <% } else { %>
            <li><a href="signup.jsp">Registration</a></li>
            <li><a href="login.jsp">Login</a></li>
            <% } %>
        </ul>
    </div><!-- /.navbar-collapse -->
</nav>
<%--------------------------%>

<div class="row">
    <div class="col-md-4 col-md-offset-4">

        <%if (request.getSession().getAttribute("authorized") != null && (Boolean)request.getAttribute("ishomepage")) { %>
            <h1 class = "text-primary text-center">Home page</h1>
        <% }
        else if (request.getSession().getAttribute("authorized") != null) { %>
            <% if ((Boolean)request.getAttribute("isfollowedbyuser")) { %>
                <form role="form" action="/unfollow/<%=user.getLogin()%>" method="get">
                    <div><button type="submit" class="btn btn-info">Unfollow</button></div>
                </form>
            <% } // /unfollow?user=
            else { %>
                <form role="form" action="/follow/<%=user.getLogin()%>" method="get">
                    <div><button type="submit" class="btn btn-info">Follow</button></div>
                </form>
            <% } %>
            <h1 class = "text-primary text-center"><%=user.getLogin()%> page</h1>
        <% } %>
    </div>
</div>

<%if (request.getSession().getAttribute("authorized") != null && (Boolean)request.getAttribute("ishomepage")) { %>
<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <form role="form" action="/message" method="post">
            <textarea name="message" value="message" class="form-control" rows="3"></textarea>
            <button type="submit" class="btn btn-default" style="margin-top: 10px">Post</button>
        </form>
    </div>
</div>
<% } %>

<% if(request.getAttribute("tweets") != null) {
List<Tweet> tweets = (List<Tweet>) request.getAttribute("tweets");
for(Tweet tweet : tweets) {%>
<div class="row" style="margin-top: 15px">
    <div class="col-md-4 col-md-offset-4">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <%= tweet.getAuthor().getLogin() %>
                </h3>
            </div>
            <div class="panel-body">
                <%= tweet.getMessage() %>
            </div>
            <div class="panel-footer">
                Posted at: <%= tweet.getDateAt() %>
            </div>
        </div>
    </div>
</div>
<%}
}%>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->

</body>
</html>
