<?php
session_start();
include "db.php";
$username = $_POST['username'];
$password = $_POST['password'];
$login = mysqli_query($con, "select * from admin 
			where username='$username' and password=('$password')");
	$row = mysqli_num_rows($login);
	if($row == 0){
		echo "<script>alert('Login Gagal'); window.location = 'login.php'</script>";		
		exit;
	}else{
		$_SESSION['login'] 		= "ok";
		$_SESSION['nama_user'] 	= $username;
		echo "<script> window.location = 'index.php?hal=buku'</script>";
		exit;		
	}
?>