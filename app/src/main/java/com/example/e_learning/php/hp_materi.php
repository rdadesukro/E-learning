<?php


$host='localhost';
$username='root';
$pwd='';
$db="e-leraning";

$con=mysqli_connect($host,$username,$pwd,$db) or die('Unable to connect');
 class usr{}
if(mysqli_connect_error($con))
{
	echo "Failed to Connect to Database ".mysqli_connect_error();
}

$id=$_GET['id'];
$sql="DELETE FROM materi WHERE id = '$id'";


$result=mysqli_query($con,$sql);
if($result)
{
	$response = new usr();
	$response->success = 1;
	die(json_encode($response));
}

mysqli_close($con);

?>