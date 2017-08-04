var timer;

function updateCurrentScores(){
  $.ajax({
    type : "get",
    url : '/currentScores',
    success : function(currentScores) {
      $('#scores').html(currentScores);
      clearTimeout(timer);
      timer = setTimeout(updateCurrentScores, 3000);
    },
    error : function(error) {
      console.log(error.responseText);
      clearTimeout(timer);
      timer = setTimeout(updateCurrentScores, 60000);
    }
  });  
}

$(document).ready(function() {
  updateCurrentScores();
  timer=setTimeout(updateCurrentScores, 3000);
});