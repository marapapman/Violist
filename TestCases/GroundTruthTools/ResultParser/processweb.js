var LineByLineReader = require('line-by-line'),
 lr = new LineByLineReader(process.argv[2]);

var fs = require('fs');
var map={}
var oldmap={};
var fd=fs.openSync("./database.txt",'r+');
var delta=0;
var unit=""
lr.on('line', function (line) {

    // 'line' contains the current line without the trailing newline character.
	if(line.match(/\d+\*.*/))
	{

			var all=line.split("*")
			if(map[all[0]])
			{
				map[all[0]][all[1]]=1;
			}
			else{
				map[all[0]]={}
				map[all[0]][all[1]]=1;
			}


		


	}

});

lr.on('end', function () {
     console.log("finished: the delta is "+delta)
     var jsonstring=JSON.stringify(map)
     fs.writeFileSync("./database.txt",jsonstring);
    // All lines are read, file is closed now.
});
