

<?php
include "konek_baru.php";

$nama 			= $_POST['nama'];
$alamat 		= $_POST['alamat'];
$jam_tutup_buka 		= $_POST['jam_tutup_buka'];
$jam_tutup_tutup 		= $_POST['jam_tutup_tutup'];
$lat 			= $_POST['lat'];
$lng 			= $_POST['lng'];


$nama_file				= $_FILES['imageUrl']['name'];
$temp 					= $_FILES['imageUrl']['tmp_name'];


if (!empty($nama_file)){
	if(!copy($temp,"images/".$nama_file)){
		echo "<script>alert('gambar harus di upload..'); window.location = 'index.php?hal=dokter'</script>";
		exit;
	}
}

	// tipe data gambar ygdi perbolehkan
	$file_type = array('jpg','jpeg');
	// mencari extensi gambar
	$explode = explode('.',$nama_file);
	$extensi = $explode[count($explode)-1];

 $jam_tutup_buka = date_format(date_create($jam_tutup_buka), 'd-M-Y');

// echo $jam_tutup_buka."  ".$tgl2;

mysqli_query($con,"INSERT INTO dokter VALUES 	('Null',
												'$nama',
												'$alamat',
												'$jam_tutup_buka',
												'$jam_tutup_tutup',
												'$lat',
												'$lng',
												'$nama_file')") or die (mysqli_error());

echo "<script>alert('Berhasil di simpan');
		window.location='index.php?hal=dokter'</script>";  
		
		
        // Enabling error reporting
        error_reporting(-1);
        ini_set('display_errors', 'On');
 
        $type = isset($_POST['type']) ? $_POST['type'] : '';
        
        $fields = NULL;
        
        if($type == "single") {
            $token = isset($_POST['token']) ? $_POST['token'] : '';
            $nama = isset($_POST['nama']) ? $_POST['nama'] : '';
            
            $res = array();
            $res['body'] = $nama;
            
            $fields = array(
                'to' => $token,
                'notification' => $res,
            );
        
            echo 'FCM Reg Id : '. $token . '<br/>nama : ' . $nama;
        }else if($type == "topics") {
           $jam_tutup_buka = date_format(date_create($jam_tutup_buka), 'd-m-Y');
            $topics = isset($_POST['topics']) ? $_POST['topics'] : '';
            $nama = isset($_POST['nama']) ? $_POST['nama'] : '';
			$alamat = isset($_POST['alamat']) ? $_POST['alamat'] : '';
			$jam_buka = isset($_POST['jam_buka']) ? $_POST['jam_buka'] : '';
            $jam_tutup = isset($_POST['jam_tutup']) ? $_POST['jam_tutup'] : '';
            $res = array();
            $res['body'] = $nama." jam_buka: ".$jam_tutup_buka." jam_tutup: ".$jam_tutup." Alamat : ".$alamat;
            
            $fields = array(
                'to' => '/topics/' . $topics,
                'notification' => $res,
            );
            
            echo json_encode($fields);
            echo 'Topics : '. $topics . '<br/>nama : ' . $nama;
        }
        
        // Set POST variables
        $url = 'https://fcm.googleapis.com/fcm/send';
        $server_key = "AAAAANSOVrgU:APA91bFo6yEkrOYiyvsllB73q5OsG-sf4xCl1vh-XwJci48bOFHImMpFYQ7wrl_faA67FxCuVeMTQnNBHfqxYWloSNu1wpVWxyWrT6AQ4gbU6MKzNEYjjjxj6B0_fbpZFPQR-z0NSQPo";
        $headers = array(
            'Authorization: key=' . $server_key,
            'Content-Type: application/json'
        );
        // Open connection
        $ch = curl_init();
 
        // Set the url, number of POST vars, POST data
        curl_setopt($ch, CURLOPT_URL, $url);
 
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
 
        // Disabling SSL Certificate support temporarly
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
 
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
 
        // Execute post
        $result = curl_exec($ch);
        if ($result === FALSE) {
            echo 'Curl failed: ' . curl_error($ch);
        }
 
        // Close connection
        curl_close($ch);
               
 

?> 