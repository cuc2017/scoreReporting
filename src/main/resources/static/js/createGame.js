var warningForOffense = '<font color="black">Blow whistle once: warning for offense</font>';
var offenceSet = '<font color="blue">Blow whistle twice: offense should be set</font>';
var playUnderWay = '<font color="red">Blow whistle three times: disc should be pulled</font>';
var countUpTimer;
var countUpTimerIndex;
var gameTimer;
var gameStartTime = null;
var homeTeamId;
var homeTeamPlayers = null;
var awayTeamId;
var awayTeamPlayers = null;
var homeTimeOutButtonName = "TO Home";
var awayTimeOutButtonName = "TO Away";

function fieldChanged(selectedField) {
  var selectedIndex = selectedField.selectedIndex;
  var fieldId = selectedField.options[selectedIndex].value;

  $.ajax({
    type : "get",
    url : '/fieldInUse/?field=' + fieldId,
    success : function(inUse) {
      $('#scoreGameButton').addClass('hidden');
      if (inUse) {
        $('#fieldInUseModal').modal('show').one('click', '#useFieldOk', function(e) {
          $('#scoreGameButton').removeClass('hidden');
        });
      } else {
        $('#scoreGameButton').removeClass('hidden');
      }
    },
    error : function(error) {
      console.log(error.responseText);
    }
  });
}

$(document).ready(
    function() {
      $("#form").submit(
          function(e) {
            var divisionSelected = $("#divisions option:selected");
            var divisionId = divisionSelected.val();
            var homeTeamSelected = $("#homeTeam option:selected");
            homeTeamId = homeTeamSelected.val();
            var awayTeamSelected = $("#awayTeam option:selected");
            awayTeamId = awayTeamSelected.val();
            var fieldSelected = $("#field option:selected");
            var fieldId = fieldSelected.val();
            $.ajax({
              type : "post",
              url : '/selectGame/?division=' + divisionId + "&homeTeam=" + homeTeamId + '&awayTeam=' + awayTeamId
                  + '&field=' + fieldId,
              beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
              },
              success : function(aboutToStartGame) {
                $('#navbar').addClass('hidden');
                $('#baseRow').html(aboutToStartGame);
                getPlayers();
              },
              error : function(error) {
                console.log(error.responseText);
                window.alert("Could not create game. Ask for help. With error: " + error.responseText);
              }
            });
            return false;
          });
    });

function getPlayers() {
  if (homeTeamPlayers == null) {
    $.ajax({
      type : "get",
      url : '/players/?team=' + homeTeamId,
      success : function(players) {
        homeTeamPlayers = players;
      },
      error : function(error) {
        console.log(error.responseText);
      }
    });
  }
  if (awayTeamPlayers == null) {
    $.ajax({
      type : "get",
      url : '/players/?team=' + awayTeamId,
      success : function(players) {
        awayTeamPlayers = players;
      },
      error : function(error) {
        console.log(error.responseText);
      }
    });
  }
}

window.onpageshow = function(event) {
  if (event.persisted) {
    window.location.reload();
  }
};

function onPageLeave(e) {
  var confirmationMessage = "The Game is not over, are you sure you want to leave?";
  (e || window.event).returnValue = confirmationMessage;
  return confirmationMessage;
}

$('#baseRow').on('click', '#startGame', function() {
  var gameId = $(this).data('game-id');
  console.log("Game started: " + gameId);
  setUpPageOnStart();
  
  $.ajax({
    type : "post",
    url : '/startGame/?game=' + gameId,
    beforeSend : function(xhr) {
      xhr.setRequestHeader(header, token);
    },
    success : function(game) {
      setUpButtonsOnStart(game);
    },
    error : function(error) {
      console.log(error.responseText);
    }
  });
});

function setUpPageOnStart(){
  $('#readyForGame').addClass('hidden');
  $('#gameOn').removeClass('hidden');
  $('#footer').removeClass('hidden');
  startGameTimer();
  window.addEventListener('beforeunload', onPageLeave);
  window.addEventListener('pageHide', onPageLeave);
}

function setUpButtonsOnStart(game){
  updateEventTable(game.id);
  homeTimeOutButtonName = "TO " + game.homeTeam.name.substring(0, 8);
  awayTimeOutButtonName = "TO " + game.awayTeam.name.substring(0, 8);
  updateTimeOutButtons();
}

