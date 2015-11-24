<?php 
	session_start();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Renren - WebService</title>
	<link rel="stylesheet" href="css/general.css" />
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/general.js"></script>
</head>
<body>
<div id="pagebody">
	<h1 style="display:inline;">Renren Service</h1>
	<a style="margin-left: 250px;" href="index.html">返回首页</a>
	
	<div id='header'>
		<h3 style='margin:10px'>SDK下载</h3>
		<ul>
			<li><a href="download/HttpClient.class.txt">HttpClient.class.php</a></li>
			<li><a href='download/renrenLoginButton.txt'>renrenLoginButton.html</a></li>
			<li><a href='download/renrenShareButton.txt'>renrenShareButton.html</a></li>
			<li><a href='download/renrenGetCurUserInfo.txt'>renrenGetCurUserInfo.php</a></li>
			<li id='litail'><a href='download/config.txt'>config.php</a></li>
		</ul>
	</div>

	<div class="feedlist">
		<div class="inner">
			<h2>renrenLogin</h2>
			<br/>
			
			<h4>利用RenrenSystem的账号信息登录系统</h4>
			<div style="padding-left:10px;">
				<p>输入参数：登录后跳转页面URL</p>
				<p>返回数据：是否登录成功</p>
				<br/>
			</div>
			
			<h4>测试</h4>
			<div style="padding-left:10px;">
				<form action="Service/renrenServiceLoginPage.php" method="POST">
					<table>
					<tr><td>
						<div class="input-text" style="height:30px;padding:1px 10px 1px 10px;">
							登录后跳转 <input type="text" name="redirectPage" value="http://192.168.2.202/RenrenSystem/renrenService.php"/>
						</div></td>
					
						<td style="text-align:left;" rowspan="2">
							<input type="submit" value="用renren账号登录" class="input-button" id="login_submit" style="height:34px;width:150px;padding:1px 10px 1px 10px;background-image:url(image/service_test.png);"/>
						</td>
					</tr>
					</table>
				</form>
				<br/>
			</div>

			<h4>HTTP POST</h4>
			<div style="padding-left:10px;">
				<p>以下是 HTTP POST 请求和响应示例。所显示的<a style="color:#33B5E5;">占位符</a>需替换为实际值</p>
				<p>POST /RenrenSystem/Service/renrenServiceLoginPage.php</p>
				<p>Host: 192.168.2.202</p>
				<br/>
				<p>redirectPage=<a style="color:#33B5E5;">string</a></p>
				<br/>
			</div>
		</div>
	</div>

	<div class="feedlist">
		<div class="inner">
			<h2>renrenShare</h2>
			<br/>
			
			<h4>在登录人人系统后，可以把当前页面的网址分享到自己的新鲜事中。</h4>
			<div style="padding-left:10px;">
				<p>输入参数：分享内容</p>
				<p>返回数据：是否发布成功的消息</p>
				<br/>
			</div>

			<h4>测试</h4>
			<div style="padding-left:10px;">
				<form action="Service/renrenShareFunction.php" method="POST">
					<table>
					<tr><td>
						<div class="input-text" style="height:30px;padding:1px 10px 1px 10px;">
							分享内容 <input type="text" name="cur_uri" value="http://192.168.2.202/RenrenSystem/renrenService.html"/>
						</div></td>
						<td style="text-align:center;" rowspan="2">
							<input type="submit" value="分享到renren" class="input-button" id="login_submit" style="height:34px;width:150px;padding:1px 10px 1px 10px;background-image:url(image/service_test.png);"/>
						</td>
					</tr>
					</table>
				</form>
				<br/>
			</div>

			<h4>HTTP POST</h4>
			<div style="padding-left:10px;">
				<p>以下是 HTTP POST 请求和响应示例。所显示的<a style="color:#33B5E5;">占位符</a>需替换为实际值</p>
				<p>POST /RenrenSystem/Service/renrenShareFunction.php</p>
				<p>Host: 192.168.2.202</p>
				<br/>
				<p>cur_uri=<a style="color:#33B5E5;">string</a></p>
				<br/>
			</div>
		</div>
	</div>

	<div class="feedlist">
		<div class="inner">
			<h2>getCurUserInfo</h2>
			<br/>

			<h4>在登录人人系统后，可以获得当前登录的人人账户的信息。</h4>
			<div style="padding-left:10px;">
				<p>输入参数：无</p>
				<p>返回数据：一个结构体（昵称，学校），封装成xml格式</p>
				<br/>
			</div>

			<h4>测试</h4>
			<div style="padding-left:10px;">
				<form action="Service/renrenGetCurUserInfo.php" method="GET">
					<table>
					<tr>
						<td style="text-align:center;" rowspan="2">
							<input type="submit" value="测试" class="input-button" id="login_submit" style="height:34px;padding:1px 10px 1px 10px;background-image:url(image/service_test.png);"/>
						</td>
					</tr>
					</table>
				</form>
				<br/>
			</div>

			<h4>HTTP GET</h4>
			<div style="padding-left:10px;">
				<p>以下是 HTTP GET 请求和响应示例。</p>
				<p>Get /RenrenSystem/Service/renrenGetCurUserInfo.php</p>
				<p>Host: 192.168.2.202</p>
				<br/>
			</div>
		</div>
	</div>

	<div class="feedlist">
		<div class="inner">
			<h2>getNewsfeed</h2>
			<br/>

			<h4>在登录人人系统后，可以获得当前登录的人人账号的新鲜事。</h4>
			<div style="padding-left:10px;">
				<p>输入参数：无</p>
				<p>返回数据：一个结构体（TID, content, author, time）数组，封装成xml格式</p>
				<br/>
			</div>

			<h4>测试</h4>
			<div style="padding-left:10px;">
				<form action="Service/renrenGetNewsfeed.php" method="GET">
					<table>
					<tr>
						<td style="text-align:center;" rowspan="2">
							<input type="submit" value="测试" class="input-button" id="login_submit" style="height:34px;padding:1px 10px 1px 10px;background-image:url(image/service_test.png);"/>
						</td>
					</tr>
					</table>
				</form>
				<br/>
			</div>

			<h4>HTTP GET</h4>
			<div style="padding-left:10px;">
				<p>以下是 HTTP GET 请求和响应示例。</p>
				<p>Get /RenrenSystem/Service/renrenGetNewsfeed.php</p>
				<p>Host: 192.168.2.202</p>
				<br/>
			</div>
		</div>
	</div>

	
</div>
</body>
</html>