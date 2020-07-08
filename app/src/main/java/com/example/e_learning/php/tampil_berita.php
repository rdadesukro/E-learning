<?php


$host='localhost';
$username='root';
$pwd='';
$db="e_info";

$con=mysqli_connect($host,$username,$pwd,$db) or die('Unable to connect');

if(mysqli_connect_error($con))
{
	echo "Failed to Connect to Database ".mysqli_connect_error();
}



$sql="SELECT *from berita ";
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
