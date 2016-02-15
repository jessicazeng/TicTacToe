/**
 * Created by sicaz on 2/5/2016.
 */

var startingPlayer = 1;
var player;
var selectedTiles;
var tiles;

var instruction = document.getElementById("instruction");
var canvas = document.getElementById("board");
var ctx = canvas.getContext("2d");

var offset = 20;
var csize = canvas.width;
var tsize = (csize - (offset*4))/3;

function tile(num, x, y, selected, value){
    this.num = num;
    this.x = x;
    this.y = y;
    this.selected = selected;
    this.value = value;
}

var counter = 0;

function initTimer(){
    counter = 6;
    setTimeout(timer, 1000);
}

function timer(){
    counter--;

    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.font = "72px Raleway";
    ctx.fillStyle = "#ffcc99";
    ctx.textAlign = "center";
    ctx.fillText(counter, csize/2, csize/2);

    if (counter == 0)
        initBoard();
    else
        setTimeout(timer, 1000);
}

initTimer();

function initBoard(){
    tiles = [];
    selectedTiles = [];
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    for (var i = 0; i < 3; i++) {
        tiles.push([]);

        for (var j = 0; j < 3; j++) {
            var num = (i*3) + j;
            var x = ((j+1) * offset) + (tsize * j);
            var y = ((i+1) * offset) + (tsize * i);
            var value = false;
            var t = new tile(num, x, y, value, '');
            ctx.fillStyle = "#ffcc99";
            ctx.fillRect(t.x, t.y, tsize, tsize);
            tiles[i].push(t);
        }
    }
    player = startingPlayer;
    if(player == 2)
        AIMove();
    else
        instruction.textContent = "Please make a move";

    canvas.addEventListener("click", gameClick);
}

function AIMove(){
    if(player == 2){
        instruction.textContent = "Your opponent is making a move...";

        var unusedTiles = [];
        for(var i=0; i<9; i++)
            if(selectedTiles.indexOf(i) == -1)
                unusedTiles.push(i);

        var tnum = unusedTiles[Math.floor(Math.random() * unusedTiles.length)];

        window.setTimeout(function(){ drawO(tiles[Math.floor(tnum/3)][tnum%3]); }, 1500);
    }
}

function drawO(t){
    ctx.beginPath();

    ctx.strokeStyle = "#ffffff";
    ctx.lineWidth="5";

    ctx.arc(t.x + (tsize/2), t.y + (tsize/2), (tsize/2) - offset, 0, 2*Math.PI);
    ctx.stroke();
    selectedTiles.push(t.num);
    t.selected = true;
    t.value = 'O';

    player = 1;

    isWin(t);

    if(player == 1){
        instruction.textContent = "Please make a move";
    }
}

function drawX(t){
    ctx.beginPath();

    ctx.strokeStyle = "#ffffff";
    ctx.lineWidth="5";

    ctx.moveTo(t.x + offset, t.y + offset);
    ctx.lineTo(t.x + tsize - offset, t.y + tsize - offset);

    ctx.moveTo(t.x + tsize - offset, t.y + offset);
    ctx.lineTo(t.x + offset, t.y + tsize - offset);

    ctx.stroke();

    selectedTiles.push(t.num);
    t.value = 'X';

    isWin(t);

    if(selectedTiles.length < 9)
        AIMove();
}

function checkSelectedTile(x, y){
    for (var i = 0; i < 3; i++) {
        for (var j = 0; j < 3; j++) {
            var left = tiles[i][j].x,
                right = left + tsize,
                top = tiles[i][j].y,
                bottom = top + tsize;

            if((x>=left) && (x<=right) && (y>=top) && (y<=bottom)){
                if(tiles[i][j].selected == false && player==1){
                    player = 2;
                    selected = true;
                    tiles[i][j].selected = true;
                    drawX(tiles[i][j]);
                }
            }
        }
    }
}

function gameClick(e){
    checkSelectedTile(e.offsetX, e.offsetY);
}

function isWin(t){
    var win = false;

    for(var i=0; i<3; i++){
        if(tiles[Math.floor((t.num)/3)][i].value != t.value)
            break;
        if(i == 2)
            win = true;
    }

    for(var i=0; i<3; i++){
        if(tiles[i][(t.num)%3].value != t.value)
            break;
        if(i == 2)
            win = true;
    }

    if((Math.floor((t.num)/3)) == ((t.num)%3)){
        for(var i=0; i<3; i++){
            if(tiles[i][i].value != t.value)
                break;
            if(i == 2)
                win = true;
        }
    }

    if(t.num==2 || t.num==4 || t.num==6){
        for(var i=0; i<3; i++){
            if(tiles[i][2-i].value != t.value)
                break;
            if(i == 2)
                win = true;
        }
    }

    if(win) {
        player = -1;
        drawResult(1, t.value);
    }else{
        if(selectedTiles.length == 9){
            drawResult(0, null)
        }
    }
}

// result: 0 is tie, 1 is win
// player: 'O', 'X' or null
function drawResult(result, player){
    instruction.textContent = "Game over";
    canvas.removeEventListener("click", gameClick);

    ctx.fillStyle = "rgba(51, 204, 204, 0.9)";
    ctx.fillRect(csize/7, (2*csize)/7, 5*(csize/7), 3*(csize/7));

    ctx.font = "35px Raleway";
    ctx.fillStyle = "#ffffff";
    ctx.textAlign = "center";

    if(result == 0) {
        text = "It's a tie!";
    } else{
        if(player == 'O')
            text = "You lose.";
        else{
            text = "You win!";

            $.post("/DBServlet", {'win': 1}).done(function( data ) { console.log( "Data Loaded." ); });
        }
    }
    ctx.fillText(text, csize/2, (3*csize)/7);

    ctx.fillStyle = "#ffffff";
    ctx.fillRect((2*csize)/7, (3.5*csize)/7, 3*(csize/7), csize/7);

    ctx.font = "20px Raleway";
    ctx.fillStyle = "#33cccc";
    ctx.textBaseline = 'middle';
    ctx.fillText("Play Again", csize/2, (4*csize)/7);

    canvas.addEventListener("click", replayClick);
}

function replayClick(e){
    var selected = false;

    var x = e.offsetX, y = e.offsetY;
    var left = (2*csize)/ 7,
        right = left + 3*(csize/7),
        top = (3.5*csize)/ 7,
        bottom = top + csize/7;

    if(x>=left && x<=right && y>=top && y<=bottom){
        selected = true;
    }

    if(selected){
        canvas.removeEventListener("click", replayClick);

        if(startingPlayer == 1)
            startingPlayer = 2;
        else
            startingPlayer = 1;

        initTimer();
    } else{
        console.log("No");
    }
}