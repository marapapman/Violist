
<?php
$a = strcspn('abcd',  'apple');
$b = strcspn('abcd',  'banana');
$c = strcspn('hello', 'l');
$d = strcspn('hello', 'world');

var_dump($a);
var_dump($b);
var_dump($c);
var_dump($d);
?>

