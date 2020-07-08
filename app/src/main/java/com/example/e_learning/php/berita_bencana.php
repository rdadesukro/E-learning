<?php
	include_once "koneksi.php";
	
	class emp{}
	
	//$idlaporan = $_POST['idlaporan'];
	
	$image = $_POST['image'];
	$id_be = $_POST['id_be'];
	$id_admin = $_POST['id_admin'];
	$deskripsi = $_POST['deskripsi'];
	$bencana = $_POST['bencana'];
	$judul = $_POST['judul'];
	$waktu = $_POST['waktu'];
	
//	$nama = $_POST['nama'];
	//$lat = $_POST['lat'];
	//$lng = $_POST['lng'];
    if (empty($id_admin)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "id_admin Data tidak lengkap"; 
		die(json_encode($response));
		
	}  else if (empty($bencana)){
		$response = new emp();
		$response->success = 0;
		$response->message = "bencana Data tidak lengkap"; 
		die(json_encode($response));
		
	}	else if (empty($waktu)){
		$response = new emp();
		$response->success = 0;
		$response->message = " waktu Data tidak lengkap"; 
		die(json_encode($response));
		
	}   else if (empty($deskripsi)){
		$response = new emp();
		$response->success = 0;
		$response->message = "Deskripsi Data tidak lengkap"; 
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
		
		$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM berita WHERE id_be='".$id_be."'"));
		
		
	       if ($num_rows == 0){
			   $query = mysqli_query($con, "INSERT INTO berita (foto,id_admin,bencana,judul,waktu,deskripsi) VALUES ('$actualpath','$id_admin','$bencana','$judul','$waktu','$deskripsi')");
		
		 	   if ($query){
					file_put_contents($path,base64_decode($image));
			
			        $response = new emp();
			        $response->success = 1;
			        $response->message = "Successfully Uploaded";
			        die(json_encode($response));
				}
			} else  {
				$query2 = mysqli_query($con, "UPDATE berita SET foto='$actualpath',id_admin='$id_admin',judul='$judul',waktu='$waktu',deskripsi='$deskripsi',bencana='$bencana' WHERE id_be='$id_be'");

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