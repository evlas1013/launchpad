/**
 * Variable Declarations
 */
var myCanvas = document.getElementById("canvas");
var pen = myCanvas.getContext("2d");
var hSpace = 50;
var vSpace = 50;
var changeColor = "#ff0000";
var fromX = 0;
var fromY = 0;
var toX = 0;
var toY = 0;
var hDist = 0;
var vDist = 0;
var boxType = "";
var mapArray = new Array();
var closedNodes = new Array();
var generationArray = new Array();
var foundEnd = false;
var attempts = 0;


/**
 * Draw a grid based on the size of the canvas and a variable spacing. The spacing could potentially be set by the user. Currently, it is hard coded using the hSpace
 * and vSpace variables set above.
 */

function drawGrid() {



    for (i = 1; i * hSpace < myCanvas.width; i++) {
        pen.beginPath();
        pen.strokeStyle = "#000000";
        pen.moveTo(hSpace * i, 0);
        pen.lineTo(hSpace * i, myCanvas.height);
        pen.stroke();
        pen.closePath();
    }

    for (i = 1; i * vSpace < myCanvas.height; i++) {
        pen.beginPath();
        pen.strokeStyle = "#000000";
        pen.moveTo(0, vSpace * i);
        pen.lineTo(myCanvas.width, vSpace * i);
        pen.stroke();
        pen.closePath();

    }
}

/**
Find out which type of square the user would like to place.
*/

function determineBoxType() {
    var radios = document.getElementsByName("boxType"),
        determineBoxTypeVar = "none";
    for (i = 0; i < radios.length; i++) {

        if (radios[i].checked) {
            determineBoxTypeVar = radios[i].value;
            break;
        } //if

    } //for
    return determineBoxTypeVar;
} //determineBoxType

/**
 * When the user clicks on a part of the grid, round the curser location  to the previous grid intersection and draw a box of the
 * desired color in that grid space. Figure out which color to draw the box based on which radio button is selected
 */

myCanvas.addEventListener("click", function () {
    var xSnap = Math.floor(event.pageX / hSpace) * hSpace,
        ySnap = Math.floor(event.pageY / vSpace) * vSpace,
        colorData = pen.getImageData(event.pageX, event.pageY, 1, 1),
        colorHex = colorData.data[0].toString(16) + colorData.data[1].toString(16) + colorData.data[2].toString(16);
    boxType = determineBoxType();

    switch (boxType) {
    case "from":
        pen.fillStyle = "#FFFFFF";

        pen.fillRect(fromX - hSpace / 2, fromY - vSpace / 2, hSpace, vSpace);
        changeColor = "#00ff00";
        fromX = xSnap + hSpace / 2;
        fromY = ySnap + vSpace / 2;
        pen.fillStyle = changeColor;
        pen.fillRect(xSnap, ySnap, hSpace, vSpace);
        drawGrid();

        break;

    case "to":
        pen.fillStyle = "#FFFFFF";
        pen.fillRect(toX - hSpace / 2, toY - vSpace / 2, hSpace, vSpace);
        changeColor = "#ff0000";
        toX = xSnap + hSpace / 2;
        toY = ySnap + vSpace / 2;
        pen.fillStyle = changeColor;
        pen.fillRect(xSnap, ySnap, hSpace, vSpace);
        drawGrid();

        break;

    case "obstacle":
        changeColor = "#0000ff";
        pen.fillStyle = changeColor;
        pen.fillRect(xSnap, ySnap, hSpace, vSpace);
        drawGrid();

        break;

    case "clear":
        pen.fillStyle = "#FFFFFF";
        pen.fillRect(xSnap, ySnap, hSpace, vSpace);
        drawGrid();
        break;

    default:
        alert("please select a box type");
        changeColor = "#ffffff";
        break;
    }




});

/** 
 * erase the entire canvas
 */

function clearAll() {
    pen.clearRect(0, 0, myCanvas.width, myCanvas.height);
    pen.fillStyle = "#FFFFFF";
    pen.fillRect(0, 0, myCanvas.width, myCanvas.height);
}

/**
 * Format an array to match the same dimensions as the gridlines. This will make viewing them easier during debugging.
 * 
 */
