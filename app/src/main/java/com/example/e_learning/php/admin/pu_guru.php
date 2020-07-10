<?php

include "db.php";

$id_guru		= $_POST['id_guru'];
$nama			= $_POST['nama'];
$alamat			= $_POST['alamat'];
$ttl 		= $_POST['ttl'];
$id_mapel 		= $_POST['id_mapel'];
$agama			= $_POST['agama'];
$jk			= $_POST['jk'];


$nama_file				= $_FILES['imageUrl']['name'];
$temp 					= $_FILES['imageUrl']['tmp_name'];


if (!empty($nama_file)){
	if(!copy($temp,"../foto/".$nama_file)){
		echo "<script>alert('gambar harus di upload..'); window.location = 'index.php?page=guru'</script>";
		exit;
	}
}
if (empty($nama_file)){

    $q = mysqli_query($con, "update guru set
                nama        = '$nama',
            
                id_mapel        = '$id_mapel',
            
                ttl     = '$ttl',
               alamat       = '$alamat',
                agama           = '$agama',
                jk          = '$jk'
                where id_guru = '$id_guru'") or die (mysqli_error());

if($q){
    echo "<script>alert('Berhasil diedit');
            window.location='index.php?hal=guru'</script>".$ttl;
            
}               
}else{
    $q = mysqli_query($con, "update guru set
                nama        = '$nama',
            
                id_mapel        = '$id_mapel',
            
                ttl     = '$ttl',
               alamat       = '$alamat',
                agama           = '$agama',
                jk          = '$jk',
                foto    = '$nama_file'
                where id_guru = '$id_guru'") or die (mysqli_error());

if($q){
    echo "<script>alert('Berhasil diedit');
            window.location='index.php?hal=guru'</script>".$ttl;
            
}               
}


 
               
?>