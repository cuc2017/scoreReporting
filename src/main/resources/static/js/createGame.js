/**
 * 
 */
function fieldChanged(selectedField) {
	var selectedIndex = selectedField.selectedIndex;
	var fieldId = selectedField.options[selectedIndex].value;
	$.ajax({
		type : "post",
		url : '/selectGame/?field=' + fieldId,
		success : function(response) {
			console.log(response);
		},
		error : function(error) {
			window.alert("Could not select field. Ask for help. With error: "
					+ error.responseText);
		}
	});
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
	console.log(homeTeamId);
	console.log("division id= " + divisionId);
	$.ajax({
		type : "post",
		url : '/selectHomeTeam/?division=' + divisionId + "&homeTeam=" + homeTeamId,
		success : function(awayTeams) {
			$('#awayTeams').removeClass('hidden');
			$('#awayTeams').html(awayTeams);
			$('#fields').addClass('hidden');
		},
		error : function(error) {
			window.alert("Could not select division. Ask for help. With error: "
					+ error.responseText);
		}
	});
}

function awayTeamChanged(selectedAwayTeam){
	$('#fields').removeClass('hidden');
}