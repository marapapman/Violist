<?php
function b() {
    echo 'I am b';
        
}
function a() {
    if ( ! function_exists('b') ) {
        b();
    }
    echo 'I am a';
}
a();
a();
?>
