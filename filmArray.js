var films = 	
		[
// PASTE NEW FILMS BELOW
//REMEMBER TO INCLUDE COMMAS BETWEEN SUBMISSIONS!!
//************************************


//************************************
//DO NOT MESS WITH ANYTHING BELOW THIS LINE
		];//end films
		
var bodyText = "", producer = "", productionCompany = "", productionContact = "", assignment = "", embed = "";


function displayFilms(){
	if(films.length == 0){
		bodyText = "<p>There are currently no live films. Be the first! Use the link above to sumbit your films!</p>";
	} else {
		for(var i = 0; i<films.length; i++){
			producer = films[i][0];
			productionCompany = films[i][1];
			productionContact = films[i][2];
			assignment = films[i][3];
			embed = films[i][4];
			
			bodyText += "<p>" + producer + "<br>" + 
			productionCompany + "<br>" + embed + "<br>" + assignment + "</p>";
		}//for
	}//else
	document.getElementById("filmArray").innerHTML = bodyText;
}//displayFilms
