<?php
header("Cache-Control: no-store, no-cache, must-revalidate, max-age=0");
header("Cache-Control: post-check=0, pre-check=0", false);
header("Pragma: no-cache");


$plateNum = addslashes(strip_tags($_POST['platecarnum']));

$con = mysqli_connect("localhost","id18234418_user", "k&ow_psA2u_skKhn","id18234418_carplateregcsci410project");

if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

$sql = "select * from Carlist where plateNum=$plateNum";
if ($result = mysqli_query($con,$sql))
  {
   $emparray = array();
   while($row =mysqli_fetch_assoc($result))
       $emparray[] = $row;

  echo(json_encode($emparray));
  mysqli_free_result($result);
  mysqli_close($con);
}

?> 	