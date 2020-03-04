<?php

	require "init.php";
	$name=$_GET["name"];

	$sql="delete from market where name='$name'";
	if(mysqli_query($con, $sql)){
		$status="delete";
	}
	else{
		$status="not delete";
	}
	echo json_encode(array("response"=>$status));
	mysqli_close($con);

?>