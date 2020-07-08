<?php

if($_SERVER['REQUEST_METHOD']=='POST'){


                $nis = $_POST['nis'];
$response = array();
	define('DB_USERnis', 'root');
define('DB_PASSWORD', '');
define('DB_HOST', 'localhost');
define('DB_nis', 'e_info');

		$conn = new mysqli(DB_HOST, DB_USERnis, DB_PASSWORD, DB_nis);

// check for post data

$sql = "SELECT * FROM siswa WHERE nis = '$nis'";
$result = $conn->query($sql) or die (mysqli_connect_error());



    if (!empty($result)) {
        // check for empty result
        if (mysqli_num_rows($result) > 0) {

            $result = mysqli_fetch_array($result);

            $data = array();
            $data["nis"] = $result["nis"];
            $data["nis"] = $result["nis"];
            $data["foto"] = $result["foto"];


            // success
            $response["success"] = 1;
			$response["image"] = array();

            array_push($response["image"], $data);


            echo json_encode($response);
        } else {

            $response["success"] = 0;
            $response["message"] = "No data found";

            echo json_encode($response);
        }
    } else {

        $response["success"] = 0;
        $response["message"] = "No data found";

        // echo no users JSON
        echo json_encode($response);
    }
 }else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>
