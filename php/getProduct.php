<?php

	require "init.php";
	$name=$_GET["name"];
	$sql="select * from product where name='$name'";
	$result=mysqli_query($con,$sql);
	$response=array();
	while ($row=mysqli_fetch_array($result)) {
	  array_push($response,array('id' =>$row['id'],'name' =>$row['name'],
	  'description'=>$row['description'],'debit'=>$row['debit'],'credit'=>$row['credit']
	  ,'date'=>$row['date']));
	}
	echo json_encode($response);
	mysqli_close($con);

?>