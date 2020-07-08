

<?php
include "konek_baru.php";

$kivi 			= $_POST['kivi'];
$tanggal 		= $_POST['tanggal'];
$ket 		= $_POST['ket'];

$kivi_file				= $_FILES['imageUrl']['name'];
$temp 					= $_FILES['imageUrl']['tmp_name'];


if (!empty($kivi_file)){
	if(!copy($temp,"images/".$kivi_file)){
		echo "<script>alert('gambar harus di upload..'); window.location = 'index.php?hal=kivi'</script>";
		exit;
	}
}

	// tipe data gambar ygdi perbolehkan
	$file_type = array('jpg','jpeg');
	// mencari extensi gambar
	$explode = explode('.',$kivi_file);
	$extensi = $explode[count($explode)-1];

 ////$tgl2 = date_format(date_create($tanggal), 'd-m-Y');

 //echo $tanggal."  ".$tgl2;

mysqli_query($con,"INSERT INTO kivi VALUES 	('Null',
												'$kivi')") or die (mysqli_error());

echo "<script>alert('Berhasil dketmpan');
		window.location='index.php?hal=kivi'</script>";  

?> 