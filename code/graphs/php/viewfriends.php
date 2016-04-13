<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>我的好友</title>
	<link rel="stylesheet" href="css/general.css" />
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/general.js"></script>
</head>
<body>
<div id="pagebody">
<?php
	include("header.php"); 
	include("column.php");
	echo "<h1>我的好友</h1>
		  <div class='feedlist'>";
	include("DBConnect.php");
	
	$flag = false;
	//search for friend
	$sql = "SELECT user_info.uid, user_info.nickname, user_info.school
			FROM user_info, user_relation 
			WHERE user_relation.user1 = $uid and user_relation.user2 = user_info.uid;";
	$result = mysql_query($sql);
	if(mysql_num_rows($result) != 0) {
		$flag = true;
		while($row = mysql_fetch_array($result)) {
			echo "<div class='peoplelist'>
					<p><a href='viewpeople.php?pid=".$row['uid']."'>".$row['nickname']."</a></p>
					<p>学校：".$row['school']."</p>
				  </div>";
		}
	}
	//disconnect to mysql
	mysql_close($con);
	if(!$flag) {
		echo "<div class='peoplelist'>Oops...我还没有好友...</div>";
	}
	echo "</div>";
?>
</div>
</body>
</html>