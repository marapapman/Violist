<?php
echo "<pre>";//without <pre> you cann't see desired output in your browser
echo chop("   Ramki   ");//right spaces are eliminated
echo chop("Ramkrishna", "a..z");
echo "</pre>";
?>
/*output
  ------
  RamkiR*/
