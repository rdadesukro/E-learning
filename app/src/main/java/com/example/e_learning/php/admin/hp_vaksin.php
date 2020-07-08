<?php
include "konek_baru.php";
$id = $_GET['id'];
mysqli_query($con,"delete from `vaksin` where id = '$id'");
echo "<script>
			window.location='index.php?hal=vaksin&pesan=Berhasil dihapus'</script>";
?>


 
