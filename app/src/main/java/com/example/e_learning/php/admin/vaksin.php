<?php
error_reporting(0);
include "konek_baru.php";
$id = $_GET['id'];
if($id != ""){
	$aksi = "pu_vaksin.php";
	$q = mysqli_query($con, "select * from vaksin where id = '$id'");
	$result = mysqli_fetch_assoc($q);
	$imageUrl = "images/".$result['imageUrl'];
}else{
	$aksi = "ps_vaksin.php";
}
?>

<h2><u>Form Tambah Dan Edit vaksin</u></h2> 
<form role = "form" action="<?php echo $aksi ?>" method="post" enctype="multipart/form-data">
   <div class = "control-group">
		<input type = "hidden" class = "form-control" id = "id" name="id" value="<?php echo $result['id']; ?>">
		
		<div class = "controls">
			<label for = "name">nama</label>
			<input type = "text" class = "form-control" required id = "nama" name="nama" value="<?php echo $result['nama']; ?>">
		</div>
	
		
		<div class = "form-group">
			<label for = "name">ket</label>
            <textarea name = "ket" class = "form-control" id = "ket"><?php echo $result['ket'] ?>
</textarea>
		
		</div>
		
		
		
    </div>
   <button type = "submit" class = "btn btn-primary">Submit</button>
</form>

<h2><u>Data vaksin</u></h2><br>   
<div class="table-responsive">
 <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<thead>
					<tr>
                    	<th>No</th>
						<th>Nama Vaksin</th>
						<th>Keterangan</th>
                        <th>Aksi</th>
					</tr>
				</thead>
				
				<tbody>
				<?php
				$view=mysqli_query($con,"select * from vaksin order by id desc");
				$no=1;
				while($row=mysqli_fetch_array($view)){
				
				$ubah	= "index.php?hal=vaksin&id=".$row['id'];
				$hapus	= "hp_vaksin.php?id=".$row['id'];
				
				?>
					<tr>
                    
                    <td><center><?php echo $no++ ?></center></td>
						<td><?php echo $row['nama'];?></td>
						<td><?php echo $row['ket'];?></td>
					
						
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
