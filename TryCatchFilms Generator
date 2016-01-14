var pickGenre, pickTrait, pickSubGenre, pickJob, pickTheCatch, pickTraitTwo, task, theCatchString, randomWord, questProp, pickProp, rTask, rCatchString;

var genre = 	
		["horror/thriller", 

		"sitcom", 

		"pirate film", 

		"science fiction adventure", 

		"romantic comedy",

		"dark comedy", 

		"action film",

		"goofy comedy",

		"heist film",
		
		"drama"];
		
var randomWordArray = [
		"that sounds impossible but I will try",
		"pan-fried dumplings",
		"that would not be the first time",
		"I really need that pogo stick",
		"my mother would not approve of this",
		"you would not believe how thirsty I am right now"

				];
				
				
var prop = 	
		[
		"a cell phone",

		"a traffic cone",

		"a pillow",

		"a credit card",

		"a banana",

		"a time machine",

		"a boat of some sort",

		"a dog",

		"a fanny pack",

		"a tennis racket",

		"a bicycle",

		"a cigarette",

		"an umbrella",

		"a sword",

		"a gun",
		
		"an electric guitar",
		
		"something Star Wars related"
		];



var subGenre = 	
		["a modern",
		
		"a post-apocaplypic",
		
		"a musical",

		"a film-noir style",

		"a black & white,",

		"a found-footage style",

		"a documentary style",
		
		"a femme-fatalle"];


var trait = 	
		["a geeky",

		"an over-compensating",

		"a timid",

		"a grammar-obsessed",

		"an indecisive",

		"a loud spoken",

		"an obsessive-compulsive",

		"a socially awkward",

		"a rude",

		"an emotionless",

		"an overly sensitive",

		"a forgetful",
		
		"a narcissistic",
		
		"a jock stereotype",
		
		"a spoiled",
		
		"a lazy",
		
		"a determined",
		
		"a struggling"];
		
var traitTwo =	
		["afraid of heights",

		"on an epic quest to find [questProp]",
		
		"one week away from retirement",
		
		"from another dimension",
		
		"just getting out of a messy divorce",
		
		"obsessed with 90's pop culture",
		
		"hiding a secret",
		
		"seaching for their purpose in life",
		
		"on the brink of a great discovery"];



var job = 	
		["police officer",

		"barrista",

		"singer",

		"news anchor",

		"taxi/limo driver",

		"realtor",

		"pilot",

		"astronaut",

		"engineer",

		"film producer",

		"surgeon",

		"chemist",

		"comedian",

		"author",

		"superhero",
		
		"b-list actor",
		
		"elderly person",
		
		"military personnel",
		
		"small business owner"];
		
var theCatch = 
		[
		"it must be shot entirely on a smart phone",
		"it must be shot in a single take (or at least edited to appear as such)",
		"it must have a V.O. narration",
		"the film must start with the final scene",
		"the cast must be entirely female",
		"you must use the word/phrase '[phraseToUse]' in your dialogue",
		"it must have at least 1 slow motion sequence",
		"your main character must have a catch phrase",
		"it must have a chase sequence"
		];		






function replaceData(){
	rTask = task.replace("[questProp]", prop[pickQuestProp()]);
	rCatchString = theCatchString.replace("[phraseToUse]", randomWordArray[pickRandomWord()]);
}

function pickQuestProp(){
	return parseInt(Math.random() * prop.length);
}

function pickRandomWord(){
	return parseInt(Math.random() * randomWordArray.length);
}

function generateData(){
	
	pickGenre = Math.floor(Math.random() * genre.length);
	pickTrait  = Math.floor(Math.random() * trait.length);
	pickSubGenre  = Math.floor(Math.random() * subGenre.length);
	pickJob  = Math.floor(Math.random() * job.length);
	pickTheCatch  = Math.floor(Math.random() * theCatch.length);
	pickTraitTwo = Math.floor(Math.random() * traitTwo.length);
	pickProp = Math.floor(Math.random() * prop.length);
	
	//start the strings
	task  = "Try to write ";
	theCatchString  = "The catch is, ";
	
	

	//build the strings based on random data
	task += subGenre[pickSubGenre] + " ";
	task += genre[pickGenre] + " starring ";
	task += trait[pickTrait] + " " + job[pickJob] + " who is " + traitTwo[pickTraitTwo] + ".";
	theCatchString += theCatch[pickTheCatch] + " and your film must include " + prop[pickProp] + ".";
	
	//replace string tags
	replaceData();
	
	//put those strings in our document
	document.getElementById("task").innerHTML = rTask;
	document.getElementById("catch").innerHTML = rCatchString;
	document.getElementById("assignment").value = rTask + " " + rCatchString;
	
	//after the data is in place, clear it so we can refresh
	task = "";
	theCatchString = "";
	rTask = "";
	rCatchString = "";
	
	}

function hideButton(button){
	document.getElementById(button).style.display="none";
}

function showDiv(divName){
	document.getElementById(divName).classList.remove('hiddendiv');
}

function hideDiv(divName){
	document.getElementById(divName).classList.add('hiddendiv');
}
