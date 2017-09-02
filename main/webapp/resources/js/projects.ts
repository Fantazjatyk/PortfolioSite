const CSS_ACTIVE_BUTTON = "active-button";
const CSS_INACTIVE_BUTTON = "inactive-button";
const ROUTING_PROJECT_ID = 'p';
const ROUTING_SECTION_ID = 's';
$(document).ready(() => {
    bindProjectLinks();
    routeByHash();
});


function routeByHash() {
    var hashMap = convertHashToMap(document.location.hash.substr(1));
    if (hashMap == null || hashMap == undefined || !(ROUTING_PROJECT_ID in hashMap)) {
        $("*[data-id=crawler").click();
    }
    else {
        var project = hashMap[ROUTING_PROJECT_ID];
        var el = $("#projects [data-id=" + project + "]");
        handleProjectLinkClick(el.get(0), false);
    }
}

function getProjectHrefScheme(id) {
    return "projects/" + id;
}

function bindProjectLinks() {
    $("#projects *[data-id]").off().on("click", (el) => {
        handleProjectLinkClick(el.currentTarget, true);
    });
}

function handleProjectLinkClick(el: Element, cleanSection) {
    if (!el.hasAttribute('data-id')) {
        throw 'This not valid project link!';
    }
    else {
        var self = el;
        var href = "projects/" + $(self).attr("data-id");
        var id = $(self).attr("data-id");

        $("#projects_swap").load(href, null, () => {
            $("#projects .nav-button").removeClass(CSS_ACTIVE_BUTTON);
            $(self).toggleClass(CSS_ACTIVE_BUTTON);

            var hashMap = convertHashToMap(document.location.hash.substr(1));

            hashMap[ROUTING_PROJECT_ID] = id;

            if (cleanSection) {
                delete hashMap[ROUTING_SECTION_ID];
            }
            else {
                document.getElementById(hashMap[ROUTING_SECTION_ID]).scrollIntoView();
            }

            document.location.hash = convertMapToHash(hashMap);
            setProjectColors();
            refreshProjectMenu();


        });
    }


}

function openProject(href: string, id: string, cleanSection: boolean) {

}
function setProjectColors() {
    var leadingColor = $("meta[name=leadingColor]").attr("content");
    var leadingTextColor = $("meta[name=leadingTextColor]").attr("content");

    if (leadingColor != undefined && leadingColor != null) {

        customize("color", leadingColor);
        customize("background", leadingColor);
    }


    if (leadingTextColor != undefined && leadingTextColor != null) {
        $("#links .nav-button").css("color", leadingTextColor);
        $("#links .nav-button *").css("color", leadingTextColor);
    }
}
function convertHashToMap(string: string) {
    var map = new Object();
    var array = string.split("&");
    array.forEach(el => {
        var pair = el.split("=");
        map[pair[0]] = pair[1];
    });
    return map;
}

function convertMapToHash(obj) {
    var keys: string[] = Object.keys(obj);
    var result: string;
    keys.forEach((el) => {
        if (!result) {
            result = "#";
        }
        if ((result.charAt(result.length - 1) != '&') && (result.charAt(result.length - 1) != '#')) {
            result += "&";
        }
        result += el;
        if (obj[el] != null && obj[el] != undefined) {
            result += "=" + obj[el];
        }
    });
    return result;
}
function refreshProjectMenu() {
    $("a[data-appendhash][href]").off().on("click", (el) => {
        el.preventDefault();
        var id = $(el.currentTarget).attr("href").replace("#", "");
        var currentHash = document.location.hash.substr(1);
        var map = convertHashToMap(currentHash);

        map[ROUTING_SECTION_ID] = id;

        document.location.hash = convertMapToHash(map);
        document.getElementById(id).scrollIntoView();
    });
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


