<?php
error_reporting(0);
include "konek_baru.php";
$id_stok = $_GET['id_stok'];
if($id_stok != ""){
	$aksi = "pu_stok.php";
	$q = mysqli_query($con, "select * from stok where id_stok = '$id_stok'");
	$result = mysqli_fetch_assoc($q);
}else{
	$aksi = "ps_stok.php";
}
?>

<h2><u>Form Tambah Dan Edit Stok</u></h2> 
<form role = "form" action="<?php echo $aksi ?>" method="post">
   <div class = "form-group">
		<input type = "hidden" class = "form-control" id = "id_stok" name="id_stok" value="<?php echo $result['id_stok']; ?>">
		
		<div class = "form-group">
			<label for = "name">Golongan</label><br>
			<input type="radio" checked="checked"  name="gol" id="gol" value="A"<?php if ($result[gol]=="A") echo ("checked") ; ?>  />A&nbsp;&nbsp;
			<input type="radio" name="gol" id="gol" value="B"<?php if ($result[gol]=="B") echo ("checked") ; ?>  />B&nbsp;&nbsp;
			<input type="radio" name="gol" id="gol" value="AB"<?php if ($result[gol]=="AB") echo ("checked") ; ?>  />AB&nbsp;&nbsp;
			<input type="radio" name="gol" id="gol" value="O"<?php if ($result[gol]=="O") echo ("checked") ; ?>  />O
		</div>
		
		<div class = "form-group">
			<label for = "name">Rhesus</label>
			<input type = "text" class = "form-control" id = "Rhesus" name="Rhesus" value="<?php echo $result['Rhesus']; ?>">
		</div>
		
		<div class = "form-group">
			<label for = "name">stok</label>
			<input type = "text" class = "form-control" id = "stok" name="stok" value="<?php echo $result['stok']; ?>">
		</div>
		
    </div>
   <button type = "submit" class = "btn btn-primary">Submit</button>
</form>

<h2><u>Data stok</u></h2><br>   
<div class="table-responsive">
 <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
				<thead>
					<tr>
                    	<th>No</th>
						<th>Golongan</th>
						<th>Rhesus</th>
						<th>Stok</th>
                        <th>Aksi</th>
					</tr>
				</thead>
				
				<tbody>
				<?php
				$view=mysqli_query($con,"select * from stok order by id_stok desc");
				$no=1;
				while($row=mysqli_fetch_array($view)){
				
				$ubah	= "index.php?hal=stok&id_stok=".$row['id_stok'];
				$hapus	= "hp_stok.php?id_stok=".$row['id_stok'];
				
				?>
					<tr>
                    
                    <td><center><?php echo $no++ ?></center></td>
						<td><?php echo $row['gol'];?></td>
						<td><?php echo $row['Rhesus'];?></td>
						<td><?php echo $row['stok'];?></td>

                       <td>
						<a href="<?php echo $ubah ?>" class="btn btn-success">
							<span></span> Edit 
						</a>
						<a href="<?php echo $hapus ?>" class="btn btn-danger" onclick="return confirm('Apakah anda yakin akan menghapus data ini ?')">
							<span></span> Hapus 
						</a>
						</td>
					</tr>
				<?php
				}
				?>
				</tbody>
				
 </table>
</div>
<br>
