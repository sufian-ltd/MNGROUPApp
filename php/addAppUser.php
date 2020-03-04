<?php

	require "init.php";

	$fullName=$_GET["fullName"];
	$username=$_GET["username"];
	$password=$_GET["password"];
	$user=$_GET["user"];

	$sql="select * from users where username = '$username'";
	$result=mysqli_query($con,$sql);
	if(mysqli_num_rows($result)>0){
		$status="exist";
	}
	else{
		$sql="insert into users (fullName,username,password,user) 
		values('$fullName','$username','$password','$user');";
		if(mysqli_query($con, $sql)){
			$status="inserted";
		}
		else{
			$status="not inserted";
		}
	}
	echo json_encode(array("response"=>$status));
	mysqli_close($con);

?>