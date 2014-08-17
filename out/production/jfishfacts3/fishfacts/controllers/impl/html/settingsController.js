
var SettingsController = (function($) {
  internal = {};

  internal.showMessage = function(msg, bad)
  {
    if (bad)
    {
        $("#bottomBar").attr("class", "badMessage")
    }
    else
    {
        $("#bottomBar").attr("class", "goodMessage")
    }

    $("#bottomBar #message").html(msg);
    $("#bottomBar").fadeIn(500).delay(3000).fadeOut(500);

  }

  internal.save = function(url, params)
  {
    $.ajax({
            url: url,
            type: "POST",
            data: params
        }).done(function(result){
            internal.showMessage(result);
        }).fail(function(xhr){
            if (xhr.responseText)
            {
                internal.showMessage(xhr.responseText, true);
            }
            else
            {
                internal.showMessage("There was an error while communicating with the server...", true);
            }
        });
  }

  internal.clockSettingSave = function(totalTime, answerDelay)
  {
    internal.save(
        "/clockSettingsSave",
        {"totalTime": totalTime, "answerDelay": answerDelay}
    );
  }

  internal.aquariumSettingsSave = function(totalFish, numCorrect)
  {
    internal.save(
        "/aquariumSettingsSave",
        {"totalFish": totalFish, "numCorrect": numCorrect}
    );
  }


  return internal;
}(jQuery));

(function($){

$("#clockSettingsSave").click(function(){
    var total = $("#csTotal").val();
    var delay = $("#csDelay").val();

    console.log("total: " + total + " delay: " + delay);

    SettingsController.clockSettingSave(total, delay);
});

$("#aquariumSettingsSave").click(function() {
    var totalFish = $("#asTotalFish").val();
    var numCorrect = $("#asNumCorrect").val();

    SettingsController.aquariumSettingsSave(totalFish, numCorrect);
})

})(jQuery)