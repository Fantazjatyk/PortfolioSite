const CSS_ACTIVE_BUTTON = "active-button";
const CSS_INACTIVE_BUTTON = "inactive-button";


$(document).ready(() => {
    $(document).ready(() => {
        $("#projects *[data-id]").off().on("click", (el) => {
            var self = el.currentTarget;
            var href = "projects/" + $(self).attr("data-id");
            var hash = $(self).attr("data-id");

            $("#projects_swap").load(href, null, () => {
                var leadingColor = $("meta[name=leadingColor]").attr("content");
                var leadingTextColor = $("meta[name=leadingTextColor]").attr("content");
                setColors(leadingColor, leadingTextColor);
            });
            document.location.hash = hash;
            $("#projects .nav-button").removeClass(CSS_ACTIVE_BUTTON);
            $(self).toggleClass(CSS_ACTIVE_BUTTON);
        });

        var hash = document.location.hash.replace("#", "");
        if (hash != null && hash != undefined && hash.length > 0) {
            $("*[data-id=" + hash + "]").click();
        }
        else {
            $("*[data-id=crawler").click();
        }

    });
});

function setColors(leadingColor, leadingTextColor) {


    if (leadingColor != undefined && leadingColor != null) {

        customize("color", leadingColor);
        customize("background", leadingColor);
    }


    if (leadingTextColor != undefined && leadingTextColor != null) {
        $("#links .nav-button").css("color", leadingTextColor);
        $("#links .nav-button *").css("color", leadingTextColor);
    }
}

function customize(attrName, attrValue) {
    var elements = $("*[data-customizer-" + attrName + "]");

    for (var i = 0; i < elements.length; i++) {
        var el = elements[i];
        var ss: any = $(el).attr("data-customizer-" + attrName).split(",").filter(el => el.length > 0);

        if (ss.length > 0) {
            for (var i2 = 0; i2 < ss.length; i2++) {
                var selector = ss[i2];
                if (selector == "this") {
                    $(el).css(attrName, attrValue);
                }
                else {
                    $(el).find(selector).css(attrName, attrValue);
                }

            }
        }
        else {
            $(el).css(attrName, attrValue);

        }
    }

}