function displayArray(arrayToDisplay) {
    arrayString = "";


    for (rows = 0; rows < arrayToDisplay.length; rows++) {
        arrayString += arrayToDisplay[rows] + "<br>";
    } //for

    return arrayString;


}

/**
 * refresh the array "mapArray" with current grid layout.
 */
function updateMapArray() {
    mapArray = mapToArray();
}



/**
check the pixel color of a certain location and return its value as a single character for use in the arrays
*/
function checkBox(x, y) {
    var boxColor = pen.getImageData(x, y, 1, 1),
        colorData = '(' + boxColor.data[0] + ',' + boxColor.data[1] + ',' + boxColor.data[2] + ')',
        boxValue;

    switch (colorData) {
    case "(255,255,255)":
        boxValue = 0;
        break;

    case "(255,0,0)":
        boxValue = "R";
        break;

    case "(0,255,0)":
        boxValue = "G";
        break;

    case "(0,0,255)":
        boxValue = "B";
        break;


    default:
        boxValue = "!";
        break;
    } //switch

    return boxValue;

} //checkBox


// The next two functions scan the entire grid and build an array of information based on visible box colors
function setCols(row) {
    var gridWidth = myCanvas.width / hSpace,
        gridHeight = myCanvas.height / vSpace,
        colArray = new Array(),
        rowArray = new Array(),
        checkSpot = myCanvas.getContext("2d");

    for (col = 0; col < gridWidth; col++) {
        var centerX = hSpace * col + (hSpace / 2);
        var centerY = vSpace * row + (vSpace / 2);
        colArray[col] = checkBox(centerX, centerY);
        checkSpot.beginPath();
        checkSpot.moveTo(centerX, centerY);
        //checkSpot.arc(centerX, centerY, 5, 0, 2 * Math.PI);
        //checkSpot.stroke();
        checkSpot.closePath();
    } //for cols

    return colArray;
} //setCols

function mapToArray() {
    var rowArray = new Array;
    for (row = 0; row < myCanvas.height / vSpace; row++) {
        rowArray[row] = setCols(row);
    } //for
    return rowArray;
}

//color a grid section a certain color
function shadeCell(x, y, color) {
    pen.beginPath();
    pen.fillStyle = color;
    pen.fillRect(x, y, hSpace, vSpace);

    pen.closePath();
} //shadeCell


//take any grid location, check if its neighbors are obstacles or available for expansion, shade them gray, and update their location in mapArray to show as expanded
function expand(x, y) {
    if (canMoveUp(mapArray, x, y)) {
        shadeCell(y * hSpace, (x - 1) * vSpace, "#CCCCCC");
        mapArray[x - 1][y] = '!';
    } //if UP

    if (canMoveRight(mapArray, x, y)) {
        shadeCell((y + 1) * hSpace, x * vSpace, "#CCCCCC");
        mapArray[x][y + 1] = '!';
    } //if RIGHT

    if (canMoveDown(mapArray, x, y)) {
        shadeCell(y * hSpace, (x + 1) * vSpace, "#CCCCCC");
        mapArray[x + 1][y] = '!';
    } //if DOWN

    if (canMoveLeft(mapArray, x, y)) {
        shadeCell((y - 1) * hSpace, x * vSpace, "#CCCCCC");
        mapArray[x][y - 1] = '!';
    } //if LEFT

} //expand

/* create all of our arrays for mapping, locating the start point, the end point, and all obstacles. Expand once from the start point and then continually until
the red box is found or enough attempts have been made to consider the red box unreachable.

*/
function expandFromGreen() {
    updateMapArray();
    createBlankGenArray();
    updateGenerations();
    closedNodes = findInArray(mapArray, 'G');
    expand(closedNodes[1], closedNodes[0]);
    updateGenerations();
    closedNodes = findInArray(mapArray, '!');

    //document.getElementById("title").innerHTML = displayArray(generationArray);
    //document.getElementById("foundRed").innerHTML = foundEnd;

    expandAgain();
    traceBack();
    drawGrid();
    document.getElementById("randMap").style.display="none";
    document.getElementById("expandFromGreen").style.display="none";
    document.getElementById("clearGrid").style.display="none";
    
}

