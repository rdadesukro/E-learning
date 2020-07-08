<?php
error_reporting(0);
include "konek_baru.php";
$id = $_GET['id'];
if($id != ""){
	$aksi = "pu_kivi.php";
	$q = mysqli_query($con, "select * from kivi where id = '$id'");
	$result = mysqli_fetch_assoc($q);
	$imageUrl = "images/".$result['imageUrl'];
}else{
	$aksi = "ps_kivi.php";
}
?>

<h2><u>Form Tambah Dan Edit Kivi</u></h2> 
<form role = "form" action="<?php echo $aksi ?>" method="post" enctype="multipart/form-data">
   <div class = "control-group">
		<input type = "hidden" class = "form-control" id = "id" name="id" value="<?php echo $result['id']; ?>">
		
	
	
		
		<div class = "form-group">
			<label for = "name">KIVI</label>
            <textarea name = "kivi" class = "form-control" id = "kivi"><?php echo $result['kivi'] ?>
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
						<th>Kivi</th>
			
                        <th>Aksi</th>
					</tr>
				</thead>
				
				<tbody>
				<?php
				$view=mysqli_query($con,"select * from kivi order by id desc");
				$no=1;
				while($row=mysqli_fetch_array($view)){
				
				$ubah	= "index.php?hal=kivi&id=".$row['id'];
				$hapus	= "hp_kivi.php?id=".$row['id'];
				
				?>
					<tr>
                    
                    <td><center><?php echo $no++ ?></center></td>
						<td><?php echo $row['kivi'];?></td>
						
					
						
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
