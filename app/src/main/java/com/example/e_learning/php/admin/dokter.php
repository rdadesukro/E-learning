<?php
error_reporting(0);
include "konek_baru.php";
$id = $_GET['id'];
if($id != ""){
	$aksi = "pu_dokter.php";
	$q = mysqli_query($con, "select * from dokter where id = '$id'");
	$result = mysqli_fetch_assoc($q);
	$imageUrl = "images/".$result['imageUrl'];
}else{
	$aksi = "ps_dokter.php";
}
?>

<h2><u>FORM TAMBAH dan EDIT Praktek Dokter</u></h2> 
<form role = "form" action="<?php echo $aksi ?>" method="POST" enctype="multipart/form-data">
   <div class = "control-group">
		<input type = "hidden" class = "form-control" id = "id" name="id" value="<?php echo $result['id']; ?>">
		
		<div class = "controls">
			<label for = "name">Nama Dokter</label>
			<input type = "text" class = "form-control" required id = "nama" name="nama" value="<?php echo $result['nama']; ?>">
		</div>
		
		
		
		<div class = "form-group">
			<label for = "name">Alamat</label>
			<input type = "text" class = "form-control" required id = "alamat" name="alamat" value="<?php echo $result['alamat']; ?>">
		</div>
		<div class = "form-group">
			<label for = "name">Jam Buka</label>
			<input type = "time" class = "form-control" required id = "jam_buka" name="jam_buka" value="<?php echo $result['jam_buka']; ?>">
		</div>
		<div class = "form-group">
			<label for = "name">Jam Tutup</label>
			<input type = "time" class = "form-control" required id = "jam_tutup" name="jam_tutup" value="<?php echo $result['jam_tutup']; ?>">
		</div>

		<div class = "form-group">
			<label for = "name">Latitude</label>
			<input type = "text" class = "form-control" required id = "lat" name="lat" value="<?php echo $result['lat']; ?>">
		</div>
		
		<div class = "form-group">
			<label for = "name">Longitude</label>
			<input type = "text" class = "form-control" required id = "lng" name="lng" value="<?php echo $result['lng']; ?>">
		</div>
		

	<div class = "form-group" type="hidden">
			   
            <select name="topics">
              <option value="news">News</option>
              <option value="promo">Promo</option>
            </select>
		</div>
		            <input type="hidden" name="type" value="topics" >
		<div class = "form-group">
		<label for = "inputfile">imageUrl</label>
		<input type = "file" name = "imageUrl"><br>
		<?php if($id != ""){ ?>
          <img src="<?php echo $imageUrl;?>" height="100"/>
        <?php } ?>
   </div>
		
    </div>
   <button type = "submit" class = "btn btn-primary">Submit</button>
</form>

<h2><u>DATA LOKASI PRAKTER Dokter</u></h2><br>   
<div class="table-responsive">
 <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<thead>
					<tr>
                    	<th>No</th>
						<th>Nama Dokter</th>
						<th>Alamat</th>
						<th>Jam Buka</th>
						<th>Jam Tutup</th>
						<th>Latitude</th>
						<th>Longitude</th>
						<th>Gambar</th>
                        <th>Aksi</th>
					</tr>
				</thead>
				
				<tbody>
				<?php
				$view=mysqli_query($con,"select * from dokter order by id desc");
				$no=1;
				while($row=mysqli_fetch_array($view)){
				
				$ubah	= "index.php?hal=dokter&id=".$row['id'];
				$hapus	= "hp_dokter.php?id=".$row['id'];
				
				?>
					<tr>
                    
                    <td><center><?php echo $no++ ?></center></td>
						<td><?php echo $row['nama'];?></td>
						<td><?php echo $row['alamat'];?></td>
						<td><?php echo $row['jam_buka'];?></td>
					    <td><?php echo $row['jam_tutup'];?></td>
						<td><?php echo $row['lat'];?></td>
						<td><?php echo $row['lng'];?></td>
						<td><?php echo "<center><img src='images/$row[imageUrl]' width='120' height='120'/></center>"; ?></td>
					

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
