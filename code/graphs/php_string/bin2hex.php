<?php
// Convert a hex-string to binary-string (the way back from bin2hex)

function hex2bin($h)
  {
  if (!is_string($h)) return null;
  $r='';
  for ($a=0; $a<strlen($h); $a+=2) { $r.=chr(hexdec($h{$a}.$h{($a+1)})); }
  return $r;
  }

echo bin2hex('Hello'); // result: 48656c6c6f
echo hex2bin('48656c6c6f'); // result: Hello
?>
