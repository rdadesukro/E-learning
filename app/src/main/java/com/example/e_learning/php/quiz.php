<?php
	include_once "koneksi.php";
	
	class emp{}
	
	//$image = $_POST['image'];
	$status = $_POST['status'];
	$nis = $_POST['nis'];
	$mapel = $_POST['mapel'];
	$nilai = $_POST['nilai'];
	$id_guru = $_POST['id_guru'];
	
	 if (empty($nis)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty jenis kelamin."; 
		die(json_encode($response));
		
		
	 }  else if (empty($id_guru)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty jenis kelamin."; 
		die(json_encode($response));
	
		
	 }  else if (empty($status)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty jenis kelamin."; 
		die(json_encode($response));
	
	} else if (empty($nilai)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty nilai."; 
		die(json_encode($response));
	} else if (empty($mapel)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty nilai."; 
		die(json_encode($response));	
		
		
		
	} else {
		$random = random_word(20);
		$tes= $random.".png";
		$path = "admin/images/".$random.".png";

		// sesuiakan ip address laptop/pc atau URL server
		$actualpath = "$path";
		$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM quiz WHERE nis='".$nis."'  and status='".$status."'"));
		
		
	       if ($num_rows == 0){
		 	    $query = mysqli_query($con, "INSERT INTO quiz (nis,nilai,mapel,status,id_guru) VALUES ('$nis','$nilai','$mapel','$status','$id_guru')");
				if ($query){
					//file_put_contents($path,base64_decode($image));
			
			        $response = new emp();
			        $response->success = 1;
			        $response->message = "Successfully Uploaded";
			        die(json_encode($response));
				}
			} else  {
				$query2 = mysqli_query($con, "UPDATE quiz SET nilai='$nilai',mapel='$mapel',status='$status' WHERE nis='$nis' and mapel='$mapel'");

				if ($query2){
					//file_put_contents($path,base64_decode($image));
					$response = new emp();	
                    $response->success = 1;					
					$response->message = "Successfully Uploaded";
		            die(json_encode($response));

				} else {
					$response = new emp();
					$response->success = 0;
	 			    $response->message = "nis  anda tid_quizak anda";
	 			    die(json_encode($response));
				}
			}
	}	
	
	// fungsi random string pada gambar untuk menghindari nilai file yang sama
	function random_word($id_quiz = 20){
		$pool = '1234567890abcdefghijkmnpqrstuvwxyz';
		
		$word = '';
		for ($i = 0; $i < $id_quiz; $i++){
			$word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
		}
		return $word; 
	}

	mysqli_close($con);
	
?>	