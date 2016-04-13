<?php
  $email = 'USER@EXAMPLE.com';
  echo stristr($email, 'e'); // outputs ER@EXAMPLE.com
  echo stristr($email, 'e', true); // As of PHP 5.3.0, outputs US
?>
