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
			success : function(response) {
				console.log(response);
				window.
			},
			error : function(error) {
				console.log(error.responseText);
				window.alert("Could not create game. Ask for help. With error: "
					+ error.responseText);
			}
		});
	});
});

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