<?php
			
	require_once('../../streams/streams.php');	
	
				include('../../library/dbConnect.php');
				session_start();
// Add New Category
if(isset($_POST['add_auth']))
{
	
	$name=$_POST['name'];
	$username=$_POST['username'];
	$password=$_POST['password'];
	$typeselect=$_POST['typeselect'];
	
	$statuscheck=$_POST['status'];
	if ($statuscheck!=""){
		$status ='1';
	}else{$status ='0';}

$arr = array($name, $username, $password, $typeselect, $status);
$json = json_encode($arr);



//save the json in block chain and return txnid as $transactionId
	$ch = curl_init();

	curl_setopt($ch, CURLOPT_URL,"http://localhost/easyKYC/multichainWeb/publish.php");
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS,
            "streamName=$streamParty&text=$json");
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		// in real life you should use something like:
		// curl_setopt($ch, CURLOPT_POSTFIELDS, 
		//          http_build_query(array('postvar1' => 'value1')));

			// receive server response ...
		

		$tx = curl_exec ($ch);

		curl_close ($ch);
	




	
	$query="INSERT INTO v_auth(name,userid,password,type,status,txnId,schemaAddress) VALUES ('$name','$username','$password','$typeselect','$status','$tx','$streamParty')";
                            if(mysqli_query($con,$query))
                            {
							
							?>
							<script type="text/javascript">
								alert('Successfull.');
window.location= "addnew.php<?php echo '?token='. $_SESSION['token'];?>";
							</script>
							<?php
							}
                            else
                            {
							
                             ?>
							<script type="text/javascript">
								alert('Failed.');
window.location= "addnew.php<?php echo '?token='. $_SESSION['token'];?>";
							</script>
							<?php  }
                            

                      
                    
                  
				 
				
}
?>