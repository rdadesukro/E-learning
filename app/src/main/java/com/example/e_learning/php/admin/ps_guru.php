

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





        $num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM guru WHERE id_guru='".$id_guru."'"));
          if ($num_rows == 0){
                $query = mysqli_query($con,"INSERT INTO guru VALUES     ('$id_guru',
                                                '$nama',
                                                '$ttl',
                                                '$jk',
                                                '$agama',
                                                '$alamat',
                                                
                                                
                                                
                                                '$id_mapel',
                                                '$nama_file')")or die (mysqli_error());

                
                    echo "<script>alert('Berhasil disimpan');
                    window.location='index.php?hal=guru'</script>";  
        

                
            } else {
                echo "<script>alert('Akun Sudah Ada');
                    window.location='index.php?hal=guru'</script>";  
            }

		
	
?> 