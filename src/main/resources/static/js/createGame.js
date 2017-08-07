var warningForOffense = 'Blow whistle once: warning for offense';
var offenceSet = 'Blow whistle twice: offense should be set';
var playUnderWay = 'Blow whistle three times: disc should be pulled';
var countUpTimer;
var countUpTimerIndex;

function fieldChanged(selectedField) {
  $('#scoreGameButton').removeClass('hidden');
}

$(document).ready(
    function() {
      $("#form").submit(
          function(e) {
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
              url : '/selectGame/?division=' + divisionId + "&homeTeam=" + homeTeamId + '&awayTeam=' + awayTeamId
                  + '&field=' + fieldId,
              success : function(aboutToStartGame) {
                $('#baseRow').html(aboutToStartGame);
              },
              error : function(error) {
                console.log(error.responseText);
                window.alert("Could not create game. Ask for help. With error: " + error.responseText);
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

$('#baseRow').on('click', '#startGame', function() {
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

function updateEventTable(gameId) {
  $.ajax({
    type : "get",
    url : '/updateLastEvent/?game=' + gameId,
    success : function(eventDetails) {
      $('#eventTable > tbody > tr').eq(0).after(eventDetails);
      updateUndoButton();
    },
    error : function(error) {
      console.log("Problem updating event list" + error.responseText);
    }
  });
}

function pointScored(button) {
  startPointScoredTimer();
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

function startHalftimeTimer(){
	setUpTimer(halftimeTimerTick);
}

function startPointScoredTimer(){
	setUpTimer(pointScoredTimerTick);
}

function setUpTimer(timerTick){
	if (countUpTimer){
		stopCountUpTimer();
	}
	showTimerMessage('');
	$('#timer').removeClass('hide-timer');
	countUpTimer = new Timer();
	countUpTimerIndex = 0;
	updateTimer(pad(countUpTimerIndex));
	countUpTimer.Tick = timerTick;
	countUpTimer.Start()
}

function stopCountUpTimer(){
	countUpTimer.Stop();
	countUpTimer = null;
	$('#timer').addClass('hide-timer');
	countUpTimerIndex = 0;
}

$('#baseRow').on('click', '#playStarted', function() {
	stopCountUpTimer();
});

function updateTimer(newTime){
	$('#countupTime').html(newTime);
}

function showTimerMessage(message){
	$('#countupMessage').html(message);
}

function pointScoredTimerTick()
{
	countUpTimerIndex  = countUpTimerIndex + 1;
	updateTimer(pad(countUpTimerIndex));
	
	if (countUpTimerIndex == 50) {
		showTimerMessage(warningForOffense);
	} else if  (countUpTimerIndex == 70) {
		showTimerMessage(offenceSet);
	}else if  (countUpTimerIndex == 90) {
		showTimerMessage(playUnderWay);
	}
}

function halftimeTimerTick()
{
	countUpTimerIndex  = countUpTimerIndex + 1;
	updateTimer(pad(countUpTimerIndex));
	
	if (countUpTimerIndex == 260) {
		showTimerMessage(warningForOffense);
	} else if  (countUpTimerIndex == 280) {
		showTimerMessage(offenceSet);
	}else if  (countUpTimerIndex == 300) {
		showTimerMessage(playUnderWay);
	}
}

function pointScoredTimerTick()
{
	countUpTimerIndex  = countUpTimerIndex + 1;
	updateTimer(pad(countUpTimerIndex));
	
	if (countUpTimerIndex == 50) {
		showTimerMessage(warningForOffense);
	} else if  (countUpTimerIndex == 70) {
		showTimerMessage(offenceSet);
	}else if  (countUpTimerIndex == 90) {
		showTimerMessage(playUnderWay);
	}else if  (countUpTimerIndex == 120) {
		stopCountUpTimerTimer();
	}
}
function pad(val)
{
    var valString = val + "";
    if(valString.length < 2) {
        return "0" + valString;
    } else {
        return valString;
    }
}

function endGame(gameId) {
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

$('#baseRow').on('click', '#homeTeamScored', function() {
  pointScored($(this));
});

$('#baseRow').on('click', '#awayTeamScored', function() {
  pointScored($(this));
});

$('#gameEnded').click(function() {
  var gameId = getGameId();
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

$('#halftime').click(function() {
	  var gameId = getGameId();
	  startHalftimeTimer();
	  $.ajax({
	    type : "post",
	    url : '/halftime/?game=' + gameId,
	    success : function(proposedFinalScore) {
	        updateEventTable(gameId);
	    },
	    error : function(error) {
	      console.log(error.responseText);
	    }
	  });
	});


function updateUndoButton() {
  var amountOfRows = $('#eventTable > tbody > tr').length;
  if (amountOfRows <= 2) {
    $('#undoButton').prop("disabled", true);
  } else {
    $('#undoButton').prop("disabled", false);
  }
}

$('#undoButton').click(function() {
  var tableRow = $('#eventTable > tbody > tr').eq(1);
  var eventId = tableRow.data('event-id');
  $.ajax({
    type : "post",
    url : '/undoEvent/?event=' + eventId,
    success : function(currentScore) {
      $('#currentScore').html(currentScore);
      tableRow.remove();
      updateUndoButton();
    },
    error : function(error) {
      console.log(error.responseText);
    }
  });
});

function getGameId() {
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
      location.href = error.responseText;
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
      window.alert("Could not select division. Ask for help. With error: " + error.responseText);
    }
  });
}

function awayTeamChanged(selectedAwayTeam) {
  $('#fields').removeClass('hidden');
  $('#scoreGameButton').addClass('hidden');
}