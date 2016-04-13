<?php
include("DBConnect.php");

$email = $_GET["email"];
$password = $_GET["password"];

$sql = "select * from user_info where name='$email' and password='$password'";
$result = mysql_query($sql);
//disconnect to mysql
mysql_close($con);

if(mysql_num_rows($result) == 0) echo "用户名或密码错误".$email;
else {
	session_start();

	while($row = mysql_fetch_array($result)){
		$_SESSION['uid'] = $row['UID'];
		//$_SESSION['email'] = $row['name'];
		$_SESSION['nickname'] = $row['nickname'];
	}
	echo "true";
}
?>