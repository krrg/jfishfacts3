
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

  internal.factSettingsSave = function(add, sub, mult, div)
  {
    internal.save(
        "/factSettingsSave",
        {
            "add": add,
            "sub": sub,
            "mult": mult,
            "div": div
        }
    )
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

$("#factSettingsSave").click(function(){
    var addEnabled = $("#fsAddEnabled").is(":checked");
    var subEnabled = $("#fsSubEnabled").is(":checked");
    var multEnabled = $("#fsMultEnabled").is(":checked");
    var divEnabled = $("#fsDivEnabled").is(":checked");

    var addNums0 = NumberSettings.getCheckedFor("fsAdd0");
    var addNums1 = NumberSettings.getCheckedFor("fsAdd1");
    var subNums0 = NumberSettings.getCheckedFor("fsSub0");
    var subNums1 = NumberSettings.getCheckedFor("fsSub1");
    var multNums0 = NumberSettings.getCheckedFor("fsMult0");
    var multNums1 = NumberSettings.getCheckedFor("fsMult1");
    var divNums0 = NumberSettings.getCheckedFor("fsDiv0");
    var divNums1 = NumberSettings.getCheckedFor("fsDiv1");

    var add = {
        "enabled": addEnabled,
        "terms0": addNums0,
        "terms1": addNums1
    }

    var sub = {
        "enabled": subEnabled,
        "terms0": subNums0,
        "terms1": subNums1
    }

    var mult = {
        "enabled": multEnabled,
        "terms0": multNums0,
        "terms1": multNums1
    }

    var div = {
        "enabled": divEnabled,
        "terms0": divNums0,
        "terms1": divNums1
    }

    SettingsController.factSettingsSave(add, sub, mult, div);
})

})(jQuery);




