<?php

include "db.php";

$id_mapel		= $_POST['id_mapel'];
$nama_mapel			= $_POST['nama_mapel'];
$nama_file				= $_FILES['imageUrl']['name'];
$temp 					= $_FILES['imageUrl']['tmp_name'];


if (!empty($nama_file)){
	if(!copy($temp,"../foto/".$nama_file)){
		echo "<script>alert('gambar harus di upload..'); window.location = 'index.php?page=datafs'</script>";
		exit;
	}
}

	if($nama_mapel == ""){
		echo "<script>alert('Data yang anda input belum lengkap');history.go(-1);</script>";
	}else if($nama_file == ""){
		
		mysqli_query($con,"update mapel set
					nama_mapel			= '$nama_mapel'
					where id_mapel		= '$id_mapel'") or die ("error");	
				
		echo "<script>alert('Berhasil disimpan');
			window.location='index.php?page=mapel'</script>";
			
	}else{
		mysqli_query($con,"update mapel set
					nama_mapel			= '$nama_mapel',
					foto_mapel			= '$nama_file'
					where id_mapel		= '$id_mapel'") or die ("error");	
				
		echo "<script>alert('Berhasil disimpan');
			window.location='index.php?page=mapel'</script>";
	}
	


				
?>