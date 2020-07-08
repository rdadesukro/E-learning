<?php

include "konek_baru.php";

$id		= $_POST['id'];
$kivi			= $_POST['kivi'];



if (!empty($kivi_file)){
	if(!copy($temp,"images/".$kivi_file)){
		echo "<script>alert('gambar harus di upload..'); window.location = 'index.php?page=datafs'</script>";
		exit;
	}
}

$q = mysqli_query($con, "update kivi set
				kivi		= '$kivi'
				where id = '$id'") or die (mysqli_error());

if($q){
	echo "<script>alert('Berhasil diedit');
			window.location='index.php?hal=kivi'</script>";
}				
?>