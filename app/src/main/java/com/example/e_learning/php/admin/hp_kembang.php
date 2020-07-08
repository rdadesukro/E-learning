<?php
include "konek_baru.php";
$id = $_GET['id'];
mysqli_query($con,"delete from `riwayat` where id = '$id'");
echo "<script>
			window.location='index.php?hal=kembang&pesan=Berhasil dihapus'</script>";
?>


 
