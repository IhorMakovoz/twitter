<!DOCTYPE html>
<html>
<head>
    <title>Twitter Clone: login</title>
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
            <li><a href="signup.jsp">Registration</a></li>
            <li><a href="login.jsp">Login</a></li>
        </ul>
    </div><!-- /.navbar-collapse -->
</nav>

<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <div class="container">
            <div class="alert alert-danger text-center">Registration was failed. This account has already taken</div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <h1 class = "text-primary text-center">Registration form</h1>

        <form role="form" action="/signup" method="post">

            <div class="form-group">
                <label class="control-label">Login</label>
                <input type="text" name="login" value="login" class="form-control">
            </div>

            <div class="form-group">
                <label class="control-label">Password</label>
                <input type="text" name="password" value="password" class="form-control">
            </div>

            <button type="submit" class="btn btn-default">Sign up</button>
            <button type="button" class="btn btn-link">Cancel</button>

        </form>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->

</body>
</html>