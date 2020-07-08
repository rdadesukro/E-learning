<?php
error_reporting(0);
include "konek_ade.php";
$kode_kategori = $_GET['kode_kategori'];
if($kode_kategori != ""){
	$aksi = "pu_kategori.php";
	$q = mysqli_query($con, "select * from kategori where kode_kategori = '$kode_kategori'");
	$result = mysqli_fetch_assoc($q);
}else{
	$aksi = "ps_kategori.php";
}
?>

<h2><u>Form Tambah Dan Edit Kategori</u></h2> 
<form role = "form" action="<?php echo $aksi ?>" method="post">
   <div class = "form-group">
		<input type = "hidden" class = "form-control" id = "kode_kategori" name="kode_kategori" value="<?php echo $result['kode_kategori']; ?>">
		
		<div class = "form-group">
			<label for = "name">Kategori</label>
			<input type = "text" class = "form-control" id = "kategori" name="kategori" value="<?php echo $result['kategori']; ?>">
		</div>
		
    </div>
   <button type = "submit" class = "btn btn-primary">Submit</button>
</form>

<h2><u>Data Kategori</u></h2><br>   
<div class="table-responsive">
 <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<thead>
					<tr>
                    	<th>No</th>
						<th width="500px">Kategori</th>
                        <th>Aksi</th>
					</tr>
				</thead>
				
				<tbody>
				<?php
				include "konek2.php";
				$view=mysqli_query($db,"select * from kategori order by kode_kategori desc");
				$no=1;
				while($row=mysqli_fetch_array($view)){
				
				$ubah	= "index.php?hal=kategori&kode_kategori=".$row['kode_kategori'];
				$hapus	= "hp_kategori.php?kode_kategori=".$row['kode_kategori'];
				
				?>
					<tr>
                    
                    <td><center><?php echo $no++ ?></center></td>
						<td><?php echo $row['kategori'];?></td>
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
