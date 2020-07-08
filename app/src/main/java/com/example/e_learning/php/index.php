
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
	define('DB_NAME', 'e-leraning');
	
	//connecting to database and getting the connection object
	$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	//Checking if any error occured while connecting
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		die();
	}
	
	$id_mapel=$_GET['id_mapel'];
  
	
	//creating a query
	$stmt = $conn->prepare("SELECT  materi.id,materi.id_mapel,mapel.nama_mapel,materi.bab,materi.pdf_url,materi.pdf_icon 
FROM materi,mapel WHERE materi.id_mapel=mapel.id_mapel AND materi.id_mapel='$id_mapel'");
	
	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($id,$id_mapel,$nama,$bab,$pdf_url,$pdf_icon);
	
	$products = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['id'] = $id; 
		$temp['id_mapel'] = $id_mapel; 
		$temp['nama'] = $nama; 
		$temp['bab'] = $bab; 
		//$temp['des'] = $des; 
		$temp['pdf_url'] = $pdf_url; 
		$temp['pdf_icon'] = $pdf_icon; 
	
		array_push($products, $temp);
	}
	
	//displaying the result in json format 
	echo json_encode($products);
	
	?>
