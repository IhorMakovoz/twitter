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
<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <h1 class = "text-primary text-left">Twitter Clone</h1>

        <form role="form"  method="post">

            <div class="form-group">
                <label class="control-label">Sender</label>
                <select class="form-control" name="sender">
                    <option value="John Smith">John Smith</option>
                    <option value="ScratchCart">ScratchCart</option>
                    <option value="System">System</option>
                </select>
            </div>

            <div class="form-group">
                <label class="control-label">Receiver</label>
                <select class="form-control" name="receiver">
                    <option value="System">System</option>
                    <option value="John Smith">John Smith</option>
                </select>
            </div>

            <div class="form-group">
                <label class="control-label">Amount</label>
                <div class="input-group">
                    <input type="text" name="amount" class="form-control">
                    <span class="input-group-addon">$</span>
                </div>
            </div>

            <button type="submit" class="btn btn-default">Send</button>
            <button type="button" class="btn btn-link">Cancel</button>

        </form>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->

</body>
</html>