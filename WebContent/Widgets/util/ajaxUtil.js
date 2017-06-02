/**
 * Ajax请求
 * @author    yanggy
 * @version    0.1
 */

define(
    [
        "dojo/_base/declare",
        "dojo/_base/lang",
        "esri/request",
        "dojo/request",
        "./infoUtil"
    ],
    function (declare, lang, esriRequest, request, infoUtil) {

        return {

            /**
             * HTTP Post请求
             * @param url
             * @param params
             * @param callback
             * @param failure
             * @param progress
             */
            request:function (url, params, callback, failure, progress) {
                var req = new XMLHttpRequest(),
                    that = this;

                req.open("POST", url, true);
                req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                req.send(params);

                req.onload = function (e) {
                    if (typeof callback === "function") {
                        var response = e.target.responseText,
                            json = null;

                        if (response && response !== "null") {
                            try {
                                json = JSON.parse(response);
                            } catch (ex) {
                                infoUtil.hideLoading();
                                //infoUtil.showAlert("返回数据格式错误 :-(");
                                console.error(ex);
                                return;
                            }

                            if (json) {
                                if (json.redirect) {
                                    infoUtil.showAlert("因系统长时间未访问服务器，请您重新登录 :-)", null, that._redirectLogin);
                                } else if (json.ErrorCode) {
                                    infoUtil.hideLoading();
                                    infoUtil.showAlert(that._getGateWayErrorMsg(json.ErrorCode));
                                    console.log(json.status);
                                } else {
                                    callback(json);
                                }
                            } else {
                                infoUtil.hideLoading();
                                infoUtil.showAlert("系统错误 :-(");
                            }
                        }
                    }
                };
                req.onerror = function (e) {
                    if (typeof failure === "function") {
                        failure(e);
                    }
                };
                req.onprogress = function (e) {
                    if (typeof progress === "function" && e.lengthComputable) {
                        progress(e);
                    }
                };
                return req;
            },

            /**
             * 通过esri/request发送请求
             * @param url
             * @param params
             * @param proxy
             * @param post
             */
            esriReq:function (url, params, proxy, post) {
                proxy = (proxy === true) ? true : false;
                post = (post === false) ? false : true;

                var req = esriRequest({
                    url:url,
                    content:params,
                    handleAs:"json",
                    timeout:300000
                }, {
                    useProxy:proxy,
                    usePost:post
                });
                var def = req.then(lang.hitch(this, function (res) {
                    if (res.redirect)
                        infoUtil.showAlert("系统超时，请您重新登录 :-)", null, function () {
                            window.location.href = "/";
                        });
                    return res;
                }));
                return def;
            },

            /**
             * 同步请求
             * @param url
             * @param params
             * @return {*}
             */
            syncReq:function (url, params) {
                var req = request.post(url, {
                    data:params,
                    sync:true,
                    handleAs:"json"
                });
                return req;
            }
        };
    });