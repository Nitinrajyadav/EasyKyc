<?php
error_reporting(0);
				require_once('../../streams/streams.php');		
				include('../../library/dbConnect.php');
				session_start();
// Add New Category
if(isset($_POST['add_doc']))
{
	
	$name=$_POST['name'];
	$dept=$_POST['dept'];
	
	$CheckBox=$_POST['needed'];  

	$checked = sizeof($CheckBox);

	
	
	$statuscheck=$_POST['status'];
	if ($statuscheck!=""){
		$status ='1';
	}else{$status ='0';}
	

	$arr = array($name, $dept, $CheckBox, $checked, $status);
	$json = json_encode($arr);



//save the json in block chain and return txnid as $transactionId
	$ch = curl_init();

	curl_setopt($ch, CURLOPT_URL,"http://localhost:8080/easyKYC/multichainWeb/publish.php");
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS,
            "streamName=$streamAdmin&text=$json");
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		// in real life you should use something like:
		// curl_setopt($ch, CURLOPT_POSTFIELDS, 
		//          http_build_query(array('postvar1' => 'value1')));

			// receive server response ...
		

		$tx = curl_exec ($ch);

		curl_close ($ch);
	






	$query="INSERT INTO document_list(name,v_auth_id,dependency,txnId) VALUES ('$name','$dept','$status','$tx')";
                            if(mysqli_query($con,$query))
                            {
								$newid=mysqli_insert_id($con);
								
								for($i=0;$i<$checked;$i++) {
    $needed = $CheckBox[$i];
    $queryadd = mysqli_query($con,"INSERT INTO `support_doc` (`parent_id`, `doc_id`) VALUES ('$newid', '$needed');");  
}

								
								
							?>
							<script type="text/javascript">
								alert('Successful.');
window.location= "doc_create.php<?php echo '?token='. $_SESSION['token'];?>";
							</script>
							<?php
							}
                            else
                            {
                             ?>
							<script type="text/javascript">
								alert('Failed.');
window.location= "doc_create.php<?php echo '?token='. $_SESSION['token'];?>";
							</script>
							<?php  }
                            

                      
                    
                  
				 
				
}
?>