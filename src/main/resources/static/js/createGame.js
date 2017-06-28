/**
 * 
 */
function fieldChanged(selectedObject) {
	var selectedIndex = selectedObject.selectedIndex;
	var selectedValue = selectedObject.options[selectedIndex].value;
	$.ajax({
		type : "post",
		url : '/selectGame/?field=' + selectedValue,
		success : function(response) {
			console.log(response);
		},
		error : function(error) {
			window.alert("Could not select field. Ask for help. With error: "
					+ error.responseText);
		}
	});
}

function divisionChanged(selectedObject) {
	var selectedIndex = selectedObject.selectedIndex;
	var selectedValue = selectedObject.options[selectedIndex].value;
	$.ajax({
		type : "post",
		url : '/selectDivision/?division=' + selectedValue,
		success : function(response) {
			console.log(response);
		},
		error : function(error) {
			window.alert("Could not select division. Ask for help. With error: "
					+ error.responseText);
		}
	});

}