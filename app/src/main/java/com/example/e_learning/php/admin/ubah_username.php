<?php
include "konek.php";
$id_admin = $_GET['id_admin'];  
if($id_admin != ""){
    $aksi = "pu_us.php";
    $query = mysql_query("SELECT * FROM  `admin` where id_admin = '$id_admin'");
    $result = mysql_fetch_assoc($query);  
}
?>

<h2><u>Edit Username</u></h2>
<form method="post" action="<?php echo $aksi; ?>">
<input type="hidden" name="id_admin" id="id_admin" value="<?php echo $result['id_admin'];?>" />
   <input type = "hidden" class = "form-control" id = "username" name = "username" value="<?php echo $result['username'];?>">
   <div class = "form-group">
      <label for = "name">Username Lama</label>
      <input type = "text" class = "form-control" id = "username_l" name = "username_l" value="<?php echo $result['username_l'];?>">
	</div>
    <div class = "form-group">
      <label for = "name">Username Baru</label>
      <input type = "text" class = "form-control" id = "username_b" name = "username_b" value="<?php echo $result['username_b'];?>">
	</div>
	    <div class = "form-group">
      <label for = "name">Ulangi Username Baru</label>
      <input type = "text" class = "form-control" id = "uub" name = "uub" value="<?php echo $result['uub'];?>">
	</div>
   <button type = "submit" class = "btn btn-primary">Submit</button>
</form>