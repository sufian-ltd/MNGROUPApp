<?php

	require "init.php";
	$name=$_GET["name"];
	$description=$_GET["description"];
	$debit=$_GET["debit"];
	$credit=$_GET["credit"];
	$date=$_GET["date"];

	$sql="insert into customer (name,description,debit,credit,date) 
	values('$name','$description','$debit','$credit','$date');";
	if(mysqli_query($con, $sql)){
		$status="inserted";
	}
	else{
		$status="not inserted";
	}
	echo json_encode(array("response"=>$status));
	mysqli_close($con);

?>