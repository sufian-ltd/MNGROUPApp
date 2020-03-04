<?php

	require "init.php";
	$name=$_GET["name"];
	$loc=$_GET["loc"];
	$contact=$_GET["contact"];
	$description=$_GET["description"];
	$paid=$_GET["paid"];
	$due=$_GET["due"];
	$date=$_GET["date"];

	$sql="insert into market (name,loc,contact,description,paid,due,date) 
	values('$name','$loc','$contact','$description','$paid','$due','$date');";
	if(mysqli_query($con, $sql)){
		$status="inserted";
	}
	else{
		$status="not inserted";
	}
	echo json_encode(array("response"=>$status));
	mysqli_close($con);

?>