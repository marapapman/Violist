<?php
	function insertHistory($uid, $pid) {
		$datetime = date('Y-m-d H:i:s');
		$sql = "SELECT * from visit_history where user1=$uid AND user2=$pid";
		$result = mysql_query($sql);
		if(mysql_num_rows($result) == 0) {
			$sql = "INSERT into visit_history values ($uid, $pid, '$datetime')";
			$result = mysql_query($sql);
		}
		else {
			$sql = "UPDATE visit_history SET time='$datetime' WHERE user1=$uid AND user2=$pid";
			$result = mysql_query($sql);
		}
		if (!$result) echo "访问历史添加失败".mysql_error();
	}

	function getHistory($pid) {
		echo "<div class='feedlist'>";
		echo "<div class='inner'>";
		echo "来访记录：";
		$sql = "SELECT * FROM visit_history WHERE user2=$pid order by time desc";
		$result = mysql_query($sql);
		if(mysql_num_rows($result) != 0) {
			$count = 0;
			while($row = mysql_fetch_array($result)) {
				if ($count < 5) {
					$row_uid = $row['user1'];
					$sql = "SELECT nickname FROM user_info WHERE uid=$row_uid";
					$temp_result = mysql_query($sql);
					$temp_row = mysql_fetch_array($temp_result);
					echo "<a href='viewpeople.php?pid=".$row_uid."' title='".$row['time']."' style='cursor:pointer'>".$temp_row['nickname']."</a>"."&nbsp&nbsp";
					$count++;
				}
				else break;
			}
		}
		echo "</div>";
		echo "</div>";
	}
?>