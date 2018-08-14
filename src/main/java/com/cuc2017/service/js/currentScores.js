var timer;
var refreshTimeSucces = 5000;
var refreshTimeFailure = 1200000;

function updateCurrentScores(){
  $.ajax({
    type : "get",
    url : '/currentScores',
    success : function(currentScores) {
      $('#scores').html(currentScores);
      clearTimeout(timer);
      timer = setTimeout(updateCurrentScores, refreshTimeSucces);
    },
    error : function(error) {
      console.log(error.responseText);
      clearTimeout(timer);
      timer = setTimeout(updateCurrentScores, refreshTimeFailure);
    }
  });  
}

$(document).ready(function() {
  updateCurrentScores();
  timer=setTimeout(updateCurrentScores, refreshTimeSucces);
});