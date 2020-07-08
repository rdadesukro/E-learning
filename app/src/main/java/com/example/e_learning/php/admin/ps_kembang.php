

<?php
include "konek_baru.php";

$id_profil 			= $_POST['id_profil'];
$tgl_imunisasi 		= $_POST['tgl_imunisasi'];
$bulan_ke 		        = $_POST['bulan_ke'];
$ket 		        = $_POST['ket'];
$berat 		        = $_POST['berat'];
$nama 		        = $_POST['nama'];
$panjang 		        = $_POST['panjang'];
$id_profil_file				= $_FILES['imageUrl']['name'];
$temp 					= $_FILES['imageUrl']['tmp_name'];


if (!empty($id_profil_file)){
	if(!copy($temp,"images/".$id_profil_file)){
		echo "<script>alert('gambar harus di upload..'); window.location = 'index.php?hal=kembang'</script>";
		exit;
	}
}

	// tipe data gambar ygdi perbolehkan
	$file_type = array('jpg','jpeg');
	// mencari extensi gambar
	$explode = explode('.',$id_profil_file);
	$extensi = $explode[count($explode)-1];

 ////$tgl2 = date_format(date_create($tgl_imunisasi), 'd-m-Y');

 //echo $tgl_imunisasi."  ".$tgl2;

mysqli_query($con,"INSERT INTO riwayat VALUES 	('Null',
												'$id_profil',
												'$bulan_ke',
												'$berat',
												'$panjang',
												'$nama',
												'$tgl_imunisasi')") or die (mysqli_error());

echo "<script>alert('Berhasil di Simpan');
		window.location='index.php?hal=kembang'</script>";  

?> 