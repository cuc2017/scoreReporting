var timer;
var refreshTimeSucces = 3000;
var refreshTimeFailure = 60000;

function getGameId() {
	var eventTable = $('#eventTable');
	return eventTable.data('game-id');
}

function updatePage(){
	updateStartTime();
	updateEndTime();
	updateEventTable();
}

function slowDownTimerOnError(){
	clearTimeout(timer);
	timer = setTimeout(updatePage, refreshTimeFailure);
}

function updateStartTime() {
	var gameId = getGameId();
	$.ajax({
		type : "get",
		url : '/gameStartTime/?game=' + gameId,
		success : function(startTime) {
			$("#startTime").html(startTime);			
		},
		error : function(error) {
			console.log("Problem updating starttime" + error.responseText);
			slowDownTimerOnError();
		}
	});
}

function updateEndTime() {
	var gameId = getGameId();
	$.ajax({
		type : "get",
		url : '/gameEndTime/?game=' + gameId,
		success : function(endTime) {
			$("#endTime").html(endTime);			
		},
		error : function(error) {
			console.log("Problem updating starttime" + error.responseText);
			slowDownTimerOnError();
		}
	});
}

function updateEventTable() {
	var gameId = getGameId();
	$.ajax({
		type : "get",
		url : '/updateAllEvents/?game=' + gameId,
		success : function(events) {
			$("#eventTable").find("tr:gt(0)").remove();			
			$('#eventTable > tbody > tr').eq(0).after(events);
		},
		error : function(error) {
			console.log("Problem updating event list" + error.responseText);
			slowDownTimerOnError();
		}
	});
}

$(document).ready(function() {
	updatePage();
	timer = setTimeout(updatePage, refreshTimeSucces);
});