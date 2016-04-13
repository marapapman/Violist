<?php include("header.php") ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Renren - <?php echo $nickname; ?></title>
	<link rel="stylesheet" href="css/general.css" />
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/general.js"></script>
	<script type="text/javascript">
	function listnews() {
		$.get("listallnews.php", function (data) {
			$("#newslist").html(data).fadeIn("slow,1000");
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
		{
			//$(str_comment_button).val("请稍候..");
			//$(str_comment_button).attr("disabled", true);
			$(str_comment_inform).html("评论不能为空").fadeIn("slow,1000").delay(1000).fadeOut("slow,1000");	
		}
		else {
			//alert("else"); 
			
			$(str_comment_inform).html("").show();
			$(str_comment_button).val("请稍候..");
			$(str_comment_button).attr("disabled", true);
			
			/*
			alert("comment.php");
			alert($(str_comment_content).val());
			alert(tid);
			alert($(str_comment_owner).val());
			*/
			
			$.get("comment.php", { 
				'content':$(str_comment_content).val(),
				'owner_text':tid,
				'owner':$(str_comment_owner).val(), 
			}, function (data) {
				$(str_comment_button).attr("disabled", false);
				$(str_comment_button).val("回复");
				if (data == "success") {
					$(str_comment_inform).html("评论成功").fadeIn("slow,1000").delay(1000).fadeOut("slow,1000");
					$(str_comment_content).val("");
					listnews();
				} else {
					$(str_comment_inform).html(data).fadeIn("slow,1000").delay(1000).fadeOut("slow,1000");
				}
			});
		}
		
		return false;
	}
	$(function() {
		$(document).ready(function(){
			listnews();
		});
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
		$("#publish_button").click(function() {
			if($("input[name='newsfeed']").val() == "")
				$("#publish").html("新鲜事不能为空").fadeIn("slow,1000").delay(1000).fadeOut("slow,1000");
			else {
				$("#publish").html("").show();
				$("#publish_button").val("请稍候..");
				$("#publish_button").attr("disabled", true);
				$.get("publish.php", { 
					'newsfeed':$("input[name='newsfeed']").val(),
				}, function (data) {
					$("#publish_button").attr("disabled", false);
					$("#publish_button").val("发布新鲜事");
					if (data == "success") {
						$("#publish").html("发布成功").fadeIn("slow,1000").delay(1000).fadeOut("slow,1000");
						$("input[name='newsfeed']").val("");
						listnews();
					} else {
						$("#publish").html(data).fadeIn("slow,1000");
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
	echo "<h1>Hello! ".$nickname."</h1>";
?>
	<form action="search.php" method="get">
		<div class="input-text" style="display:inline;"><input type="text" name="searchpeople" /></div>
		<input type="submit" value="找人" id="search_button" class="input-button"/>
		<span id="searchpeople"></span>
	</form>
	<form action="publish.php" method="post">
		<div class="input-text" style="display:inline;"><input type="text" name="newsfeed" /></div>
		<input type="submit" value="发布新鲜事" id="publish_button" class="input-button"/>
		<span id="publish"></span>
	</form>
	<div id="newslist"></div>
</div>
</body>
</html>