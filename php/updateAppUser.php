<?php

	require "init.php";
	$fullName=$_GET["fullName"];	
	$username=$_GET["username"];
	$password=$_GET["password"];
	$id=$_GET["id"];

	$sql="update users set fullName='$fullName',username='$username',password='$password' where id='$id'"; 
	if(mysqli_query($con, $sql)){
		$status="saved";
	}
	else{
		$status="not saved";
	}

	echo json_encode(array("response"=>$status));
	mysqli_close($con);

?>