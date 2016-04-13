<?php
$csv_data = "test,this,thing
             hi,there,this
             is,cool,dude
             have,fun";
$lines = explode("\n", $csv_data);
$formatting = explode(",", $lines[0]);
unset($lines[0]);
$results = array();
foreach ( $lines as $line ) {
   $parsedLine = str_getcsv( $line, ',' );
   $result = array();
   foreach ( $formatting as $index => $caption ) {
      if(isset($parsedLine[$index])) {
         $result[$formatting[$index]] = trim($parsedLine[$index]);
      } else {
         $result[$formatting[$index]] = '';
      }
   }
   $results[] = $result;
}
?>
