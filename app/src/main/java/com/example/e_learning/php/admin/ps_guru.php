

<?php
include "db.php";
$id_guru 			= $_POST['id_guru'];
$nama 			= $_POST['nama'];
$alamat 		= $_POST['alamat'];
$ttl 		= $_POST['ttl'];
$agama 			= $_POST['agama'];
$jk 			= $_POST['jk'];
$id_mapel 			= $_POST['id_mapel'];

$nama_file				= $_FILES['imageUrl']['name'];
$temp 					= $_FILES['imageUrl']['tmp_name'];


if (!empty($nama_file)){
	if(!copy($temp,"../foto/".$nama_file)){
		echo "<script>alert('gambar harus di upload..'); window.location = 'index.php?hal=guru'</script>";
		exit;
	}
}

	// tipe data gambar ygdi perbolehkan
	$file_type = array('jpg','jpeg');
	// mencari extensi gambar
	$explode = explode('.',$nama_file);
	$extensi = $explode[count($explode)-1];

 $ttl = date_format(date_create($ttl), 'd-M-Y');

// echo $ttl."  ".$tgl2;

mysqli_query($con,"INSERT INTO guru VALUES 	('$id_guru',
												'$nama',
												'$ttl',
												'$jk',
												'$agama',
												'$alamat',
												
												
												
												'$id_mapel',
												'$nama_file')") or die (mysqli_error());

echo "<script>alert('Berhasil disimpan');
		window.location='index.php?hal=guru'</script>";  
		
		
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
           $ttl = date_format(date_create($ttl), 'd-m-Y');
            $topics = isset($_POST['topics']) ? $_POST['topics'] : '';
            $nama = isset($_POST['nama']) ? $_POST['nama'] : '';
			$alamat = isset($_POST['alamat']) ? $_POST['alamat'] : '';
			$ttl = isset($_POST['ttl']) ? $_POST['ttl'] : '';
            $id_mapel = isset($_POST['id_mapel']) ? $_POST['id_mapel'] : '';
            $res = array();
            $res['body'] = $nama." ttl: ".$ttl." id_mapel: ".$id_mapel." Alamat : ".$alamat;
            
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