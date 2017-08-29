const CSS_ACTIVE_BUTTON = "active-button";
const CSS_INACTIVE_BUTTON = "inactive-button";

var swapper;

$(document).ready(() => {
    swapper = new PageSwapper(".navigation .nav-button", (el) => {
        $(".navigation .nav-button").removeClass(CSS_ACTIVE_BUTTON);
        $(el).toggleClass(CSS_ACTIVE_BUTTON);
    }, () => { });
    swapper.init();

    var hash = document.location.hash;
    if (hash == null || hash == undefined) {
        $("#aboutme").click();
    }
    else {
        new RouteReproducer().reproduce(hash, swapper);
    }
});





