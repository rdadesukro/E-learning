<?php
error_reporting(0);
include "db.php";
$nis = $_GET['nis'];
if($nis != ""){
	$aksi = "pu_siswa.php";
	$q = mysqli_query($con, "select * from siswa where nis = '$nis'");
	$result = mysqli_fetch_assoc($q);
	$imageUrl = "../foto/".$result['foto'];
}else{
	$aksi = "ps_siswa.php";
}
?>

<h2><u>Form Tambah Dan Edit Data siswa</u></h2> 
<form role = "form" action="<?php echo $aksi ?>" method="POST" enctype="multipart/form-data">
   <div class = "control-group">
	
		<div class = "controls">
			<label for = "name">NIS</label>
			<input type = "text" class = "form-control" required id = "nis" name="nis" value="<?php echo $result['nis']; ?>">
		</div>
		<div class = "controls">
			<label for = "name">Nama</label>
			<input type = "text" class = "form-control" required id = "nama" name="nama" value="<?php echo $result['nama']; ?>">
		</div>
		
		<div class = "controls">
		<label for = "name">JENIS KELAMIN</label>
		<select class="form-control" id = "jenkel" name="jenkel">
		
				// tampilkan untuk form ubah mahasiswa
			
				
				<option value="LK" <?php if($result['jenkel'] == 'LK'){ echo 'selected'; } ?>>LK</option> 
			    <option value="PR" <?php if($result['jenkel'] == 'PR'){ echo 'selected'; } ?>>PR</option> 
	
			</select>
			</div>
				<div class = "form-group">
			<label for = "name">Kelas</label>
			<input type = "text" class = "form-control" required id = "kelas" name="kelas" value="<?php echo $result['kelas']; ?>">
		</div>
		
	
		<div class = "form-group">
			<label for = "name">agama</label>
			<input type = "text" class = "form-control" required id = "agama" name="agama" value="<?php echo $result['agama']; ?>">
		</div>
		
		<div class = "form-group">
			<label for = "name">Tanggal Lahir</label>
			<input type = "date" class = "form-control" required id = "ttl" name="ttl" value="<?php echo $result['ttl']; ?>">
		</div>
		
		<div class = "form-group">
			<label for = "name">Alamat</label>
			<input type = "text" class = "form-control" required id = "alamat" name="alamat" value="<?php echo $result['alamat']; ?>">
		</div>
		
	
	
		<div class = "form-group">
		<label for = "inputfile">imageUrl</label>
		<input type = "file" name = "imageUrl"><br>
		<?php if($nis != ""){ ?>
          <img src="<?php echo $imageUrl;?>" height="100"/>
        <?php } ?>
   </div>
		
    </div>
   <button type = "submit" class = "btn btn-primary">Submit</button>
</form>

<h2><u>Data profil</u></h2><br>   
<div class="table-responsive">
 <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<thead>
					<tr>
                    	<th>No</th>
						<th>Nama</th>
						<th>Tgl Lahir</th>
						<th>Jenis Kelamin</th>
						<th>Agama</th>
						
						
						<th>Kelas</th>
					
						<th>Alamat</th>
						<th>Foto</th>
                        <th>Aksi</th>
					</tr>
				</thead>
				
				<tbody>
				<?php
				$view=mysqli_query($con,"select * from siswa order by nis desc");
				$no=1;
				while($row=mysqli_fetch_array($view)){
				
				$ubah	= "index.php?hal=siswa&nis=".$row['nis'];
				$hapus	= "hp_siswa.php?nis=".$row['nis'];
				
				?>
					<tr>
                    
                    <td><center><?php echo $no++ ?></center></td>
						<td><?php echo $row['nama'];?></td>
						<td><?php echo $row['ttl'];?></td>
						<td><?php echo $row['jenkel'];?></td>
						<td><?php echo $row['agama'];?></td>

					
						
						
						<td><?php echo $row['kelas'];?></td>
							<td><?php echo $row['alamat'];?></td>
						
						
						<td><?php echo "<center><img src='../foto/$row[foto]' width='120' height='120'/></center>"; ?></td>
						
                       <td>
						
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
