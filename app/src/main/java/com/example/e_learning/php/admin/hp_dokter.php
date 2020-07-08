<?php
include "konek_baru.php";
$id = $_GET['id'];
mysqli_query($con,"delete from `dokter` where id = '$id'");
echo "<script>
			window.location='index.php?hal=dokter&pesan=Berhasil dihapus'</script>";
?>


 
