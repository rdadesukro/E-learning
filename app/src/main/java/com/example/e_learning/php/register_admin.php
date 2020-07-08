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
	 $token = $_POST["token"];
	 $nama = $_POST["nama"];
	 $no_hp = $_POST["no_hp"];
     $email = $_POST["email"];
	 $alamat = $_POST["alamat"];
	 $password =md5($_POST["password"]);
     $confirm_password =md5($_POST["confirm_password"]);
     $kunci = $_POST["kunci"];
	 if ((empty($kunci))) {
		$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom kunci tidak boleh kosong";
	 	die(json_encode($response));	
	 }	else if ((empty($no_hp))) {
		$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom nama tidak boleh kosong";
	 	die(json_encode($response));
	 }	else if ((empty($alamat))) {
		$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom alamat tidak boleh kosong";
	 	die(json_encode($response));
	 } else if ((empty($nama))) {
		$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom nama tidak boleh kosong";
	 	die(json_encode($response));
	 } else if ((empty($password))) {
	 	$response = new usr();
	    $response->success = 0;
		$response->message = "Kolom password tidak boleh kosong";
		die(json_encode($response));
	} if ((empty($email))) {
		$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Kolom email tidak boleh kosong";
	 	die(json_encode($response));
	 }else if ((empty($confirm_password)) || $password != $confirm_password) {
	 	$response = new usr();
		$response->success = 0;
	 	$response->message = "Konfirmasi password tidak sama";
		die(json_encode($response));
 } else {
		 if ($password == $confirm_password){
		 	$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM admin WHERE email='".$email."'"));

		 	if ($num_rows == 0){
		 		$query = mysqli_query($con, "INSERT INTO admin (id, email, password,kunci,token,nama,no_hp,alamat) VALUES(0,'".$email."','".$password."','".$kunci."','".$token."','".$nama."','".$no_hp."','".$alamat."')");

				if ($query){
					$response = new usr();	
                     $response->success = 0;					
					$response->message = "Register berhasil, silahkan login.";
	             	die(json_encode($response));

				} else {
					$response = new usr();
					$response->success = 0;
	 		     	$response->message = "Email sudah ada";
	 			die(json_encode($response));
				}
			} else {
				$response = new usr();
				$response->success = 0;
				$response->message = "Email sudah ada";
				die(json_encode($response));
			}
	 }
	}

 mysqli_close($con);

?>	