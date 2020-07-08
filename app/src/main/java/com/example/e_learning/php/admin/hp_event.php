<?php
include "konek_baru.php";
$id_event = $_GET['id_event'];
mysqli_query($con,"delete from `event` where id_event = '$id_event'");
echo "<script>
			window.location='index.php?hal=event&pesan=Berhasil dihapus'</script>";
?>


 
