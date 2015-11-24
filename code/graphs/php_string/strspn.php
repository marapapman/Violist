
<?php
// subject does not start with any characters from mask
var_dump(strspn("foo", "o"));

// examine two characters from subject starting at offset 1
var_dump(strspn("foo", "o", 1, 2));

// examine one character from subject starting at offset 1
var_dump(strspn("foo", "o", 1, 1));
?>

