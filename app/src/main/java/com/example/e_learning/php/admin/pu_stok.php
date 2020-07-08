<?php
include "konek_baru.php";

$id_stok	= $_POST['id_stok'];
$gol		= $_POST['gol'];
$Rhesus		= $_POST['Rhesus'];
$stok		= $_POST['stok'];

mysqli_query ($con, "update stok set
					gol 		= '$gol',
					Rhesus	    = '$Rhesus',
					stok		= '$stok'
					where id_stok = '$id_stok'
					") or die (mysqli_error());
					
echo "<script>alert('Berhasil disimpan');
			window.location='index.php?hal=stok'</script>";					
?>