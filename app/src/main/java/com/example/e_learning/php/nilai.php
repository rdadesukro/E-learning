
<?php 
 
	/*
	* Created by Belal Khan
	* website: www.simplifiedcoding.net 
	* Retrieve Data From MySQL Database in Android
	*/
	
	//database constants
	define('DB_HOST', 'localhost');
	define('DB_USER', 'root');
	define('DB_PASS', '');
	define('DB_NAME', 'e_info');
	
	//connecting to database and getting the connection object
	$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	//Checking if any error occured while connecting
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		die();
	}
	
	$nis=$_GET['nis'];

	
	//creating a query
	$stmt = $conn->prepare("SELECT nilai.id_mapel,nilai.nilai,mapel.nama_mapel,nilai.smester FROM nilai,mapel WHERE   nilai.nis_siswa='$nis'AND (nilai.id_mapel=mapel.id_mapel)");
	
	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($id, $nilai, $mapel, $sms);
	
	$products = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['id_mapel'] = $id; 
		$temp['nilai'] = $nilai; 
		$temp['nama_mapel'] = $mapel; 
		$temp['smester'] = $sms; 
	
		array_push($products, $temp);
	}
	
	//displaying the result in json format 
	echo json_encode($products);
	
	?>
