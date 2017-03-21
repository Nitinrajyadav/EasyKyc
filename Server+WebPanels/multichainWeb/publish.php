<?php
	// error_reporting(0);
	require_once 'functions.php';
	
 	 set_multichain_config();

?>
<?php


	 $data=string_to_txout_bin($_POST['text']);

	 $streamName=string_to_txout_bin($_POST['streamName']);

	 if (no_displayed_error_result($publishtxid, multichain(
			'publishfrom', '15zQxuoQjsYAScPeaBoBB6WikeC7jzw6r5TQR3', $streamName, '', bin2hex($data)
		))){

		echo $publishtxid;

	}
		
?>

			