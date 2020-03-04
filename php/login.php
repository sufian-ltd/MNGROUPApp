<?php

require "init.php";
$username=$_GET["username"];
$password=$_GET["password"];

$sql="select user from users where username = '$username' and password='$password'";
$result=mysqli_query($con,$sql);
if(mysqli_num_rows($result)>0){
	$row=mysqli_fetch_array($result);
	$status=$row['user'];
}
else{
	$status="not exist";
}
echo json_encode(array("response"=>$status));
mysqli_close($con);

?>