<?php
error_reporting(0);
include "db.php";
$id_mapel = $_GET['id_mapel'];
if($id_mapel != ""){
	$aksi = "pu_mapel.php";
	$q = mysqli_query($con, "select * from mapel where id_mapel = '$id_mapel'");
	$result = mysqli_fetch_assoc($q);
	$imageUrl = "../foto/".$result['foto_mapel'];
}else{
	$aksi = "ps_mapel.php";
}
?>

<h2><u>Form Tambah Dan Edit Mapel</u></h2> 
<form role = "form" action="<?php echo $aksi ?>" method="post" enctype="multipart/form-data">
   <div class = "control-group">
		<input type = "hidden" class = "form-control" id = "id_mapel" name="id_mapel" value="<?php echo $result['id_mapel']; ?>">
		
	
	
		
		<div class = "controls">
			<label for = "name">nama</label>
			<input type = "text" class = "form-control" required id = "nama_mapel" name="nama_mapel" value="<?php echo $result['nama_mapel']; ?>">
		</div>
	
		<div class = "form-group">
		<label for = "inputfile">foto_Sampul</label>
		<input type = "file" name = "imageUrl"><br>
		<?php if($id_mapel != ""){ ?>
          <img src="<?php echo $imageUrl;?>" height="100"/>
        <?php } ?>
   </div>
		
		
    </div>
   <button type = "submit" class = "btn btn-primary">Submit</button>
</form>
<h2><u>Data mapel</u></h2><br>   
<div class="table-responsive">
 <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<thead>
					<tr>
                    	<th>No</th>
						<th>Nama Mapel</th>
			
						<th>Foto</th>
                        <th>Aksi</th>
					</tr>
				</thead>
				
				<tbody>
				<?php
				$view=mysqli_query($con,"select * from mapel order by id_mapel desc");
				$no=1;
				while($row=mysqli_fetch_array($view)){
				
				$ubah	= "index.php?hal=mapel&id_mapel=".$row['id_mapel'];
				$hapus	= "hp_mapel.php?id_mapel=".$row['id_mapel'];
				
				?>
					<tr>
                    
                    <td><center><?php echo $no++ ?></center></td>
						<td><?php echo $row['nama_mapel'];?></td>
						
						
						<td><?php echo "<center><img src='../foto/$row[foto_mapel]' width='120' height='120'/></center>"; ?></td>
						
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
