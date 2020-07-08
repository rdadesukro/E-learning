<?php

define('HOST','localhost');

define('USER','root');

define('PASS','');

define('DB','properti');

$con = mysqli_connect(HOST,USER,PASS,DB);

?>
<?php
$id_admin = $_GET['id_admin'];  
if($id_admin != ""){
    $aksi = "pu_up.php";
    $query = mysql_query("SELECT * FROM  `admin` where id_admin = $id_admin");
    $result = mysql_fetch_assoc($query);  
}
?>

<h2><u>Edit Username atau Password</u></h2>
<form method="post" action="<?php echo $aksi; ?>">
<input type="hidden" name="id_admin" id="id_admin" value="<?php echo $result['id_admin'];?>" />
   <div class = "form-group">
      <label for = "name">Username</label>
      <input type = "text" class = "form-control" id = "username" name = "username" value="<?php echo $result['username'];?>">
	</div>  
	 <div class = "form-group">
      <label for = "name">Password</label>
      <input type = "password" class = "form-control" id = "password" name = "password" value="<?php echo $result['password'];?>">
	</div>  
   <button type = "submit" class = "btn btn-primary">Submit</button>
</form>