$('#baseRow').on('click', '#noGame', function() {
  var gameId = $(this).data('game-id')
  $.ajax({
    type : "post",
    url : '/doNotUseGame/?game=' + gameId,
    beforeSend : function(xhr) {
      xhr.setRequestHeader(header, token);
    },
    success : function(game) {
      window.location.replace("/");
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

function loadEventsOnStart() {
  var gameId = getGameId();
  $.ajax({
    type : "get",
    url : '/updateEvents/?game=' + gameId,
    success : function(eventDetails) {
      $('#eventTable > tbody > tr').eq(0).after(eventDetails);
      updateUndoButton();
    },
    error : function(error) {
      console.log("Problem updating event " + eventId + error.responseText);
    }
  });
}

function selectGoalBy(goalBy) {
  var tr = goalBy.closest('tr');
  var eventId = $(tr).data('event-id');
  var selectedIndex = goalBy.selectedIndex;
  var goalById = goalBy.options[selectedIndex].value;
  $.ajax({
    type : "post",
    url : '/goal/?event=' + eventId + '&goal=' + goalById,
    beforeSend : function(xhr) {
      xhr.setRequestHeader(header, token);
    },
   success : function(game) {
      console.log("Goal scored successful");
    },
    error : function(error) {
      console.log(error.responseText);
    }
  });
}

function selectAssistBy(assistBy) {
  var tr = assistBy.closest('tr');
  var eventId = $(tr).data('event-id');
  var selectedIndex = assistBy.selectedIndex;
  var assistById = assistBy.options[selectedIndex].value;
  $.ajax({
    type : "post",
    url : '/assist/?event=' + eventId + '&assist=' + assistById,
    beforeSend : function(xhr) {
      xhr.setRequestHeader(header, token);
    },
   success : function(game) {
      console.log("Assist successful: " + game);
    },
    error : function(error) {
      console.log(error.responseText);
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
    beforeSend : function(xhr) {
      xhr.setRequestHeader(header, token);
    },
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

function startHalftimeTimer() {
  setUpTimer(halftimeTimerTick, "half time");
}

function startGameTimer() {
  gameStartTime = new Date();
  gameTimer = new Timer();
  updateTimer(timerTimeInMinsAndSecs(0));
  gameTimer.Tick = gameTimerTick;
  gameTimer.Start()
}

function startPointScoredTimer() {
  setUpTimer(pointScoredTimerTick, "point scored");
}

function startTimeOutTimer() {
  if (countUpTimerIndex <= 0 || countUpTimerIndex > 90) {
    setUpTimer(timeOutTimerTick, "timeout taken");
    return;
  }
  var oldCountUpTimerIndex =   countUpTimerIndex;

  stopCountUpTimer();
  updateTimer(pad(countUpTimerIndex));
  $('#countupEvent').html("point and timout: ");
  $('#timer').removeClass('hide-timer');
  countUpTimer = new Timer();
  countUpTimerIndex = oldCountUpTimerIndex;
  updateTimer(pad(countUpTimerIndex));
  countUpTimer.Tick = pointScoredAndTimeoutTimerTick;
  countUpTimer.Start()
}

function setUpTimer(timerTick, countUpEvent) {
  if (countUpTimer) {
    stopCountUpTimer();
  }
  showTimerMessage('');
  $('#countupEvent').html(countUpEvent + ': ');
  $('#timer').removeClass('hide-timer');
  countUpTimer = new Timer();
  countUpTimerIndex = 0;
  updateTimer(pad(countUpTimerIndex));
  countUpTimer.Tick = timerTick;
  countUpTimer.Start()
}

function stopCountUpTimer() {
  if (countUpTimer != null) {
    countUpTimer.Stop();
  }
  countUpTimer = null;
  $('#timer').addClass('hide-timer');
  countUpTimerIndex = 0;
}

$('#baseRow').on('click', '#playStarted', function() {
  stopCountUpTimer();
});

function updateTimer(newTime) {
  $('#countupTime').html(newTime);
}

function showTimerMessage(message) {
  $('#countupMessage').html(message);
}

function pointScoredTimerTick() {
  countUpTimerIndex = countUpTimerIndex + 1;
  updateTimer(pad(countUpTimerIndex));

  if (countUpTimerIndex == 50) {
    showTimerMessage(warningForOffense);
  } else if (countUpTimerIndex == 60) {
    showTimerMessage(offenceSet);
  } else if (countUpTimerIndex == 80) {
    showTimerMessage(playUnderWay);
  }
}

function timeOutTimerTick() {
  countUpTimerIndex = countUpTimerIndex + 1;
  updateTimer(pad(countUpTimerIndex));

  if (countUpTimerIndex == 50) {
    showTimerMessage(warningForOffense);
  } else if (countUpTimerIndex == 70) {
    showTimerMessage(offenceSet);
  } else if (countUpTimerIndex == 90) {
    showTimerMessage(playUnderWay);
  }
}
function pointScoredAndTimeoutTimerTick() {
  countUpTimerIndex = countUpTimerIndex + 1;
  updateTimer(pad(countUpTimerIndex));

  if (countUpTimerIndex == 120) {
    showTimerMessage(warningForOffense);
  } else if (countUpTimerIndex == 130) {
    showTimerMessage(offenceSet);
  } else if (countUpTimerIndex == 150) {
    showTimerMessage(playUnderWay);
  }
}

function halftimeTimerTick() {
  countUpTimerIndex = countUpTimerIndex + 1;
  updateTimer(timerTimeInMinsAndSecs(countUpTimerIndex));

  if (countUpTimerIndex == 470) {
    showTimerMessage(warningForOffense);
  } else if (countUpTimerIndex == 480) {
    showTimerMessage(offenceSet);
  } else if (countUpTimerIndex == 500) {
    showTimerMessage(playUnderWay);
  }
}

function timerTimeInMinsAndSecs(time) {
  var mins = Math.floor(time / 60);
  var secs = time % 60;
  return pad(mins) + " mins " + pad(secs) + " secs";
}

function gameTimerTick() {
  var elapsedTime = getElapsedTime(gameStartTime);
  $('#gameTime').html(timerTimeInMinsAndSecs(elapsedTime));
}

function getElapsedTime(time){
  var currentTime = new Date();
  var elapsedTimeInMs = currentTime.getTime() - time.getTime();
  var elapsedTime = Math.floor(elapsedTimeInMs / 1000);
  return elapsedTime;
}
function pad(val) {
  var valString = val + "";
  if (valString.length < 2) {
    return "0" + valString;
  } else {
    return valString;
  }
}

function endGame(gameId) {
  stopCountUpTimer();
  console.log("Game ended: " + gameId);
  $.ajax({
    type : "post",
    url : '/endGame/?game=' + gameId,
    beforeSend : function(xhr) {
      xhr.setRequestHeader(header, token);
    },
    success : function(endGame) {
      updateEventTable(gameId);
      $('#finishedGame').prop("disabled", false);

    },
    error : function(error) {
      console.log(error.responseText);
    }
  });
}

function finishGame(gameId) {
  window.removeEventListener('beforeunload', onPageLeave);
  $.ajax({
    type : "post",
    url : '/finishGame/?game=' + gameId,
    beforeSend : function(xhr) {
      xhr.setRequestHeader(header, token);
    },
    success : function(endGame) {
      window.location.replace("/scoresheet?game=" + gameId);
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
  endGame(getGameId());
});

$('#finishedGame').click(function() {
  var gameId = getGameId();
  $.ajax({
    type : "get",
    url : '/proposedFinalScore/?game=' + gameId,
    success : function(proposedFinalScore) {
      $('#proposedFinalScore').html(proposedFinalScore)
      $('#endGameModal').modal('show').one('click', '#endGameOk', function(e) {
        finishGame(gameId);
      });
    },
    error : function(error) {
      console.log(error.responseText);
    }
  });
});

function updateTimeOutButtons() {
  $('#homeTimeOuts').html(homeTimeOutButtonName);
  $('#awayTimeOuts').html(awayTimeOutButtonName);
  var gameId = getGameId();

  $.ajax({
    type : "get",
    url : '/game/?game=' + gameId,
    success : function(game) {
      var timeOutsHomeTeam = game.homeTimeOutThisHalf;
      var timeOutsAwayTeam = game.awayTimeOutThisHalf;
      if (timeOutsHomeTeam >= 2) {
        $('#homeTimeOuts').prop("disabled", true);
      } else {
        $('#homeTimeOuts').prop("disabled", false);
      }
      if (timeOutsAwayTeam >= 2) {
        $('#awayTimeOuts').prop("disabled", true);
      } else {
        $('#awayTimeOuts').prop("disabled", false);
      }
    },
    error : function(error) {
      console.log(error.responseText);
    }
  });

}

$('#halftime').click(function() {
  var gameId = getGameId();
  startHalftimeTimer();
  $.ajax({
    type : "post",
    url : '/halftime/?game=' + gameId,
    beforeSend : function(xhr) {
      xhr.setRequestHeader(header, token);
    },
   success : function(game) {
      updateEventTable(gameId);
      updateTimeOutButtons();
    },
    error : function(error) {
      console.log(error.responseText);
    }
  });
});

$('#homeTimeOuts').click(function() {
  timeOut(homeTeamId);
});

$('#awayTimeOuts').click(function() {
  timeOut(awayTeamId);
});

function timeOut(teamId) {
  var gameId = getGameId();
  startTimeOutTimer();
  $.ajax({
    type : "post",
    url : '/timeOut/?game=' + gameId + '&team=' + teamId,
    beforeSend : function(xhr) {
      xhr.setRequestHeader(header, token);
    },
    success : function(game) {
      updateEventTable(gameId);
      updateTimeOutButtons();
    },
    error : function(error) {
      console.log(error.responseText);
    }
  });

}
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
    beforeSend : function(xhr) {
      xhr.setRequestHeader(header, token);
    },
    success : function(currentScore) {
      $('#currentScore').html(currentScore);
      tableRow.remove();
      updateUndoButton();
      updateTimeOutButtons();
      stopCountUpTimer();
      $('#finishedGame').prop("disabled", true);
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
    beforeSend : function(xhr) {
      xhr.setRequestHeader(header, token);
    },
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
    beforeSend : function(xhr) {
      xhr.setRequestHeader(header, token);
    },
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