<?php
	include_once "koneksi.php";
	
	class emp{}
	
	//$idlaporan = $_POST['idlaporan'];
	
	$image = $_POST['image'];
	$id_user = $_POST['id_user'];
	$deskripsi = $_POST['deskripsi'];
	$jenis = $_POST['jenis'];
	$alamat = $_POST['alamat'];
	$waktu = $_POST['waktu'];
	$lat = $_POST['lat'];
	$lng = $_POST['lng'];
//	$nama = $_POST['nama'];
	//$lat = $_POST['lat'];
	//$lng = $_POST['lng'];
    if (empty($id_user)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Data tidak lengkap"; 
		die(json_encode($response));
	}  else if (empty($jenis)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Data tidak lengkap"; 
		die(json_encode($response));
	}	else if (empty($lat)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Data tidak lengkap"; 
		die(json_encode($response));
	}   else if (empty($jenis)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Data tidak lengkap"; 
		die(json_encode($response));
	} else if (empty($lng)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Data tidak lengkap"; 
		die(json_encode($response));
	} else if (empty($waktu)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Data tidak lengkap"; 
		die(json_encode($response));
	} else {
		$random = random_word(20);
		
		$path = "foto/".$random.".png";
		
		// sesuiakan ip address laptop/pc atau URL server
		$actualpath = "$path";
		
		$query = mysqli_query($con, "INSERT INTO laporan (foto,id_user,jenis,alamat,waktu,lat,lng,deskripsi) VALUES ('$actualpath','$id_user','$jenis','$alamat','$waktu','$lat','$lng','$deskripsi')");
		
		if ($query){
			file_put_contents($path,base64_decode($image));
			
			        $response = new emp();
			        $response->success = 1;
			        $response->message = "Successfully Uploaded";
			        die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error Upload idlaporan";
			die(json_encode($response)); 
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