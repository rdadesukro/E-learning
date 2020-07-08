<?php
include "db.php";
$id_guru = $_GET['id_guru'];
mysqli_query($con,"delete from `guru` where id_guru = '$id_guru'");
echo "<script>
			window.location='index.php?hal=guru&pesan=Berhasil dihapus'</script>";
?>


 
