<?php


$host='localhost';
$username='root';
$pwd='';
$db="ngadu";

$con=mysqli_connect($host,$username,$pwd,$db) or die('Unable to connect');

if(mysqli_connect_error($con))
{
	echo "Failed to Connect to Database ".mysqli_connect_error();
}

$id_be=$_GET['id_be'];
$sql="DELETE FROM berita WHERE id_be = '$id_be'";


$result=mysqli_query($con,$sql);
if($result)
{
	echo "Berhasil dihapus ";
}

mysqli_close($con);

?>