
<?php

$number = 1234.56;

// english notation (default)
$english_format_number = number_format($number);
// 1,235

echo $english_format_number;

$number = 1234.5678;

// english notation without thousands separator
$english_format_number = number_format($number, 2, '.', '');
// 1234.57

?>

