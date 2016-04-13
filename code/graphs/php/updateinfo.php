<?php
include("header.php");
include("DBConnect.php");

$sql = "update user_info 
		set password='$newpassword', nickname='$newnickname', school='$newschool'
		where uid=$uid";
$result = mysql_query($sql);
//echo $sql;
if($result) echo "success";
else echo "error";
mysql_close($con);
?>