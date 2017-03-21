
 
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
          <section id="main">
  <section id="content">	
<div class="row">  
		<?php
		$KYCId= $_POST["username"];
		require_once('../library/dbConnect.php');
		$data=array();
		$fetchdoc =  mysqli_query($con,"SELECT * FROM user_doc WHERE user_id='$KYCId'");
		while ($row = mysqli_fetch_array($fetchdoc)) {
		
		

		$x=$row['doc_id'];
		$y = $row['status'];
		
		
		$fetchdocname =  mysqli_query($con,"SELECT * FROM `document_list` WHERE id='$x'");
		while ($rowname = mysqli_fetch_array($fetchdocname)) {
			
			$name=$rowname['name'];
		
		
		?>
			  <div class="col-md-4 card c-dark palette-Red-400 bg">
                            <div class="card-header">
                                <h2><?php echo $name;?></h2>

                            </div>
                            <div class="card-body card-padding">
                                <p class="m-t-0 m-b-15 c-white">
								<?php
								if($y=='100'){
									echo '<i class="zmdi zmdi-check m-r-5"></i>Applied and Pending';
								}
								elseif($y=='101'){
									echo '<i class="zmdi zmdi-check-all m-r-5"></i>Verified';
								}
								elseif($y=='102'){
									echo '<i class="zmdi zmdi-close m-r-5"></i>Cancelled';
								}
								?>
                                    
                                </p>
                            </div>
                        </div>
		<?php
		}
		
		}
 ?>
		
</div>
<?php

    echo "<hr/><h1>GO BACK</h1>";

?>
 <div class="row text-center">
 <a href="index.php"><button class="btn palette-Deep-Orange bg waves-effect" >Proceed</button></a>
 </div>
	  
	  </section>
	  </section>
	  
	  
	  

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
