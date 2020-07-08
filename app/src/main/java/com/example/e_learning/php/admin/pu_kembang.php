

<?php
include "konek_baru.php";
$id			= $_POST['id'];
$id_profil 			= $_POST['id_profil'];
$tgl_imunisasi 		= $_POST['tgl_imunisasi'];
$bulan_ke 		        = $_POST['bulan_ke'];
$ket 		        = $_POST['ket'];
$berat 		        = $_POST['berat'];
$nama 		        = $_POST['nama'];
$panjang 		        = $_POST['panjang'];



if (!empty($id_profil_file)){
	if(!copy($temp,"images/".$id_profil_file)){
		echo "<script>alert('gambar harus di upload..'); window.location = 'index.php?hal=kembang'</script>";
		exit;
	}
}

	// tipe data gambar ygdi perbolehkan
	

 ////$tgl2 = date_format(date_create($tgl_imunisasi), 'd-m-Y');

 //echo $tgl_imunisasi."  ".$tgl2;

$q = mysqli_query($con, "update riwayat set
				id_profil		= '$id_profil',
				bulan_ke		    = '$bulan_ke',
				berat		= '$berat',
				panjang	= '$panjang',
				vaksin	= '$nama',
				tgl_imunisasi	= '$tgl_imunisasi'
				where id = '$id'") or die (mysqli_error());

echo "<script>alert('Berhasil di Simpan');
		window.location='index.php?hal=kembang'</script>";  

?> 