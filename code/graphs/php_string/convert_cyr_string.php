<?php
function convert_cyr_array($array,$from,$to){
    foreach($array as $key=>$value){
        if(is_array($value)) {
            $result[$key] = convert_cyr_array($value,$from,$to);
            continue;
        }
        $result[$key] = convert_cyr_string($value,$from,$to);
    }
    return $result;
}
$array[0] = "сВМПЛП";
$array[1] = "зТХЫБ";
$array[2] = array("пЗХТЕГ","рПНЙДПТ");
$array[3] = array(
                     array("бРЕМШУЙО","нБОДБТЙО"),
                     array("бВТЙЛПУ","рЕТУЙЛ")
                );

$result = convert_cyr_array($array,"k","w");
echo $result;
?>
