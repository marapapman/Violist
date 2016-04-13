var singletonCal;

function handleSelect(type, args, calTxt) {
    var dates = args[0]; var date = dates[0];
    var year = date[0], month = date[1], day = date[2];
    txtField = document.getElementById(calTxt[1]);
    txtField.value = year + '-' + month + '-' + day;
    calShowing = false;
    calTxt[0].hide();
    singletonCal = null;
}

function showCalendar(cal, txtId) {
    if(singletonCal) {                        
        singletonCal.hide();
        if(singletonCal == cal) {
            singletonCal = null;
            return;
        }
    }
    txtField = document.getElementById(txtId);                    
    if(txtField.value) {                        
        var a = txtField.value.split('-'); 
        var date = new Date(a[0], a[1] - 1, a[2]);                        
        if(date.valueOf() + '' == 'NaN') {
            return;
        }
        cal.select(date);
        cal.cfg.setProperty('pagedate', a[1] + '/' + a[0]);
    }
    cal.render();                    
    cal.show();
    singletonCal = cal;
}