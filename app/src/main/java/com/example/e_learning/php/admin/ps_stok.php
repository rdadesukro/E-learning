<?php
include "konek_baru.php";

$gol		= $_POST['gol'];
$Rhesus		= $_POST['Rhesus'];
$stok		= $_POST['stok'];

mysqli_query ($con, "insert into stok values(	
					0,
					'$gol',
					'$Rhesus',
					'$stok'
					)") or die (mysqli_error());
					
echo "<script>alert('Berhasil disimpan');
			window.location='index.php?hal=stok'</script>";					
?>