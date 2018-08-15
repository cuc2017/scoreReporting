function gameSelected(selectedGame) {
	var selectedIndex = selectedGame.selectedIndex;
	var gameId = selectedGame.options[selectedIndex].value;
	window.location.replace("/scoresheet?game=" + gameId);
}

