

var fs = require('fs');
var map={}
if(process.argv[2])
{
	var databasestring=fs.readFileSync(process.argv[2])
	map=JSON.parse(databasestring);
	for(var key in map)
	{
		console.log(key+" "+Object.keys(map[key]).length);
	}
}

