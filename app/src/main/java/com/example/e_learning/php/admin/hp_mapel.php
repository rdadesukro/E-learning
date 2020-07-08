<?php
include "db.php";
$id_mapel = $_GET['id_mapel'];
mysqli_query($con,"delete from `mapel` where id_mapel = '$id_mapel'");
echo "<script>
			window.location='index.php?hal=mapel&pesan=Berhasil dihapus'</script>";
?>


 
