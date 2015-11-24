<?php
include("header.php");
include("DBConnect.php");
$time = date('Y-m-d H:i:s');
$sql = "insert into text_base(content, author, time, type) 
					values ('$leavemessage_content', $uid, '$time', 2)";
$result = mysql_query($sql);
if(!$result) echo "留言失败:".mysql_error();

$sql = "SELECT LAST_INSERT_ID()";
$result = mysql_query($sql);
if(!$result) echo "留言失败:".mysql_error();

$rs = mysql_fetch_array($result);
$last_tid = $rs[0];

$sql = "insert into message(tid, owner) 
					values ($last_tid, $pid)";
$result = mysql_query($sql);
if(!$result) echo "留言失败:".mysql_error();

$sql = "insert into reminder(tid, uid) 
					values ($last_tid, $pid)";
$result = mysql_query($sql);
if(!$result) echo "留言失败:".mysql_error();

else {
	//disconnect to mysql
	mysql_close($con);
	echo "success";
}
?>