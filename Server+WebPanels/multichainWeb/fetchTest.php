<?php
	// error_reporting(0);
	require_once 'functions.php';
 	 set_multichain_config();
	 
	 
	 $schemaAddress=string_to_txout_bin($_POST['schemaAddress']);
	 $publishtxid=string_to_txout_bin($_POST['publishtxid']);
	 
	 
	 if (no_displayed_error_result($streamItem, multichain('getstreamitem', $schemaAddress, $publishtxid, false)))
			{

			echo $streamItem;
			$binary=pack('H*', $streamItem['data']);
			$size=strlen($binary);
			echo html($binary);
			}
			
?>
			