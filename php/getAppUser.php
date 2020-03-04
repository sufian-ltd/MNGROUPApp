<?php

	require "init.php";
	$sql="select * from appUsers";
	$result=mysqli_query($con,$sql);
	$response=array();
	while ($row=mysqli_fetch_array($result)) {
	  array_push($response,array('id' =>$row['id'],'fullName' =>$row['fullName'],'username' =>$row['username'],'password' =>$row['password'],'user'=>$row['user']));
	}
	echo json_encode($response);
	mysqli_close($con);

?>