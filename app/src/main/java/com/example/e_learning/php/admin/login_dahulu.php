<?php
error_reporting(0);
session_start();
define( 'engine', 1 );
if($_SESSION['login'] != "ok"){ 
	 echo "<script> window.location = 'login.php'</script>";
	exit;
}
$pesan =  strip_tags($_GET['pesan']);
?>