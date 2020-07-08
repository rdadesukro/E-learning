<?php
/* ===== www.dedykuncoro.com ===== */
/*	include 'koneksi.php';

	class usr{}

	$id_berita = $_POST["id_berita"];
	$status = $_POST["status"];
	$confirm_status = $_POST["confirm_status"];

	if ((empty($id_berita))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom id_berita tidak boleh kosong";
		die(json_encode($response));
	} else if ((empty($status))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom status tidak boleh kosong";
		die(json_encode($response));
	} else if ((empty($confirm_status)) || $status != $confirm_status) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Konfirmasi status tidak sama";
		die(json_encode($response));
	} else {
		if (!empty($id_berita) && $status == $confirm_status){
			$num_rows = mysql_num_rows(mysql_query("SELECT * FROM user WHERE id_berita='".$id_berita."'"));

			if ($num_rows == 0){
				$query = mysql_query("INSERT INTO user (id, id_berita, status) VALUES(0,'".$id_berita."','".$status."')");

				if ($query){
					$response = new usr();
					$response->success = 1;
					$response->message = "Register berhasil, silahkan login.";
					die(json_encode($response));

				} else {
					$response = new usr();
					$response->success = 0;
					$response->message = "id_berita sudah ada";
					die(json_encode($response));
				}
			} else {
				$response = new usr();
				$response->success = 0;
				$response->message = "id_berita sudah ada";
				die(json_encode($response));
			}
		}
	}

	mysql_close();*/


	/* ========= KALAU PAKAI MYSQLI YANG ATAS SEMUA DI REMARK, TERUS YANG INI DI UNREMARK ======== */
	include_once "koneksi.php";

 class usr{}

 $id_berita = $_POST["id_berita"];
 $status = $_POST["status"];
	 if ((empty($id_berita))) {
		$response = new usr();
	 	$response->success = 1;
	 	$response->message = "Kolom id_berita tidak boleh kosong";
	 	die(json_encode($response));
	} else if ((empty($status))) {
	 	$response = new usr();
	    $response->success = 0;
		$response->message = "Kolom status tidak boleh kosong";
		die(json_encode($response));
	}  else {
		 if (!empty($id_berita)){
		 	$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM berita WHERE id_berita='".$id_berita."'"));

		 	if ($num_rows == 1){
		 		$query = mysqli_query($con, "UPDATE berita SET status='$status'  WHERE id_berita='$id_berita'");

				if ($query){
					$response = new usr();
					$response->success = 0;
					$response->message = "Register berhasil, silahkan login.";
		            die(json_encode($response));

				} else {
					$response = new usr();
					$response->success = 0;
	 			$response->message = "id_berita  anda tidak anda";
	 			die(json_encode($response));
				}
			} else {
				$response = new usr();
				$response->success = 0;
				$response->message = "id_berita tidak ada";
				die(json_encode($response));
			}
	 }
	}

 mysqli_close($con);

?>
