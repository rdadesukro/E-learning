<?php
include "db.php";
$nis = $_GET['nis'];
mysqli_query($con,"delete from `user` where nis = '$nis'");
echo "<script>
			window.location='index.php?hal=akun&pesan=Berhasil dihapus'</script>";
?>


 