//after the green box has been located and expanded, continue to expand - searching for the red box.
function expandAgain() {
    while (attempts < mapArray.length * mapArray[0].length && foundEnd == false) {
        for (i = 0; i < closedNodes.length; i += 2) {
            expand(closedNodes[i + 1], closedNodes[i]);

        }
        //updateMapArray();
        updateGenerations();
        closedNodes = findInArray(mapArray, '!');
        //document.getElementById("title").innerHTML = displayArray(generationArray);
        attempts++;
    } //while
    if (attempts >= mapArray.length * mapArray[0].length) {
        alert("No posible route found");
    }
    //document.getElementById("foundRed").innerHTML = foundEnd;
} //expandAgain

//find a specific character in a 2d array
function findInArray(arrayToSearch, query) {
    var location = new Array(),
        nextX = 0,
        nextY = 1;

    for (i = 0; i < arrayToSearch.length; i++) {

        for (j = 0; j < arrayToSearch[i].length; j++) {
            if (arrayToSearch[i][j] == query) {
                location[nextX] = j;
                location[nextY] = i;
                nextX += 2;
                nextY += 2;

            } //if
        } //for j
    } //for i
    return location;
} //findInArray

//this maps the initial array (before any expansions have happened) to set all the locations of Generation 0. From here, each expansion will be tracked and incremented to find out which gridpoints are furthest from the start point.
function createBlankGenArray() {
    for (i = 0; i < mapArray.length; i++) {

        generationArray[i] = mapArray[i];

        for (j = 0; j < mapArray[i].length; j++) {

            generationArray[i][j] = mapArray[i][j];

        } //for j
    } //for i

    //document.getElementById("title").innerHTML = displayArray(generationArray);
} //createBlankGen

//this increases the generation value of all visited gridpoints
function updateGenerations() {
    updateMapArray();
    var location = new Array(),
        nextX = 0,
        nextY = 1;

    for (i = 0; i < mapArray.length; i++) {

        for (j = 0; j < mapArray[i].length; j++) {
            if (mapArray[i][j] == '!') {
                generationArray[i][j] += 1;
                location[nextX] = j;
                location[nextY] = i;
                nextX += 2;
                nextY += 2;

            } //if
        } //for j
    } //for i
} //updateGenerations

//the next set of methods check the availability of neighboring gridpoints. Is it free? Is it an obsitacle? is it the endpoint?
function canMoveUp(arrayToSearch, x, y) {
    if (x > 0 && x <= arrayToSearch.length - 1) {
        if (arrayToSearch[x - 1][y] == 0) {
            return true;
        } else if (arrayToSearch[x - 1][y] == "R") {
            foundEnd = true;
            return false;
        }
    } //if
    else return false;
} //moveUP

function canMoveRight(arrayToSearch, x, y) {
    if (y >= 0 && y < arrayToSearch[0].length - 1) {
        if (arrayToSearch[x][y + 1] == 0) {
            return true;
        } else if (arrayToSearch[x][y + 1] == "R") {
            foundEnd = true;
            return false;
        }
    } //if
    else return false;
} //moveUP


function canMoveDown(arrayToSearch, x, y) {
    if (x >= 0 && x < arrayToSearch.length - 1) {
        if (arrayToSearch[x + 1][y] == 0) {
            return true;
        } else if (arrayToSearch[x + 1][y] == "R") {
            foundEnd = true;
            return false;
        }
    } //if
    else return false;
} //moveUP


function canMoveLeft(arrayToSearch, x, y) {
    if (y > 0 && y <= arrayToSearch[0].length - 1) {
        if (arrayToSearch[x][y - 1] == 0) {
            return true;
        } else if (arrayToSearch[x][y - 1] == "R") {
            foundEnd = true;
            return false;
        }
    } //if
    else return false;
} //moveUP

