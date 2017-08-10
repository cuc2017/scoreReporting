$(document).ready(function(){
  updateEventTable();
});


function getGameId() {
  var eventTable = $('#eventTable');
  return eventTable.data('game-id');
}

function updateEventTable() {
  var gameId = getGameId();
	$.ajax({
		type : "get",
		url : '/updateAllPointEvents/?game=' + gameId,
		success : function(events) {
		  $("eventTable").find("tr:gt(0)").remove();
			$('#eventTable > tbody > tr').eq(0).after(events);
		},
		error : function(error) {
			console.log("Problem updating event list" + error.responseText);
		}
	});
}