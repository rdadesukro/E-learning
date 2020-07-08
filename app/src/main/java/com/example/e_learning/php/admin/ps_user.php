

<?php
include "db.php";
$nis 			= $_POST['nis'];
$username 			= $_POST['username'];
$password 		= md5($_POST["password"]);
//$ttl 		= $_POST['ttl'];
$level 			= $_POST['level'];
$kunci 			= "null";
$q = mysqli_query($con, "update user set
                username        = '$username',
            
                password        = '$password'
                where nis = '$nis'") or die (mysqli_error());

if($q){
    echo "<script>alert('Berhasil diedit');
            window.location='index.php?hal=akun'</script>";
            
}               


		
        // Enabling error reporting
        error_reporting(-1);
        ini_set('display_errors', 'On');
 
        $type = isset($_POST['type']) ? $_POST['type'] : '';
        
        $fields = NULL;
        
        if($type == "single") {
            $token = isset($_POST['token']) ? $_POST['token'] : '';
            $username = isset($_POST['username']) ? $_POST['username'] : '';
            
            $res = array();
            $res['body'] = $username;
            
            $fields = array(
                'to' => $token,
                'notification' => $res,
            );
        
            echo 'FCM Reg Id : '. $token . '<br/>username : ' . $username;
        }else if($type == "topics") {
           $ttl = date_format(date_create($ttl), 'd-m-Y');
            $topics = isset($_POST['topics']) ? $_POST['topics'] : '';
            $username = isset($_POST['username']) ? $_POST['username'] : '';
			$password = isset($_POST['password']) ? $_POST['password'] : '';
			$ttl = isset($_POST['ttl']) ? $_POST['ttl'] : '';
            $id_mapel = isset($_POST['id_mapel']) ? $_POST['id_mapel'] : '';
            $res = array();
            $res['body'] = $username." ttl: ".$ttl." id_mapel: ".$id_mapel." password : ".$password;
            
            $fields = array(
                'to' => '/topics/' . $topics,
                'notification' => $res,
            );
            
            echo json_encode($fields);
            echo 'Topics : '. $topics . '<br/>username : ' . $username;
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