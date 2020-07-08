<?php
error_reporting(0);
include "konek_baru.php";
$id_event = $_GET['id_event'];
if($id_event != ""){
	$aksi = "pu_event.php";
	$q = mysqli_query($con, "select * from event where id_event = '$id_event'");
	$result = mysqli_fetch_assoc($q);
	$imageUrl = "images/".$result['imageUrl'];
}else{
	$aksi = "ps_event.php";
}
?>

<h2><u>Form Tambah Dan Edit event</u></h2> 
<form role = "form" action="<?php echo $aksi ?>" method="post" enctype="multipart/form-data">
   <div class = "control-group">
		<input type = "hidden" class = "form-control" id = "id_event" name="id_event" value="<?php echo $result['id_event']; ?>">
		
		<div class = "controls">
			<label for = "name">Nama</label>
			<input type = "text" class = "form-control" required id = "nama" name="nama" value="<?php echo $result['nama']; ?>">
		</div>
		
		<div class = "form-group">
			<label for = "name">Lokasi</label>
			<input type = "text" class = "form-control" required id = "lokasi" name="lokasi" value="<?php echo $result['lokasi']; ?>">
		</div>
		
		<div class = "form-group">
			<label for = "name">Tanggal</label>
			<input type = "date" class = "form-control" id = "tanggal" name="tanggal" value="<?php echo $result['tanggal']; ?>">
		</div>
		
		<div class = "form-group">
			<label for = "name">Latitude</label>
			<input type = "text" class = "form-control" id = "lat" name="lat" value="<?php echo $result['lat']; ?>">
		</div>
		
		<div class = "form-group">
			<label for = "name">Longitude</label>
			<input type = "text" class = "form-control" id = "lng" name="lng" value="<?php echo $result['lng']; ?>">
		</div>
		
		<div class = "form-group">
			<label for = "name">Jam</label>
			<input type = "text" class = "form-control" id = "jam" name="jam" value="<?php echo $result['jam']; ?>">
		</div>
		
		<div class = "form-group">
		<label for = "inputfile">imageUrl</label>
		<input type = "file" name = "imageUrl"><br>
		<?php if($id_event != ""){ ?>
          <img src="<?php echo $imageUrl;?>" height="100"/>
        <?php } ?>
   </div>
		
    </div>
   <button type = "submit" class = "btn btn-primary">Submit</button>
</form>

<h2><u>Data event</u></h2><br>   
<div class="table-responsive">
 <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<thead>
					<tr>
                    	<th>No</th>
						<th>Nama</th>
						<th>Lokasi</th>
						<th>Latitude</th>
						<th>Longitude</th>
						<th>Gambar</th>
						<th>Tanggal</th>
                        <th>Aksi</th>
					</tr>
				</thead>
				
				<tbody>
				<?php
				$view=mysqli_query($con,"select * from event order by id_event desc");
				$no=1;
				while($row=mysqli_fetch_array($view)){
				
				$ubah	= "index.php?hal=event&id_event=".$row['id_event'];
				$hapus	= "hp_event.php?id_event=".$row['id_event'];
				
				?>
					<tr>
                    
                    <td><center><?php echo $no++ ?></center></td>
						<td><?php echo $row['nama'];?></td>
						<td><?php echo $row['lokasi'];?></td>
						<td><?php echo $row['lat'];?></td>
						<td><?php echo $row['lng'];?></td>
						<td><?php echo "<center><img src='images/$row[imageUrl]' width='120' height='120'/></center>"; ?></td>
						<td><?php echo $row['tanggal'];?></td>

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
