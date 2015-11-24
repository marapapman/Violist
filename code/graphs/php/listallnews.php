<?php
include("header.php");
include("DBConnect.php");



$sql = "select distinct user_info.uid, user_info.nickname, text_base.TID, text_base.author, 
			   text_base.time, text_base.type, text_base.content
		from text_base, user_info, user_relation 
		where ((user_relation.user1 = $uid and user_relation.user2 = user_info.uid and user_relation.user2 = text_base.author) or
			  (text_base.author = $uid and user_info.uid = $uid)) and text_base.type = 0
		order by text_base.time desc;";		//本人及好友的新鲜事
$result = mysql_query($sql);

if(mysql_num_rows($result) != 0) {
	while($row = mysql_fetch_array($result)) {
		echo "<div class='feedlist'>";
		echo "<div class='inner'>
				<a href='viewpeople.php?pid=".$row['uid']."'>".$row['nickname']."</a>: ".$row['content'].
				"<div>".$row['time']."</div></div>";
				
		$sql_temp = "select distinct user_info.uid, user_info.nickname, text_base.time, text_base.content
		from comment, text_base, user_info
		where (comment.owner_text = ".$row['TID']." and comment.TID = text_base.TID and text_base.author = user_info.uid)
		order by text_base.time asc;";
		$result_temp = mysql_query($sql_temp);
		
		echo "<ul>";
		/************************* 评论显示 *************************/
		while($row_temp = mysql_fetch_array($result_temp)) {
			echo "<li>";
			echo "<a href='viewpeople.php?pid=".$row_temp['uid']."'>".$row_temp['nickname']."</a>: ".$row_temp['content'].
					"<div>".$row_temp['time']."</div>";
			echo "</li>";
			
		}
		
		echo "<li id='litail'>";
		/************************* 评论输入 *************************/
		echo "<form action='mainpage.php' method='get' onSubmit='return addComment(".$row['TID'].")'>
				<input type='text' class='input-text-thin' name='comment_content".$row['TID']."' />";	//评论输入框
        echo "<input type='hidden' value= ".$row['uid']." id='comment_owner".$row['TID']."'/>";
		echo "<input type='submit' value='回复' id='comment_button".$row['TID']."' class='input-button-thin'/>";	//回复按钮
		echo "<span id='comment_inform".$row['TID']."'></span>";	//提示信息
		echo "</form>";
		echo "</li>";
		echo "</ul>";
		echo "</div>";
	}
}
?>