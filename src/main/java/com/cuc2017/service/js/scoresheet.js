var timer;
var refreshTimeSucces = 5000;
var refreshTimeFailure = 60000;

function getGameId() {
	var eventTable = $('#eventTable');
	return eventTable.data('game-id');
}

function updatePage(){
  var gameId = getGameId();
  $.ajax({
    type : "get",
    url : '/gameScoreSheet/?game=' + gameId,
    success : function(gameScoreSheet) {
      updateScore(gameScoreSheet.currentScore);
      updateStartTime(gameScoreSheet.startTime);
      updateEndTime(gameScoreSheet.endTime);
      updateEventTable(gameScoreSheet.events);
      setTimer();
    },
    error : function(error) {
      console.log("Problem updating scoreSheet" + error.responseText);
      slowDownTimerOnError();
    }
  });
}

function setTimer(){
  timer = setTimeout(updatePage, refreshTimeSucces);
}

function slowDownTimerOnError(){
	clearTimeout(timer);
	timer = setTimeout(updatePage, refreshTimeFailure);
}

function updateScore(score) {
  $("#currentGameScore").html(score);      
}

function updateStartTime(startTime) {
	$("#startTime").html(startTime);			
}

function updateEndTime(endTime) {
	$("#endTime").html(endTime);			
}

function updateEventTable(events) {
	$("#eventTable").find("tr:gt(0)").remove();			
	$('#eventTable > tbody > tr').eq(0).after(events);
}

$(document).ready(function() {
	updatePage();
});