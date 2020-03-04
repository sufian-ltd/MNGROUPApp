<?php

	require "init.php";
	$name=$_GET["name"];
	$sql="select * from market where name='$name'";
	$result=mysqli_query($con,$sql);
	$response=array();
	while ($row=mysqli_fetch_array($result)) {
	  array_push($response,array('id' =>$row['id'],'name' =>$row['name'],
	  'description'=>$row['description'],'paid'=>$row['paid'],'due'=>$row['due']
	  ,'date'=>$row['date']));
	}
	echo json_encode($response);
	mysqli_close($con);

?>