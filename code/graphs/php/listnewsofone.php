<?php
include("header.php");
include("DBConnect.php");

$flag = false;
$sql = "select text_base.tid, text_base.content, text_base.time
		from text_base
		where text_base.author=$pid and text_base.type = 0
		order by text_base.time desc;";
//echo $sql;
$result = mysql_query($sql);

if(mysql_num_rows($result) != 0) {
	$flag = true;
	while($row = mysql_fetch_array($result)) {
		echo "<div class='feedlist'><div class='inner'>
				<a href='viewpeople.php?pid=".$pid."'>".$pnickname."</a>: ".$row['content'].
				"<div>".$row['time']."</div></div>";
			
		$sql_temp = "select distinct user_info.uid, user_info.nickname, text_base.time, text_base.content
		from comment, text_base, user_info
		where (comment.owner_text = ".$row['tid']." and comment.tid = text_base.tid and text_base.author = user_info.uid)
		order by text_base.time asc;";
		$result_temp = mysql_query($sql_temp);
		
		echo "<ul>";
		/************************* 评论显示 *************************/
		while($row_temp = mysql_fetch_array($result_temp)) {
			echo "<li>
					<a href='viewpeople.php?pid=".$row_temp['uid']."'>".$row_temp['nickname']."</a>: ".$row_temp['content'].
					"<div>".$row_temp['time']."</div></li>";
		}
		
		echo "<li id='litail'>";
		/************************* 评论输入 *************************/
		echo "<form action='viewpeople.php' method='get' onSubmit='return addComment(".$row['tid'].")'>
				<input type='text' class='input-text-thin' name='comment_content".$row['tid']."' />";	//评论输入框
		echo "<input type='hidden' value= ".$pid." id='comment_owner".$row['tid']."'/>";
		echo "<input type='submit' value='回复' id='comment_button".$row['tid']."' class='input-button-thin'/>";	//回复按钮
		echo "<span id='comment_inform".$row['tid']."'></span>";	//提示信息
		echo "</form></li></ul></div>";
	}
}
if(!$flag) {
	if($pid == $uid) echo "<div class='feedlist'><div class='inner'>我还没有发布任何新鲜事</div></div>";
	else echo "<div class='feedlist'><div class='inner'>Ta还没有发布任何新鲜事</div></div>";
}
?>