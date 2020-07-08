<?php

include "db.php";

$id_guru		= $_POST['id_guru'];
$nama			= $_POST['nama'];
$alamat			= $_POST['alamat'];
$ttl 		= $_POST['ttl'];
$id_mapel 		= $_POST['id_mapel'];
$agama			= $_POST['agama'];
$jk			= $_POST['jk'];


$nama_file				= $_FILES['imageUrl']['name'];
$temp 					= $_FILES['imageUrl']['tmp_name'];


if (!empty($nama_file)){
	if(!copy($temp,"../foto/".$nama_file)){
		echo "<script>alert('gambar harus di upload..'); window.location = 'index.php?page=guru'</script>";
		exit;
	}
}
   $ttl = date_format(date_create($ttl), 'd-M-Y');

 //echo $tanggal."  ".$tgl2;

$q = mysqli_query($con, "update guru set
				nama		= '$nama',
			
				id_mapel		= '$id_mapel',
			
				ttl		= '$ttl',
			   alamat		= '$alamat',
				agama			= '$agama',
				jk			= '$jk',
				foto	= '$nama_file'
				where id_guru = '$id_guru'") or die (mysqli_error());

if($q){
	echo "<script>alert('Berhasil diedit');
			window.location='index.php?hal=guru'</script>";
			
}				
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
        
            echo 'FCM Reg id_guru : '. $token . '<br/>nama : ' . $nama;
        }else if($type == "topics") {
           $alamat_buka = date_format(date_create($alamat_buka), 'd-m-Y');
            $topics = isset($_POST['topics']) ? $_POST['topics'] : '';
            $nama = isset($_POST['nama']) ? $_POST['nama'] : '';
			$alamat = isset($_POST['alamat']) ? $_POST['alamat'] : '';
			$ttl = isset($_POST['ttl']) ? $_POST['ttl'] : '';
            $alamat = isset($_POST['alamat']) ? $_POST['alamat'] : '';
            $res = array();
            $res['body'] = $nama." ttl: ".$alamat_buka." alamat: ".$alamat." Alamat : ".$alamat;
            
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