<?php
include("header.php");
include("DBConnect.php");

$newsfeed = $_GET["newsfeed"];

$time = date('Y-m-d H:i:s');
$sql = "insert into text_base(content, author, time, type) 
					values ('$newsfeed', $uid, '$time', 0)";
$result = mysql_query($sql);
if(!$result) echo "发布失败:".mysql_error();
else {
	//disconnect to mysql
	mysql_close($con);
	echo "success";
}
?>