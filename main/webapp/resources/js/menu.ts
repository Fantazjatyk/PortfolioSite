const CSS_ACTIVE_BUTTON = "active-button";

$(document).ready(()=>{
    var id = $("meta[name=menu_active_tab]").attr("content");
    $("#" + id).addClass(CSS_ACTIVE_BUTTON);
});