<?php
error_reporting(0);
include "db.php";
$nis = $_GET['nis'];
if($nis != ""){
	$aksi = "pu_user.php";
	$q = mysqli_query($con, "select * from user where nis = '$nis'");
	$result = mysqli_fetch_assoc($q);
	$imageUrl = "../foto/".$result['foto'];
}else{
	$aksi = "ps_user.php";
}
?>

<h2><u>Form Tambah Dan Edit Data user</u></h2> 
<form role = "form" action="<?php echo $aksi ?>" method="POST" enctype="multipart/form-data">
   <div class = "control-group">
	
		<input type="hidden" id="nis" name="nis" value="<?php echo $result['nis']; ?>" />
	  <div class = "form-group">
			<label for = "name">Pilih Guru</label>
			
<select name="nis" id="nis" class="form-control">
<option value="" disabled="disabled">--Guru--</option>
 <?php     
        $h = mysqli_query($con, "select * from guru order by id_guru desc") or die(mysql_error());    
        while ($baris = mysqli_fetch_array($h)) {
			if($baris['id_guru'] == $result['nis']) {
				echo '<option value="' . $baris['id_guru'] . '" selected>' . $baris['nama'].'</option>';    
			} else {
				echo '<option value="' . $baris['id_guru'] . '">' . $baris['nama'].'</option>';    
			}
        
          }    
        echo '</select>';    
        ?>
 </select>
 	</div>
		<div class = "controls">
			<label for = "name">Username</label>
			<input type = "text" class = "form-control" required id = "username" name="username" value="<?php echo $result['username']; ?>">
		</div>
		
		
	
		
		<div class = "form-group">
			<label for = "name">Password</label>
			<input type = "text" class = "form-control" required id = "password" name="password" value="<?php echo $result['password']; ?>">
		</div>
		
	
<div class = "controls">
		<label for = "name">LEVEL</label>
		<select class="form-control" id = "level" name="level">
		
				// tampilkan untuk form ubah mahasiswa
			
				
				<option value="guru" <?php if($result['level'] == 'guru'){ echo 'selected'; } ?>>Guru</option> 
			    <option value="siswa" <?php if($result['level'] == 'siswa'){ echo 'selected'; } ?>>Siswa</option> 
	
			</select>
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
						<th>Username</th>
						<th>Password</th>
						<th>Level</th>
						
                        <th>Aksi</th>
					</tr>
				</thead>
				
				<tbody>
				<?php
				$view=mysqli_query($con,"select * from user order by nis desc");
				$no=1;
				while($row=mysqli_fetch_array($view)){
				
				$ubah	= "index.php?hal=akun&nis=".$row['nis'];
				$hapus	= "hp_user.php?nis=".$row['nis'];
				
				?>
					<tr>
                    
                    <td><center><?php echo $no++ ?></center></td>
						<td><?php echo $row['username'];?></td>
						<td><?php echo $row['password'];?></td>
						<td><?php echo $row['level'];?></td>
						

					
						
						
						
						
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
