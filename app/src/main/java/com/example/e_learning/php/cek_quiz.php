
<?php
require_once 'koneksi_new.php';
header("Content-type:application/json");


if(!$konek){
die('Could not connect: '.mysqli_error());
}
$nis=$_GET['nis'];
$quiz=$_GET['quiz'];
$query = "SELECT * FROM `quiz` WHERE nis='$nis' and status='$quiz'";

$result = mysqli_query($konek,$query);
$array = array();

while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row;
}


echo ($result) ?

json_encode(array("kode" => 1,"search_count" => count($array), "result"=>$array)) :
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));
?>

