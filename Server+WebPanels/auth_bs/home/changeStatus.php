<?php		
				include('../../library/dbConnect.php');
				session_start();
// Add New Category
if(isset($_GET['reqtId']))
{
	$reqtId=$_GET['reqtId'];
	
	$status=$_GET['id'];
	 $query="UPDATE `user_doc` SET status='$status' WHERE id='$reqtId'";
                          echo $query;

                            if(mysqli_query($con,$query))
                            {
							?>
							<script type="text/javascript">
								alert('Successfull.');
								history.go(-1);
							</script>
							<?php
							}
	
}


?>