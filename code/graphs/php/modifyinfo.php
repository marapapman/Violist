<?php include("header.php") ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>修改我的信息</title>
	<link rel="stylesheet" href="css/general.css" />
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/general.js"></script>
	<script type="text/javascript">
	function reload() {
		location.reload();
	}
	function checkpassword() {
		if($("input[name='password']").val()=="") {
			$("#password").html("密码不能为空").show();
			return false;
		} else $("#password").html("").show();
		if($("input[name='password']").val()!= $("input[name='password2']").val()) {
			$("#password2").html("输入密码不一致").show();
			return false;
		} else $("#password2").html("").show();
		return true;
	}
	function checknickname() {
		if($("input[name='nickname']").val()=="") {
			$("#nickname").html("姓名不能为空").show();
			return false;
		} else $("#nickname").html("").show();
		return true;
	}
	$(function(){
		$(document).ready(function(){
			$("input[name='password']").bind({focus:checkpassword, blur:checkpassword});
			$("input[name='password2']").bind({focus:checkpassword, blur:checkpassword});
			$("input[name='nickname']").bind({focus:checknickname, blur:checknickname});
		});
		$("#submit_button").click(function(){
			//check password
			if(!checkpassword()) return false;
			//check nickname
			if(!checknickname()) return false;			
			//post
			$.post("updateinfo.php",{
				'newpassword':$("input[name='password']").val(),
				'newnickname':$("input[name='nickname']").val(),
				'newschool':$("input[name='school']").val(),
			},function (data){
				if(data == "success"){
					$("#submit").html("修改成功").fadeIn("slow, 1000").delay(5000).fadeOut("slow,1000");
					setTimeout("reload()", 1000);
				} else {
					$("#submit").html(data).fadeIn("slow, 1000").delay(5000).fadeOut("slow,1000");
				}
			});
			return false;
		});
	});
	</script>
</head>
<body>
<div id="pagebody">
<?php
	include("column.php");
	include("DBConnect.php");
	$sql = "SELECT * FROM user_info WHERE user_info.uid = $uid";
	$result = mysql_query($sql);
	if(mysql_num_rows($result) != 0) {
		while($row = mysql_fetch_array($result)) {
			$password = $row['password'];
			$nickname = $row['nickname'];
			$school = $row['school'];
		}
	}
	$_SESSION['nickname'] = $nickname;
	//disconnect to mysql
	mysql_close($con);
?>
	<h1>修改我的信息</h1>
<div class="feedlist"><div class="inner">
	<form style="margin-left: 90px;" action="modifyinfo.php" method="post">
	<table>
		<tr>
			<td><div class="input-text">
				密码 <?php echo "<input type='password' name='password' value='".$password."'/>";?>
			</div></td>
			<td id="password"></td>
		</tr>
		<tr>
			<td><div class="input-text">
				确认密码 <?php echo "<input type='password' name='password2' value='".$password."'/>"; ?>
			</div></td>
			<td id="password2"></td>
		</tr>
		<tr>
			<td><div class="input-text">
				姓名 <?php echo "<input type='text' name='nickname' value='".$nickname."'/>"; ?>
			</div></td>
			<td id="nickname"></td>
		</tr>
		<tr>
			<td><div class="input-text">
				学校 <?php echo "<input type='text' name='school' value='".$school."'/>"; ?>
			</div></td>
			<td></td>
		</tr>
		<tr>
			<td style="text-align:center;">
				<input type="submit" value="修改" id="submit_button" class="input-button"/>
				<input type="reset" value="重置" id="reset_button" class="input-button" />
			</td>
			<td><span id="submit"></span></td>
		</tr>
	</table>
	</form>
</div></div>
</div>
</body>
</html>