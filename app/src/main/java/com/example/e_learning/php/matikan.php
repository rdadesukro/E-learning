<?php
/* ===== www.dedykuncoro.com ===== */
/*	include 'koneksi.php';

	class usr{}

	$nis = $_POST["nis"];
	$token = $_POST["token"];
	$confirm_token = $_POST["confirm_token"];

	if ((empty($nis))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom nis tidak boleh kosong";
		die(json_encode($response));
	} else if ((empty($token))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom token tidak boleh kosong";
		die(json_encode($response));
	} else if ((empty($confirm_token)) || $token != $confirm_token) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Konfirmasi token tidak sama";
		die(json_encode($response));
	} else {
		if (!empty($nis) && $token == $confirm_token){
			$num_rows = mysql_num_rows(mysql_query("SELECT * FROM user WHERE nis='".$nis."'"));

			if ($num_rows == 0){
				$query = mysql_query("INSERT INTO user (id, nis, token) VALUES(0,'".$nis."','".$token."')");

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
 $token = $_POST["token"];
	 if ((empty($nis))) {
		$response = new usr();
	 	$response->success = 1;
	 	$response->message = "Kolom nis tidak boleh kosong";
	 	die(json_encode($response));
	} else if ((empty($token))) {
	 	$response = new usr();
	    $response->success = 0;
		$response->message = "Kolom token tidak boleh kosong";
		die(json_encode($response));
	}  else {
		 if (!empty($nis)){
		 	$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM user WHERE nis='".$nis."'"));

		 	if ($num_rows == 1){
		 		$query = mysqli_query($con, "UPDATE user SET token='$token'  WHERE nis='$nis'");

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
