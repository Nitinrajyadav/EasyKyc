
<!DOCTYPE html>
    <!--[if IE 9 ]><html class="ie9"><![endif]-->
    
<head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>INVIGILATOR | EASY KYC | BARSPAN</title>
        
        <!-- Vendor CSS -->
        <link href="vendors/bower_components/animate.css/animate.min.css" rel="stylesheet">
        <link href="vendors/bower_components/google-material-color/dist/palette.css" rel="stylesheet">
        <link href="vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css" rel="stylesheet">
            
        <!-- CSS -->
        <link href="css/app.min.1.css" rel="stylesheet">
        <link href="css/app.min.2.css" rel="stylesheet">
    </head>
    
    <body>
        <div class="login" data-lbg="orange">
            <!-- Login -->
			
            <div class="l-block toggled" id="l-login">
                <div class="lb-header palette-Red-400 bg">
                    <i class="zmdi zmdi-account-circle"></i>
                    Hi there! Validate User
                </div>
<form method="post" role="form" id="validate" action="check.php">
                <div class="lb-body">
                    <div class="form-group fg-float">
                        <div class="fg-line">
                            <input type="text" name="username" id="username" class="input-sm form-control fg-input" required>
                            <label class="fg-label">User ID</label>
                        </div>
                    </div>
                    <button type="submit" name="validate" class="btn palette-Red-400 bg">Validate</button>

                    <div class="m-t-20">
					<!----Future Reference for implementation of forget password---->
                      <!---  <a data-block="#l-forget-password" data-bg="purple" href="#" class="palette-Teal text">Forgot password?</a>  --->
                    </div>
                </div>
</form>
            </div>


      
        </div>


        <!-- Javascript Libraries -->
        <script src="vendors/bower_components/jquery/dist/jquery.min.js"></script>
        <script src="vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="vendors/bower_components/Waves/dist/waves.min.js"></script>

        <!-- Placeholder for IE9 -->
        <!--[if IE 9 ]>
            <script src="vendors/bower_components/jquery-placeholder/jquery.placeholder.min.js"></script>
        <![endif]-->

        <script src="js/functions.js"></script>
        
    </body>

</html>