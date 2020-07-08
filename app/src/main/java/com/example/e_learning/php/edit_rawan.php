<?php
	include_once "koneksi.php";
	
	class emp{}
	
	//$idlaporan = $_POST['idlaporan'];
	
	$image = $_POST['image'];
	$id = $_POST['id'];
	$lat = $_POST['lat'];
	$lng = $_POST['lng'];
	$alamat = $_POST['alamat'];

	$judul = $_POST['judul'];

	
//	$nama = $_POST['nama'];
	//$lat = $_POST['lat'];
	//$lng = $_POST['lng'];
    if (empty($lat)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "lat Data tidak lengkap"; 
		die(json_encode($response));
		

		
	}   else if (empty($lng)){
		$response = new emp();
		$response->success = 0;
		$response->message = "lng Data tidak lengkap"; 
		die(json_encode($response));
		
	}	else if (empty($alamat)){
		$response = new emp();
		$response->success = 0;
		$response->message = "lng Data tidak lengkap"; 
		die(json_encode($response));
		
	}	else if (empty($judul)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Judul Data tidak lengkap"; 
		die(json_encode($response));
		
		
		
		
	}  else {
		$random = random_word(20);
		
		$path = "foto/".$random.".png";
		
		// sesuiakan ip address laptop/pc atau URL server
		$actualpath = "$path";
		
		$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM lokasi_rawan WHERE id='".$id."'"));
		
		
	       if ($num_rows == 0){
			   $query = mysqli_query($con, "INSERT INTO lokasi_rawan (foto,lat,judul,lng,alamat) VALUES ('$actualpath','$lat','$judul','$lng','$alamat')");
		
		 	   if ($query){
					file_put_contents($path,base64_decode($image));
			
			        $response = new emp();
			        $response->success = 1;
			        $response->message = "Successfully Uploaded";
			        die(json_encode($response));
				}
			} else  {
				$query2 = mysqli_query($con, "UPDATE lokasi_rawan SET foto='$actualpath',lat='$lat',judul='$judul',lng='$lng',alamat='$alamat' WHERE id='$id'");

				if ($query2){
					file_put_contents($path,base64_decode($image));
					$response = new emp();	
                    $response->success = 1;					
					$response->message = "Successfully Uploaded";
		            die(json_encode($response));

				} else {
					$response = new emp();
					$response->success = 0;
	 			    $response->message = "email  anda tidak anda";
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