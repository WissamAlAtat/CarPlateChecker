<?php
$plateNum = addslashes(strip_tags($_POST['platecarnum']));
$ownerName = addslashes(strip_tags($_POST['ownername']));
$brand = addslashes(strip_tags($_POST['brand']));
$model = addslashes(strip_tags($_POST['model']));
$year = addslashes(strip_tags($_POST['year']));


$con = mysqli_connect("localhost","id18234418_user", "k&ow_psA2u_skKhn","id18234418_carplateregcsci410project");

if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

$sql = "insert into Carlist values ($plateNum, '$ownerName', '$brand', '$model', $year)";
mysqli_query($con,$sql) or
    die ("can't add record");

echo "Record Added";
   
mysqli_close($con);
?> 			