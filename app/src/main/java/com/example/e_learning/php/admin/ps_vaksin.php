

<?php
include "konek_baru.php";

$nama 			= $_POST['nama'];
$tanggal 		= $_POST['tanggal'];
$ket 		= $_POST['ket'];

$nama_file				= $_FILES['imageUrl']['name'];
$temp 					= $_FILES['imageUrl']['tmp_name'];


if (!empty($nama_file)){
	if(!copy($temp,"images/".$nama_file)){
		echo "<script>alert('gambar harus di upload..'); window.location = 'index.php?hal=vaksin'</script>";
		exit;
	}
}

	// tipe data gambar ygdi perbolehkan
	$file_type = array('jpg','jpeg');
	// mencari extensi gambar
	$explode = explode('.',$nama_file);
	$extensi = $explode[count($explode)-1];

 ////$tgl2 = date_format(date_create($tanggal), 'd-m-Y');

 //echo $tanggal."  ".$tgl2;

mysqli_query($con,"INSERT INTO vaksin VALUES 	('Null',
												'$nama',
												'$ket')") or die (mysqli_error());

echo "<script>alert('Berhasil dketmpan');
		window.location='index.php?hal=vaksin'</script>";  

?> 