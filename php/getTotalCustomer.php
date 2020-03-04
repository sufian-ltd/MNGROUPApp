<?php

	require "init.php";
	$sql="select name,sum(debit) as debit,sum(credit) as credit from customer group by name
";
	$result=mysqli_query($con,$sql);
	$response=array();
	while ($row=mysqli_fetch_array($result)) {
	  array_push($response,array('name' =>$row['name'],'debit'=>$row['debit'],'credit'=>$row['credit']));
	}
	echo json_encode($response);
	mysqli_close($con);

?>