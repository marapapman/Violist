<?php
setlocale (LC_COLLATE, 'NL');
echo strcoll("Hello World!","Hello World!");
echo "<br>";

setlocale (LC_COLLATE, 'en_US');
echo strcoll("Hello World!","Hello World!");
?>
