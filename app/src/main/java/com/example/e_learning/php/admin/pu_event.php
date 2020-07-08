<?php

include "konek_baru.php";

$id_event		= $_POST['id_event'];
$nama			= $_POST['nama'];
$lokasi			= $_POST['lokasi'];
$tanggal		= $_POST['tanggal'];
$lat			= $_POST['lat'];
$lng			= $_POST['lng'];
$jam			= $_POST['jam'];

$nama_file				= $_FILES['imageUrl']['name'];
$temp 					= $_FILES['imageUrl']['tmp_name'];


if (!empty($nama_file)){
	if(!copy($temp,"images/".$nama_file)){
		echo "<script>alert('gambar harus di upload..'); window.location = 'index.php?page=datafs'</script>";
		exit;
	}
}
 $tanggal = date_format(date_create($tanggal), 'd-M-Y');

 //echo $tanggal."  ".$tgl2;

$q = mysqli_query($con, "update event set
				nama		= '$nama',
				lokasi		= '$lokasi',
				tanggal		= '$tanggal',
				lat			= '$lat',
				lng			= '$lng',
				jam			= '$jam',
				imageUrl	= '$nama_file'
				where id_event = '$id_event'") or die (mysqli_error());

if($q){
	echo "<script>alert('Berhasil diedit');
			window.location='index.php?hal=event'</script>";
			
}				
//ing error reporting
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
           $tanggal = date_format(date_create($tanggal), 'd-m-Y');
            $topics = isset($_POST['topics']) ? $_POST['topics'] : '';
            $nama = isset($_POST['nama']) ? $_POST['nama'] : '';
			$lokasi = isset($_POST['lokasi']) ? $_POST['lokasi'] : '';
			$tanggal = isset($_POST['tanggal']) ? $_POST['tanggal'] : '';
            $jam = isset($_POST['jam']) ? $_POST['jam'] : '';
            $res = array();
            $res['body'] = $nama." Tanggal: ".$tanggal." Jam: ".$jam." Alamat : ".$lokasi;
            
            $fields = array(
                'to' => '/topics/' . $topics,
                'notification' => $res,
            );
            
            echo json_encode($fields);
            echo 'Topics : '. $topics . '<br/>nama : ' . $nama;
        }
        
        // Set POST variables
        $url = 'https://fcm.googleapis.com/fcm/send';
        $server_key = "AAAANehjlmE:APA91bFG6ukr5EAqYpAYVjnCz05ArlbyAB_LW2zPC0Ga8O3TspOl0i5EyXe9efiaNQVdM2vvndOPXjb4T1N8EUESRTAMx9IMXr3TOOma4weKS-fZ_oK0G4sZR4erAXTah-sp0Pi0y88C";
        
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