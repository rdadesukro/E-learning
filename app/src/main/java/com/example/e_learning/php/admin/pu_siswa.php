<?php

include "db.php";

$nis            = $_POST['nis'];
$nama           = $_POST['nama'];
$alamat         = $_POST['alamat'];
$jenkel         = $_POST['jenkel'];
$agama          = $_POST['agama'];
$kelas          = $_POST['kelas'];
$ttl          = $_POST['ttl'];
$nama_file              = $_FILES['imageUrl']['name'];
$temp                   = $_FILES['imageUrl']['tmp_name'];


if (!empty($nama_file)){
	if(!copy($temp,"../foto/".$nama_file)){
		echo "<script>alert('gambar harus di upload..'); window.location = 'index.php?page=guru'</script>";
		exit;
	}
}
if (empty($nama_file)){

    $q = mysqli_query($con, "update siswa set
                nama        = '$nama',
            
                kelas        = '$kelas',
                jenkel = '$jenkel',
            
                ttl     = '$ttl',
               alamat       = '$alamat',
                agama           = '$agama'
                where nis = '$nis'") or die (mysqli_error());

if($q){
    echo "<script>alert('Berhasil diedit');
            window.location='index.php?hal=siswa'</script>".$ttl;
            
}               
}else{
    $q = mysqli_query($con, "update siswa set
               nama        = '$nama',
            
                kelas        = '$kelas',
                 jenkel = '$jenkel',
            
                ttl     = '$ttl',
               alamat       = '$alamat',
                agama           = '$agama',
                foto    = '$nama_file'
                where nis = '$nis'") or die (mysqli_error());

if($q){
    echo "<script>alert('Berhasil diedit');
            window.location='index.php?hal=siswa'</script>".$ttl;
            
}               
}



 
               
?>