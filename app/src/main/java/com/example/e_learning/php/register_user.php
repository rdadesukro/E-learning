<?php
/* ===== www.dedykuncoro.com ===== */
/*	include 'koneksi.php';

	class usr{}

	$name = $_POST["name"];
	$password = $_POST["password"];
	$confirm_password = $_POST["confirm_password"];

	if ((empty($name))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom name tidak boleh kosong";
		die(json_encode($response));
	} else if ((empty($password))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom password tidak boleh kosong";
		die(json_encode($response));
	} else if ((empty($confirm_password)) || $password != $confirm_password) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Konfirmasi password tidak sama";
		die(json_encode($response));
	} else {
		if (!empty($name) && $password == $confirm_password){
			$num_rows = mysql_num_rows(mysql_query("SELECT * FROM user WHERE name='".$name."'"));

			if ($num_rows == 0){
				$query = mysql_query("INSERT INTO user (id, name, password) VALUES(0,'".$name."','".$password."')");

				if ($query){
					$response = new usr();
					$response->success = 1;
					$response->message = "Register berhasil, silahkan login.";
					die(json_encode($response));

				} else {
					$response = new usr();
					$response->success = 0;
					$response->message = "name sudah ada";
					die(json_encode($response));
				}
			} else {
				$response = new usr();
				$response->success = 0;
				$response->message = "name sudah ada";
				die(json_encode($response));
			}
		}
	}

	mysql_close();*/


	/* ========= KALAU PAKAI MYSQLI YANG ATAS SEMUA DI REMARK, TERUS YANG INI DI UNREMARK ======== */
	include_once "koneksi.php";

 class usr{}

     //$name = $_POST["name"];
  $username = $_POST["username"];
  $nis = $_POST["nis"];
   $level = $_POST["level"];
  $password =md5($_POST["password"]);
  $confirm_password =md5($_POST["confirm_password"]);
   $kunci = $_POST["kunci"];

	 if ((empty($nis))) {
		$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom NIS tidak boleh kosong";
	 	die(json_encode($response));
	 } else if ((empty($password))) {
	 	$response = new usr();
	  $response->success = 0;
		$response->message = "Kolom password tidak boleh kosong";
		die(json_encode($response));
  	} if ((empty($kunci))) {
		$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom kunci tidak boleh kosong";
	 	die(json_encode($response));
	 }else if ((empty($confirm_password)) || $password != $confirm_password) {
	 	$response = new usr();
		$response->success = 0;
	 	$response->message = "Konfirmasi password tidak sama";
		die(json_encode($response));
 } else {
		 if ($password == $confirm_password){
		 	$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM siswa WHERE nis='".$nis."'"));
            $num_rows1 = mysqli_num_rows(mysqli_query($con, "SELECT * FROM user WHERE nis='".$nis."'"));
		 	if ($num_rows ==1 && $num_rows1==0 ){
		 			$query = mysqli_query($con, "INSERT INTO user (nis,username,password,level,kunci) VALUES ('$nis','$username','$password','$level','$kunci')");

		 		if ($query){
		 			$response = new usr();
		 			$response->success = 1;
		 			$response->message = "Register berhasil, silahkan login.";
		 			die(json_encode($response));
		 		} else {
		 			$response = new usr();
		 			$response->success = 0;
		 			$response->message = "nis tidak terdaftar";
		 			die(json_encode($response));
		 		}
		 	} else {
		 		$response = new usr();
		 		$response->success = 0;
		 		$response->message = "nis anda sudah terdaftar atau anda belum terdaftar sebagai siswa";
		 		die(json_encode($response));
		 	}
		 }
	 }

	 mysqli_close($con);

?>	 