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

$nis=$_GET['nis'];
$mapel=$_GET['mapel'];
$sql="SELECT *FROM quiz where nis='$nis' and mapel='$mapel'";
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
