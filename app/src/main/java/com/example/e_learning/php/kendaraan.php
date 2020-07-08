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

$id_perusahaan=$_GET['id_perusahaan'];


$sql="SELECT kendaraan.`id_kendaraan`,kendaraan.`id_perusahaan`,kendaraan.`foto`,kendaraan.`harga`,kendaraan.`nama`,kendaraan.`no_kendaraan`,kendaraan.`harga`,kendaraan.`id_kat`,perusahaan.`lat`,perusahaan.`lng` FROM kendaraan,perusahaan WHERE kendaraan.id_perusahaan='$id_perusahaan' AND kendaraan.`id_perusahaan`=perusahaan.`id_perusahaan`"; 
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