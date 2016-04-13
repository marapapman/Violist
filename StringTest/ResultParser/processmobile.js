var LineByLineReader = require('line-by-line'),
 lr = new LineByLineReader(process.argv[2]);

var fs = require('fs');
var map={}
var oldmap={};
if(process.argv[3])
{
	var databasestring=fs.readFileSync(process.argv[3])
	map=JSON.parse(databasestring);
	oldmap=JSON.parse(databasestring);
}
var fd=fs.openSync(process.argv[3],'r+');
var delta=0;
lr.on('line', function (line) {
    // 'line' contains the current line without the trailing newline character.
	var list=line.match(/.*([0-9]+)\*(.*)/);
	if(list)
	{
		//console.log(line)
		if(!map.hasOwnProperty([list[1]]))
		{
			map[list[1]]={};
		}
		if(!map[list[1]].hasOwnProperty([list[2]]))
		{
			delta++;
		}
		map[list[1]][list[2]]=1;
		//console.log(list[2]);
	}	

});

lr.on('end', function () {
     console.log("finished: the delta is "+delta)
     var jsonstring=JSON.stringify(map)
     fs.writeFileSync("./database.txt",jsonstring);
    // All lines are read, file is closed now.
});
