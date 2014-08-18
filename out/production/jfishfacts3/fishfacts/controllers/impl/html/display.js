var NumberSettings = (function($){

    internal = {};

    internal.createTogglesFor = function(nums, idTarget)
    {
        for (var x in nums)
        {
            $("#" + idTarget).append('<input type="checkbox" id="' + idTarget + nums[x].toString() + '"/><label for="' + idTarget + nums[x].toString() + '">' + nums[x] + '</label>');
        }
    }

    internal.setCheckedFor = function(nums, idTarget)
    {
        for (var i in nums)
        {
            $("#" + idTarget + nums[i]).prop("checked", true);
        }
    }

    internal.getCheckedFor = function(idTarget)
    {
        var universe = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
        result = [];
        for (var i in universe)
        {
            if ($("#" + idTarget + universe[i]).prop("checked"))
            {
                result.push(universe[i]);
            }
        }
        return result;
    }

    return internal;

})(jQuery);


(function($)
{
    //JQuery UI Initialization code.
    $(".accordionWidget").accordion({
        collapsible: true,
        heightStyle: "content"
    });
    $("button").button();
    $(".spinner").spinner();

    var oneToNine = [1, 2, 3, 4, 5, 6, 7, 8, 9];
    var zeroToNine = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
    NumberSettings.createTogglesFor(zeroToNine, "fsAdd0");
    NumberSettings.createTogglesFor(zeroToNine, "fsAdd1");
    NumberSettings.createTogglesFor(zeroToNine, "fsSub0");
    NumberSettings.createTogglesFor(zeroToNine, "fsSub1");
    NumberSettings.createTogglesFor(zeroToNine, "fsMult0");
    NumberSettings.createTogglesFor(zeroToNine, "fsMult1");
    NumberSettings.createTogglesFor(oneToNine, "fsDiv0");
    NumberSettings.createTogglesFor(oneToNine, "fsDiv1");



})(jQuery);


