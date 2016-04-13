<?php
	//显示所有留言
	$sql = "select distinct user_info.uid, user_info.nickname, text_base.TID, text_base.author, 
				   text_base.time, text_base.type, text_base.content
			from text_base, user_info, message 
			where text_base.type = 2 and message.owner = $uid and message.tid = text_base.tid and text_base.author = user_info.uid
			order by text_base.time desc;";		//给本人的所有留言
	$result = mysql_query($sql);
	if(mysql_num_rows($result) != 0) {
		echo "<div class='feedlist'>
				<div style='padding:5px 0px 5px 20px'>
					<h2>留言</h2>
				</div>";
		while($row = mysql_fetch_array($result)) {
			echo "<div class='peoplelist'><a href='viewpeople.php?pid=".$row['uid']."'>".$row['nickname']."</a>: ".$row['content'].
					"<div>".$row['time']."</div></div>";
		}
		echo "</div>";
	}
?>