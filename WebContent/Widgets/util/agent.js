/**
 * 浏览器判断
 * @author    yanggy
 * @version    0.1
 */

define("require".split(" "), function () {
    return {
        info:function () {
            var engine = {
                ie:0,
                gecko:0,
                webkit:0,
                ktml:0,
                opera:0,
                ver:null
            };
            var browser = {
                ie:0,
                firefox:0,
                safari:0,
                konq:0,
                opera:0,
                chrome:0,
                ver:null
            };
            var ua = window.navigator.userAgent;

            if (window.opera) {
                engine.ver = browser.ver = window.opera.version();
                engine.opera = browser.opera = parseFloat(engine.ver);
            } else if (/AppleWebKit\/(\S+)/.test(ua)) {
                engine.ver = RegExp.$1;
                engine.webkit = parseFloat(engine.ver);

                if (/Chrome\/(\S+)/.test(ua)) {
                    browser.ver = RegExp.$1;
                    browser.chrome = parseFloat(browser.ver);
                } else if (/Version\/(\S+)/.test(ua)) {
                    browser.ver = RegExp.$1;
                    browser.safari = parseFloat(browser.ver);
                } else {
                    var safariVer = 1;

                    if (engine.webkit < 100) {
                        safariVer = 1;
                    } else if (engine.webkit < 312) {
                        safariVer = 1.2;
                    } else if (engine.webkit < 412) {
                        safariVer = 1.3;
                    } else {
                        safariVer = 2;
                    }
                    browser.ver = browser.safari = safariVer;
                }
            } else if (/KHTML\/(\S+)/.test(ua) || /Konqueror\/([^;]+)/.test(ua)) {
                engine.ver = browser.ver = RegExp.$1;
                engine.khtml = browser.konq = parseFloat(engine.ver);
            } else if (/rv:[^\)]+\) Gecko\/\d{8}/.test(ua)) {
                engine.ver = RegExp.$1;
                engine.gecko = parseFloat(engine.ver);

                if (/Firefox\/(\S+)/.test(ua)) {
                    browser.ver = RegExp.$1;
                    browser.firefox = parseFloat(browser.ver);
                }
            } else if (/MSIE ([^;]+)/.test(ua)) {
                engine.ver = browser.ver = RegExp.$1;
                engine.ie = browser.ie = parseFloat(engine.ver);
            }
            return {
                engine:engine,
                browser:browser
            };
        }
    }
});