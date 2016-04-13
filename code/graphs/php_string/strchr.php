
<?php
$email  = 'name@example.com';
$domain = strchr($email, '@');
echo $domain; // prints @example.com

$user = strchr($email, '@', true); // As of PHP 5.3.0
echo $user; // prints name
?>

