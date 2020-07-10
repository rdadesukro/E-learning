<?php
	include_once "koneksi.php";
	
	class emp{}
	
	$image = $_POST['image'];
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
  
	} else {
		$random = random_word(20);
		$tes= $random.".png";
		$path = "foto/".$random.".png";

		// sesuiakan ip address laptop/pc atau URL server
		$actualpath = "$path";
		$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM guru WHERE id_guru='".$nis."'"));
		
		
	       if ($num_rows == 0){
		 	//     $query = mysqli_query($con, "INSERT INTO guru (foto,agama,nama,alamat,ttl,jk) VALUES ('$tes','$agama','$kelas','$nama','$alamat','$nis','$ttl','$jenkel')");
				// if ($query){
				// 	file_put_contents($path,base64_decode($image));
			
			 //        $response = new emp();
			 //        $response->success = 1;
			 //        $response->message = "Successfully Uploaded";
			 //        die(json_encode($response));
				// }
			} else  {
				$query2 = mysqli_query($con, "UPDATE guru SET foto='$tes' WHERE id_guru='$nis'");

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