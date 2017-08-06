/**
 * 
 */
function fieldChanged(selectedField) {
	$('#scoreGameButton').removeClass('hidden');
}

$(document).ready(function() {
	$("#form").submit(function(e) {
		var divisionSelected = $("#divisions option:selected");
		var divisionId = divisionSelected.val();
		var homeTeamSelected = $("#homeTeam option:selected");
		var homeTeamId = homeTeamSelected.val();
		var awayTeamSelected = $("#awayTeam option:selected");
		var awayTeamId = awayTeamSelected.val();
		var fieldSelected = $("#field option:selected");
		var fieldId = fieldSelected.val();
		$.ajax({
			type : "post",
			url : '/selectGame/?division=' + divisionId + "&homeTeam=" + homeTeamId + '&awayTeam=' + awayTeamId + '&field=' + fieldId,
			success : function(aboutToStartGame) {
				$('#baseRow').html(aboutToStartGame);
			},
			error : function(error) {
				console.log(error.responseText);
				window.alert("Could not create game. Ask for help. With error: "
					+ error.responseText);
			}
		});
		return false;
	});
});

function onPageLeave(e) {
  var confirmationMessage = "The Game is not over, are you sure you want to leave?";
  (e || window.event).returnValue = confirmationMessage;
  return confirmationMessage;
}

$('#baseRow').on('click', '#startGame', function () {
	var gameId = $(this).data('game-id')
	console.log("Game started: " + gameId);
	$('#readyForGame').addClass('hidden');
	$('#gameOn').removeClass('hidden');
	$('#footer').removeClass('hidden');
	window.addEventListener('beforeunload', onPageLeave);
	$.ajax({
		type : "post",
		url : '/startGame/?game=' + gameId,
		success : function(startGame) {
		    updateEventTable(gameId);
		},
		error : function(error) {
			console.log(error.responseText);
		}
	});
});

function updateEventTable(gameId){
	$.ajax({
	    type : "get",
	    url : '/updateLastEvent/?game=' + gameId,
	    success : function(eventDetails) {
	      $('#eventTable > tbody > tr').eq(0).after(eventDetails)
	    },
	    error : function(error) {
	      console.log("Problem updating event list" + error.responseText);
	    }
	  });	
}

function pointScored(button){
  var gameId = button.data('game-id')
  var teamId = button.data('team-id')
  $.ajax({
    type : "post",
    url : '/pointScored/?game=' + gameId + '&team=' + teamId,
    success : function(currentScore) {
      $('#currentScore').html(currentScore);
      updateEventTable(gameId);
    },
    error : function(error) {
      console.log(error.responseText);
    }
  });
  console.log("Point scored: " + gameId + " for team: " + teamId);
}

function endGame(gameId){
	console.log("Game ended: " + gameId);
	window.removeEventListener('beforeunload', onPageLeave);
	$.ajax({
		type : "post",
		url : '/endGame/?game=' + gameId,
		success : function(startGame) {
			window.location.replace("/");
		},
		error : function(error) {
			console.log(error.responseText);
		}
	});	
}

$('#baseRow').on('click', '#homeTeamScored', function () {
	pointScored($(this));
});

$('#baseRow').on('click', '#awayTeamScored', function () {
	pointScored($(this));
});

$('#gameEnded').click(function () {
	 var gameId =  getGameId();
	 $.ajax({
		    type : "get",
		    url : '/proposedFinalScore/?game=' + gameId,
		    success : function(proposedFinalScore) {
		        $('#proposedFinalScore').html(proposedFinalScore)
		        $('#endGameModal').modal('show').one('click', '#endGameOk', function(e) {
		    	    endGame(gameId);
		        });
		    },
		    error : function(error) {
		      console.log(error.responseText);
		    }
		  });
});

function getGameId(){
	var info = $('#gameOn');
	return info.data('game-id');
}

function divisionChanged(selectedDivision) {
	var selectedIndex = selectedDivision.selectedIndex;
	var divisionId = selectedDivision.options[selectedIndex].value;
	$.ajax({
		type : "post",
		url : '/selectDivision/?division=' + divisionId,
		success : function(homeTeams) {
			$('#homeTeams').html(homeTeams);
			$('#awayTeams').addClass('hidden');
			$('#fields').addClass('hidden');
			$('#scoreGameButton').addClass('hidden');
		},
		error : function(error) {
			console.log("Could not select division.");
			location.href=error.responseText;
		}
	});
}

function homeTeamChanged(selectedHomeTeam) {
	var selectedIndex = selectedHomeTeam.selectedIndex;
	var homeTeamId = selectedHomeTeam.options[selectedIndex].value;
	var divisionSelected = $("#divisions option:selected");
	var divisionId = divisionSelected.val();
	$.ajax({
		type : "post",
		url : '/selectHomeTeam/?division=' + divisionId + "&homeTeam=" + homeTeamId,
		success : function(awayTeams) {
			$('#awayTeams').removeClass('hidden');
			$('#awayTeams').html(awayTeams);
			$('#fields').addClass('hidden');
			$('#scoreGameButton').addClass('hidden');
		},
		error : function(error) {
			window.alert("Could not select division. Ask for help. With error: "
					+ error.responseText);
		}
	});
}

function awayTeamChanged(selectedAwayTeam){
	$('#fields').removeClass('hidden');
	$('#scoreGameButton').addClass('hidden');
}