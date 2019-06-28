var gameId;

function gameSelected(selectedGame) {
  var selectedIndex = selectedGame.selectedIndex;
  gameId = selectedGame.options[selectedIndex].value;
  $('#finishGame').prop("disabled", false);
}

 $('#baseRow').on('click', '#finishGame', function() {
 
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
});