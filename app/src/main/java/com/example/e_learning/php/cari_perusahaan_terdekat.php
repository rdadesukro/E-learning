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

$lat = $_GET['lat'];
$lng = $_GET['lng'];	
$alamat_perusahaan=$_POST['tes'];
$nama_perusahaan=$_POST['tes'];

$sql="SELECT id_perusahaan, nama_perusahaan, alamat_perusahaan,lat,lng,foto,jam_operasional,id_kec,(6371 * ACOS(SIN(RADIANS(lat)) * SIN(RADIANS($lat)) + COS(RADIANS(lng - $lng)) * COS(RADIANS(lat)) * COS(RADIANS($lat)))) AS jarak FROM perusahaan WHERE   nama_perusahaan LIKE '%$nama_perusahaan%' or alamat_perusahaan LIKE '%$alamat_perusahaan' HAVING jarak < 6371";
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