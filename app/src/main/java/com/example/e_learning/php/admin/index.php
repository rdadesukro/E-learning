<?php
error_reporting(0);
session_start();
include "konek.php";
include "login_dahulu.php";


$hal = $_GET['hal'];
$hal = $_GET['hal'];
?>

<HTML>
	<HEAD>
	<title>Administrator</title>
	<link rel="icon" href="Data/img/favicon.ico" type="image/x-icon"/>
		<link rel="stylesheet" type="text/css" href="Data/Laporan/datatables.min.css"/>
		<link rel="stylesheet" type="text/css" href="Data/Laporan/stylenya.css"/>
		<script type="text/javascript" src="Data/Laporan/datatables.min.js"></script>
		<link rel="stylesheet" href="Data/assets/bootstrap/css/bootstrap.min.css">

		<link rel="stylesheet" href="Data/assets/bootstrap/css/bootstrap-theme.min.css">
		<script src="Data/assets/bootstrap/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="Data/assets/media/css/dataTables.bootstrap.css">
		<link rel="stylesheet" type="text/css" href="Data/assets/media/css/dataTables.responsive.css">
		<link href="Data/validasi.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" language="javascript" src="Data/assets/media/js/jquery.dataTables.js"></script>
		<script type="text/javascript" language="javascript" src="Data/assets/media/js/dataTables.responsive.js"></script>
		<script type="text/javascript" language="javascript" src="Data/assets/media/js/dataTables.bootstrap.js"></script>
		<script type="text/javascript" language="javascript" src="Data/assets/media/js/common.js"></script>
		
		  <!-- utk ckeditor -->
		<script src="Data/ckeditor/ckeditor.js"></script>
		  <!-- utk ckeditor -->
		
		  <!-- form input -->
		<script src="bootstrap.min.js"></script>
		
	   	  <!-- utk table -->
    <script src="assets_table/js/jquery.js"></script>
	  <script src="assets_table/js/jquery.dataTables.js"></script>
	  <script src="assets_table/js/jquery.dataTables.min.js"></script>
	  
	  <script>
	  $(document).ready(function() {
    $('#example').DataTable();
} );
	  </script>
     <!-- utk table sampe sni -->
	   <!-- utk table -->
   <link href="assets_table/css/bootstrap.css" rel="stylesheet" media="screen">
 
	<link href="assets_table/css/jquery.dataTables.css" rel="stylesheet" media="screen">
	<link href="assets_table/css/jquery.dataTables.min.css" rel="stylesheet" media="screen">

      <!-- utk table sampe sni -->
	
<!-- validasi textfiled  utk angka atau huruf atau dll -->
<script language="javascript">
function getkey(e)
{
if (window.user)
   return window.user.keyCode;
else if (e)
   return e.which;
else
   return null;
}
function goodchars(e, goods, field)
{
var key, keychar;
key = getkey(e);
if (key == null) return true;
 
keychar = String.fromCharCode(key);
keychar = keychar.toLowerCase();
goods = goods.toLowerCase();
 
// check goodkeys
if (goods.indexOf(keychar) != -1)
    return true;
// control keys
if ( key==null || key==0 || key==8 || key==9 || key==27 )
   return true;
    
if (key == 13) {
    var i;
    for (i = 0; i < field.form.elements.length; i++)
        if (field == field.form.elements[i])
            break;
    i = (i + 1) % field.form.elements.length;
    field.form.elements[i].focus();
    return false;
    };
// else return false
return false;
}
</script>
<!-- sampe sini -->	
	
	<!-- ini desain menu baru -->
	<link href="Data/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="Data/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="Data/vendors/nprogress/nprogress.css" rel="stylesheet">
    <link href="Data/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <link href="Data/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <link href="Data/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>
    <link href="Data/vendors/build/css/custom.min.css" rel="stylesheet">
	<!-- ini desain menu baru -->
	<style type="text/css">
		.left_col{
			position: fixed !important;
			width: 230px;
		}
	</style>
	</HEAD>
	<BODY class="nav-md">

	 <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
		
          <div class="left_col scroll-view">
            
			<div class="profile">
              <div class="profile_pic">
                <img src="Data/vendors/images/img.jpg" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>Login Sebagai</span>
                <h2><?php echo "Admin"?></h2><br>
              </div>
            </div>
			
            <br/>
			
			
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
               
				<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
             <div class="menu_section">
                <h1>Menu</h1>
                <ul class="nav side-menu">
				  <li><a href="index.php?hal=guru"><i class="fa fa-list-alt"></i>Data Guru</a></li>
				   <li><a href="index.php?hal=akun"><i class="fa fa-list-alt"></i>Data Akun Guru</a></li>
				   <li><a href="index.php?hal=siswa"><i class="fa fa-list-alt"></i>Data Siswa</a></li>
				     <li><a href="index.php?hal=mapel"><i class="fa fa-list-alt"></i>Data Mapel</a></li>
				 

				  <li><a href="logout.php" ><i class="fa fa-list-alt" onclick="return confirm('Apakah anda yakin akan menghapus data ini ?')"></i>Logout</a></li>
                </ul>
              </div>
			</div>		  
               
              </div>
			  
			
			
			</div>		
			
          </div>
        </div>

		 <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle" style="width:200px;padding-left:20px">
                <font size="4">
				</font>
              </div>

              <ul class="nav navbar-nav navbar-right">
                
			
				<!-- Ini menu ujung -->
				<li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                  </a>
                 
                </li>
				<!-- Ini menu ujung -->
				
			
				
				
              </ul>
            </nav>
          </div>
        </div>
		
        <div class="right_col" role="main">
		<!-- utk pesan -->
		<style>
		h1{
			color:black;
			text-align:center;
		}
		</style>
		<?php echo 
		"<h1><u> $_GET[pesan] </u></h1>"; 
		?>
		<!-- sampe sni -->
		<?php
		if($hal == "beranda"){
			include "beranda.php";
		}else if($hal == "vaksin"){
			include "vaksin.php";
			
		}else if($hal == "mapel"){
			include "mapel.php";
		}else if($hal == "user"){
			include "user.php";
		}else if($hal == "guru"){
			include "guru.php";
		}else if($hal == "akun"){
			include "akun.php";	
		}else if($hal == "siswa"){
			include "siswa.php";	
		}else{
			echo "<h1>E-LEARNING</h1>";
			
              
		}
		?>
		
		</div>
		</div>
		
		</div>
		<footer>
          <div class="pull-right">
            
          </div>
          <div class="clearfix"></div>
        </footer>
      </div>
    </div>
		
	
		
		
	<script src="Data/vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
    <script src="Data/vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
    <script src="Data/vendors/flot.curvedlines/curvedLines.js"></script>
    <script src="Data/vendors/DateJS/build/date.js"></script>
    <script src="Data/vendorsjs/moment/moment.min.js"></script>
    <script src="Data/vendorsjs/datepicker/daterangepicker.js"></script>
    <script src="Data/vendors/build/js/custom.min.js"></script>
	</BODY>
<footer>
          <div >
            <center><p>Design by IT STIKOM DB JAMBI Kota Jambi &copy; 2019.</p></center>
          </div>
          <div class="clearfix"></div>
        </footer>
	
</HTML>