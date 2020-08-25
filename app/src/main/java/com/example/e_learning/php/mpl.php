<?php


$host='localhost';
$username='root';
$pwd='';
$db="e-leraning";

$con=mysqli_connect($host,$username,$pwd,$db) or die('Unable to connect');

if(mysqli_connect_error($con))
{
	echo "Failed to Connect to Database ".mysqli_connect_error();
}

$id_mapel=$_GET['id_mapel'];
$sql="SELECT * FROM   mapel  WHERE id_mapel= '$id_mapel'";


$result=mysqli_query($con,$sql);
if($result)
{
	while($row=mysqli_fetch_array($result))
	{
		$data[]=$row;
	}
	
	print(json_encode($data));
}

mysqli_close($con);

?>