<?php

$uid = file_get_contents('php://input');


$result = json_decode($uid, true);

//$adhr_id= $result['hof_Details'];
//$adhr_id=$adhr_id['AADHAR_ID'];
//$file = "resident_signup/".$adhr_id.".txt";

$KYCId= $result['KYCid'];
 require_once('../library/dbConnect.php');
 
 
 
 /* change character set to utf8 */
if (!$con->set_charset("utf8")) {
    printf("", $con->error);
    exit();
} else {
    printf("", $con->character_set_name());
}



 $fetchdoc =  mysqli_query($con,"SELECT * FROM document_list");
	$documents=array();

 while ($row = mysqli_fetch_assoc($fetchdoc)) {
		$data = array();
		$sd   = array();
		foreach ($row as $key => $value) {
		 $data[$key]=$value; }
		 
		 $document_id = $data['id'];
		 
		$fetchdoc2 = mysqli_query($con,"SELECT * FROM user_doc WHERE doc_id='$document_id' AND user_id='$KYCId' AND verification!=''");
		if($rowdoc2 = mysqli_fetch_assoc($fetchdoc2)){
			 $data['user_doc_id']= $rowdoc2['verification'];
			 
			 $data['status']= $rowdoc2['status'];
			 
			 
			 //  101 - Verified
			 //  100 - Pending
			 //  102 - Cancel
		}
		
		
		 $fetch2     = mysqli_query($con, "SELECT * FROM support_doc WHERE parent_id='$document_id' ");
        while ($row2 = mysqli_fetch_assoc($fetch2)) {
            $data2 = array();
            foreach ($row2 as $key2 => $value2) {
                $data2[$key2] = $value2;
			} 
			$sid = $data2['doc_id'];
		$sd[] = $sid;
		}
		
	
	 $data['support_docs'] = $sd;
	 $documents[] = $data;
			
 }
  echo json_encode(compact('documents'));
	
 
 
mysqli_close($con);

 exit();
 
 ?>
 
 