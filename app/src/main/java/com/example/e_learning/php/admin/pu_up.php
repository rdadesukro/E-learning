<?php
include "konek.php";

$id_admin				= $_POST['id_admin'];
$password				= $_POST['password'];
$password_l				= $_POST['password_l'];
$password_b				= $_POST['password_b'];
$upb					= $_POST['upb'];

if($password_l == "" or $password_b == "" or $upb == ""){
	echo "<script>
			window.location='index.php?hal=ubah_password&id_admin=1&pesan=Semua data tdk boleh kosong'</script>";
}else if($password != $password_l){
	echo "<script>
			window.location='index.php?hal=ubah_password&id_admin=1&pesan=Password lama anda salah'</script>";
}else if($password_b != $upb){
	echo "<script>
			window.location='index.php?hal=ubah_password&id_admin=1&pesan=Password baru dan ulangi password baru tidak sesuai'</script>";			
}else{
	mysql_query("UPDATE  admin SET  password =  '$password_b' WHERE  id_admin ='$id_admin'");
	echo "<script>
			window.location='index.php?hal=ubah_password&id_admin=1&pesan=Berhasil diedit'</script>";
}
?>