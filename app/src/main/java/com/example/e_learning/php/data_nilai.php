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
$sql="SELECT  DISTINCT mapel.nama_mapel,quiz.id_quiz,quiz.nilai,siswa.kelas,quiz.nis,siswa.nama,siswa.foto FROM quiz,mapel,guru,siswa
 WHERE quiz.mapel=mapel.id_mapel AND quiz.nis=siswa.nis and quiz.id_guru='$id_guru'";


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