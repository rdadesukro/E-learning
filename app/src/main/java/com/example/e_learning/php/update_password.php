<?php
/* ===== www.dedykuncoro.com ===== */
/*	include 'koneksi.php';
	
	class usr{}
	
	$nis = $_POST["nis"];
	$password = $_POST["password"];
	$confirm_password = $_POST["confirm_password"];
	
	if ((empty($nis))) { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom nis tidak boleh kosong"; 
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
		if (!empty($nis) && $password == $confirm_password){
			$num_rows = mysql_num_rows(mysql_query("SELECT * FROM user WHERE nis='".$nis."'"));

			if ($num_rows == 0){
				$query = mysql_query("INSERT INTO user (id, nis, password) VALUES(0,'".$nis."','".$password."')");

				if ($query){
					$response = new usr();
					$response->success = 1;
					$response->message = "Register berhasil, silahkan login.";
					die(json_encode($response));

				} else {
					$response = new usr();
					$response->success = 0;
					$response->message = "nis sudah ada";
					die(json_encode($response));
				}
			} else {
				$response = new usr();
				$response->success = 0;
				$response->message = "nis sudah ada";
				die(json_encode($response));
			}
		}
	}

	mysql_close();*/


	/* ========= KALAU PAKAI MYSQLI YANG ATAS SEMUA DI REMARK, TERUS YANG INI DI UNREMARK ======== */
	include_once "koneksi.php";

 class usr{}

 $nis = $_POST["nis"];
 $kunci = $_POST["kunci"];
 $password = md5($_POST["password"]);
 $confirm_password = md5($_POST["confirm_password"]);
	 if ((empty($nis))) {
		$response = new usr();
	 	$response->success = 1;
	 	$response->message = "Kolom nis tidak boleh kosong";
	 	die(json_encode($response));
	}  elseif ((empty($kunci))) {
		$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom kunci tidak boleh kosong";
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
		 if (!empty($nis) && $password == $confirm_password){
		 	$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM user WHERE nis='".$nis."' and kunci='".$kunci."'"));

		 	if ($num_rows == 1){
		 		$query = mysqli_query($con, "UPDATE user SET password='$password'  WHERE nis='$nis'");

				if ($query){
					$response = new usr();		
					$response->success = 0;
					$response->message = "Register berhasil, silahkan login.";
		            die(json_encode($response));

				} else {
					$response = new usr();
					$response->success = 0;
	 			$response->message = "nis  anda tidak anda";
	 			die(json_encode($response));
				}
			} else {
				$response = new usr();
				$response->success = 0;
				$response->message = "nis tidak ada";
				die(json_encode($response));
			}
	 }
	}

 mysqli_close($con);

?>	