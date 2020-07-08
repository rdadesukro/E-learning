<?php
/*
Author: Javed Ur Rehman
Website: http://www.allphptricks.com/
*/
?>
<!DOCTYPE html>
<html>
<head>
<title>Hal Admin</title>
    <link href="Data/login/bootstrap.css" rel="stylesheet">
    <link href="Data/login/font-awesome.css" rel="stylesheet" />
    <link href="Data/login/style.css" rel="stylesheet">
    <link href="Data/login/style-responsive.css" rel="stylesheet">
	<link href="Data/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <style type="text/css">
    body{
    	background-image: url('login.jpg');
    	background-repeat:no-repeat;
 		background-size:cover;
    }
    </style>
</head>

<body>
<?php
	/*require('konek_baru.php');
	session_start();
    // If form submitted, insert values into the database.
    if (isset($_POST['username'])){
		
		$username = stripslashes($_REQUEST['username']); // removes backslashes
		$username = mysqli_real_escape_string($con,$username); //escapes special characters in a string
		$password = stripslashes($_REQUEST['password']);
		$password = mysqli_real_escape_string($con,$password);
		
	//Checking is user existing in the database or not
        $query = "SELECT * FROM `admin` WHERE username='$username' and password='".md5($password)."'";
		$result = mysqli_query($con,$query) or die(mysql_error());
		$rows = mysqli_num_rows($result);
        if($rows==1){
			$_SESSION['username'] = $username;
			header("Location: index.php"); // Redirect user to index.php
            }else{
				echo "<div class='form'><h3>Username/password is incorrect.</h3><br/>Click here to <a href='login.php'>Login</a></div>";
				}
    }else{*/
?>
<div id="login-page">
	<div class="container">
<div class="form">

<form class="form-login" action="proses_login.php" method="post" name="login">
<h2 class="form-login-heading" style="background:#26ae90">  LOGIN ADMIN</h2>
 <div class="login-wrap">
<div class="input-group input-icon right">
<span class="input-group-addon">
						<i class="fa fa-user"></i>
					</span>
<input  class="form-control" type="text" name="username" placeholder="Username" required />
</div>
	<br>
<div class="input-group input-icon right">
<span class="input-group-addon">
						<i class="fa fa-key"></i>
					</span>
<input class="form-control" type="password" name="password" placeholder="Password" required />
</div>
  <label class="checkbox">
		                <span class="pull-right">
		                   
		
		                </span>
		            </label>
<input name="submit" type="submit" value="Login" class="btn btn-theme03 btn-block" />'
<div class="login-social-link centered">
		      
		            </div>
					  <div class="registration">
                
		            </div>
					

<br /><br />
 </div>
</form>

</div>
</div>
</div>


</body>
</html>
