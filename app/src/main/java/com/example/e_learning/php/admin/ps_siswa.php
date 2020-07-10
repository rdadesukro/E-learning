

<?php
include "db.php";
$nis 			= $_POST['nis'];
$nama 			= $_POST['nama'];
$alamat 		= $_POST['alamat'];
$jenkel 		= $_POST['jenkel'];
$agama 			= $_POST['agama'];
$kelas 			= $_POST['kelas'];
$ttl          = $_POST['ttl'];
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



		
		
     
        $num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM siswa WHERE nis='".$nis."'"));
          if ($num_rows == 0){
                $query = mysqli_query($con,"INSERT INTO siswa VALUES    ('$nis',
                                                '$nama',
                                                 '$kelas',
                                                '$ttl',
                                                '$jenkel',
                                                
                                                '$alamat',
                                                '$agama',
                                                '$nama_file')")or die (mysqli_error());

                
                    echo "<script>alert('Berhasil disimpan');
                    window.location='index.php?hal=siswa'</script>";  
        

                
            } else {
                echo "<script>alert('Akun Sudah Ada');
                    window.location='index.php?hal=siswa'</script>";  
            }

 

?> 