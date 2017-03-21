<?php

	require_once('../streams/streams.php');
	$streamAddress = $streamUsers;
$json = file_get_contents('php://input');
$result = json_decode($json, true);


$KYCId = $result['KYCid'];
$doc_id = $result['doc_id'];


	//save the json in block chain and return txnid as $transactionId
	$ch = curl_init();

	curl_setopt($ch, CURLOPT_URL,"http://localhost/easyKYC/multichainWeb/publish.php");
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS,
            "streamName=$streamUsers&text=$json");
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		// in real life you should use something like:
		// curl_setopt($ch, CURLOPT_POSTFIELDS, 
		//          http_build_query(array('postvar1' => 'value1')));

			// receive server response ...
		

		$tx = curl_exec ($ch);

		curl_close ($ch);


require_once('../library/dbConnect.php');

$sql="SELECT * FROM document_list WHERE id=$doc_id";

$fetchdoc =  mysqli_query($con,$sql);

$row = mysqli_fetch_array($fetchdoc,MYSQLI_ASSOC);
$ver=123;
if($row['dependency'] == 1){
	
	// notify admin to approve
	$sql1 = "INSERT INTO user_doc (user_id, status, verification, doc_id,	txnId) VALUES ('$KYCId','100', '$ver','$doc_id','$tx')";
	mysqli_query($con,$sql1);
	
	
			$userResponse=array('message' =>'KYC Done! Go To center with verification id for further proceedings.');
 
}
else{



	// generate verification string
	$sql2 = "INSERT INTO user_doc (user_id, status, verification, doc_id,	txnId) VALUES ('$KYCId','101','$ver','$doc_id','$tx')";
	mysqli_query($con,$sql2);
			$userResponse=array('verificationId'=>$ver, 'message' =>'KYC Done and Added to your List.');
}

  echo json_encode(compact('userResponse'));

mysqli_close($con);

?>
