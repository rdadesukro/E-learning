<?php
	include_once "koneksi.php";
	
	class emp{}
	
	//$image = $_POST['image'];
	$nis = $_POST['nis'];
	$agama = $_POST['agama'];
	$kelas = $_POST['kelas'];
	$jenkel = $_POST['jenkel'];
	$ttl = $_POST['ttl'];
	$nama = $_POST['nama'];
	$alamat = $_POST['alamat'];
	//$berat = $_POST['berat'];
	//$panjang = $_POST['panjang'];

	if (empty($nis)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty nis."; 
		die(json_encode($response));
    }	else if (empty($jenkel)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty jenis kelamin."; 
		die(json_encode($response));
	} else if (empty($agama)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty agama."; 
		die(json_encode($response));
	} else if (empty($ttl)){
	    $response = new emp();
		$response->success = 0;
		$response->message = "Please dont Tanggal Lahir."; 
		die(json_encode($response));
	} 	else if (empty($kelas)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty kelas."; 
		die(json_encode($response));
	} else if (empty($nama)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty nama."; 
		die(json_encode($response));
	} else if(empty($alamat)) {
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty alamat."; 
		die(json_encode($response));
	} else {
		$random = random_word(20);
		$tes= $random.".png";
		$path = "foto/".$random.".png";

		// sesuiakan ip address laptop/pc atau URL server
		$actualpath = "$path";
		$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM siswa WHERE nis='".$nis."'"));
		
		
	       if ($num_rows == 0){
		 	    $query = mysqli_query($con, "INSERT INTO siswa (agama,kelas,nama,alamat,nis,ttl,jenkel) VALUES ('$agama','$kelas','$nama','$alamat','$nis','$ttl','$jenkel')");
				if ($query){
					file_put_contents($path,base64_decode($image));
			
			        $response = new emp();
			        $response->success = 1;
			        $response->message = "Successfully Uploaded";
			        die(json_encode($response));
				}
			} else  {
				$query2 = mysqli_query($con, "UPDATE siswa SET kelas='$kelas',nama='$nama',agama='$agama',alamat='$alamat',ttl='$ttl',jenkel='$jenkel' WHERE nis='$nis'");

				if ($query2){
					file_put_contents($path,base64_decode($image));
					$response = new emp();	
                    $response->success = 1;					
					$response->message = "Successfully Uploaded";
		            die(json_encode($response));

				} else {
					$response = new emp();
					$response->success = 0;
	 			    $response->message = "nama  anda tidak anda";
	 			    die(json_encode($response));
				}
			}
	}	
	
	// fungsi random string pada gambar untuk menghindari nama file yang sama
	function random_word($id = 20){
		$pool = '1234567890abcdefghijkmnpqrstuvwxyz';
		
		$word = '';
		for ($i = 0; $i < $id; $i++){
			$word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
		}
		return $word; 
	}

	mysqli_close($con);
	
?>	