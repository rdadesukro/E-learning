<?php

include 'DatabaseConfig.php';

// Create connection
$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);
 
 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
	 $Defaultid_be = 0;
 
 $ImageData = $_POST['image_path'];
 
 $judul = $_POST['judul'];

 $GetOldid_beSQL ="SELECT id_be FROM berita ORDER BY id_be ASC";
 
 $Query = mysqli_query($conn,$GetOldid_beSQL);
 
 while($row = mysqli_fetch_array($Query)){
 
 $Defaultid_be = $row['id_be'];
 }
 
 $ImagePath = "foto/$Defaultid_be.png";
 
 $ServerURL = "$ImagePath";
 
 $InsertSQL = "insert into berita (image_path,judul) values ('$ServerURL','$judul')";
 
 if(mysqli_query($conn, $InsertSQL)){

 file_put_contents($ImagePath,base64_decode($ImageData));

 echo "Your Image Has Been Uploaded.";
 }
 
 mysqli_close($conn);
 }else{
 echo "Not Uploaded";
 }

?>