<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>搜索结果</title>
	<link rel="stylesheet" href="css/general.css" />
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/general.js"></script>
	<script type="text/javascript">
	function addfriend(friend) {
		var path = "addfriend.php?friendid=" + friend;
		$.get(path, function (data) {
			alert(data);
			location.reload();
		});
	}
	</script>
</head>
<body>
<div id="pagebody">
<?php
	include("header.php"); 
	include("column.php");

	$searchpeople = $_GET["searchpeople"];

	echo "<h1>搜索 ".$searchpeople." 结果</h1>
		  <div class='feedlist'>";
	include("DBConnect.php");
	
	$flag = false;
	//search for friend
	$sql = "SELECT user_info.uid, user_info.nickname, user_info.school
			FROM user_info, user_relation 
			WHERE user_relation.user1 = $uid and user_relation.user2 = user_info.uid
				  and user_info.nickname = '$searchpeople';";
	//echo $sql;

	$result = mysql_query($sql);
	//echo $result;
	
	if(mysql_num_rows($result) != 0) {
		$flag = true;
		while($row = mysql_fetch_array($result)) {
			echo "<div class='peoplelist'>
					<p><a href='viewpeople.php?pid=".$row['uid']."'>".$row['nickname']."</a></p>
					<p>学校：".$row['school']."</p>
				  </div>";
		}
	}
	//search for non-friend
	$sql = "SELECT distinct user_info.uid, user_info.nickname, user_info.school
			FROM user_info, user_relation
			WHERE user_info.nickname = '$searchpeople'
				  and ($uid, user_info.uid) NOT IN (select * from user_relation);";
	$result = mysql_query($sql);
	if(mysql_num_rows($result) != 0) {
		$flag = true;
		while($row = mysql_fetch_array($result)){
			echo "<div class='peoplelist'>
					<p><a href='viewpeople.php?pid=".$row['uid']."'>".$row['nickname']."</a></p>
					<p>学校：".$row['school']."</p>
					<a href='#' onclick='addfriend(".$row['uid'].")'>添加好友</a>
				  </div>";
		}
	}
	//disconnect to mysql
	mysql_close($con);
	
	if(!$flag) {
		echo "<div class='peoplelist'>查找用户不存在</div>";
	}
	echo "</div>";
?>
</div>
</body>
</html>