<?php

 require_once('../library/dbConnect.php');

	$fetch = mysqli_query($con,"SELECT * FROM `v_auth`");
	
	while ($row = mysqli_fetch_assoc($fetch)) {
			
			$data['id']=$row['id'];
			$data['name']=$row['name'];
			$publishtxid=$data['txnId'];
			$schemaAddress=$data['schemaAddress'];
			
			$ch = curl_init();

			curl_setopt($ch, CURLOPT_URL,"http://localhost/easyKYC/multichainWeb/fetchTest.php");
			curl_setopt($ch, CURLOPT_POST, 1);
			curl_setopt($ch, CURLOPT_POSTFIELDS,
            "schemaAddress=$schemaAddress&publishtxid=$publishtxid");
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		
				$receive = curl_exec ($ch);

				curl_close ($ch);
			
			if($receive!=''){
			$authorities[] = $data; }
			}

echo json_encode(compact('authorities'));
			mysqli_close($con);

 exit();
 
 ?>
 
 

