<?php

	require "init.php";
	$sql="select name,sum(paid) as paid,sum(due) as due from market group by name
";
	$result=mysqli_query($con,$sql);
	$response=array();
	while ($row=mysqli_fetch_array($result)) {
	  array_push($response,array('name' =>$row['name'],'paid'=>$row['paid'],'due'=>$row['due']));
	}
	echo json_encode($response);
	mysqli_close($con);

?>