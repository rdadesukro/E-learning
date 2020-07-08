<?php
	include_once "koneksi.php";
	
	class emp{}
	
	$image = $_POST['image'];
	$id = $_POST['id'];
	$email = $_POST['email'];
	$nama = $_POST['nama'];
	//$nama_ibu = $_POST['nama_ibu'];
	$no_hp = $_POST['no_hp'];
	//email = $_POST['email'];
	$alamat = $_POST['alamat'];
	//$berat = $_POST['berat'];
	//$panjang = $_POST['panjang'];

	if (empty($id)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty id_user."; 
		die(json_encode($response));
    }else if (empty($email)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty jenis kelamin."; 
		die(json_encode($response));
	} else if (empty($nama)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty nama."; 
		die(json_encode($response));
	} else if (empty($no_hp)){
	    $response = new emp();
		$response->success = 0;
		$response->message = "Please dont Tanggal Lahir."; 
		die(json_encode($response));
	} else if(empty($alamat)) {
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty alamat."; 
		die(json_encode($response));
	} else {
		$random = random_word(20);
		$tes= $random.".png";
		$path = "admin/images/".$random.".png";

		// sesuiakan ip address laptop/pc atau URL server
		$actualpath = "$path";
		$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM admin WHERE id='".$id."'"));
		
		
	       if ($num_rows == 0){
		 	    $query = mysqli_query($con, "INSERT INTO admin (nama,alamat,no_hp,email) VALUES ('$nama','$alamat','$no_hp','$email')");
				if ($query){
					//file_put_contents($path,base64_decode($image));
			
			        $response = new emp();
			        $response->success = 1;
			        $response->message = "Successfully Uploaded";
			        die(json_encode($response));
				}
			} else  {
				$query2 = mysqli_query($con, "UPDATE admin SET nama='$nama',alamat='$alamat',no_hp='$no_hp',email='$email' WHERE id='$id'");

				if ($query2){
					//file_put_contents($path,base64_decode($image));
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