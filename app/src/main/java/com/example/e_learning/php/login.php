<?php
/*	/* ===== www.dedykuncoro.com ===== */
	/*include 'koneksi.php';
	
	class usr{}
	
	$username = $_POST["username"];
	$password = $_POST["password"];
	
	if ((empty($username)) || (empty($password))) { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom tid_userak boleh kosong"; 
		die(json_encode($response));
	}
	
	$query = mysql_query("SELECT * FROM users WHERE username='$username' AND password='$password'");
	
	$row = mysql_fetch_array($query);
	
	if (!empty($row)){
		$response = new usr();
		$response->success = 1;
		$response->message = "Selamat datang ".$row['username'];
		$response->id_user = $row['id_user'];
		$response->username = $row['username'];
		die(json_encode($response));
		
	} else { 
		$response = new usr();
		$response->success = 0;
		$response->message = "username atau password salah";
		die(json_encode($response));
	}
	
	mysql_close();*/


	//=================== KALAU PAKAI MYSQLI YANG ATAS SEMUA DI REMARK, TERUS YANG INI RI UNREMARK ========
	 include_once "koneksi.php";

	 class usr{}
	 $username = $_POST["username"];
	$password = md5($_POST["password"]);
	
	 if ((empty($username)) || (empty($password))) { 
 	$response = new usr();
	$response->success = 0;
	$response->message = "Kolom tid_userak boleh kosong"; 
		die(json_encode($response));
	 }
	
	 $query = mysqli_query($con, "SELECT * FROM user WHERE username='$username' AND password='$password'");
	
	 $row = mysqli_fetch_array($query);
	
	 if (!empty($row)){
	 	$response = new usr();
	 	$response->success = 1;
	 	$response->message = "Selamat datang ".$row['username'];
	 	$response->nis = $row['nis'];
	 	$response->username = $row['username'];
		$response->level = $row['level'];
	 	die(json_encode($response));
		
	 } else { 
	 	$response = new usr();
		$response->success = 0;
		$response->message = "username atau password salah";
	 	die(json_encode($response));
	 }
	
	 mysqli_close($con);

?>