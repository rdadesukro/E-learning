<?php
include "konek.php";
$id_admin = $_GET['id_admin'];  
if($id_admin != ""){
    $aksi = "pu_up.php";
    $query = mysql_query("SELECT * FROM  `admin` where id_admin = '$id_admin'");
    $result = mysql_fetch_assoc($query);  
}
?>

<h2><u>Edit password</u></h2>
<form method="post" action="<?php echo $aksi; ?>">
<input type="hidden" name="id_admin" id="id_admin" value="<?php echo $result['id_admin'];?>" />
   <input type = "hidden" class = "form-control" id = "password" name = "password" value="<?php echo $result['password'];?>">
   <div class = "form-group">
      <label for = "name">Password Lama</label>
      <input type = "text" class = "form-control" id = "password_l" name = "password_l" value="<?php echo $result['password_l'];?>">
	</div>
    <div class = "form-group">
      <label for = "name">Password Baru</label>
      <input type = "password" class = "form-control" id = "password_b" name = "password_b" value="<?php echo $result['password_b'];?>">
	</div>
	    <div class = "form-group">
      <label for = "name">Ulangi password Baru</label>
      <input type = "password" class = "form-control" id = "upb" name = "upb" value="<?php echo $result['upb'];?>">
	</div>
   <button type = "submit" class = "btn btn-primary">Submit</button>
</form>