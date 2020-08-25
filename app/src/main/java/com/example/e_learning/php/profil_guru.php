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

$id_guru=$_GET['id_guru'];
$sql="SELECT guru.id_guru,guru.nama,guru.ttl,guru.jk,guru.agama,guru.alamat,guru.foto,mapel.nama_mapel,guru.id_mapel FROM guru,mapel 
WHERE mapel.id_mapel=guru.id_mapel and guru.id_guru='$id_guru'";
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
