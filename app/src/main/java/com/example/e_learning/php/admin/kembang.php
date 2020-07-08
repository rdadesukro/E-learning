

<h2><u>Form Tambah Dan Edit Data Kembnag Anak</u></h2> 

 
 <?php
error_reporting(0);
include "konek_baru.php";
$id = $_GET['id'];
if($id != ""){
	$aksi = "pu_kembang.php";
	$q = mysqli_query($con, "SELECT * FROM riwayat r,profil p WHERE r.`id_profil`=p.`id_profil` and id = '$id'");
	$result = mysqli_fetch_assoc($q);
	$imageUrl = "images/".$result['imageUrl'];
}else{
	$aksi = "ps_kembang.php";
}
?>
<form role = "form" action="<?php echo $aksi ?>" method="POST" enctype="multipart/form-data">
   <div class = "control-group">
		<input type = "hidden" class = "form-control" id = "id" name="id" value="<?php echo $result['id']; ?>">
					<div class = "control">
			<label for = "name">Nama Anak <?php $result['id_profil'] ?></label>
			
<select name="id_profil" id="id_profil" class="form-control">

 <?php     
        $h = mysqli_query($con, "select * from profil order by id_profil desc") or die(mysql_error());    
        while ($data = mysqli_fetch_array($h)) {
			if($data['id_profil'] == $result['id_profil']) {
				echo '<option value="' . $data['id_profil'] . '" selected>' . $data['nama_anak'].'</option>';    
			} else {
				echo '<option value="' . $data['id_profil'] . '">' . $data['nama_anak'].'</option>';    
			}
        
          }    
        echo '</select>';    
        ?>
 	</div>
		<div class = "controls">
			<label for = "name">Bulan Ke</label>
			<input type = "text" class = "form-control"  id = "bulan_ke" name="bulan_ke" value="<?php echo $result['bulan_ke']; ?>">
		</div>
		<div class = "control">
			<div class = "form-group">
			<label for = "name">Tanggal Imunisasi</label>
			<input type = "date" class = "form-control" id = "tgl_imunisasi" name="tgl_imunisasi" value="<?php echo $result['tgl_imunisasi']; ?>">
		</div>

			
		</div>
		<div class = "form-group">
			<label for = "name">Berat</label>
			<input type = "text" class = "form-control"  id = "berat" name="berat" value="<?php echo $result['berat']; ?>">
		</div>
		
		<div class = "form-group">
			<label for = "name">Panjang</label>
			<input type = "text" class = "form-control"  id = "panjang" name="panjang" value="<?php echo $result['panjang']; ?>">
		</div>
		
		<?php
			mysqli_query($con, "select nama from vaksin order by");
		?>
		
		<div class = "form-group">
			<label for = "name">Vaksin <?php echo $_GET['id'] ?></label>
			<select name="nama" id="nama" class="form-control">

 <?php     
        $h = mysqli_query($con, "select * from vaksin order by id desc") or die(mysql_error());    
        while ($data = mysqli_fetch_array($h)) {
			if($data['nama'] == $result['vaksin']) {
				echo '<option value="' . $data['nama'] . '" selected>' . $data['nama'].'</option>';    
			} else {
				echo '<option value="' . $data['nama'] . '">' . $data['nama'].'</option>';    
			}
        
          }    
        echo '</select>';    
        ?>
		</div>
	
	<div class = "form-group">
			
			
			 <?php
  include "konek_baru.php";

$nama_anak = $_POST['nama_anak'];
 $sql = mysqli_query($con,"select id_profil from profil WHERE nama_anak = '$nama_anak'");
 $result = mysqli_fetch_array($sql)
 ?>
  <!--
 <input type = "hidden" class = "form-control"  id = "id_profil" name="id_profil" value="<?php echo $result['id_profil']; ?>">
-->
 
			
		</div>	
    </div>
   <button type = "submit" class = "btn btn-primary">Submit</button>
</form>

<h2><u>Data Kembnag Anak</u></h2><br>   
<div class="table-responsive">
 <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<thead>
					<tr>
                    	<th>No</th>
						<th>Nama Anak</th>
						<th>Tanggal Imunisasi</th>
						<th>Bulan Ke</th>
						<th>Berat</th>
						<th>Panjang</th>
						<th>Vaksin</th>
                        <th>Aksi</th>
					</tr>
				</thead>
				
				<tbody>
				<?php
				$view=mysqli_query($con,"SELECT *FROM riwayat r,profil p WHERE r.`id_profil`=p.`id_profil` order by r.id desc");
				$no=1;
				while($row=mysqli_fetch_array($view)){
				
				$ubah	= "index.php?hal=kembang&id=".$row['id'];
				$hapus	= "hp_kembang.php?id=".$row['id'];
				
				?>
					<tr>
                    
                    <td><center><?php echo $no++ ?></center></td>
						<td><?php echo $row['nama_anak'];?></td>
						<td><?php echo $row['tgl_imunisasi'];?></td>
						<td><?php echo $row['bulan_ke'];?></td>
						<td><?php echo $row['berat'];?></td>
						<td><?php echo $row['panjang'];?></td>
						<td><?php echo $row['vaksin'];?></td>
						
					

                       <td>
						<a href="<?php echo $ubah ?>" class="btn btn-success">
							<span></span> Edit 
						</a>
						<a href="<?php echo $hapus ?>" class="btn btn-danger" onclick="return confirm('Apakah anda yakin akan menghapus data ini ?')">
							<span></span> Hapus 
						</a>
					</tr>
				<?php
				}
				?>
				</tbody>
 </table>
</div>
<br>
