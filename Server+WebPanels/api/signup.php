

<?php
error_reporting(0);

	require_once('../streams/streams.php');
// JSON decode
$signUp = file_get_contents('php://input');


$result = json_decode($signUp, true);

$bhamashahId= $result['bhamashahId'];
 require_once('../library/dbConnect.php');
 $sql = "SELECT * FROM user WHERE BHAMASHAH_ID='$bhamashahId'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$sql));
 
 if(isset($check)){

	$fetch = mysqli_query($con,"SELECT * FROM `user` WHERE BHAMASHAH_ID='$bhamashahId'");
	
	while ($row = mysqli_fetch_assoc($fetch)) {
		
		foreach ($row as $key => $value) {
			 
			if($value!=NULL && $value!=null && $value!=''){
				$data[$key]=$value;
				$publishtxid=$data['txnId'];
				$schemaAddress=$data['schemaAddress'];
			}
			
			} 
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
			
			
 }
 
 else{
	// fetch data from api for the bhamashah id given


$url="https://apitest.sewadwaar.rajasthan.gov.in/app/live/Service/Bhamashah/hofAndMember/".$bhamashahId."?client_id=ad7288a4-7764-436d-a727-783a977f1fe1";
//  Initiate curl
$ch = curl_init();
// Disable SSL verification
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
// Will return the response, if false it print the response
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
// Set the url
curl_setopt($ch, CURLOPT_URL,$url);
// Execute
$result=curl_exec($ch);

curl_close($ch);

$json =json_decode($result,true);



$user_Details=$json['hof_Details'];
	
	//save the json in block chain and return txnid as $transactionId
	$ch = curl_init();

	curl_setopt($ch, CURLOPT_URL,"http://localhost/easyKYC/multichainWeb/publish.php");
	curl_setopt($ch, CURLOPT_POST, 1);
	curl_setopt($ch, CURLOPT_POSTFIELDS,
            "streamName=$streamUsers&text=$user_Details");
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		
	$tx = curl_exec($ch);

		curl_close ($ch);
	
			   $CATEGORY_DESC_ENG=$user_Details["CATEGORY_DESC_ENG"];
			   $AADHAR_ID=$user_Details["AADHAR_ID"];
			   $STATE=$user_Details["STATE"];
			   $MOTHER_NAME_ENG=$user_Details["MOTHER_NAME_ENG"];
			   $HOUSE_NUMBER_ENG=$user_Details["HOUSE_NUMBER_ENG"];
			   $RELATION_ENG=$user_Details["RELATION_ENG"];
			   $DOB=$user_Details["DOB"];
			   $ECONOMIC_GROUP=$user_Details["ECONOMIC_GROUP"];
			   $BUILDING_NO_ENG=$user_Details["BUILDING_NO_ENG"];
			   $BHAMASHAH_ID=$user_Details["BHAMASHAH_ID"];
			   $STREET_NAME_ENG=$user_Details["STREET_NAME_ENG"];
			   $IFSC_CODE=$user_Details["IFSC_CODE"];
			   $M_ID=$user_Details["M_ID"];
			   $FAMILYIDNO=$user_Details["FAMILYIDNO"];
			   $PIN_CODE=$user_Details["PIN_CODE"];
			   $LANDLINE_NO=$user_Details["LANDLINE_NO"];
			   $VILLAGE_NAME=$user_Details["VILLAGE_NAME"];
			   $GP_WARD=$user_Details["GP_WARD"];
			   $EMAIL=$user_Details["EMAIL"];
			   $SPOUCE_NAME_ENG=$user_Details["SPOUCE_NAME_ENG"];
			   $IS_RURAL=$user_Details["IS_RURAL"];
			   $DISTRICT=$user_Details["DISTRICT"];
			   $EDUCATION_DESC_ENG=$user_Details["EDUCATION_DESC_ENG"];
			   $ACC_NO=$user_Details["ACC_NO"];
			   $ADDRESS=$user_Details["ADDRESS"];
			   $INCOME_DESC_ENG=$user_Details["INCOME_DESC_ENG"];
			   $BANK_NAME=$user_Details["BANK_NAME"];
			   $LAND_MARK_ENG=$user_Details["LAND_MARK_ENG"];
			   $RATION_CARD_NO=$user_Details["RATION_CARD_NO"];
			   $NAME_ENG=$user_Details["NAME_ENG"];
			   $MOBILE_NO=$user_Details["MOBILE_NO"];
			   $GENDER=$user_Details["GENDER"];
			   $FATHER_NAME_ENG=$user_Details["FATHER_NAME_ENG"];
			   $FAX_NO=$user_Details["FAX_NO"];
			   $BLOCK_CITY=$user_Details["BLOCK_CITY"];
			   $streamAddress = $streamUsers;
			
				
			$sqlentry="INSERT INTO `user` (MEMBER_AADHAR_ID, STATE,DOB, BHAMASHAH_ID, NAME_ENG, MOBILE, GENDER, schemaAddress, txnId) 
			VALUES('$AADHAR_ID','$STATE','$DOB','$BHAMASHAH_ID','$NAME_ENG','$MOBILE_NO','$GENDER','$streamAddress','$tx')";
			  $result = mysqli_query($con,$sqlentry);
			  
			  
			   if($result){

			  	$fetch = mysqli_query($con,"SELECT * FROM `user` WHERE BHAMASHAH_ID='$bhamashahId'");
	
	while ($row = mysqli_fetch_assoc($fetch)) {
		
		foreach ($row as $key => $value) {
			 
			if($value!=NULL && $value!=null && $value!=''){
				$data[$key]=$value;
			}
			
			} 
			
			$resident_details = $data; }
			  
			  
			  
		  }
		 
		 
		 
	  
 
 }
 
 
echo json_encode(compact('resident_details'));
			mysqli_close($con);

 exit();
 
 ?>
 
 
 
