<?php
$arr = array(1, 2, 3);
foreach($arr as $number) {
  if($number == 2) {
    continue;
  }
  print $number;
}
?>
