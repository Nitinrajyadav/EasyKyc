<?php

// Not in USE ---- IGNORE
error_reporting(0);
	require_once('../streams/streams.php');
	$streamAddress = $streamUsers;

$request = file_get_contents('php://input');
$result = json_decode($request, true);


$KYCId = $result['KYCid'];



require_once('../library/dbConnect.php');

$fetch = mysqli_query($con,"SELECT * FROM `user` WHERE BHAMASHAH_ID='$KYCId' LIMIT 1");
	
	while ($row = mysqli_fetch_assoc($fetch)) {
		
		foreach ($row as $key => $value) {
			 
			if($value!=NULL && $value!=null && $value!=''){
				 $data[$key]=$value;				
			}
			
			} 
				$publishtxid = $data['txnId'];
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
			$resident_details = $data;}
		
}
  echo json_encode(compact('resident_details'));

mysqli_close($con);

?>
