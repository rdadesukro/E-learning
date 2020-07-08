<?php
include "konek_baru.php";
$id = $_GET['id'];
mysqli_query($con,"delete from `kivi` where id = '$id'");
echo "<script>
			window.location='index.php?hal=kivi&pesan=Berhasil dihapus'</script>";
?>


 
