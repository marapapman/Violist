<?php include("header.php") ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>消息提醒</title>
	<link rel="stylesheet" href="css/general.css" />
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/general.js"></script>
	<script type="text/javascript">
	</script>
</head>
<body>
<div id="pagebody">
<?php
	include("column.php");
	include("DBConnect.php");
?>
	<h1>消息提醒</h1>
<?php
include("header.php");
include("DBConnect.php");
$sql = "select distinct user_info.uid, user_info.nickname, text_base.tid, text_base.author, 
			   text_base.type, text_base.content
		from text_base, user_info, reminder
		where reminder.uid = $uid and reminder.tid = text_base.tid and text_base.author = user_info.uid
		order by text_base.time desc;";	
$result = mysql_query($sql); 
if(!$result) echo mysql_error();
$flag = false;
echo "<div class='feedlist'>";
if(mysql_num_rows($result) != 0) {

	$flag = true;
	while($row = mysql_fetch_array($result)) {
		if ($row['type'] == 1) {
			$temp_tid = $row['tid'];
			$temp_sql = "select owner_text from comment where tid = $temp_tid;";
			$temp_result = mysql_query($temp_sql); 
			if(!$temp_result) echo mysql_error();
			
			$temp_rs = mysql_fetch_array($temp_result);
			$temp_tid = $temp_rs[0];
			
			$temp_sql = "select text_base.content from text_base where text_base.tid = $temp_tid;";	
			$temp_result = mysql_query($temp_sql); 
			if(!$temp_result) echo mysql_error();
			
			$temp_rs = mysql_fetch_array($temp_result);
			$my_content = $temp_rs[0];
			
			/************************* 消息提醒显示 *************************/
			echo "<div class='peoplelist'>
					<a href='viewpeople.php?pid=".$row['uid']."'>".$row['nickname']."</a> 在新鲜事\" ".$my_content."\"中回复了你：".$row['content'].
					"<div>".$row['time']."</div>
				 </div>"; 
		}
		if ($row['type'] == 2) {
			/************************* 消息提醒显示 *************************/
			echo "<div class='peoplelist'>
					<a href='viewpeople.php?pid=".$row['uid']."'>".$row['nickname']."</a> 给你留言了: ".$row['content'].
					"<div>".$row['time']."</div>
				  </div>"; 
		}
	}
}
if ($flag) {
	$sql = "delete from reminder where reminder.uid = $uid;";	
	$result = mysql_query($sql); 
	if(!$result) echo mysql_error();
}
else {
	echo "<div class='inner'>没有新的消息提醒</div>";
}
echo "</div>";

?>
</div>
</body>
</html>