//once we've found the end point, trace backwards toward the beginning. Boxes with the highest generation valuse have been around the longest and are, thus, closest to the green box.
function traceBack() {
    var rLocation = new Array(),
        currentLocation = new Array(),
        currentCoords = new Array(),
        moveArray = new Array(),
        moveDistance = new Array();
    attempts++;
    updateMapArray();
    rLocation = findInArray(mapArray, 'R');
    currentLocation[0] = rLocation[0];
    currentLocation[1] = rLocation[1];
    currentCoords = [currentLocation[0] * hSpace, currentLocation[1] * vSpace];
    moveArray = findClosestNeighbor(currentLocation[1], currentLocation[0]);
    moveDisance = [moveArray[0] * hSpace, moveArray[1] * vSpace];
    shadeCell(currentCoords[0] + moveDisance[0], currentCoords[1] + moveDisance[1], "#FFFF00");
    currentLocation[1] += moveArray[1];
    currentLocation[0] += moveArray[0];

    while (moveArray[2] < attempts) {
        currentCoords = [currentLocation[0] * hSpace, currentLocation[1] * vSpace];
        moveArray = findClosestNeighbor(currentLocation[1], currentLocation[0]);
        moveDisance = [moveArray[0] * hSpace, moveArray[1] * vSpace];
        shadeCell(currentCoords[0] + moveDisance[0], currentCoords[1] + moveDisance[1], "#FFFF00");

        currentLocation[1] += moveArray[1];
        currentLocation[0] += moveArray[0];
    }


    drawGrid();
}


//check all neighboring elements of the generation array to get their values. Sort them from highest to lowest so our trace method knows which direction to move.
function findClosestNeighbor(x, y) {
    var neighbors = [-1, -1, -1, -1],
        max, maxLocation = -1,
        moveArray = "";
    if (x - 1 >= 0 && generationArray[x - 1][y] != 'R' && generationArray[x - 1][y] != 'B' && generationArray[x - 1][y] != 'G') neighbors[0] = generationArray[x - 1][y]; //above
    if (x + 1 < generationArray.length && generationArray[x + 1][y] != 'R' && generationArray[x + 1][y] != 'B' && generationArray[x + 1][y] != 'G') neighbors[1] = generationArray[x + 1][y]; //below
    if (y - 1 >= 0 && generationArray[x][y - 1] != 'R' && generationArray[x][y - 1] != 'B' && generationArray[x][y - 1] != 'G') neighbors[2] = generationArray[x][y - 1]; //left
    if (y + 1 < generationArray[0].length && generationArray[x][y + 1] != 'R' && generationArray[x][y + 1] != 'B' && generationArray[x][y + 1] != 'G') neighbors[3] = generationArray[x][y + 1]; //right
    max = Math.max(parseInt(neighbors[0]), parseInt(neighbors[1]), parseInt(neighbors[2]), parseInt(neighbors[3]));

    for (i = 0; i < neighbors.length; i++) {
        if (neighbors[i] == max) {
            maxLocation = i;
            break;
        }
    }

    switch (maxLocation) {
    case 0:
        moveArray = [0, -1, max];
        break;

    case 1:
        moveArray = [0, 1, max];
        break;

    case 2:
        moveArray = [-1, 0, max];
        break;
    case 3:
        moveArray = [1, 0, max];
        break;

    default:
        moveArray = [0, 0, max];
        break;
    } //switch

    return moveArray;
} //findClosest

//generate a random Map for lazy people. Make sure the green box and the red box are in opposite thirds of the grid. Otherwise, what's the point, right?
function randomMap() {
    clearAll();
    updateMapArray();
    var randX, randY, height = mapArray.length, width = mapArray[0].length; 
    for (i = 0; i < (.25 * mapArray.length * mapArray[1].length); i++) {
        randX = Math.floor(Math.random() * height);
        randY = Math.floor(Math.random() * width);

        mapArray[randX][randY] = 'B';
        shadeCell(randY * vSpace, randX * hSpace, "#0000FF");
    } //for
    
    //make a green box
    randX = Math.floor(Math.random() * mapArray.length);
    randY = Math.floor(Math.random() * (mapArray[1].length / 3));
    mapArray[randX][randY] = 'G';
    shadeCell(randY * vSpace, randX * hSpace, "#00FF00");

    //make a red box
    randX = Math.floor(Math.random() * mapArray.length);
    randY = Math.floor(Math.random() * (mapArray[1].length / 3)) + Math.floor(2 * (mapArray[1].length / 3));
    mapArray[randX][randY] = 'R';
    shadeCell(randY * vSpace, randX * hSpace, "#FF0000");
    
    drawGrid();
} //randomObstacles

//reset all the arrays before mapping to ensure everyting starts correctly.

function resetArrays(){
    mapArray.length = 0;
    generationArray.length = 0;
    closedNodes.length = 0;
}
