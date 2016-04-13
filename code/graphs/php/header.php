<?php
session_start();
if(!isset($_SESSION['uid'])) {
	header("Location:index.html");
	exit();
}
$uid = $_SESSION['uid'];
$nickname = $_SESSION['nickname'];
?>