<?php

	require "init.php";
	$sql="select distinct name from market";
	$result=mysqli_query($con,$sql);
	$response=array();
	while ($row=mysqli_fetch_array($result)) {
	  array_push($response,array('name' =>$row['name']));
	}
	echo json_encode($response);
	mysqli_close($con);

?>