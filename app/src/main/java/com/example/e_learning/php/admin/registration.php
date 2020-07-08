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
	require('db.php');
    // If form submitted, insert values into the database.
    if (isset($_REQUEST['username'])){
		$username = stripslashes($_REQUEST['username']); // removes backslashes
		$username = mysqli_real_escape_string($con,$username); //escapes special characters in a string
		$password = stripslashes($_REQUEST['password']);
		$password = mysqli_real_escape_string($con,$password);

		$trn_date = date("Y-m-d H:i:s");
        $query = "INSERT into `admin` (username, password) VALUES ('$username', '".md5($password)."')";
        $result = mysqli_query($con,$query);
        if($result){
            echo "<div class='form'><h3>You are registered successfully.</h3><br/>Click here to <a href='login.php'>Login</a></div>";
        }
    }else{
?>
<div id="login-page">
	<div class="container">
<div class="form" class="form-login" >
<form class="form-login"  name="registration" action="" method="post">
 <h2 class="form-login-heading" style="background:#FC8C43">  Registration</h2>
 <div class="login-wrap">
<div class="input-group input-icon right">
					<span class="input-group-addon">
						<i class="fa fa-user"></i>
					</span>
<input class="form-control" type="text" name="username" placeholder="Username" required />
</div>
		<br>
<div class="input-group input-icon right">
					<span class="input-group-addon">
						<i class="fa fa-user"></i>
					</span>
<input  class="form-control" type="password" name="password" placeholder="Password" required />
</div>
<label class="checkbox">
		                <span class="pull-right">
		                   
		
		                </span>
		            </label>
<input class="btn btn-theme03 btn-block" type="submit" name="submit" value="Register" />
</div>
</form>
<br /><br />
</div>
</div>
</div>
<?php } ?>
</body>
</html>
