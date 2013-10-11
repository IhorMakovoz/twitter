<!DOCTYPE html>
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
        <a href="home-page.jsp">
            <img src="images/logo.jpeg" width="50" height="50" alt="logo"/>
        </a>
    </div>
    <div>
        <ul class="nav navbar-nav navbar-right">
            <% if ((Boolean)request.getSession().getAttribute("authorized") != null) {  %>
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
        <% if (request.getSession().getAttribute("login") != null) {  %>
        <h1 class = "text-primary text-center"><%=request.getSession().getAttribute("login") %> Home page</h1>
        <% }
        else { %>
        <h1 class = "text-primary text-center">Home page</h1>
        <% } %>
    </div>
</div>
<%if (request.getSession().getAttribute("authorized") != null) { %>
<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <form role="form" action="/message" method="post">
            <textarea name="message" value="message" class="form-control" rows="3"></textarea>
            <button type="submit" class="btn btn-default">Post</button>
        </form>
    </div>
</div>
<% } %>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->

</body>
</html>
