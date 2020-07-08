<?php
include "db.php";
$nis = $_GET['nis'];
mysqli_query($con,"delete from `siswa` where nis = '$nis'");
echo "<script>
			window.location='index.php?hal=siswa&pesan=Berhasil dihapus'</script>";
?>


 
