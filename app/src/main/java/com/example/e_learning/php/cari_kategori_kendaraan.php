<?php


$host='localhost';
$username='root';
$pwd='';
$db="mobil";

$con=mysqli_connect($host,$username,$pwd,$db) or die('Unable to connect');

if(mysqli_connect_error($con))
{
	echo "Failed to Connect to Database ".mysqli_connect_error();
}
$nama=$_POST['tes'];
$id_kat=$_GET['id_kat'];
$sql="SELECT *FROM kendaraan WHERE id_kat='$id_kat' AND nama LIKE '%$nama%'";
$query=mysqli_query($con,$sql);
if($query)
{
    while($row=mysqli_fetch_array($query))
  {
    $data[]=$row;
  }
    //print(json_encode($data));
	print(json_encode($data));
}else
{
  echo('Not Found ');
}
mysqli_close($con);

?>