<?php 
	include("header.php"); 
	include("DBConnect.php");

	$sql = "select * from user_info where uid=$pid";
	$result = mysql_query($sql);
	if(mysql_num_rows($result) == 0) {
		echo "用户不存在";
		return;
	}
	while($row = mysql_fetch_array($result)) {
		$pnickname = $row['nickname'];
		$pschool = $row['school'];
	}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title><?php if($pid == $uid) echo "我"; else echo $pnickname; ?>的主页</title>
	<link rel="stylesheet" href="css/general.css" />
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/general.js"></script>
	<script type="text/javascript">
	function listnewsofone() {
	/*
		alert($("#h_pid").val());
		alert($("#h_pnickname").val());
		alert($("#h_pschool").val());
		*/
		$.get("listnewsofone.php", {
			'pid':$("#h_pid").val(), 
			'pnickname':$("#h_pnickname").val(), 
			'pschool':$("#h_pschool").val()
			}, 
			function (data) {
			$("#newslistofone").html(data).fadeIn("slow,1000");
		});
	}
	function addComment(tid) {
	    var str_comment_button = "#commemt_button" + tid;
		var str_comment_content = "input[name='comment_content" + tid + "']";
		var str_comment_inform = "#comment_inform" + tid;
		var str_comment_owner = "#comment_owner" + tid;
		
		/*
		alert(str_comment_button); 
		alert(str_comment_content); 
		alert(str_comment_inform); 
		alert(str_comment_owner); 
		*/
		
		if($(str_comment_content).val() == "")
			$(str_comment_inform).html("评论不能为空").fadeIn("slow,1000").delay(1000).fadeOut("slow,1000");
		else {
			//alert(tid); 
			
			$(str_comment_inform).html("").show();
			$(str_comment_button).val("请稍候..");
			$(str_comment_button).attr("disabled", true);
			
			/*
			alert("comment.php");
			alert($(str_comment_content).val());
			alert(tid);
			alert($(str_comment_owner).val());
			alert($(str_comment_content).val());
			alert($(str_comment_owner).val());
			*/
			
			$.get("comment.php", { 
				'content':$(str_comment_content).val(),
				'owner_text':tid,
				'owner':$(str_comment_owner).val()
			}, function (data) {
				$(str_comment_button).attr("disabled", false);
				$(str_comment_button).val("回复");
				if (data == "success") {
					$(str_comment_inform).html("评论成功").fadeIn("slow,1000").delay(1000).fadeOut("slow,1000");
					$(str_comment_content).val("");
					listnewsofone();
				} else {
					$(str_comment_inform).html(data).fadeIn("slow,1000").delay(1000).fadeOut("slow,1000");
				}
			});
		}
		return false;
	}
	$(function() {
		$(document).ready(function(){
			//alert("fuck");
			listnewsofone();
		});
		/*
		$("#search_button").click(function() {
			if($("input[name='searchpeople']").val() == "") {
				$("#searchpeople").html("请输入姓名").fadeIn("slow,1000").delay(1000).fadeOut("slow,1000");
				return false;
			}
			else {
				$("#searchpeople").html("").show();
				return true;
			}
		});
		*/
		$("#leavemessage_button").click(function() {
			if($("input[name='leavemessage_content']").val() == "")
				$("#leavemessage_inform").html("留言不能为空").fadeIn("slow,1000").delay(1000).fadeOut("slow,1000");
			else {
				$("#leavemessage_inform").html("").show();
				$("#leavemessage_button").val("请稍候..");
				$("#leavemessage_button").attr("disabled", true);
				$.get("leavemessage.php", { 
					'leavemessage_content':$("input[name='leavemessage_content']").val(),
					'pid':$("#h_pid").val()
				}, function (data) {
					$("#leavemessage_button").attr("disabled", false);
					$("#leavemessage_button").val("留言");
					if (data == "success") {
						$("#leavemessage_inform").html("留言成功").fadeIn("slow,1000").delay(1000).fadeOut("slow,1000");
						$("input[name='leavemessage_content']").val("");
					} else {
						$("#leavemessage_inform").html(data).fadeIn("slow,1000").delay(1000).fadeOut("slow,1000");
					}
				});
			}
			return false;
		});

	});
	</script>
</head>
<body>
<div id="pagebody">
<?php
	include("column.php");

	echo "<h1>";
	if($pid == $uid) echo "我"; else echo $pnickname;
	echo "的主页</h1>";

	echo "<div class='graycontent'>姓名：".$pnickname."<br/>";
	if($pschool != null) echo "学校：".$pschool;
	echo "</div>";
?>
<?php
	include("visit_history.php");
	insertHistory($uid, $pid);
	getHistory($pid);

	if($pid != $uid)	//浏览别人的主页，显示留言栏
	{
		echo "<form action='leavemessage.php' method='post'>";
		/************************* 留言栏 *************************/
		echo "<div style='margin-top:20px'>";
		echo "<div class='input-text' style='display:inline;'><input type='text' name='leavemessage_content' /></div>";
		echo "<input type='submit' value='留言' id='leavemessage_button' class='input-button'/>";
		echo "</div>";
	}
	echo "<input type='hidden' value=$pid id='h_pid' />";
	echo "<input type='hidden' value='$pnickname' id='h_pnickname' />";
	echo "<input type='hidden' value='$pschool' id='h_pschool' />";
	if($pid != $uid) {
		echo "<span id='leavemessage_inform'></span></form>";
	}
	else
	{
		include("viewmessage.php");
	}
?>
<div id="newslistofone"></div>
</div>
</body>
</html>