

var fs = require('fs');
var map={}
if(process.argv[2])
{
	//console.log(process.argv[3])
	var databasestring=fs.readFileSync(process.argv[2])
	map=JSON.parse(databasestring);
	var entry=map[process.argv[3].trim()]
	for(var key in map)
	{
		var flag= (key == process.argv[3]);
		console.log(key+" "+flag)
	}
}

