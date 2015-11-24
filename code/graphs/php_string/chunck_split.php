<?php
$string = '1234';
$a = substr(chunk_split($string, 2, ':'), 0, -1);
// will return 12:34
echo $a;
?>
