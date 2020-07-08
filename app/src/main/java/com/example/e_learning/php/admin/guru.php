<?php
error_reporting(0);
include "db.php";
$id_guru = $_GET['id_guru'];
if($id_guru != ""){
	$aksi = "pu_guru.php";
	$q = mysqli_query($con, "select * from guru where id_guru = '$id_guru'");
	$result = mysqli_fetch_assoc($q);
	$imageUrl = "../foto/".$result['foto'];
}else{
	$aksi = "ps_guru.php";
}
?>

<h2><u>Form Tambah Dan Edit Data Guru</u></h2> 
<form role = "form" action="<?php echo $aksi ?>" method="POST" enctype="multipart/form-data">
   <div class = "control-group">
	
		<div class = "controls">
			<label for = "name">ID GURU</label>
			<input type = "text" class = "form-control" required id = "id_guru" name="id_guru" value="<?php echo $result['id_guru']; ?>">
		</div>
		<div class = "controls">
			<label for = "name">Nama</label>
			<input type = "text" class = "form-control" required id = "nama" name="nama" value="<?php echo $result['nama']; ?>">
		</div>
		
		<div class = "controls">
		<label for = "name">JENIS KELAMIN</label>
		<select class="form-control" id = "jk" name="jk">
		
				// tampilkan untuk form ubah mahasiswa
			
				
				<option value="LK" <?php if($result['jk'] == 'LK'){ echo 'selected'; } ?>>LK</option> 
			    <option value="PR" <?php if($result['jk'] == 'PR'){ echo 'selected'; } ?>>PR</option> 
	
			</select>
			</div>
			
			   <div class = "form-group">
			<label for = "name">Pilih Mapel</label>
			
<select name="id_mapel" id="id_mapel" class="form-control">
<option value="" disabled="disabled">--Jenis--</option>
 <?php     
        $h = mysqli_query($con, "select * from mapel order by id_mapel desc") or die(mysql_error());    
        while ($baris = mysqli_fetch_array($h)) {
			if($baris['id_mapel'] == $result['id_mapel']) {
				echo '<option value="' . $baris['id_mapel'] . '" selected>' . $baris['nama_mapel'].'</option>';    
			} else {
				echo '<option value="' . $baris['id_mapel'] . '">' . $baris['nama_mapel'].'</option>';    
			}
        
          }    
        echo '</select>';    
        ?>
 </select>
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
		<?php if($id_guru != ""){ ?>
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
						
						
						<th>Mapel</th>
					
						<th>Alamat</th>
						<th>Foto</th>
                        <th>Aksi</th>
					</tr>
				</thead>
				
				<tbody>
				<?php
				$view=mysqli_query($con,"select * from guru order by id_guru desc");
				$no=1;
				while($row=mysqli_fetch_array($view)){
				
				$ubah	= "index.php?hal=guru&id_guru=".$row['id_guru'];
				$hapus	= "hp_guru.php?id_guru=".$row['id_guru'];
				
				?>
					<tr>
                    
                    <td><center><?php echo $no++ ?></center></td>
						<td><?php echo $row['nama'];?></td>
						<td><?php echo $row['ttl'];?></td>
						<td><?php echo $row['jk'];?></td>
						<td><?php echo $row['agama'];?></td>

					
						
						
						<td><?php echo $row['id_mapel'];?></td>
							<td><?php echo $row['alamat'];?></td>
						
						
						<td><?php echo "<center><img src='../foto/$row[foto]' width='120' height='120'/></center>"; ?></td>
						
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
