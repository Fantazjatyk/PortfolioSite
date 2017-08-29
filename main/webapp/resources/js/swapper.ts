class PageSwapper {

    private scope: string;
    private afterswap;
    private beforeswap;

    constructor(scope, beforeswap: (el) => any, afterswap: (el) => any) {
        this.scope = scope;
        this.afterswap = afterswap;
        this.beforeswap = beforeswap;
    }


    init() {
        this.bindEvery(this.scope);
    }

    swapAndAppendHash(container, page: string, hash: string, routingLevel) {
        var self = this;

        this.swap(container, page, () => {
            new HashUpdater().updateHash(hash, routingLevel);
        });

    }


    swap(container, page: string, afterload) {
        $(container).load(page, () => {
            afterload();
        });
    }


    private bindEvery(selector) {
        var me = this;
        $(selector).off().on("click", (el) => {
            var self = el.currentTarget;
            me.beforeswap(self);

            var hash = $(self).attr("data-hash");
            var routingLevel = $(self).attr("data-routing-level");
            var appendHash = hash !== null && hash !== undefined;
            var href = $(self).attr("data-href");
            var swapContainerId = "#" + $(self).attr("data-swapId");

            if (appendHash) {
                me.swapAndAppendHash(swapContainerId, href, hash, routingLevel);
            }
            else {
                me.swap(swapContainerId, href, null);
            }

            me.afterswap(self);
        });
    }
}

class SwapTriggerInvoker{

}

class RouteReproducer {


    reproduce(hash: string, swapper:PageSwapper) {
        hash = hash.replace("#", "");
        var hashes = hash.split(",");
        var self = this;
        if (hashes.length > 1) {
                var el = hashes.shift();
                this.invokeElement(el, swapper, ()=>{
                    self.reproduce("#" + hashes.toString(), swapper);
                });
        }
        else if (hash !== null && hash !== undefined) {
            this.invokeElement(hash, swapper, null);
        }
    }

        invokeElement(hash, swapper:PageSwapper, afterInvoke:()=>any) {
        var element = $("* [data-hash=" + hash + "]");
        var swapId = "#" + $(element).attr("data-swapId");
        var href = $(element).attr("data-href");
        swapper.swap(swapId, href, afterInvoke);
    }


}


class HashUpdater {
    updateHash(hash: string, routingLevel) {
        var currentHash = document.location.hash;
        var currentHashes = currentHash.split(",");

        if (routingLevel == 1 || routingLevel == undefined || routingLevel == "null") {
            document.location.hash = hash.toString();
        }
        else if (currentHashes.length > 0) {
            var newHash = document.location.hash.split(",");

            if (currentHashes.length == routingLevel) {
                newHash.pop();
            }
            else if (currentHashes.length != (routingLevel - 1)) {
                return;
            }
            hash = hash.replace("#", "");
            newHash.push(hash);
            document.location.hash = newHash.toString();
        }

    }
}





