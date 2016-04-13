<?php

	include("DBConnect.php");

	$email = $_POST['email'];
	$password = $_POST['password'];
	$nickname = $_POST['nickname'];
	$school = $_POST['school'];

	$sql = "select * from user_info where name='$email'";
	$result = mysql_query($sql);
	if(mysql_num_rows($result) != 0) {
		echo "user exist";
		mysql_close($con);
		return;
	}
	$sql = "insert into user_info(name, password, isAdmin, nickname, school)
						values('$email', '$password', 0, '$nickname', '$school')";
	$result = mysql_query($sql);

	if(!$result) echo "注册失败:".mysql_error();
	else {
		session_start();
		$sql = "select * from user_info where name='$email'";
		$result = mysql_query($sql);
		while($row = mysql_fetch_array($result)){
			$_SESSION['uid'] = $row['UID'];
			$_SESSION['email'] = $row['name'];
			$_SESSION['nickname'] = $row['nickname'];
		}
		//disconnect to mysql
		mysql_close($con);
		echo "success";
	}
?>