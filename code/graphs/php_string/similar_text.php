
<?php
$var_1 = 'PHP IS GREAT';
$var_2 = 'WITH MYSQL';

similar_text($var_1, $var_2, $percent);

echo $percent;
// 27.272727272727

similar_text($var_2, $var_1, $percent);

echo $percent;
// 18.181818181818
?>
